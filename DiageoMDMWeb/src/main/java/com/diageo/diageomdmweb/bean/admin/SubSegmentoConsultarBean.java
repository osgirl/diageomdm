/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageonegocio.enums.StateDiageo;
import com.diageo.diageonegocio.beans.SubSegmentoBeanLocal;
import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.entidades.DbSubSegments;
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

/**
 *
 * @author yovanoty126
 */
@Named(value = "subSegmentoConsultarBean")
@ViewScoped
public class SubSegmentoConsultarBean extends SegementoConsultarBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(SubSegmentoConsultarBean.class.getName());
    @EJB
    private SubSegmentoBeanLocal subSegmentoBeanLocal;
    private List<DbSubSegments> listaSubSegmentos;
    private DbSegments segmento;
    private DbSubSegments subSegmentoSeleccionado;

    /**
     * Creates a new instance of SubSegmentoBean
     */
    public SubSegmentoConsultarBean() {
    }

    @PostConstruct
    @Override
    public void init() {
        setListaSubSegmentos(subSegmentoBeanLocal.findAllSubSegment());
        super.init();
    }

    public void listenerSegmento() {
        if (super.getListaSubCanales() != null && !super.getListaCanales().isEmpty()) {
            List<DbSegments> segementoTemporal = super.segmentoBeanLocal.findBySubChannel(super.getSubCanal().getSubChannelId());
            if (segementoTemporal != null && !segementoTemporal.isEmpty()) {
                super.setListaSegmento(segementoTemporal);
            } else {
                super.setListaSegmento(new ArrayList<DbSegments>());
            }
        } else {
            super.setListaSegmento(new ArrayList<DbSegments>());
        }
    }

    public void detalle(DbSubSegments subSegmento) {
        setSubSegmentoSeleccionado(subSegmento);
        super.setVerDetalle(Boolean.FALSE);
        super.setNombre(subSegmento.getNameSubsegment());
        super.setEstado(subSegmento.getStateSubSegment().equals(StateDiageo.ACTIVO.getId()));
        super.setCanal(subSegmento.getSegmentId().getSubChannelId().getChannelId());
        super.setSubCanal(subSegmento.getSegmentId().getSubChannelId());
        super.setAthenaCode(subSegmento.getDistri_1());
        setSegmento(subSegmento.getSegmentId());
        super.listenerListaSubCanales();
        listenerSegmento();
    }

    @Override
    public void guardarCambios() {
        try {            
            getSubSegmentoSeleccionado().setNameSubsegment(super.getNombre().toUpperCase());
            getSubSegmentoSeleccionado().setStateSubSegment(super.isEstado() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
            getSubSegmentoSeleccionado().setSegmentId(getSegmento());
            getSubSegmentoSeleccionado().setDistri_1(getAthenaCode().toUpperCase());
            subSegmentoBeanLocal.updateSubSegment(getSubSegmentoSeleccionado());
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        }
    }

    /**
     * @return the listaSubSegmentos
     */
    public List<DbSubSegments> getListaSubSegmentos() {
        return listaSubSegmentos;
    }

    /**
     * @param listaSubSegmentos the listaSubSegmentos to set
     */
    public void setListaSubSegmentos(List<DbSubSegments> listaSubSegmentos) {
        this.listaSubSegmentos = listaSubSegmentos;
    }

    /**
     * @return the subSegmentoSeleccionado
     */
    public DbSubSegments getSubSegmentoSeleccionado() {
        return subSegmentoSeleccionado;
    }

    /**
     * @param subSegmentoSeleccionado the subSegmentoSeleccionado to set
     */
    public void setSubSegmentoSeleccionado(DbSubSegments subSegmentoSeleccionado) {
        this.subSegmentoSeleccionado = subSegmentoSeleccionado;
    }

    /**
     * @return the segmento
     */
    public DbSegments getSegmento() {
        return segmento;
    }

    /**
     * @param segmento the segmento to set
     */
    public void setSegmento(DbSegments segmento) {
        this.segmento = segmento;
    }

}
