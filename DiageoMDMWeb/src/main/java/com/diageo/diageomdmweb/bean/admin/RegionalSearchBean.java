/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.RegionalBeanLocal;
import com.diageo.diageonegocio.entidades.Db3partyRegional;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.Serializable;
import java.util.List;
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
@Named(value = "regionalSearchBean")
@ViewScoped
public class RegionalSearchBean extends DiageoRootBean implements Serializable {

    @EJB
    protected RegionalBeanLocal regionalBeanLocal;
    private String regionalName;
    private String athenaCode;
    private boolean detail;
    private List<Db3partyRegional> listRegional;
    private Db3partyRegional regionalSelected;

    /**
     * Creates a new instance of RegionalSearchBean
     */
    public RegionalSearchBean() {
    }

    @PostConstruct
    public void init() {
        setRegionalName("");
        setAthenaCode("");
        setRegionalSelected(new Db3partyRegional());
        setDetail(Boolean.TRUE);
        setListRegional(regionalBeanLocal.findAll());
    }

    public void updateRegional() {
        try {
            getRegionalSelected().setNameRegional(regionalName.toUpperCase());
            getRegionalSelected().setDistri_1(athenaCode.toUpperCase());
            regionalBeanLocal.updateRegional(getRegionalSelected());
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(RegionalSearchBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }
    
    public void listenerDetail(Db3partyRegional selected){
        setDetail(Boolean.FALSE);
        setRegionalSelected(selected);
        setRegionalName(selected.getNameRegional());
        setAthenaCode(selected.getDistri_1());        
    }

    public String getRegionalName() {
        return regionalName;
    }

    public void setRegionalName(String regionalName) {
        this.regionalName = regionalName;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public List<Db3partyRegional> getListRegional() {
        return listRegional;
    }

    public void setListRegional(List<Db3partyRegional> listRegional) {
        this.listRegional = listRegional;
    }

    public Db3partyRegional getRegionalSelected() {
        return regionalSelected;
    }

    public void setRegionalSelected(Db3partyRegional regionalSelected) {
        this.regionalSelected = regionalSelected;
    }

    public String getAthenaCode() {
        return athenaCode;
    }

    public void setAthenaCode(String athenaCode) {
        this.athenaCode = athenaCode;
    }

}
