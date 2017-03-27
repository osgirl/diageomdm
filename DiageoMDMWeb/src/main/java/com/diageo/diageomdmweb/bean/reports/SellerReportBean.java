/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.reports;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.datamodel.InfoSellerDataModel;
import com.diageo.diageonegocio.beans.reports.InformeVendedoreBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "sellerReportBean")
@ViewScoped
public class SellerReportBean extends DiageoRootBean {

    @EJB
    private InformeVendedoreBean informeVendedoreBean;
    private InfoSellerDataModel sellerDataModel;

    @PostConstruct
    public void init() {
        sellerDataModel = new InfoSellerDataModel(informeVendedoreBean);
    }

    public InfoSellerDataModel getSellerDataModel() {
        return sellerDataModel;
    }

    public void setSellerDataModel(InfoSellerDataModel sellerDataModel) {
        this.sellerDataModel = sellerDataModel;
    }

}
