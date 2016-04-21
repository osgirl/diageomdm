/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.enums.EstadosDiageo;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.entidades.Channel;
import com.diageo.diageonegocio.entidades.SubChannel;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.io.Serializable;
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
@Named(value = "subChannelCrear")
@ViewScoped
public class SubChannelCrear extends DiageoRootBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(SubChannelCrear.class.getName());
    @EJB
    private SubChannelBeanLocal subChannelBeanLocal;
    @EJB
    private ChannelBeanLocal channelBeanLocal;
    private List<Channel> listaChannel;
    @Size(max = 100, message = "{size.invalido}")
    private String nombreSubChannel;
    private boolean estado;
    private Channel channel;

    /**
     * Creates a new instance of SubChannelCrear
     */
    public SubChannelCrear() {
    }

    @PostConstruct
    public void init() {
        setListaChannel(channelBeanLocal.consultarTodosChannel());
        inicializarCampos();
    }

    private void inicializarCampos() {
        setNombreSubChannel("");
        setEstado(Boolean.FALSE);
        if (getListaChannel() != null && !getListaChannel().isEmpty()) {
            setChannel(getListaChannel().get(0));
        }else{
            setChannel(new Channel());
        }        
    }
    
    public void guardarSubCanal(){
        try {
            SubChannel sc=new SubChannel();
            sc.setNombre(getNombreSubChannel());
            sc.setEstado(isEstado()?EstadosDiageo.ACTIVO.getId():EstadosDiageo.INACTIVO.getId());
            sc.setChannelIdchannel(getChannel());
            subChannelBeanLocal.crearSubChannel(sc);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
            inicializarCampos();
        } catch (DiageoNegocioException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    /**
     * @return the listaChannel
     */
    public List<Channel> getListaChannel() {
        return listaChannel;
    }

    /**
     * @param listaChannel the listaChannel to set
     */
    public void setListaChannel(List<Channel> listaChannel) {
        this.listaChannel = listaChannel;
    }

    /**
     * @return the nombreSubChannel
     */
    public String getNombreSubChannel() {
        return nombreSubChannel;
    }

    /**
     * @param nombreSubChannel the nombreSubChannel to set
     */
    public void setNombreSubChannel(String nombreSubChannel) {
        this.nombreSubChannel = nombreSubChannel;
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
     * @return the channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}
