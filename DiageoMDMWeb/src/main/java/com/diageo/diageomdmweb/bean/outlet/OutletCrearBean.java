/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.outlet;

import com.diageo.admincontrollerweb.entities.TipoDoc;
import com.diageo.diageomdmweb.bean.DiageoApplicationBean;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.BattleGroundBeanLocal;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.DistributorBeanLocal;
import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.beans.PotencialBeanLocal;
import com.diageo.diageonegocio.entidades.Battleground;
import com.diageo.diageonegocio.entidades.Channel;
import com.diageo.diageonegocio.entidades.Departamento;
import com.diageo.diageonegocio.entidades.Distribuidor;
import com.diageo.diageonegocio.entidades.Municipio;
import com.diageo.diageonegocio.entidades.Outlet;
import com.diageo.diageonegocio.entidades.Persona;
import com.diageo.diageonegocio.entidades.Potencial;
import com.diageo.diageonegocio.entidades.Segmento;
import com.diageo.diageonegocio.entidades.SubChannel;
import com.diageo.diageonegocio.entidades.SubSegmento;
import com.diageo.diageonegocio.entidades.Telefonos;
import com.diageo.diageonegocio.entidades.TipoDocumento;
import com.diageo.diageonegocio.entidades.TipoTelefono;
import com.diageo.diageonegocio.entidades.Ubicacion;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
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
    private ChannelBeanLocal channelBeanLocal;
    @EJB
    private PotencialBeanLocal potencialBeanLocal;
    @EJB
    private DistributorBeanLocal distribuidorBeanLocal;
    @EJB
    private BattleGroundBeanLocal battleGroundBeanLocal;
    @Inject
    private DiageoApplicationBean diageoApplicationBean;
    private List<Channel> listaCanales;
    private List<Potencial> listaPotencial;
    private List<Distribuidor> listaDistribuidor;
    private List<Battleground> listaBattleground;
    private Battleground battlegroundSeleccionado;
    private Channel canalSeleccionado;
    private SubChannel subCanalSeleccionado;
    private Segmento segmentoSeleccionado;
    private SubSegmento subSegmentoSeleccionado;
    private Potencial potencialSeleccionado;
    private Distribuidor distribuidorSeleccionado;
    private Departamento departamentoDistribuidor;
    private Departamento departamentoOutlet;
    private Municipio municipioDistribuidor;
    private Municipio municipioOutlet;

    private String razonSocial;
    private String tipoOutlet;
    private String nombreOutlet;
    private String nit;
    private String nombresPropietarios;
    private String apellidosPropietario;
    private String numeroDocumento;
    private TipoDoc tipoDocumento;
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
        setListaCanales(channelBeanLocal.consultarTodosChannel());
        setCanalSeleccionado(getListaCanales().get(0));
        cargarListas();
        setListaPotencial(potencialBeanLocal.constultarTodosPotenciales());
        setPotencialSeleccionado(getListaPotencial().get(0));
        setListaDistribuidor(distribuidorBeanLocal.searchAllDistributor());
        setDistribuidorSeleccionado(getListaDistribuidor().get(0));
        setDepartamentoDistribuidor(getDiageoApplicationBean().getListaDepartamento().get(0));
        setDepartamentoOutlet(getDiageoApplicationBean().getListaDepartamento().get(0));
        setMunicipioDistribuidor(getDepartamentoDistribuidor().getMunicipioList().get(0));
        setMunicipioOutlet(getDepartamentoOutlet().getMunicipioList().get(0));
        setTipoDocumento(getDiageoApplicationBean().getListaTipoDocumento().get(0));
        setListaBattleground(battleGroundBeanLocal.consultarTodosBattlegroun());
        setBattlegroundSeleccionado(getListaBattleground().get(0));
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
        setSubCanalSeleccionado(getCanalSeleccionado().getSubChannelList().get(0));
        setSegmentoSeleccionado(getSubCanalSeleccionado().getSegmentoList().get(0));
        setSubSegmentoSeleccionado(getSegmentoSeleccionado().getSubSegmentoList().get(0));
    }

    public void guardarOutlet() {
        try {
            Outlet outletEntidad = new Outlet();
            //DATOS BASICOS
            outletEntidad.setRazonsocial(razonSocial);
            outletEntidad.setTipoPersona(tipoOutlet);
            outletEntidad.setOutletname(nombreOutlet);
            outletEntidad.setNit(nit);
            Persona personaPropietaria = new Persona();
            personaPropietaria.setApellidos(apellidosPropietario);
            personaPropietaria.setNombres(nombresPropietarios);
            personaPropietaria.setNumDoc(numeroDocumento);
            personaPropietaria.setTipodoc(new TipoDocumento(tipoDocumento.getIdtipoDoc()));
            outletEntidad.setPropietario(personaPropietaria);
            //CLASIFICACION
            outletEntidad.setIdsubsegmento(subSegmentoSeleccionado);
            outletEntidad.setIdPotencial(potencialSeleccionado);
            outletEntidad.setIdDistribuidor(distribuidorSeleccionado);
            outletEntidad.setIdbattledground(battlegroundSeleccionado);
            //UBICACION
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setIdMunicipio(municipioOutlet);
            ubicacion.setBarrio(barrio);
            ubicacion.setDireccion(direccion);
            outletEntidad.setIdubicacion(ubicacion);
            //DATOS CONTACTO
            List<Telefonos> listaTelefonoses = new ArrayList<>();
            Telefonos cel = new Telefonos();
            cel.setNumeroTel(celular);
            cel.setTipoTelefono(new TipoTelefono(1));
            Telefonos telUno = new Telefonos();
            telUno.setNumeroTel(telefonoUno);
            telUno.setTipoTelefono(new TipoTelefono(2));
            Telefonos telDos = new Telefonos();
            telDos.setNumeroTel(telefonoDos);
            telDos.setTipoTelefono(new TipoTelefono(3));
            listaTelefonoses.add(cel);
            listaTelefonoses.add(telUno);
            listaTelefonoses.add(telDos);
            outletEntidad.setTelefonosList(listaTelefonoses);
            //OTROS DATOS
            outletEntidad.setLineanegocio(lineaNegocio);
            outletEntidad.setCodigoEAN(codigoEan);
            outletEntidad.setNumPDV(nit);
            outletBeanLocal.crearOutlet(outletEntidad);
            init();
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoNegocioException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public List<Channel> getListaCanales() {
        return listaCanales;
    }

    public void setListaCanales(List<Channel> listaCanales) {
        this.listaCanales = listaCanales;
    }

    public Channel getCanalSeleccionado() {
        return canalSeleccionado;
    }

    public void setCanalSeleccionado(Channel canalSeleccionado) {
        this.canalSeleccionado = canalSeleccionado;
    }

    public ChannelBeanLocal getChannelBeanLocal() {
        return channelBeanLocal;
    }

    public void setChannelBeanLocal(ChannelBeanLocal channelBeanLocal) {
        this.channelBeanLocal = channelBeanLocal;
    }

    public SubChannel getSubCanalSeleccionado() {
        return subCanalSeleccionado;
    }

    public void setSubCanalSeleccionado(SubChannel subCanalSeleccionado) {
        this.subCanalSeleccionado = subCanalSeleccionado;
    }

    public Segmento getSegmentoSeleccionado() {
        return segmentoSeleccionado;
    }

    public void setSegmentoSeleccionado(Segmento segmentoSeleccionado) {
        this.segmentoSeleccionado = segmentoSeleccionado;
    }

    public SubSegmento getSubSegmentoSeleccionado() {
        return subSegmentoSeleccionado;
    }

    public void setSubSegmentoSeleccionado(SubSegmento subSegmentoSeleccionado) {
        this.subSegmentoSeleccionado = subSegmentoSeleccionado;
    }

    public Potencial getPotencialSeleccionado() {
        return potencialSeleccionado;
    }

    public void setPotencialSeleccionado(Potencial potencialSeleccionado) {
        this.potencialSeleccionado = potencialSeleccionado;
    }

    public Distribuidor getDistribuidorSeleccionado() {
        return distribuidorSeleccionado;
    }

    public void setDistribuidorSeleccionado(Distribuidor distribuidorSeleccionado) {
        this.distribuidorSeleccionado = distribuidorSeleccionado;
    }

    public Departamento getDepartamentoDistribuidor() {
        return departamentoDistribuidor;
    }

    public void setDepartamentoDistribuidor(Departamento departamentoDistribuidor) {
        this.departamentoDistribuidor = departamentoDistribuidor;
    }

    public Municipio getMunicipioDistribuidor() {
        return municipioDistribuidor;
    }

    public void setMunicipioDistribuidor(Municipio municipioDistribuidor) {
        this.municipioDistribuidor = municipioDistribuidor;
    }

    public List<Potencial> getListaPotencial() {
        return listaPotencial;
    }

    public void setListaPotencial(List<Potencial> listaPotencial) {
        this.listaPotencial = listaPotencial;
    }

    public List<Distribuidor> getListaDistribuidor() {
        return listaDistribuidor;
    }

    public void setListaDistribuidor(List<Distribuidor> listaDistribuidor) {
        this.listaDistribuidor = listaDistribuidor;
    }

    public DiageoApplicationBean getDiageoApplicationBean() {
        return diageoApplicationBean;
    }

    public void setDiageoApplicationBean(DiageoApplicationBean diageoApplicationBean) {
        this.diageoApplicationBean = diageoApplicationBean;
    }

    public Departamento getDepartamentoOutlet() {
        return departamentoOutlet;
    }

    public void setDepartamentoOutlet(Departamento departamentoOutlet) {
        this.departamentoOutlet = departamentoOutlet;
    }

    public Municipio getMunicipioOutlet() {
        return municipioOutlet;
    }

    public void setMunicipioOutlet(Municipio municipioOutlet) {
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

    public TipoDoc getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDoc tipoDocumento) {
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

    public PotencialBeanLocal getPotencialBeanLocal() {
        return potencialBeanLocal;
    }

    public void setPotencialBeanLocal(PotencialBeanLocal potencialBeanLocal) {
        this.potencialBeanLocal = potencialBeanLocal;
    }

    public DistributorBeanLocal getDistribuidorBeanLocal() {
        return distribuidorBeanLocal;
    }

    public void setDistribuidorBeanLocal(DistributorBeanLocal distribuidorBeanLocal) {
        this.distribuidorBeanLocal = distribuidorBeanLocal;
    }

    public List<Battleground> getListaBattleground() {
        return listaBattleground;
    }

    public void setListaBattleground(List<Battleground> listaBattleground) {
        this.listaBattleground = listaBattleground;
    }

    public Battleground getBattlegroundSeleccionado() {
        return battlegroundSeleccionado;
    }

    public void setBattlegroundSeleccionado(Battleground battlegroundSeleccionado) {
        this.battlegroundSeleccionado = battlegroundSeleccionado;
    }

}
