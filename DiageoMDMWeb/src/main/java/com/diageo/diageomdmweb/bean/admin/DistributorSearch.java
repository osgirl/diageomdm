/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageonegocio.beans.Db3PartyBeanLocal;
import com.diageo.diageonegocio.beans.RegionalBeanLocal;
import com.diageo.diageonegocio.entidades.Audit;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.Db3partyRegional;
import com.diageo.diageonegocio.enums.FatherDistributorEnum;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.Size;
import org.primefaces.context.RequestContext;

/**
 *
 * @author EDUARDO
 */
@Named(value = "distributorSearch")
@ViewScoped
public class DistributorSearch extends DiageoRootBean implements Serializable {

    @Inject
    protected LoginBean loginBean;
    @EJB
    protected Db3PartyBeanLocal distributorBeanLocal;
    @EJB
    protected RegionalBeanLocal regionalBeanLocal;
    @Size(max = 50, message = "{size.invalido}")
    private String name;
    private String nameAdmin;
    private String athenaCode;
    private Db3partyRegional db3partyRegionalSelected;
    private Db3party partyFatherSelected;
    private List<Db3party> listDistributor;
    private List<Db3party> listDistributorFiltered;
    private List<Db3party> listDistributorFather;
    private List<Db3partyRegional> listRegional;
    private Db3party selectedDistributor;
    private boolean seeDetail;
    private boolean isFather;
    private boolean father;
    private String isChain;
    private String status;

    /**
     * Creates a new instance of DistributorSearch
     */
    public DistributorSearch() {
    }

    @PostConstruct
    public void init() {
        setListDistributor(distributorBeanLocal.searchAll());
        setListDistributorFiltered(getListDistributor());
        setListDistributorFather(distributorBeanLocal.searchDistributorFatherIsChain(FatherDistributorEnum.FATHER.getIsFather(),"1"));        
        setSeeDetail(Boolean.TRUE);
        setSelectedDistributor(new Db3party());
        setListRegional(regionalBeanLocal.findAll());
    }

    public void detail(Db3party distri) {
        setSelectedDistributor(distri);
        setName(distri.getName3party());
        setNameAdmin(distri.getDb3PartyAdmin() != null ? distri.getDb3PartyAdmin().getAdminName() : "");
        setFather(distri.getIsFather().equals(FatherDistributorEnum.FATHER.getIsFather()));
        //setFather(distri.getDb3partyIdFather() != null);
        setDb3partyRegionalSelected(distri.getDb3partyRegionalId());
        setIsChain(distri.getIsChain());
        setStatus(distri.getStatus());
        setListDistributorFather(distributorBeanLocal.searchDistributorFatherIsChain(FatherDistributorEnum.FATHER.getIsFather(),distri.getIsChain()));        
        if (isFather()) {
            setPartyFatherSelected(distri.getDb3partyIdFather());
        }
        setAthenaCode(distri.getDistri1());
        if (father) {
            setPartyFatherSelected(distri.getDb3partyIdFather());
        }
        setSeeDetail(Boolean.FALSE);
    }

    public void update() {
        try {
            getSelectedDistributor().setName3party(getName().toUpperCase());
            getSelectedDistributor().setDistri1(getAthenaCode().toUpperCase());
            getSelectedDistributor().setDb3partyRegionalId(db3partyRegionalSelected);
            getSelectedDistributor().setIsFather(isFather ? FatherDistributorEnum.FATHER.getIsFather() : FatherDistributorEnum.NOT_FATHER.getIsFather());
            getSelectedDistributor().setIsChain(getIsChain());
            getSelectedDistributor().setStatus(getStatus());
            Audit audit = new Audit();
            audit.setModificationDate(super.getCurrentDate());
            audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
            audit.setCreationUser(getSelectedDistributor().getAudit() != null ? getSelectedDistributor().getAudit().getCreationUser() : null);
            audit.setCreationDate(getSelectedDistributor().getAudit() != null ? getSelectedDistributor().getAudit().getCreationDate() : null);
            getSelectedDistributor().setAudit(audit);
            if (father) {
                getSelectedDistributor().setDb3partyIdFather(partyFatherSelected);
            }
            distributorBeanLocal.updateDistributor(getSelectedDistributor());
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(DistributorSearch.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void back() {
        setSeeDetail(Boolean.TRUE);
        setName("");
        setSelectedDistributor(null);
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('dtDistri').clearFilters()");
    }

    public void listenerListDistributor() {
        setListDistributorFather(distributorBeanLocal.searchDistributorFatherIsChain(FatherDistributorEnum.FATHER.getIsFather(), getIsChain()));
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the listDistributor
     */
    public List<Db3party> getListDistributor() {
        return listDistributor;
    }

    /**
     * @param listDistributor the listDistributor to set
     */
    public void setListDistributor(List<Db3party> listDistributor) {
        this.listDistributor = listDistributor;
    }

    /**
     * @return the seeDetail
     */
    public boolean isSeeDetail() {
        return seeDetail;
    }

    /**
     * @param seeDetail the seeDetail to set
     */
    public void setSeeDetail(boolean seeDetail) {
        this.seeDetail = seeDetail;
    }

    /**
     * @return the selectedDistributor
     */
    public Db3party getSelectedDistributor() {
        return selectedDistributor;
    }

    /**
     * @param selectedDistributor the selectedDistributor to set
     */
    public void setSelectedDistributor(Db3party selectedDistributor) {
        this.selectedDistributor = selectedDistributor;
    }

    public String getNameAdmin() {
        return nameAdmin;
    }

    public void setNameAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

    public String getAthenaCode() {
        return athenaCode;
    }

    public void setAthenaCode(String athenaCode) {
        this.athenaCode = athenaCode;
    }

    public Db3partyRegional getDb3partyRegionalSelected() {
        return db3partyRegionalSelected;
    }

    public void setDb3partyRegionalSelected(Db3partyRegional db3partyRegionalSelected) {
        this.db3partyRegionalSelected = db3partyRegionalSelected;
    }

    public Db3party getPartyFatherSelected() {
        return partyFatherSelected;
    }

    public void setPartyFatherSelected(Db3party partyFatherSelected) {
        this.partyFatherSelected = partyFatherSelected;
    }

    public List<Db3party> getListDistributorFather() {
        return listDistributorFather;
    }

    public void setListDistributorFather(List<Db3party> listDistributorFather) {
        this.listDistributorFather = listDistributorFather;
    }

    public boolean isIsFather() {
        return isFather;
    }

    public void setIsFather(boolean isFather) {
        this.isFather = isFather;
    }

    public List<Db3partyRegional> getListRegional() {
        return listRegional;
    }

    public void setListRegional(List<Db3partyRegional> listRegional) {
        this.listRegional = listRegional;
    }

    public boolean isFather() {
        return father;
    }

    public void setFather(boolean father) {
        this.father = father;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public String getIsChain() {
        return isChain;
    }

    public void setIsChain(String isChain) {
        this.isChain = isChain;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Db3party> getListDistributorFiltered() {
        return listDistributorFiltered;
    }

    public void setListDistributorFiltered(List<Db3party> listDistributorFiltered) {
        this.listDistributorFiltered = listDistributorFiltered;
    }

}
