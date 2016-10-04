/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
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
@Named(value = "subChannelCrear")
@ViewScoped
public class SubChannelCrear extends DiageoRootBean implements Serializable {

    private static final String ATHENA_CODE = "SUBCHANN";
    private static final Logger LOG = Logger.getLogger(SubChannelCrear.class.getName());
    @EJB
    private SubChannelBeanLocal subChannelBeanLocal;
    @EJB
    private ChannelBeanLocal channelBeanLocal;
    private List<DbChannels> listaChannel;
    @Size(max = 100, message = "{size.invalido}")
    private String nombreSubChannel;
    private boolean estado;
    private DbChannels channel;
    private String athenaCode;

    /**
     * Creates a new instance of SubChannelCrear
     */
    public SubChannelCrear() {
    }

    @PostConstruct
    public void init() {
        setListaChannel(channelBeanLocal.findAllChannel());
        inicializarCampos();
    }

    private void inicializarCampos() {
        setNombreSubChannel("");
        setEstado(Boolean.FALSE);
        setAthenaCode("");
        if (getListaChannel() != null && !getListaChannel().isEmpty()) {
            setChannel(getListaChannel().get(0));
        } else {
            setChannel(new DbChannels());
        }
    }

    public void guardarSubCanal() {
        try {
            DbSubChannels sc = new DbSubChannels();
            sc.setNameSubChannel(getNombreSubChannel().toUpperCase());
            sc.setStateSubChannel(isEstado() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
            sc.setChannelId(getChannel());
            subChannelBeanLocal.crearSubChannel(sc);
            if (sc.getSubChannelId() != null) {
                if (sc.getSubChannelId() < 10) {
                    sc.setDistri_1(ATHENA_CODE + "0" + sc.getSubChannelId());
                } else {
                    sc.setDistri_1(ATHENA_CODE + sc.getSubChannelId());
                }
                subChannelBeanLocal.modificarSubChannel(sc);
            }
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
            inicializarCampos();
        } catch (DiageoBusinessException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
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
     * @return the athenaCode
     */
    public String getAthenaCode() {
        return athenaCode;
    }

    /**
     * @param athenaCode the athenaCode to set
     */
    public void setAthenaCode(String athenaCode) {
        this.athenaCode = athenaCode;
    }

}
