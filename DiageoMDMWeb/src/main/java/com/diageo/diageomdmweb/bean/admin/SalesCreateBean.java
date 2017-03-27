/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.Db3PartyBeanLocal;
import com.diageo.diageonegocio.beans.Db3PartyManagerBeanLocal;
import com.diageo.diageonegocio.beans.Db3PartyProfilesBeanLocal;
import com.diageo.diageonegocio.beans.DbPartySalesBeanLocal;
import com.diageo.diageonegocio.beans.TerritoryBeanLocal;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.Db3partyManagers;
import com.diageo.diageonegocio.entidades.Db3partyProfiles;
import com.diageo.diageonegocio.entidades.Db3partySales;
import com.diageo.diageonegocio.entidades.Db3partyTerritory;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "salesCreateBean")
@ViewScoped
public class SalesCreateBean extends DiageoRootBean {

    @EJB
    protected DbPartySalesBeanLocal dbPartySalesBeanLocal;
    @EJB
    protected Db3PartyProfilesBeanLocal db3PartyProfilesBeanLocal;
    @EJB
    protected Db3PartyManagerBeanLocal db3PartyManagerBeanLocal;
    @EJB
    protected Db3PartyBeanLocal db3PartyBeanLocal;
    @EJB
    protected TerritoryBeanLocal territoryBeanLocal;
    private List<Db3partyManagers> managerList;
    private List<Db3partyProfiles> profilesList;
    private List<Db3party> db3PartyList;
    private List<Db3partyTerritory> db3PartyTerritoryList;
    private Db3partyManagers managerSelected;
    private Db3partyProfiles profilesSelected;
    private Db3party db3PartySelected;
    private Db3partyTerritory territorySelected;
    private String salesName;
    private String distributor_2;
    private boolean focalizado;
    private boolean renderButtonBack;

    @PostConstruct
    public void init() {
        initList();
        initObjects();
    }

    protected void initObjects() {
        setManagerSelected(new Db3partyManagers());
        setProfilesSelected(new Db3partyProfiles());
        setDb3PartySelected(new Db3party());
        setSalesName("");
        setDistributor_2("");
        setFocalizado(Boolean.FALSE);
        setTerritorySelected(new Db3partyTerritory());
    }

    protected void initList() {
        setManagerList(db3PartyManagerBeanLocal.searchAllManagers());
        setProfilesList(db3PartyProfilesBeanLocal.searchAll3PartyProfiles());
        setDb3PartyList(db3PartyBeanLocal.searchAll());
        setDb3PartyTerritoryList(territoryBeanLocal.findAll());
    }

    public void saveSales() {
        try {
            Db3partySales entity = new Db3partySales();
            entity.setNameSales(getSalesName().toUpperCase());
            entity.setDb3PartyProfileId(getProfilesSelected());
            entity.setDb3partyManagerId(getManagerSelected());
            entity.setDbeParty(getDb3PartySelected().getDb3partyId());            
            entity.setFocalizado(isFocalizado() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());            
            dbPartySalesBeanLocal.createSales(entity);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
            initObjects();
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(SalesCreateBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public List<Db3partyManagers> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<Db3partyManagers> managerList) {
        this.managerList = managerList;
    }

    public List<Db3partyProfiles> getProfilesList() {
        return profilesList;
    }

    public void setProfilesList(List<Db3partyProfiles> profilesList) {
        this.profilesList = profilesList;
    }

    public List<Db3party> getDb3PartyList() {
        return db3PartyList;
    }

    public void setDb3PartyList(List<Db3party> db3PartyList) {
        this.db3PartyList = db3PartyList;
    }

    public Db3partyManagers getManagerSelected() {
        return managerSelected;
    }

    public void setManagerSelected(Db3partyManagers managerSelected) {
        this.managerSelected = managerSelected;
    }

    public Db3partyProfiles getProfilesSelected() {
        return profilesSelected;
    }

    public void setProfilesSelected(Db3partyProfiles profilesSelected) {
        this.profilesSelected = profilesSelected;
    }

    public Db3party getDb3PartySelected() {
        return db3PartySelected;
    }

    public void setDb3PartySelected(Db3party db3PartySelected) {
        this.db3PartySelected = db3PartySelected;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public boolean isFocalizado() {
        return focalizado;
    }

    public void setFocalizado(boolean focalizado) {
        this.focalizado = focalizado;
    }

    public String getDistributor_2() {
        return distributor_2;
    }

    public void setDistributor_2(String distributor_2) {
        this.distributor_2 = distributor_2;
    }

    public boolean isRenderButtonBack() {
        return renderButtonBack;
    }

    public void setRenderButtonBack(boolean renderButtonBack) {
        this.renderButtonBack = renderButtonBack;
    }

    public List<Db3partyTerritory> getDb3PartyTerritoryList() {
        return db3PartyTerritoryList;
    }

    public void setDb3PartyTerritoryList(List<Db3partyTerritory> db3PartyTerritoryList) {
        this.db3PartyTerritoryList = db3PartyTerritoryList;
    }

    public Db3partyTerritory getTerritorySelected() {
        return territorySelected;
    }

    public void setTerritorySelected(Db3partyTerritory territorySelected) {
        this.territorySelected = territorySelected;
    }

}
