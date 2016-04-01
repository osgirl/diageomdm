/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageonegocio.EstadosDiageo;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.entidades.Channel;
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
@Named(value = "consultarChannelBean")
@ViewScoped
public class ConsultarChannelBean extends DiageoRootBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(ConsultarChannelBean.class.getName());
    @EJB
    private ChannelBeanLocal channelBeanLocal;
    private List<Channel> listaChannels;
    private Channel channelSeleccionado;
    @Size(max = 100, message = "{size.invalido}")
    private String nombreChannel;
    private boolean verDetalle;
    private boolean estado;

    /**
     * Creates a new instance of ConsultarChannelBean
     */
    public ConsultarChannelBean() {
    }

    @PostConstruct
    public void init() {
        setVerDetalle(Boolean.TRUE);
        consultarListaChannel();

    }

    public void consultarListaChannel() {
        setListaChannels(channelBeanLocal.consultarTodosChannel());
    }

    public void detalleChannel(Channel cha) {
        setChannelSeleccionado(cha);
        setNombreChannel(cha.getNombre());
        setEstado(cha.getEstado().equals(EstadosDiageo.ACTIVO.getId()));
        setVerDetalle(Boolean.FALSE);
    }

    public void modificarChannel() {
        try {
            getChannelSeleccionado().setEstado(isEstado() ? EstadosDiageo.ACTIVO.getId() : EstadosDiageo.INACTIVO.getId());
            getChannelSeleccionado().setNombre(getNombreChannel());
            channelBeanLocal.modificarChannel(getChannelSeleccionado());
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoNegocioException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void regresar() {
        setChannelSeleccionado(null);
        setEstado(Boolean.FALSE);
        setVerDetalle(Boolean.TRUE);
    }

    /**
     * @return the listaChannels
     */
    public List<Channel> getListaChannels() {
        return listaChannels;
    }

    /**
     * @param listaChannels the listaChannels to set
     */
    public void setListaChannels(List<Channel> listaChannels) {
        this.listaChannels = listaChannels;
    }

    /**
     * @return the channelSeleccionado
     */
    public Channel getChannelSeleccionado() {
        return channelSeleccionado;
    }

    /**
     * @param channelSeleccionado the channelSeleccionado to set
     */
    public void setChannelSeleccionado(Channel channelSeleccionado) {
        this.channelSeleccionado = channelSeleccionado;
    }

    public boolean isVerDetalle() {
        return verDetalle;
    }

    public void setVerDetalle(boolean verDetalle) {
        this.verDetalle = verDetalle;
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

    public String getNombreChannel() {
        return nombreChannel;
    }

    public void setNombreChannel(String nombreChannel) {
        this.nombreChannel = nombreChannel;
    }

}
