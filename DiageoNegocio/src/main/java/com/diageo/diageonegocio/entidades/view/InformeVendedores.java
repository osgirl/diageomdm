/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades.view;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "VW_5_INFORME_VENDEDOR_TERRITORIO")
public class InformeVendedores implements Serializable {

    @Id
    @Column(name = "OUTLET_ID")
    private Integer outletId;
    @Column(name = "Territorio")
    private String territorio;
    @Column(name = "SucursalDistribuidor")
    private String sucursalDistribuidor;
    @Column(name = "CodigoVendedor")
    private String codigoVendedor;
    @Column(name = "OUTLET_ID_FATHER")
    private Integer outletIdFather;
    @Column(name = "KIERNAN_ID")
    private String kiernanId;
    @Column(name = "NombreComercial")
    private String nombreComercial;
    @Column(name = "MODIFICATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;
    @Column(name = "MODIFICATION_USER")
    private String modificationUser;
    @Column(name = "STATUS_OUTLET")
    private String statusOutlet;
    @Column(name = "JOURNEY_PLAN")
    private String journeyPlan;

    public InformeVendedores() {
    }

    public Integer getOutletId() {
        return outletId;
    }

    public void setOutletId(Integer outletId) {
        this.outletId = outletId;
    }

    public String getTerritorio() {
        return territorio;
    }

    public void setTerritorio(String territorio) {
        this.territorio = territorio;
    }

    public String getSucursalDistribuidor() {
        return sucursalDistribuidor;
    }

    public void setSucursalDistribuidor(String sucursalDistribuidor) {
        this.sucursalDistribuidor = sucursalDistribuidor;
    }

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public Integer getOutletIdFather() {
        return outletIdFather;
    }

    public void setOutletIdFather(Integer outletIdFather) {
        this.outletIdFather = outletIdFather;
    }

    public String getKiernanId() {
        return kiernanId;
    }

    public void setKiernanId(String kiernanId) {
        this.kiernanId = kiernanId;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getModificationUser() {
        return modificationUser;
    }

    public void setModificationUser(String modificationUser) {
        this.modificationUser = modificationUser;
    }

    public String getStatusOutlet() {
        return statusOutlet;
    }

    public void setStatusOutlet(String statusOutlet) {
        this.statusOutlet = statusOutlet;
    }

    public String getJourneyPlan() {
        return journeyPlan;
    }

    public void setJourneyPlan(String journeyPlan) {
        this.journeyPlan = journeyPlan;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.outletId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(obj instanceof InformeVendedores){
            InformeVendedores i=(InformeVendedores)obj;
            return i.outletId.equals(this.outletId);
        }
        return false;
    }
    
    
}
