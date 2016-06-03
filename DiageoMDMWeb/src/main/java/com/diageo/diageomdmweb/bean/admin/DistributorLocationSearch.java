/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.Db3PartyBeanLocal;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.enums.FatherDistributorEnum;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.validation.constraints.Size;

/**
 *
 * @author EDUARDO
 */
@Named(value = "distributorLocationSearch")
@ViewScoped
public class DistributorLocationSearch extends DiageoRootBean implements Serializable {

    @EJB
    protected Db3PartyBeanLocal distributorBeanLocal;
    @Size(max = 50, message = "{size.invalido}")
    private String name;
    private List<Db3party> listDistributor;
    private List<Db3party> listDistributorFather;
    private Db3party selectedDistributor;
    private Db3party selectedDistributorFather;
    private boolean seeDetail;

    /**
     * Creates a new instance of DistributorSearch
     */
    public DistributorLocationSearch() {
    }

    @PostConstruct
    public void init() {
        setListDistributor(distributorBeanLocal.searchDistributorFather(FatherDistributorEnum.NOT_FATHER.getIsPadre()));
        setListDistributorFather(distributorBeanLocal.searchDistributorFather(FatherDistributorEnum.FATHER.getIsPadre()));
        setSeeDetail(Boolean.TRUE);
        setSelectedDistributor(new Db3party());
    }

    public void detail(Db3party distri) {
        setSelectedDistributor(distri);
        setSelectedDistributorFather(distri.getDb3partyIdFather());
        setName(distri.getName3party());
        setSeeDetail(Boolean.FALSE);
    }

    public void update() {
        try {
            getSelectedDistributor().setName3party(getName());
            distributorBeanLocal.updateDistributor(getSelectedDistributor());
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(DistributorLocationSearch.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void back() {
        setSeeDetail(Boolean.TRUE);
        setName("");
        setSelectedDistributor(null);
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

    /**
     * @return the listDistributorFather
     */
    public List<Db3party> getListDistributorFather() {
        return listDistributorFather;
    }

    /**
     * @param listDistributorFather the listDistributorFather to set
     */
    public void setListDistributorFather(List<Db3party> listDistributorFather) {
        this.listDistributorFather = listDistributorFather;
    }

    /**
     * @return the selectedDistributorFather
     */
    public Db3party getSelectedDistributorFather() {
        return selectedDistributorFather;
    }

    /**
     * @param selectedDistributorFather the selectedDistributorFather to set
     */
    public void setSelectedDistributorFather(Db3party selectedDistributorFather) {
        this.selectedDistributorFather = selectedDistributorFather;
    }

}
