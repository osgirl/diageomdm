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
@Table(name = "VW_1_INFORME_PENDIENTE_GESTION")
public class InformePendientesGestion implements Serializable {

    @Id
    @Column(name = "OUTLET_ID")
    private Integer outletId;
    @Column(name = "LINEA_NEGOCIO")
    private String lineaNegocio;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "KIERNAN_ID")
    private String kiernanId;
    @Column(name = "COD_DISTRIBUIDOR")
    private String codDistribuidor;
    @Column(name = "NOMBRE_SUCURSAL_DISTRIBUIDOR")
    private String nombreSucursalDistribuidor;
    @Column(name = "NIT")
    private String nit;
    @Column(name = "CODIGO_PDV_DISTRIBUIDOR")
    private String codigoPdvDistribuidor;
    @Column(name = "BUSINESS_NAME")
    private String businessName;
    @Column(name = "OUTLET_NAME")
    private String outletName;
    @Column(name = "NOMBRE_CIUDAD")
    private String nombreCiudad;
    @Column(name = "NOMBRE_DEPARTAMENTO")
    private String nombreDepartamento;
    @Column(name = "COD_VENDEDOR")
    private String codVendedor;
    @Column(name = "NOMBRE_PERFIL")
    private String nombrePerfil;
    @Column(name = "TMC_KAM")
    private String tmcKam;
    @Column(name = "USUARIO_A_GESTIONAR")
    private String usuarioAGestionar;
    @Column(name = "GERENCIA_COMERCIAL")
    private String gerenciaComercial;
    @Column(name = "SUB_SEGMENTO_DIAGEO")
    private String subSegmentoDiageo;
    @Column(name = "SUB_SEGMENTO_DIST")
    private String subSegmentoDist;
    @Column(name = "STATUS_OUTLET")
    private String statusOutlet;

    public InformePendientesGestion() {
    }

    public Integer getOutletId() {
        return outletId;
    }

    public void setOutletId(Integer outletId) {
        this.outletId = outletId;
    }

    public String getLineaNegocio() {
        return lineaNegocio;
    }

    public void setLineaNegocio(String lineaNegocio) {
        this.lineaNegocio = lineaNegocio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getKiernanId() {
        return kiernanId;
    }

    public void setKiernanId(String kiernanId) {
        this.kiernanId = kiernanId;
    }

    public String getCodDistribuidor() {
        return codDistribuidor;
    }

    public void setCodDistribuidor(String codDistribuidor) {
        this.codDistribuidor = codDistribuidor;
    }

    public String getNombreSucursalDistribuidor() {
        return nombreSucursalDistribuidor;
    }

    public void setNombreSucursalDistribuidor(String nombreSucursalDistribuidor) {
        this.nombreSucursalDistribuidor = nombreSucursalDistribuidor;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCodigoPdvDistribuidor() {
        return codigoPdvDistribuidor;
    }

    public void setCodigoPdvDistribuidor(String codigoPdvDistribuidor) {
        this.codigoPdvDistribuidor = codigoPdvDistribuidor;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
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

    public String getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(String codVendedor) {
        this.codVendedor = codVendedor;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getTmcKam() {
        return tmcKam;
    }

    public void setTmcKam(String tmcKam) {
        this.tmcKam = tmcKam;
    }

    public String getUsuarioAGestionar() {
        return usuarioAGestionar;
    }

    public void setUsuarioAGestionar(String usuarioAGestionar) {
        this.usuarioAGestionar = usuarioAGestionar;
    }

    public String getGerenciaComercial() {
        return gerenciaComercial;
    }

    public void setGerenciaComercial(String gerenciaComercial) {
        this.gerenciaComercial = gerenciaComercial;
    }

    public String getSubSegmentoDiageo() {
        return subSegmentoDiageo;
    }

    public void setSubSegmentoDiageo(String subSegmentoDiageo) {
        this.subSegmentoDiageo = subSegmentoDiageo;
    }

    public String getSubSegmentoDist() {
        return subSegmentoDist;
    }

    public void setSubSegmentoDist(String subSegmentoDist) {
        this.subSegmentoDist = subSegmentoDist;
    }

    public String getStatusOutlet() {
        return statusOutlet;
    }

    public void setStatusOutlet(String statusOutlet) {
        this.statusOutlet = statusOutlet;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.outletId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof InformePendientesGestion) {
            InformePendientesGestion in = (InformePendientesGestion) obj;
            return in.outletId.equals(this.outletId);
        }
        return false;
    }

}
