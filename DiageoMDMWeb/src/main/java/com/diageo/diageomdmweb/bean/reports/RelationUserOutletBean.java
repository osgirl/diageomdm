/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.reports;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.datamodel.InformeRelUsuOutletDataModel;
import com.diageo.diageonegocio.beans.reports.InformerRelacionUsuarioOutletBeanLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "relationUserOutletBean")
@ViewScoped
public class RelationUserOutletBean extends DiageoRootBean {

    @EJB
    private InformerRelacionUsuarioOutletBeanLocal usuarioOutletBeanLocal;
    private InformeRelUsuOutletDataModel informeRelUsuOutletDataModel;

    @PostConstruct
    public void init() {
        informeRelUsuOutletDataModel = new InformeRelUsuOutletDataModel(usuarioOutletBeanLocal);
    }

    public InformeRelUsuOutletDataModel getInformeRelUsuOutletDataModel() {
        return informeRelUsuOutletDataModel;
    }

    public void setInformeRelUsuOutletDataModel(InformeRelUsuOutletDataModel informeRelUsuOutletDataModel) {
        this.informeRelUsuOutletDataModel = informeRelUsuOutletDataModel;
    }

}
