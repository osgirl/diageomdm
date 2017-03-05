/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.reports;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.datamodel.InfoIncompleteDataModel;
import com.diageo.diageonegocio.beans.reports.InformeBlancosBeanLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "incompleteInformationBean")
@ViewScoped
public class IncompleteInformationBean extends DiageoRootBean {

    @EJB
    private InformeBlancosBeanLocal informeBlancosBeanLocal;
    private InfoIncompleteDataModel infoIncompleteDataModel;

    @PostConstruct
    public void init() {
        infoIncompleteDataModel = new InfoIncompleteDataModel(informeBlancosBeanLocal);
    }

    public InfoIncompleteDataModel getInfoIncompleteDataModel() {
        return infoIncompleteDataModel;
    }

    public void setInfoIncompleteDataModel(InfoIncompleteDataModel infoIncompleteDataModel) {
        this.infoIncompleteDataModel = infoIncompleteDataModel;
    }

}
