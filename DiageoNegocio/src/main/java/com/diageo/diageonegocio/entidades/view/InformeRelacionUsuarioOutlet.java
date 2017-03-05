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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "VW_2_INFORME_RELACION_USUARIO_OUTLET")
@NamedQueries({
    @NamedQuery(name = InformeRelacionUsuarioOutlet.FIND_ALL, 
            query = "SELECT i FROM InformeRelacionUsuarioOutlet i"),
    @NamedQuery(name = InformeRelacionUsuarioOutlet.FIND_ALL_COUNT, 
            query = "SELECT COUNT(i) FROM InformeRelacionUsuarioOutlet i"),
})
public class InformeRelacionUsuarioOutlet implements Serializable {

    public static final String FIND_ALL = "InformeRelacionUsuarioOutlet.findAll";
    public static final String FIND_ALL_COUNT = "InformeRelacionUsuarioOutlet.findAllCount";
    @Id
    @Column(name = "OUTLET_ID")
    private Integer outletId;
    @Column(name = "ORIGEN")
    private String origen;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "JOURNEY_PLAN")
    private Integer journeyPlan;
    @Column(name = "OUTLET_ID_FATHER")
    private Integer outletIdFather;
    @Column(name = "KIERNAN_ID")
    private String kiernanId;
    @Column(name = "GERENCIA_COMERCIAL")
    private String gerenciaComercial;
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
    @Column(name = "NIT")
    private String nit;
    @Column(name = "CANAL_DIAGEO")
    private String canalDiageo;
    @Column(name = "SUB_CANAL_DIAGEO")
    private String subCanalDiageo;
    @Column(name = "SEGMENTO_DIAGEO")
    private String segmentoDiageo;
    @Column(name = "SUB_SEGMENTO_DIAGEO")
    private String subSegmentoDiageo;
    @Column(name = "NAME_POTENTIAL")
    private String namePotential;
    @Column(name = "SUB_SEGMENTO_DISTRIBUIDOR")
    private String subSegmentoDistribuidor;
    @Column(name = "CODIGO_VENDEDOR")
    private String codigoVendedor;
    @Column(name = "NOMBRE_VENDEDOR")
    private String nombreVendedor;
    @Column(name = "ROL_TMC_KAM")
    private String rolTmcKam;
    @Column(name = "NOMBRE_TMC_KAM")
    private String nombreTmcKam;
    @Column(name = "ROL_CPA")
    private String rolCpa;
    @Column(name = "NOMBRE_CPA")
    private String nombreCpa;
    @Column(name = "CODIGO_ADMINISTRADOR_ATHENA")
    private String codigoAdministradorAthena;
    @Column(name = "NOMBRE_ADMINISTRADOR")
    private String nombreAdministrador;

    public InformeRelacionUsuarioOutlet() {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getJourneyPlan() {
        return journeyPlan;
    }

    public void setJourneyPlan(Integer journeyPlan) {
        this.journeyPlan = journeyPlan;
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

    public String getGerenciaComercial() {
        return gerenciaComercial;
    }

    public void setGerenciaComercial(String gerenciaComercial) {
        this.gerenciaComercial = gerenciaComercial;
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCanalDiageo() {
        return canalDiageo;
    }

    public void setCanalDiageo(String canalDiageo) {
        this.canalDiageo = canalDiageo;
    }

    public String getSubCanalDiageo() {
        return subCanalDiageo;
    }

    public void setSubCanalDiageo(String subCanalDiageo) {
        this.subCanalDiageo = subCanalDiageo;
    }

    public String getSegmentoDiageo() {
        return segmentoDiageo;
    }

    public void setSegmentoDiageo(String segmentoDiageo) {
        this.segmentoDiageo = segmentoDiageo;
    }

    public String getSubSegmentoDiageo() {
        return subSegmentoDiageo;
    }

    public void setSubSegmentoDiageo(String subSegmentoDiageo) {
        this.subSegmentoDiageo = subSegmentoDiageo;
    }

    public String getNamePotential() {
        return namePotential;
    }

    public void setNamePotential(String namePotential) {
        this.namePotential = namePotential;
    }

    public String getSubSegmentoDistribuidor() {
        return subSegmentoDistribuidor;
    }

    public void setSubSegmentoDistribuidor(String subSegmentoDistribuidor) {
        this.subSegmentoDistribuidor = subSegmentoDistribuidor;
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

    public String getRolTmcKam() {
        return rolTmcKam;
    }

    public void setRolTmcKam(String rolTmcKam) {
        this.rolTmcKam = rolTmcKam;
    }

    public String getNombreTmcKam() {
        return nombreTmcKam;
    }

    public void setNombreTmcKam(String nombreTmcKam) {
        this.nombreTmcKam = nombreTmcKam;
    }

    public String getRolCpa() {
        return rolCpa;
    }

    public void setRolCpa(String rolCpa) {
        this.rolCpa = rolCpa;
    }

    public String getNombreCpa() {
        return nombreCpa;
    }

    public void setNombreCpa(String nombreCpa) {
        this.nombreCpa = nombreCpa;
    }

    public String getCodigoAdministradorAthena() {
        return codigoAdministradorAthena;
    }

    public void setCodigoAdministradorAthena(String codigoAdministradorAthena) {
        this.codigoAdministradorAthena = codigoAdministradorAthena;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
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
        if (obj instanceof InformeRelacionUsuarioOutlet) {
            InformeRelacionUsuarioOutlet i = (InformeRelacionUsuarioOutlet) obj;
            return i.outletId.equals(this.outletId);
        }
        return false;
    }
}
