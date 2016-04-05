/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.DistributorBeanLocal;
import com.diageo.diageonegocio.entidades.Distribuidor;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
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
@Named(value = "distributorSearch")
@ViewScoped
public class DistributorSearch extends DiageoRootBean implements Serializable {

    @EJB
    protected DistributorBeanLocal distributorBeanLocal;
    @Size(max = 50, message = "{size.invalido}")
    private String name;
    private List<Distribuidor> listDistributor;
    private Distribuidor selectedDistributor;
    private boolean seeDetail;

    /**
     * Creates a new instance of DistributorSearch
     */
    public DistributorSearch() {
    }

    @PostConstruct
    public void init() {
        setListDistributor(distributorBeanLocal.searchAllDistributor());
        setSeeDetail(Boolean.TRUE);
        setSelectedDistributor(new Distribuidor());
    }

    public void detail(Distribuidor distri) {
        setSelectedDistributor(distri);
        setName(distri.getNombre());
        setSeeDetail(Boolean.FALSE);
    }

    public void update() {
        try {
            getSelectedDistributor().setNombre(getName());
            distributorBeanLocal.updateDistributor(getSelectedDistributor());
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoNegocioException ex) {
            Logger.getLogger(DistributorSearch.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Distribuidor> getListDistributor() {
        return listDistributor;
    }

    /**
     * @param listDistributor the listDistributor to set
     */
    public void setListDistributor(List<Distribuidor> listDistributor) {
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
    public Distribuidor getSelectedDistributor() {
        return selectedDistributor;
    }

    /**
     * @param selectedDistributor the selectedDistributor to set
     */
    public void setSelectedDistributor(Distribuidor selectedDistributor) {
        this.selectedDistributor = selectedDistributor;
    }

}
