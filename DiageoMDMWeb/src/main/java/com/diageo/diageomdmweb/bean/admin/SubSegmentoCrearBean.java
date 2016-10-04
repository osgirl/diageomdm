/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.enums.StateDiageo;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author yovanoty126
 */
@Named(value = "subSegmentoCrearBean")
@ViewScoped
public class SubSegmentoCrearBean extends SubSegmentoConsultarBean implements Serializable {

    private static final String ATHENA_CODE = "SUBSEGMENT";
    private static final Logger LOG = Logger.getLogger(SubSegmentoCrearBean.class.getName());

    /**
     * Creates a new instance of SubSegmentoCrearBean
     */
    public SubSegmentoCrearBean() {
    }

    @Override
    @PostConstruct
    public void init() {
        setSubCanal(new DbSubChannels());
        setSegmento(new DbSegments());
        setListaCanales(channelBeanLocal.findAllChannel());
        if (getListaCanales() != null && !getListaCanales().isEmpty()) {
            setCanal(getListaCanales().get(0));
            super.listenerListaSubCanales();
            if (getListaSubCanales() != null && !getListaSubCanales().isEmpty()) {
                setSubCanal(getListaSubCanales().get(0));
                super.listenerSegmento();
            } else {
                setListaSubCanales(new ArrayList<DbSubChannels>());
                setListaSegmento(new ArrayList<DbSegments>());
            }
        } else {
            setListaCanales(new ArrayList<DbChannels>());
        }
    }

    @Override
    public void listenerListaSubCanales() {
        System.out.println("entro");
        super.listenerListaSubCanales();
        if (getListaSubCanales() != null && !getListaSubCanales().isEmpty()) {
            setSubCanal(getListaSubCanales().get(0));
            super.listenerSegmento();
        } else {
            setListaSegmento(new ArrayList<DbSegments>());
        }
    }

    @Override
    public void guardarCambios() {
        setSubSegmentoSeleccionado(new DbSubSegments());
        try {
            getSubSegmentoSeleccionado().setNameSubsegment(super.getNombre().toUpperCase());
            getSubSegmentoSeleccionado().setStateSubSegment(super.isEstado() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
            getSubSegmentoSeleccionado().setSegmentId(getSegmento());
            subSegmentoBeanLocal.createSubSegment(getSubSegmentoSeleccionado());
            if (getSubSegmentoSeleccionado().getSubSegmentId() != null) {
                if (getSubSegmentoSeleccionado().getSubSegmentId() < 10) {
                    getSubSegmentoSeleccionado().setDistri_1(ATHENA_CODE + "0" + getSubSegmentoSeleccionado().getSubSegmentId());
                } else {
                    getSubSegmentoSeleccionado().setDistri_1(ATHENA_CODE + getSubSegmentoSeleccionado().getSubSegmentId());
                }
                subSegmentoBeanLocal.updateSubSegment(getSubSegmentoSeleccionado());
            }
            init();
            setNombre("");
            setAthenaCode("");
            setEstado(Boolean.FALSE);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        }

    }

}
