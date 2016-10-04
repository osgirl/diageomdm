/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageonegocio.entidades.Audit;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "regionalCreateBean")
@ViewScoped
public class RegionalCreateBean extends RegionalSearchBean {

    private static final String ATHENA_CODE = "GER";

    /**
     * Creates a new instance of RegionalCreateBean
     */
    public RegionalCreateBean() {
    }

    private void cleanUpFields() {
        setRegionalName("");
        setAthenaCode("");
    }

    @Override
    public void updateRegional() {
        try {
            getRegionalSelected().setNameRegional(getRegionalName().toUpperCase());
            Audit audit = new Audit();
            audit.setCreationDate(super.getCurrentDate());
            audit.setCreationUser(getLoginBean().getUsuario().getEmailUser());
            getRegionalSelected().setAudit(audit);
            regionalBeanLocal.createRegional(getRegionalSelected());
            if (getRegionalSelected().getDb3partyRegionalId() != null) {
                if (getRegionalSelected().getDb3partyRegionalId() < 10) {
                    getRegionalSelected().setDistri_1(ATHENA_CODE + "0" + getRegionalSelected().getDb3partyRegionalId());
                } else {
                    getRegionalSelected().setDistri_1(ATHENA_CODE + getRegionalSelected().getDb3partyRegionalId());
                }
                regionalBeanLocal.updateRegional(getRegionalSelected());
            }
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
            cleanUpFields();
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(RegionalCreateBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

}
