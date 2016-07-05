/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.enums.StateDiageo;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.SegmentBeanLocal;
import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.entidades.DbSubChannels;
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
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Named(value = "segementoConsultarBean")
@ViewScoped
public class SegementoConsultarBean extends DiageoRootBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(SegementoConsultarBean.class.getName());
    @EJB
    protected SegmentBeanLocal segmentoBeanLocal;
    @EJB
    protected ChannelBeanLocal channelBeanLocal;
    @EJB
    protected SubChannelBeanLocal subChannelBeanLocal;
    private List<DbSegments> listaSegmento;
    private List<DbChannels> listaCanales;
    private List<DbSubChannels> listaSubCanales;
    @Size(max = 100, message = "{size.invalido}")
    private String nombre;
    private DbSegments segmentoSeleccionado;
    private DbChannels canal;
    private DbSubChannels subCanal;
    private boolean estado;
    private boolean verDetalle;
    private String athenaCode;

    /**
     * Creates a new instance of SegementoConsultarBean
     */
    public SegementoConsultarBean() {
    }

    @PostConstruct
    public void init() {
        setListaSegmento(segmentoBeanLocal.findAllSegment());
        setListaCanales(channelBeanLocal.findAllChannel());
        setVerDetalle(Boolean.TRUE);
        inicializarCampos();
    }

    private void inicializarCampos() {
        setNombre("");
        setEstado(Boolean.FALSE);
    }

    public void detalle(DbSegments seg) {
        setSegmentoSeleccionado(seg);
        setNombre(seg.getNameSegment());
        setEstado(seg.getStateSegment().equals(StateDiageo.ACTIVO.getId()));
        setCanal(seg.getSubChannelId().getChannelId());
        setAthenaCode(seg.getDistri_1());
        listenerListaSubCanales();
        setSubCanal(seg.getSubChannelId());
        setVerDetalle(Boolean.FALSE);
    }

    public void guardarCambios() {
        try {
            getSegmentoSeleccionado().setNameSegment(getNombre().toUpperCase());
            getSegmentoSeleccionado().setStateSegment(isEstado() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
            getSegmentoSeleccionado().setSubChannelId(getSubCanal());
            getSegmentoSeleccionado().setDistri_1(getAthenaCode().toUpperCase());
            segmentoBeanLocal.updateSegment(getSegmentoSeleccionado());
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void listenerListaSubCanales() {
        if (getListaCanales() != null && !getListaCanales().isEmpty()) {
            List<DbSubChannels> listaSubChaTemporal = subChannelBeanLocal.consultarSubChannelPorChannel(getCanal().getChannelId());
            if (listaSubChaTemporal != null && !listaSubChaTemporal.isEmpty()) {
                setListaSubCanales(listaSubChaTemporal);
            } else {
                setListaSubCanales(new ArrayList<DbSubChannels>());
            }
        } else {
            setListaSubCanales(new ArrayList<DbSubChannels>());
        }
    }

    public void regresar() {
        inicializarCampos();
        setVerDetalle(Boolean.TRUE);
    }

    /**
     * @return the listaSegmento
     */
    public List<DbSegments> getListaSegmento() {
        return listaSegmento;
    }

    /**
     * @param listaSegmento the listaSegmento to set
     */
    public void setListaSegmento(List<DbSegments> listaSegmento) {
        this.listaSegmento = listaSegmento;
    }

    /**
     * @return the listaCanales
     */
    public List<DbChannels> getListaCanales() {
        return listaCanales;
    }

    /**
     * @param listaCanales the listaCanales to set
     */
    public void setListaCanales(List<DbChannels> listaCanales) {
        this.listaCanales = listaCanales;
    }

    /**
     * @return the listaSubCanales
     */
    public List<DbSubChannels> getListaSubCanales() {
        return listaSubCanales;
    }

    /**
     * @param listaSubCanales the listaSubCanales to set
     */
    public void setListaSubCanales(List<DbSubChannels> listaSubCanales) {
        this.listaSubCanales = listaSubCanales;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the segmentoSeleccionado
     */
    public DbSegments getSegmentoSeleccionado() {
        return segmentoSeleccionado;
    }

    /**
     * @param segmentoSeleccionado the segmentoSeleccionado to set
     */
    public void setSegmentoSeleccionado(DbSegments segmentoSeleccionado) {
        this.segmentoSeleccionado = segmentoSeleccionado;
    }

    /**
     * @return the canal
     */
    public DbChannels getCanal() {
        return canal;
    }

    /**
     * @param canal the canal to set
     */
    public void setCanal(DbChannels canal) {
        this.canal = canal;
    }

    /**
     * @return the subCanal
     */
    public DbSubChannels getSubCanal() {
        return subCanal;
    }

    /**
     * @param subCanal the subCanal to set
     */
    public void setSubCanal(DbSubChannels subCanal) {
        this.subCanal = subCanal;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the verDetalle
     */
    public boolean isVerDetalle() {
        return verDetalle;
    }

    /**
     * @param verDetalle the verDetalle to set
     */
    public void setVerDetalle(boolean verDetalle) {
        this.verDetalle = verDetalle;
    }

    /**
     * @return the athenaCode
     */
    public String getAthenaCode() {
        return athenaCode;
    }

    /**
     * @param athenaCode the athenaCode to set
     */
    public void setAthenaCode(String athenaCode) {
        this.athenaCode = athenaCode;
    }

}
