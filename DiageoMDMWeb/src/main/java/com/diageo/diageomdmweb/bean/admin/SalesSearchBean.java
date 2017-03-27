/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.enums.StateEnum;
import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.Db3partySales;
import com.diageo.diageonegocio.entidades.Db3partyTerritory;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author EDUARDO
 */
@Named(value = "salesSearchBean")
@ViewScoped
public class SalesSearchBean extends SalesCreateBean {

    private boolean renderTable;
    private List<Db3partySales> listSales;
    private Db3partySales salesSelected;

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
    }

    public void btnBack() {
        init();
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('dtSales').clearFilters()");
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
            dbPartySalesBeanLocal.updateSales(entity);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(SalesCreateBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
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

}
