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
@Named(value = "segmentoCrearBean")
@ViewScoped
public class SegmentoCrearBean extends DiageoRootBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(SegmentoCrearBean.class.getName());
    @EJB
    private SegmentBeanLocal segmentoBeanLocal;
    @EJB
    private ChannelBeanLocal channelBeanLocal;
    @EJB
    private SubChannelBeanLocal subChannelBeanLocal;
    private List<DbChannels> listaCanales;
    private List<DbSubChannels> listaSubCanales;
    @Size(max = 100, message = "{size.invalido}")
    private String nombre;
    private DbChannels canal;
    private DbSubChannels subCanal;
    private boolean estado;

    /**
     * Creates a new instance of SegmentoCrear
     */
    public SegmentoCrearBean() {
    }

    @PostConstruct
    public void init() {
        setListaCanales(channelBeanLocal.findAllChannel());
        inicializarCampos();

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

    public void guardarCambios() {
        try {
            DbSegments seg = new DbSegments();
            seg.setNameSegment(getNombre());
            seg.setStateSegment(isEstado() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
            seg.setSubChannelId(getSubCanal());
            segmentoBeanLocal.createSegment(seg);
            inicializarCampos();
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
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

}
