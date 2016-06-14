/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.outlet;

import com.diageo.admincontrollerweb.entities.DwDocumentTypes;
import com.diageo.diageomdmweb.bean.DiageoApplicationBean;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.Db3PartyBeanLocal;
import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.beans.PotentialBeanLocal;
import com.diageo.diageonegocio.beans.SubSegmentoBeanLocal;
import com.diageo.diageonegocio.entidades.DbDepartaments;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.DbTowns;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbPotentials;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.entidades.DbPhones;
import com.diageo.diageonegocio.entidades.DbTypePhones;
import com.diageo.diageonegocio.enums.StateOutletChain;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author yovanoty126
 */
@Named(value = "outletCrearBean")
@ViewScoped
public class OutletCrearBean extends DiageoRootBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(OutletCrearBean.class.getName());
    @EJB
    private OutletBeanLocal outletBeanLocal;
    @EJB
    private Db3PartyBeanLocal distribuidorBeanLocal;    
    @EJB
    protected PotentialBeanLocal potentialBeanLocal;
    @EJB
    protected SubSegmentoBeanLocal subSegmentoBeanLocal;
    @Inject
    private DiageoApplicationBean diageoApplicationBean;
    //list segment   
    private List<DbSubSegments> listSubSegment;
    private List<DbPotentials> listaPotentialAutomatic;
    private List<DbPotentials> listaPotentialManual;
    private String channelLabel;
    private String subChannelLabel;
    private String segmentLabel;
    //Other list
    private List<Db3party> listaDistribuidor;    
    private DbSubSegments subSegmentoSeleccionado;
    private Db3party distribuidorSeleccionado;
    private DbPotentials potentialAutomatic;
    private DbPotentials potentialManula;
    //location
    private DbDepartaments departamentoDistribuidor;
    private DbDepartaments departamentoOutlet;
    private DbTowns municipioDistribuidor;
    private DbTowns municipioOutlet;

    private String razonSocial;
    private String tipoOutlet;
    private String nombreOutlet;
    private String nit;
    private String nombresPropietarios;
    private String apellidosPropietario;
    private String numeroDocumento;
    private DwDocumentTypes tipoDocumento;
    private String direccion;
    private String barrio;
    private String correoElectronico;
    private String celular;
    private String telefonoUno;
    private String telefonoDos;
    private String lineaNegocio;
    private String codigoEan;
    private String puntoVenta;

    /**
     * Creates a new instance of OutletVistaBean
     */
    public OutletCrearBean() {
    }

    @PostConstruct
    public void init() {
        setListaPotentialManual(potentialBeanLocal.findAll());
        setPotentialManula(getListaPotentialManual().get(0));
        cargarListas();
        setListaDistribuidor(distribuidorBeanLocal.searchAllDistributor());
        setDistribuidorSeleccionado(getListaDistribuidor().get(0));
        setDepartamentoDistribuidor(getDiageoApplicationBean().getListaDepartamento().get(0));
        setDepartamentoOutlet(getDiageoApplicationBean().getListaDepartamento().get(0));
        setMunicipioDistribuidor(getDepartamentoDistribuidor().getDbTownsList().get(0));
        setMunicipioOutlet(getDepartamentoOutlet().getDbTownsList().get(0));
        setTipoDocumento(getDiageoApplicationBean().getListaTipoDocumento().get(0));                
        inicializarCampos();
    }

    private void inicializarCampos() {
        setApellidosPropietario("");
        setBarrio("");
        setCelular("");
        setCodigoEan("");
        setCorreoElectronico("");
        setDireccion("");
        setLineaNegocio("");
        setTipoOutlet("");
        setTelefonoUno("");
        setTelefonoDos("");
        setRazonSocial("");
        setPuntoVenta("");
        setNit("");
        setNumeroDocumento("");
        setNombresPropietarios("");
        setNombreOutlet("");
    }

    public void cargarListas() {
        setListSubSegment(subSegmentoBeanLocal.findAllSubSegment());
    }

    public void guardarOutlet() {
        try {
            DbOutlets outletEntidad = new DbOutlets();
            //DATOS BASICOS
            outletEntidad.setBusinessName(razonSocial);
            outletEntidad.setTypeOutlet(tipoOutlet);
            outletEntidad.setOutletName(nombreOutlet);
            outletEntidad.setNit(nit);            
            //pendiente guardar los tipos de personas pertenecientes al outlet
            //CLASIFICACION
            outletEntidad.setSubSegmentId(subSegmentoSeleccionado);
            //UBICACION            
            outletEntidad.setAddress(direccion);
            outletEntidad.setNeighborhood(barrio);
            setDepartamentoOutlet(departamentoOutlet);
            setMunicipioOutlet(municipioOutlet);            
            //DATOS CONTACTO
            List<DbPhones> listaTelefonoses = new ArrayList<>();
            DbPhones cel = new DbPhones();
            cel.setNumberPhone(celular);
            cel.setTypePhoneId(new DbTypePhones(1));
            DbPhones telUno = new DbPhones();
            telUno.setNumberPhone(telefonoUno);
            telUno.setTypePhoneId(new DbTypePhones(2));
            DbPhones telDos = new DbPhones();
            telDos.setNumberPhone(telefonoDos);
            telDos.setTypePhoneId(new DbTypePhones(3));
            listaTelefonoses.add(cel);
            listaTelefonoses.add(telUno);
            listaTelefonoses.add(telDos);
            outletEntidad.setDbPhonesList(listaTelefonoses);
            //OTROS DATOS
            outletEntidad.setBusinessLine(lineaNegocio);
            outletEntidad.setEanCode(codigoEan);
            outletEntidad.setNumberPdv(nit);
            
            outletEntidad.setPotentialId(potentialAutomatic);
            outletEntidad.setStateOutletId(StateOutletChain.OUTLET_TMC.getId());
            outletBeanLocal.createOutlet(outletEntidad);
            init();
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void listenerSubSegment() {
        if (getSubSegmentoSeleccionado().getDbPotentialsList() == null || getSubSegmentoSeleccionado().getDbPotentialsList().isEmpty()) {
            setListaPotentialAutomatic(new ArrayList<DbPotentials>());
        } else {
            setPotentialAutomatic(getSubSegmentoSeleccionado().getDbPotentialsList().get(0));
            setListaPotentialAutomatic(getSubSegmentoSeleccionado().getDbPotentialsList());
            setSegmentLabel(getSubSegmentoSeleccionado().getSegmentId().getNameSegment());
            setSubChannelLabel(getSubSegmentoSeleccionado().getSegmentId().getSubChannelId().getNameSubChannel());
            setChannelLabel(getSubSegmentoSeleccionado().getSegmentId().getSubChannelId().getChannelId().getNameChannel());
        }

    }

    public DbSubSegments getSubSegmentoSeleccionado() {
        return subSegmentoSeleccionado;
    }

    public void setSubSegmentoSeleccionado(DbSubSegments subSegmentoSeleccionado) {
        this.subSegmentoSeleccionado = subSegmentoSeleccionado;
    }

    public Db3party getDistribuidorSeleccionado() {
        return distribuidorSeleccionado;
    }

    public void setDistribuidorSeleccionado(Db3party distribuidorSeleccionado) {
        this.distribuidorSeleccionado = distribuidorSeleccionado;
    }

    public DbDepartaments getDepartamentoDistribuidor() {
        return departamentoDistribuidor;
    }

    public void setDepartamentoDistribuidor(DbDepartaments departamentoDistribuidor) {
        this.departamentoDistribuidor = departamentoDistribuidor;
    }

    public DbTowns getMunicipioDistribuidor() {
        return municipioDistribuidor;
    }

    public void setMunicipioDistribuidor(DbTowns municipioDistribuidor) {
        this.municipioDistribuidor = municipioDistribuidor;
    }

    public List<Db3party> getListaDistribuidor() {
        return listaDistribuidor;
    }

    public void setListaDistribuidor(List<Db3party> listaDistribuidor) {
        this.listaDistribuidor = listaDistribuidor;
    }

    public DiageoApplicationBean getDiageoApplicationBean() {
        return diageoApplicationBean;
    }

    public void setDiageoApplicationBean(DiageoApplicationBean diageoApplicationBean) {
        this.diageoApplicationBean = diageoApplicationBean;
    }

    public DbDepartaments getDepartamentoOutlet() {
        return departamentoOutlet;
    }

    public void setDepartamentoOutlet(DbDepartaments departamentoOutlet) {
        this.departamentoOutlet = departamentoOutlet;
    }

    public DbTowns getMunicipioOutlet() {
        return municipioOutlet;
    }

    public void setMunicipioOutlet(DbTowns municipioOutlet) {
        this.municipioOutlet = municipioOutlet;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTipoOutlet() {
        return tipoOutlet;
    }

    public void setTipoOutlet(String tipoOutlet) {
        this.tipoOutlet = tipoOutlet;
    }

    public String getNombreOutlet() {
        return nombreOutlet;
    }

    public void setNombreOutlet(String nombreOutlet) {
        this.nombreOutlet = nombreOutlet;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombresPropietarios() {
        return nombresPropietarios;
    }

    public void setNombresPropietarios(String nombresPropietarios) {
        this.nombresPropietarios = nombresPropietarios;
    }

    public String getApellidosPropietario() {
        return apellidosPropietario;
    }

    public void setApellidosPropietario(String apellidosPropietario) {
        this.apellidosPropietario = apellidosPropietario;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public DwDocumentTypes getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(DwDocumentTypes tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefonoUno() {
        return telefonoUno;
    }

    public void setTelefonoUno(String telefonoUno) {
        this.telefonoUno = telefonoUno;
    }

    public String getTelefonoDos() {
        return telefonoDos;
    }

    public void setTelefonoDos(String telefonoDos) {
        this.telefonoDos = telefonoDos;
    }

    public String getLineaNegocio() {
        return lineaNegocio;
    }

    public void setLineaNegocio(String lineaNegocio) {
        this.lineaNegocio = lineaNegocio;
    }

    public String getCodigoEan() {
        return codigoEan;
    }

    public void setCodigoEan(String codigoEan) {
        this.codigoEan = codigoEan;
    }

    public String getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(String puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public OutletBeanLocal getOutletBeanLocal() {
        return outletBeanLocal;
    }

    public void setOutletBeanLocal(OutletBeanLocal outletBeanLocal) {
        this.outletBeanLocal = outletBeanLocal;
    }

    public Db3PartyBeanLocal getDistribuidorBeanLocal() {
        return distribuidorBeanLocal;
    }

    public void setDistribuidorBeanLocal(Db3PartyBeanLocal distribuidorBeanLocal) {
        this.distribuidorBeanLocal = distribuidorBeanLocal;
    }

    public List<DbSubSegments> getListSubSegment() {
        return listSubSegment;
    }

    public void setListSubSegment(List<DbSubSegments> listSubSegment) {
        this.listSubSegment = listSubSegment;
    }

    public List<DbPotentials> getListaPotentialAutomatic() {
        return listaPotentialAutomatic;
    }

    public void setListaPotentialAutomatic(List<DbPotentials> listaPotentialAutomatic) {
        this.listaPotentialAutomatic = listaPotentialAutomatic;
    }

    public List<DbPotentials> getListaPotentialManual() {
        return listaPotentialManual;
    }

    public void setListaPotentialManual(List<DbPotentials> listaPotentialManual) {
        this.listaPotentialManual = listaPotentialManual;
    }

    public DbPotentials getPotentialAutomatic() {
        return potentialAutomatic;
    }

    public void setPotentialAutomatic(DbPotentials potentialAutomatic) {
        this.potentialAutomatic = potentialAutomatic;
    }

    public DbPotentials getPotentialManula() {
        return potentialManula;
    }

    public void setPotentialManula(DbPotentials potentialManula) {
        this.potentialManula = potentialManula;
    }

    public String getChannelLabel() {
        return channelLabel;
    }

    public void setChannelLabel(String channelLabel) {
        this.channelLabel = channelLabel;
    }

    public String getSubChannelLabel() {
        return subChannelLabel;
    }

    public void setSubChannelLabel(String subChannelLabel) {
        this.subChannelLabel = subChannelLabel;
    }

    public String getSegmentLabel() {
        return segmentLabel;
    }

    public void setSegmentLabel(String segmentLabel) {
        this.segmentLabel = segmentLabel;
    }

}
