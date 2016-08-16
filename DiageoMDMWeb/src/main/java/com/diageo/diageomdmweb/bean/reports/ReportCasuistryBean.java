/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.reports;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.reports.CasuistryBeanLocal;
import com.diageo.diageonegocio.dto.ReportDto;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author EDUARDO
 */
@Named(value = "reportCasuistryBean")
@ViewScoped
public class ReportCasuistryBean extends DiageoRootBean implements Serializable {

    @EJB
    private CasuistryBeanLocal casuistryBeanLocal;
    private List<ReportDto> listReport;
    private String description;
    private String casuistryType;
    /**
     * Creates a new instance of ReportCasuistryBean
     */
    public ReportCasuistryBean() {
    }
    
    public void generateReport(){
        listReport=casuistryBeanLocal.casuistry_1();
        addDescription();
    }
    
    public void addDescription(){
        switch(casuistryType){
            case "1":
                setDescription("<b>Total Distribuidor/Cadena</b>");
        }
    }

    /**
     * @return the listReport
     */
    public List<ReportDto> getListReport() {
        return listReport;
    }

    /**
     * @param listReport the listReport to set
     */
    public void setListReport(List<ReportDto> listReport) {
        this.listReport = listReport;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the casuistryType
     */
    public String getCasuistryType() {
        return casuistryType;
    }

    /**
     * @param casuistryType the casuistryType to set
     */
    public void setCasuistryType(String casuistryType) {
        this.casuistryType = casuistryType;
    }

}
