/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.enums.FatherDistributorEnum;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
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
        Db3party distri = new Db3party();
        distri.setDb3partyIdFather(getSelectedDistributor());
        distri.setIsFather(FatherDistributorEnum.NOT_FATHER.getIsPadre());
        distri.setName3party(getName().toUpperCase());
        try {
            distributorBeanLocal.createDistributor(distri);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(DistributorLocationCreateBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }


}
