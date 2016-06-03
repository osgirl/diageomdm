/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageonegocio.enums.StateDiageo;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
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
@Named(value = "consultarSubChannel")
@ViewScoped
public class ConsultarSubChannelBean extends DiageoRootBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(ConsultarSubChannelBean.class.getName());
    @EJB
    private SubChannelBeanLocal subChannelBeanLocal;
    @EJB
    private ChannelBeanLocal channelBeanLocal;
    private List<DbSubChannels> listaSubChannel;
    private List<DbChannels> listaChannel;
    private DbSubChannels subChannelSeleccionado;
    private DbChannels channel;
    @Size(max = 100, message = "{size.invalido}")
    private String nombreSubChannel;
    private boolean verDetalle;
    private boolean estado;

    /**
     * Creates a new instance of ConsultarSubChannel
     */
    public ConsultarSubChannelBean() {
    }

    @PostConstruct
    public void init() {
        setVerDetalle(Boolean.TRUE);
        setListaChannel(channelBeanLocal.findAllChannel());
        consultarSubChannel();
    }

    public void consultarSubChannel() {
        setListaSubChannel(subChannelBeanLocal.consultarTodosSubChannel());
    }

    public void detalleSubChannel(DbSubChannels sc) {       
        setNombreSubChannel(sc.getNameSubChannel());
        setEstado(sc.getStateSubChannel().equals(StateDiageo.ACTIVO.getId()));
        setChannel(sc.getChannelId());
        setSubChannelSeleccionado(sc);
         setVerDetalle(Boolean.FALSE);
    }
    
    public void modificarChannel() {
        try {
            getSubChannelSeleccionado().setStateSubChannel(isEstado() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
            getSubChannelSeleccionado().setNameSubChannel(getNombreSubChannel());
            getSubChannelSeleccionado().setChannelId(getChannel());
            subChannelBeanLocal.modificarSubChannel(getSubChannelSeleccionado());
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void regresar() {
        setSubChannelSeleccionado(null);
        setChannel(null);
        setEstado(Boolean.FALSE);
        setVerDetalle(Boolean.TRUE);
    }

    /**
     * @return the listaSubChannel
     */
    public List<DbSubChannels> getListaSubChannel() {
        return listaSubChannel;
    }

    /**
     * @param listaSubChannel the listaSubChannel to set
     */
    public void setListaSubChannel(List<DbSubChannels> listaSubChannel) {
        this.listaSubChannel = listaSubChannel;
    }

    /**
     * @return the subChannelSeleccionado
     */
    public DbSubChannels getSubChannelSeleccionado() {
        return subChannelSeleccionado;
    }

    /**
     * @param subChannelSeleccionado the subChannelSeleccionado to set
     */
    public void setSubChannelSeleccionado(DbSubChannels subChannelSeleccionado) {
        this.subChannelSeleccionado = subChannelSeleccionado;
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
    public DbChannels getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(DbChannels channel) {
        this.channel = channel;
    }

    /**
     * @return the listaChannel
     */
    public List<DbChannels> getListaChannel() {
        return listaChannel;
    }

    /**
     * @param listaChannel the listaChannel to set
     */
    public void setListaChannel(List<DbChannels> listaChannel) {
        this.listaChannel = listaChannel;
    }

}
