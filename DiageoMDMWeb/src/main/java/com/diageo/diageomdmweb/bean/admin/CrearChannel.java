/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageonegocio.enums.StateDiageo;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.entidades.Audit;
import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Named(value = "crearChannel")
@ViewScoped
public class CrearChannel extends DiageoRootBean implements Serializable {

    private static final String ATHENA_CODE = "CHANN";    
    private static final Logger LOG = Logger.getLogger(CrearChannel.class.getName());
    @Inject
    private LoginBean loginBean;
    @EJB
    private ChannelBeanLocal channelBeanLocal;
    @Size(max = 100, message = "{size.invalido}")
    private String nombreChannel;
    private String athenaCode;
    private boolean estado;

    /**
     * Creates a new instance of CrearChannel
     */
    public CrearChannel() {
    }

    @PostConstruct
    public void init() {
        setNombreChannel("");
        setAthenaCode("");
        setEstado(Boolean.FALSE);
    }

    public void createChannel() {
        try {
            DbChannels channel = new DbChannels();
            channel.setNameChannel(getNombreChannel().toUpperCase());
            channel.setStateChannel(isEstado() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
            Audit audit = new Audit();
            audit.setCreationDate(super.getCurrentDate());
            audit.setCreationUser(getLoginBean().getUsuario().getEmailUser());
            channel.setAudit(audit);
            channelBeanLocal.createChannel(channel);
            if (channel.getChannelId() != null) {
                if (channel.getChannelId() < 10) {
                    channel.setDistri_1(ATHENA_CODE + "0" + channel.getChannelId());
                } else {
                    channel.setDistri_1(ATHENA_CODE + channel.getChannelId());
                }
                channelBeanLocal.updateChannel(channel);
            }
            init();
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
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

    /**
     * @return the loginBean
     */
    public LoginBean getLoginBean() {
        return loginBean;
    }

}
