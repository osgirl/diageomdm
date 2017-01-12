/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.Db3PartyAdminBeanLocal;
import com.diageo.diageonegocio.entidades.Db3partyAdmin;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "adminDistributorCreateBean")
@ViewScoped
public class AdminDistributorCreateBean extends DiageoRootBean {

    private static final Logger LOG = Logger.getLogger(AdminDistributorCreateBean.class.getName());
    @EJB
    protected Db3PartyAdminBeanLocal db3PartyAdminBeanLocal;
    private String nameAdmin;
    private String athenaCode;

    @PostConstruct
    public void init() {
        setNameAdmin("");
        setAthenaCode("");
    }

    public void saveAdmin() {
        try {
            Db3partyAdmin admin = new Db3partyAdmin();
            admin.setAdminName(getNameAdmin().toUpperCase());
            admin.setDistri1(getAthenaCode().toUpperCase());
            db3PartyAdminBeanLocal.createAdmin(admin);
            init();
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
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

}
