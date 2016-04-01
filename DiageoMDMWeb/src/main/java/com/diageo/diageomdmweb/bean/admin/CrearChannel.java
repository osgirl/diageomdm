/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.EstadosDiageo;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.entidades.Channel;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.io.Serializable;
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
@Named(value = "crearChannel")
@ViewScoped
public class CrearChannel extends DiageoRootBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(CrearChannel.class.getName());
    @EJB
    private ChannelBeanLocal channelBeanLocal;
    @Size(max = 100, message = "{size.invalido}")
    private String nombreChannel;
    private boolean estado;

    /**
     * Creates a new instance of CrearChannel
     */
    public CrearChannel() {
    }

    @PostConstruct
    public void init() {
        setNombreChannel("");
        setEstado(Boolean.FALSE);
    }

    public void crearChannel() {
        try {
            Channel channel = new Channel();
            channel.setNombre(getNombreChannel());
            channel.setEstado(isEstado() ? EstadosDiageo.ACTIVO.getId() : EstadosDiageo.INACTIVO.getId());
            channelBeanLocal.crearChannel(channel);
            init();
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoNegocioException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    /**
     * @return the nombreChannel
     */
    public String getNombreChannel() {
        return nombreChannel;
    }

    /**
     * @param nombreChannel the nombreChannel to set
     */
    public void setNombreChannel(String nombreChannel) {
        this.nombreChannel = nombreChannel;
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
