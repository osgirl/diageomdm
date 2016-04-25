/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageonegocio.entidades.Departamento;
import com.diageo.diageonegocio.entidades.Distribuidor;
import com.diageo.diageonegocio.entidades.Municipio;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author yovanoty126
 */
@Named(value = "distributorLocationCreateBean")
@ViewScoped
public class DistributorLocationCreateBean extends DistributorCreate {

    /**
     * Creates a new instance of DistributorLocationBean
     */
    public DistributorLocationCreateBean() {
    }

    @PostConstruct
    @Override
    public void init() {
        super.init();
    }

    public void createLocatorDistributor() {
        Distribuidor distri = new Distribuidor();
        distri.setPadreIdDistribuidor(getSelectedDistributor());
        distri.setIsPadre("0");
        distri.setNombre(getName().toUpperCase());
        try {
            distributorBeanLocal.createDistributor(distri);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoNegocioException ex) {
            Logger.getLogger(DistributorLocationCreateBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }


}
