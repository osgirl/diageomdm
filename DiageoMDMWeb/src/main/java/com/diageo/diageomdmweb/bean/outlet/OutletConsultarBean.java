/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.outlet;

import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.entidades.Outlet;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author yovanoty126
 */
@Named(value = "outletConsultarBean")
@ViewScoped
public class OutletConsultarBean extends OutletCrearBean implements Serializable{

    @EJB
    private OutletBeanLocal outletBeanLocal;
    private List<Outlet> listaOutlets;
    /**
     * Creates a new instance of OutletConsultarBean
     */
    public OutletConsultarBean() {
    }
    
    @PostConstruct
    @Override
    public void init(){ 
        setListaOutlets(outletBeanLocal.consultarTodosOutlets());
        super.init();
    }

    public List<Outlet> getListaOutlets() {
        return listaOutlets;
    }

    public void setListaOutlets(List<Outlet> listaOutlets) {
        this.listaOutlets = listaOutlets;
    }
    
}
