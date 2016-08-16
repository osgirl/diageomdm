/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.diageo.diageonegocio.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author EDUARDO
 */
public class ReportDto implements Serializable{
    
    private BigDecimal id;
    private BigDecimal idFather;
    private String kiernanId;
    private String numberPDV;
    private String nit;
    private String outletName;
    private String subSegment;
    private String potential;
    private String source;

    public ReportDto() {
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getIdFather() {
        return idFather;
    }

    public void setIdFather(BigDecimal idFather) {
        this.idFather = idFather;
    }

    public String getKiernanId() {
        return kiernanId;
    }

    public void setKiernanId(String kiernanId) {
        this.kiernanId = kiernanId;
    }

    public String getNumberPDV() {
        return numberPDV;
    }

    public void setNumberPDV(String numberPDV) {
        this.numberPDV = numberPDV;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getSubSegment() {
        return subSegment;
    }

    public void setSubSegment(String subSegment) {
        this.subSegment = subSegment;
    }

    public String getPotential() {
        return potential;
    }

    public void setPotential(String potential) {
        this.potential = potential;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
    
}
