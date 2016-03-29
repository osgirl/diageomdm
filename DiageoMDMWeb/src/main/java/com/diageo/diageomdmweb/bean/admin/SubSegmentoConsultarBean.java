/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageonegocio.EstadosDiageo;
import com.diageo.diageonegocio.beans.SubSegmentoBeanLocal;
import com.diageo.diageonegocio.entidades.Segmento;
import com.diageo.diageonegocio.entidades.SubSegmento;
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
    private List<SubSegmento> listaSubSegmentos;
    private Segmento segmento;
    private SubSegmento subSegmentoSeleccionado;

    /**
     * Creates a new instance of SubSegmentoBean
     */
    public SubSegmentoConsultarBean() {
    }

    @PostConstruct
    @Override
    public void init() {
        setListaSubSegmentos(subSegmentoBeanLocal.consultarTodosSubSegmentos());
        super.init();
    }

    public void listenerSegmento() {
        if (super.getListaSubCanales() != null && !super.getListaCanales().isEmpty()) {
            List<Segmento> segementoTemporal = super.segmentoBeanLocal.consultarPorSubChannel(super.getSubCanal().getIdsubchannel());
            if (segementoTemporal != null && !segementoTemporal.isEmpty()) {
                super.setListaSegmento(segementoTemporal);
            } else {
                super.setListaSegmento(new ArrayList<Segmento>());
            }
        } else {
            super.setListaSegmento(new ArrayList<Segmento>());
        }
    }

    public void detalle(SubSegmento subSegmento) {
        setSubSegmentoSeleccionado(subSegmento);
        super.setVerDetalle(Boolean.FALSE);
        super.setNombre(subSegmento.getNomnbre());
        super.setEstado(subSegmento.getEstado().equals(EstadosDiageo.ACTIVO.getId()));
        super.setCanal(subSegmento.getIdsegmento().getIdsubchannel().getChannelIdchannel());
        super.setSubCanal(subSegmento.getIdsegmento().getIdsubchannel());
        setSegmento(subSegmento.getIdsegmento());
        super.listenerListaSubCanales();
        listenerSegmento();
    }

    @Override
    public void guardarCambios() {
        try {            
            getSubSegmentoSeleccionado().setNomnbre(super.getNombre());
            getSubSegmentoSeleccionado().setEstado(super.isEstado() ? EstadosDiageo.ACTIVO.getId() : EstadosDiageo.INACTIVO.getId());
            getSubSegmentoSeleccionado().setIdsegmento(getSegmento());
            subSegmentoBeanLocal.modificarSubSegmento(getSubSegmentoSeleccionado());
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoNegocioException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        }
    }

    /**
     * @return the listaSubSegmentos
     */
    public List<SubSegmento> getListaSubSegmentos() {
        return listaSubSegmentos;
    }

    /**
     * @param listaSubSegmentos the listaSubSegmentos to set
     */
    public void setListaSubSegmentos(List<SubSegmento> listaSubSegmentos) {
        this.listaSubSegmentos = listaSubSegmentos;
    }

    /**
     * @return the subSegmentoSeleccionado
     */
    public SubSegmento getSubSegmentoSeleccionado() {
        return subSegmentoSeleccionado;
    }

    /**
     * @param subSegmentoSeleccionado the subSegmentoSeleccionado to set
     */
    public void setSubSegmentoSeleccionado(SubSegmento subSegmentoSeleccionado) {
        this.subSegmentoSeleccionado = subSegmentoSeleccionado;
    }

    /**
     * @return the segmento
     */
    public Segmento getSegmento() {
        return segmento;
    }

    /**
     * @param segmento the segmento to set
     */
    public void setSegmento(Segmento segmento) {
        this.segmento = segmento;
    }

}
