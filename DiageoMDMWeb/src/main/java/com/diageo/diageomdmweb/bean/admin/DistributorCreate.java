/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageonegocio.entidades.Audit;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.Db3partyRegional;
import com.diageo.diageonegocio.enums.FatherDistributorEnum;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "distributorCreate")
@ViewScoped
public class DistributorCreate extends DistributorSearch implements Serializable {

    /**
     * Creates a new instance of DistributorCreate
     */
    public DistributorCreate() {
    }

    private void cleanUpFields() {
        setDb3partyRegionalSelected(new Db3partyRegional());
        setName("");
        setNameAdmin("");
        setAthenaCode("");
        setIsFather(Boolean.FALSE);
    }

    public void create() {
        try {
            getSelectedDistributor().setName3party(getName().toUpperCase());
            getSelectedDistributor().setDistri1(getAthenaCode().toUpperCase());
            getSelectedDistributor().setDb3partyRegionalId(getDb3partyRegionalSelected());
            getSelectedDistributor().setIsFather(isIsFather() ? FatherDistributorEnum.FATHER.getIsPadre() : FatherDistributorEnum.NOT_FATHER.getIsPadre());
            Audit audit = new Audit();
            audit.setCreationDate(super.getCurrentDate());
            audit.setCreationUser(getLoginBean().getUsuario().getEmailUser());
            getSelectedDistributor().setAudit(audit);
            if (isFather()) {
                getSelectedDistributor().setDb3partyIdFather(getPartyFatherSelected());
            }
            distributorBeanLocal.createDistributor(getSelectedDistributor());
            setSelectedDistributor(new Db3party());
            cleanUpFields();
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(DistributorSearch.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }

    }

}
