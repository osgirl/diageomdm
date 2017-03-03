/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades.view;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "VW_3_INFORME_BLANCOS")
public class InformeBlancos implements Serializable {

    @Id
    @Column(name = "OUTLET_ID")
    private Integer outletId;
    @Column(name = "ORIGEN")
    private String origen;
    @Column(name = "OUTLET_ID_FATHER")
    private Integer outletIdFather;
    @Column(name = "KIERNAN_ID")
    private String kiernanId;
    @Column(name = "GERENCIA")
    private String gerencia;
    @Column(name = "CODIGO_DISTRIBUIDOR")
    private String codigoDistribuidor;
    @Column(name = "NOMBRE_DISTRIBUIDOR")
    private String nombreDistribuidor;
    @Column(name = "NUMBER_PDV")
    private String numberPDV;
    @Column(name = "OUTLET_NAME")
    private String outletName;
    @Column(name = "BUSINESS_NAME")
    private String businessName;
    @Column(name = "NIT_EAN")
    private String nitEan;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "NOMBRE_CIUDAD")
    private String nombreCiudad;
    @Column(name = "NOMBRE_DEPARTAMENTO")
    private String nombreDepartamento;
    @Column(name = "CODIGO_VENDEDOR")
    private String codigoVendedor;
    @Column(name = "NOMBRE_VENDEDOR")
    private String nombreVendedor;
    @Column(name = "TMC_KAM")
    private String tmcKam;
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public InformeBlancos() {
    }

    public Integer getOutletId() {
        return outletId;
    }

    public void setOutletId(Integer outletId) {
        this.outletId = outletId;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
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

    public String getGerencia() {
        return gerencia;
    }

    public void setGerencia(String gerencia) {
        this.gerencia = gerencia;
    }

    public String getCodigoDistribuidor() {
        return codigoDistribuidor;
    }

    public void setCodigoDistribuidor(String codigoDistribuidor) {
        this.codigoDistribuidor = codigoDistribuidor;
    }

    public String getNombreDistribuidor() {
        return nombreDistribuidor;
    }

    public void setNombreDistribuidor(String nombreDistribuidor) {
        this.nombreDistribuidor = nombreDistribuidor;
    }

    public String getNumberPDV() {
        return numberPDV;
    }

    public void setNumberPDV(String numberPDV) {
        this.numberPDV = numberPDV;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getNitEan() {
        return nitEan;
    }

    public void setNitEan(String nitEan) {
        this.nitEan = nitEan;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getTmcKam() {
        return tmcKam;
    }

    public void setTmcKam(String tmcKam) {
        this.tmcKam = tmcKam;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.outletId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof InformeBlancos) {
            InformeBlancos i = (InformeBlancos) obj;
            return i.outletId.equals(this.outletId);
        }
        return false;
    }

}
