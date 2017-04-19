/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.enums.StateEnum;
import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageonegocio.entidades.Audit;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.Db3partyRegional;
import com.diageo.diageonegocio.enums.FatherDistributorEnum;
import com.diageo.diageonegocio.enums.StateDiageo;
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
        setIsChain(StateDiageo.ACTIVO.getId());
        setStatus(StateDiageo.ACTIVO.getId());
    }

    private void cleanUpFields() {
        setDb3partyRegionalSelected(new Db3partyRegional());
        setName("");
        setNameAdmin("");
        setAthenaCode("");
        setIsFather(Boolean.FALSE);
        setAdminSelected(null);
        setBusinessName("");
        setCodeEanChain("");
    }

    public void create() {
        try {
            getSelectedDistributor().setName3party(getName().toUpperCase());
            getSelectedDistributor().setDistri1(getAthenaCode().toUpperCase());
            getSelectedDistributor().setDb3partyRegionalId(getDb3partyRegionalSelected());
            getSelectedDistributor().setIsFather(isFather() ? FatherDistributorEnum.FATHER.getIsFather() : FatherDistributorEnum.NOT_FATHER.getIsFather());
            getSelectedDistributor().setIsChain(getIsChain());
            getSelectedDistributor().setStatus(getStatus());
            getSelectedDistributor().setDb3PartyAdmin(getAdminSelected());
            getSelectedDistributor().setCodeEanCadena(getCodeEanChain());
            getSelectedDistributor().setBusinessName(getBusinessName());
            Audit audit = new Audit();
            audit.setCreationDate(super.getCurrentDate());
            audit.setCreationUser(getLoginBean().getUsuario().getEmailUser());
            getSelectedDistributor().setAudit(audit);
            if (!isFather()) {
                getSelectedDistributor().setDb3partyIdFather(getPartyFatherSelected());
            }
            Db3party createDistributor = distributorBeanLocal.createDistributor(getSelectedDistributor());
            if (isFather() || getIsChain().equals(StateEnum.ACTIVE.getState())) {
                createDistributor.setDb3partyIdFather(createDistributor);
                createDistributor.setIsFather(FatherDistributorEnum.FATHER.getIsFather());
                distributorBeanLocal.updateDistributor(getSelectedDistributor());
            }
            setSelectedDistributor(new Db3party());
            cleanUpFields();
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(DistributorSearch.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }

    }

}
