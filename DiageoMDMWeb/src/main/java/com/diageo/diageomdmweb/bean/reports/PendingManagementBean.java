/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.reports;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.reports.InformePendientesGestionBeanLocal;
import com.diageo.diageonegocio.entidades.view.InformePendientesGestion;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "pendingManagementBean")
@ViewScoped
public class PendingManagementBean extends DiageoRootBean implements Serializable {

    @EJB
    private InformePendientesGestionBeanLocal pendientesGestionBeanLocal;
    private List<InformePendientesGestion> listReport;
    private List<InformePendientesGestion> listReportFilter;
    
    @PostConstruct
    public void init(){
        listReport=pendientesGestionBeanLocal.findAll();
        listReportFilter=listReport;
    }

    public List<InformePendientesGestion> getListReport() {
        return listReport;
    }

    public void setListReport(List<InformePendientesGestion> listReport) {
        this.listReport = listReport;
    }

    public List<InformePendientesGestion> getListReportFilter() {
        return listReportFilter;
    }

    public void setListReportFilter(List<InformePendientesGestion> listReportFilter) {
        this.listReportFilter = listReportFilter;
    }
    
    
}
