/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.DistributorBeanLocal;
import com.diageo.diageonegocio.entidades.Distribuidor;
import java.io.Serializable;
import java.util.List;
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

    /**
     * Creates a new instance of DistributorSearch
     */
    public DistributorSearch() {
    }

    @PostConstruct
    public void init() {

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

}
