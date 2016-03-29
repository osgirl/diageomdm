/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageonegocio.entidades.Channel;
import com.diageo.diageonegocio.entidades.Segmento;
import com.diageo.diageonegocio.entidades.SubChannel;
import com.diageo.diageonegocio.entidades.SubSegmento;
import java.io.Serializable;
import java.util.ArrayList;
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

    /**
     * Creates a new instance of SubSegmentoCrearBean
     */
    public SubSegmentoCrearBean() {
    }

    @Override
    @PostConstruct
    public void init() {
        setSubCanal(new SubChannel());
        setSegmento(new Segmento());
        setListaCanales(channelBeanLocal.consultarTodosChannel());
        if (getListaCanales() != null && !getListaCanales().isEmpty()) {
            setCanal(getListaCanales().get(0));
            super.listenerListaSubCanales();
            if (getListaSubCanales() != null && !getListaSubCanales().isEmpty()) {
                setSubCanal(getListaSubCanales().get(0));
                super.listenerSegmento();
            } else {
                setListaSubCanales(new ArrayList<SubChannel>());
                setListaSegmento(new ArrayList<Segmento>());
            }
        } else {
            setListaCanales(new ArrayList<Channel>());
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
            setListaSegmento(new ArrayList<Segmento>());
        }
    }
    
    @Override
    public void guardarCambios(){
        setSubSegmentoSeleccionado(new SubSegmento());
        super.guardarCambios();
        setNombre("");
        setEstado(Boolean.FALSE);
    }

}
