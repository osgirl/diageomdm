/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.enums.StateEnum;
import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageomdmweb.enums.TableOutletFields;
import com.diageo.diageomdmweb.enums.TablesEnum;
import com.diageo.diageomdmweb.enums.WaitingEnum;
import com.diageo.diageonegocio.beans.LogTerritoryBean;
import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.Db3partySales;
import com.diageo.diageonegocio.entidades.Db3partyTerritory;
import com.diageo.diageonegocio.entidades.DbLogTerritory;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author EDUARDO
 */
@Named(value = "salesSearchBean")
@ViewScoped
public class SalesSearchBean extends SalesCreateBean {

    @EJB
    private OutletBeanLocal outletBeanLocal;
    @EJB
    private LogTerritoryBean logTerritoryBean;
    @Inject
    private LoginBean loginBean;
    private boolean renderTable;
    private List<Db3partySales> listSales;
    private Db3partySales salesSelected;
    private Db3partyTerritory territoryTemp;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        setDb3PartyTerritoryList(territoryBeanLocal.findAll());
        setRenderTable(Boolean.TRUE);
        setListSales(dbPartySalesBeanLocal.searchAll());
        setRenderButtonBack(Boolean.TRUE);
    }

    public void seeDetail(Db3partySales sales) {
        super.initObjects();
        setSalesName(sales.getNameSales());
        setFocalizado(sales.getFocalizado() != null ? sales.getFocalizado().equals(StateEnum.ACTIVE.getState()) : false);
        setManagerSelected(sales.getDb3partyManagerId());
        setProfilesSelected(sales.getDb3PartyProfileId());
        setDb3PartySelected(new Db3party(sales.getDbeParty() != null ? sales.getDbeParty() : 0));
        setSalesSelected(sales);
        setRenderTable(Boolean.FALSE);
        setTerritorySelected(sales.getDb3partyTerritory());
        territoryTemp = sales.getDb3partyTerritory();
    }

    public String btnBack() {
        init();
//        RequestContext rc = RequestContext.getCurrentInstance();
//        rc.execute("PF('dtSales').clearFilters()");
        return "/admin/sales/salesSearch?faces-redirect=true";
    }

    @Override
    public void saveSales() {
        try {
            Db3partySales entity = getSalesSelected();
            entity.setNameSales(getSalesName().toUpperCase());
            entity.setDb3PartyProfileId(getProfilesSelected());
            entity.setDb3partyManagerId(getManagerSelected());
            entity.setDbeParty(getDb3PartySelected() != null ? getDb3PartySelected().getDb3partyId() : 0);
            entity.setFocalizado(isFocalizado() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
            entity.setDb3partyTerritory(getTerritorySelected());
            dbPartySalesBeanLocal.updateSales(entity);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
            changedTerritory();
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(SalesCreateBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    /**
     * verifica si el territorio cambia, si cambia, entonces notifica con los
     * estados waiting por cada outlet que tenga asignado ese vendedor
     */
    private void changedTerritory() {
        if (getTerritorySelected() != null && territoryTemp != null) {
            if (!getTerritorySelected().getNameTerritory().equalsIgnoreCase(territoryTemp.getNameTerritory())) {
                //buscar todos los outlets con el vendedor asociado
                List<DbOutlets> list = outletBeanLocal.findBySeller(getSalesSelected().getDb3partySaleId());
                if (!list.isEmpty()) {
                    for (DbOutlets out : list) {
                        DbLogTerritory logTerritory = new DbLogTerritory();
                        logTerritory.setCreationDate(getCurrentDate());
                        logTerritory.setCreationUser(getLoginBean().getUsuario().getEmailUser());
                        logTerritory.setDbOutletId(out.getOutletId());
                        logTerritory.setFieldLog(TableOutletFields.TERRITORY.name());
                        logTerritory.setOutletType(TablesEnum.DB_OUTLETS.name());
                        logTerritory.setWaitingStatus(WaitingEnum.REMOVAL.getState());
                        logTerritory.setValueLog(territoryTemp.getNameTerritory());
                        logTerritoryBean.create(logTerritory);
                        logTerritory.setWaitingStatus(WaitingEnum.ACTIVATION.getState());
                        logTerritory.setValueLog(getTerritorySelected().getNameTerritory());
                        logTerritoryBean.create(logTerritory);
                    }
                }
            }
        }
    }

    public String seeLabel3Party(Integer id) {
        if (id == null) {
            return "";
        }
        try {
            return db3PartyBeanLocal.searchId(id).getName3party();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isRenderTable() {
        return renderTable;
    }

    public void setRenderTable(boolean renderTable) {
        this.renderTable = renderTable;
    }

    public List<Db3partySales> getListSales() {
        return listSales;
    }

    public void setListSales(List<Db3partySales> listSales) {
        this.listSales = listSales;
    }

    public Db3partySales getSalesSelected() {
        return salesSelected;
    }

    public void setSalesSelected(Db3partySales salesSelected) {
        this.salesSelected = salesSelected;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

}
