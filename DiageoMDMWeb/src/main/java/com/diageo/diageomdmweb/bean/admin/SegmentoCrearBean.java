/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.EstadosDiageo;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.SegmentoBeanLocal;
import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.entidades.Channel;
import com.diageo.diageonegocio.entidades.Segmento;
import com.diageo.diageonegocio.entidades.SubChannel;
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
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Named(value = "segmentoCrearBean")
@ViewScoped
public class SegmentoCrearBean extends DiageoRootBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(SegmentoCrearBean.class.getName());
    @EJB
    private SegmentoBeanLocal segmentoBeanLocal;
    @EJB
    private ChannelBeanLocal channelBeanLocal;
    @EJB
    private SubChannelBeanLocal subChannelBeanLocal;
    private List<Channel> listaCanales;
    private List<SubChannel> listaSubCanales;
    @Size(max = 100, message = "{size.invalido}")
    private String nombre;
    private Channel canal;
    private SubChannel subCanal;
    private boolean estado;

    /**
     * Creates a new instance of SegmentoCrear
     */
    public SegmentoCrearBean() {
    }

    @PostConstruct
    public void init() {
        setListaCanales(channelBeanLocal.consultarTodosChannel());
        inicializarCampos();

    }

    public void listenerListaSubCanales() {
        if (getListaCanales() != null && !getListaCanales().isEmpty()) {
            List<SubChannel> listaSubChaTemporal = subChannelBeanLocal.consultarSubChannelPorChannel(getCanal().getIdchannel());
            if (listaSubChaTemporal != null && !listaSubChaTemporal.isEmpty()) {
                setListaSubCanales(listaSubChaTemporal);
            } else {
                setListaSubCanales(new ArrayList<SubChannel>());
            }
        } else {
            setListaSubCanales(new ArrayList<SubChannel>());
        }
    }

    public void guardarCambios() {
        try {
            Segmento seg = new Segmento();
            seg.setNombre(getNombre());
            seg.setEstado(isEstado() ? EstadosDiageo.ACTIVO.getId() : EstadosDiageo.INACTIVO.getId());
            seg.setIdsubchannel(getSubCanal());
            segmentoBeanLocal.crearSegmento(seg);
            inicializarCampos();
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoNegocioException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        }
    }

    private void inicializarCampos() {
        setNombre("");
        setEstado(Boolean.FALSE);
        if (getListaCanales() != null && !listaCanales.isEmpty()) {
            setCanal(getListaCanales().get(0));
        }
        listenerListaSubCanales();
    }

    /**
     * @return the listaCanales
     */
    public List<Channel> getListaCanales() {
        return listaCanales;
    }

    /**
     * @param listaCanales the listaCanales to set
     */
    public void setListaCanales(List<Channel> listaCanales) {
        this.listaCanales = listaCanales;
    }

    /**
     * @return the listaSubCanales
     */
    public List<SubChannel> getListaSubCanales() {
        return listaSubCanales;
    }

    /**
     * @param listaSubCanales the listaSubCanales to set
     */
    public void setListaSubCanales(List<SubChannel> listaSubCanales) {
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
     * @return the canal
     */
    public Channel getCanal() {
        return canal;
    }

    /**
     * @param canal the canal to set
     */
    public void setCanal(Channel canal) {
        this.canal = canal;
    }

    /**
     * @return the subCanal
     */
    public SubChannel getSubCanal() {
        return subCanal;
    }

    /**
     * @param subCanal the subCanal to set
     */
    public void setSubCanal(SubChannel subCanal) {
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

}
