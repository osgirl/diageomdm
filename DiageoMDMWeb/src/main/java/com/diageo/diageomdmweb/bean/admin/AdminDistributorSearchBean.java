/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageonegocio.entidades.Db3partyAdmin;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "adminDistributorSearchBean")
@ViewScoped
public class AdminDistributorSearchBean extends AdminDistributorCreateBean {

    private static final Logger LOG = Logger.getLogger(AdminDistributorSearchBean.class.getName());
    private List<Db3partyAdmin> listAdmin;
    private Db3partyAdmin adminSelected;
    private boolean detail;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        setListAdmin(db3PartyAdminBeanLocal.findAll());
    }

    
    public void seeDetail(Db3partyAdmin admin) {
        setNameAdmin(admin.getAdminName());
        setAthenaCode(admin.getDistri1());
        setAdminSelected(admin);
        setDetail(Boolean.TRUE);
    }

    @Override
    public void saveAdmin() {
        try {
            Db3partyAdmin admin = getAdminSelected();
            admin.setAdminName(getNameAdmin().toUpperCase());
            admin.setDistri1(getAthenaCode().toUpperCase());
            db3PartyAdminBeanLocal.updateAdmin(admin);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void back() {
        setAthenaCode("");
        setNameAdmin("");
        setDetail(Boolean.FALSE);
    }

    public List<Db3partyAdmin> getListAdmin() {
        return listAdmin;
    }

    public void setListAdmin(List<Db3partyAdmin> listAdmin) {
        this.listAdmin = listAdmin;
    }

    public Db3partyAdmin getAdminSelected() {
        return adminSelected;
    }

    public void setAdminSelected(Db3partyAdmin adminSelected) {
        this.adminSelected = adminSelected;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

}
