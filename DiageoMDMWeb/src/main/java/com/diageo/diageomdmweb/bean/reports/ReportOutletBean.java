/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.reports;

import com.diageo.diageomdmweb.bean.outlet.OutletConsultarBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "reportOutletBean")
@ViewScoped
public class ReportOutletBean extends OutletConsultarBean {

    /**
     * Creates a new instance of ReportOutletBean
     */
    public ReportOutletBean() {
    }

    public void filerListOutlet() {
        setListaOutlets(outletBeanLocal.findBySubSegment(getSubSegmentoSeleccionado().getSubSegmentId()));
    }

}
