/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.PotentialBeanLocal;
import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.entidades.DbPotentials;
import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.enums.StateDiageo;
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
@Named(value = "potentialCreateBean")
@ViewScoped
public class PotentialCreateBean extends DiageoRootBean implements Serializable {

    private static final String NEW_CLIENT = "N";
    @EJB
    private ChannelBeanLocal channelBeanLocal;
    @EJB
    private PotentialBeanLocal potentialBeanLocal;
    private DbChannels channel;
    private DbSubChannels subChannel;
    private DbSegments segment;
    private DbSubSegments subSegmento;
    private String name;
    private Integer valueDefineSegment;
    private String clientType;
    private List<DbChannels> listChannel;
    private List<DbSubChannels> listSubChannel;
    private List<DbSegments> listSegment;
    private List<DbSubSegments> listSubSegment;

    /**
     * Creates a new instance of PotentialCreateBean
     */
    public PotentialCreateBean() {

    }

    @PostConstruct
    public void init() {
        initFields();
    }

    protected void initFields() {
        setName("");
        setValueDefineSegment(null);
        setClientType("");
        setListChannel(channelBeanLocal.findAllChannel());
        setChannel(getListChannel().get(0));
        setSubChannel(getChannel().getDbSubChannelsList().get(0));
        setSegment(getSubChannel().getDbSegmentsList().get(0));
        setSubSegmento(getSegment().getDbSubSegmentsList().get(0));
        setListSubChannel(getChannel().getDbSubChannelsList());
        setListSegment(getSubChannel().getDbSegmentsList());
        setListSubSegment(getSegment().getDbSubSegmentsList());
    }

    public void createPotential() {
        try {
            DbPotentials pot = new DbPotentials();
            pot.setNamePotential(getName());
            pot.setSubSegmentId(getSubSegmento());
            pot.setNewClient(getClientType().equals(NEW_CLIENT) ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
            potentialBeanLocal.createPotential(pot);
            initFields();
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(PotentialCreateBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void listenerChannel() {
        setListSubChannel(getChannel().getDbSubChannelsList());
        if (getListSubChannel() != null || !getListSubChannel().isEmpty()) {
            setSubChannel(getListSubChannel().get(0));
            this.listenerSubChannel();
        } else {
            listSubChannel = new ArrayList<>();
            listSegment = new ArrayList<>();
            listSubSegment = new ArrayList<>();
            setSubChannel(new DbSubChannels());
            setSegment(new DbSegments());
            setSubSegmento(new DbSubSegments());
        }
    }

    public void listenerSubChannel() {
        if (getSubChannel().getDbSegmentsList() != null || !getSubChannel().getDbSegmentsList().isEmpty()) {
            setSegment(getSubChannel().getDbSegmentsList().get(0));
            setListSegment(getSubChannel().getDbSegmentsList());
            this.listenerSegment();
        } else {
            listSegment = new ArrayList<>();
            listSubSegment = new ArrayList<>();
            setSegment(new DbSegments());
            setSubSegmento(new DbSubSegments());
        }
    }

    public void listenerSegment() {
        if (getSegment().getDbSubSegmentsList() != null || !getSegment().getDbSubSegmentsList().isEmpty()) {
            setSubSegmento(getSegment().getDbSubSegmentsList().get(0));
            setListSubSegment(getSegment().getDbSubSegmentsList());
        } else {
            listSubSegment = new ArrayList<>();
            setSubSegmento(new DbSubSegments());
        }
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
     * @return the subChannel
     */
    public DbSubChannels getSubChannel() {
        return subChannel;
    }

    /**
     * @param subChannel the subChannel to set
     */
    public void setSubChannel(DbSubChannels subChannel) {
        this.subChannel = subChannel;
    }

    /**
     * @return the segment
     */
    public DbSegments getSegment() {
        return segment;
    }

    /**
     * @param segment the segment to set
     */
    public void setSegment(DbSegments segment) {
        this.segment = segment;
    }

    /**
     * @return the subSegmento
     */
    public DbSubSegments getSubSegmento() {
        return subSegmento;
    }

    /**
     * @param subSegmento the subSegmento to set
     */
    public void setSubSegmento(DbSubSegments subSegmento) {
        this.subSegmento = subSegmento;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the valueDefineSegment
     */
    public Integer getValueDefineSegment() {
        return valueDefineSegment;
    }

    /**
     * @param valueDefineSegment the valueDefineSegment to set
     */
    public void setValueDefineSegment(Integer valueDefineSegment) {
        this.valueDefineSegment = valueDefineSegment;
    }

    /**
     * @return the clientType
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * @param clientType the clientType to set
     */
    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    /**
     * @return the listChannel
     */
    public List<DbChannels> getListChannel() {
        return listChannel;
    }

    /**
     * @param listChannel the listChannel to set
     */
    public void setListChannel(List<DbChannels> listChannel) {
        this.listChannel = listChannel;
    }

    /**
     * @return the listSubChannel
     */
    public List<DbSubChannels> getListSubChannel() {
        return listSubChannel;
    }

    /**
     * @param listSubChannel the listSubChannel to set
     */
    public void setListSubChannel(List<DbSubChannels> listSubChannel) {
        this.listSubChannel = listSubChannel;
    }

    /**
     * @return the listSegment
     */
    public List<DbSegments> getListSegment() {
        return listSegment;
    }

    /**
     * @param listSegment the listSegment to set
     */
    public void setListSegment(List<DbSegments> listSegment) {
        this.listSegment = listSegment;
    }

    /**
     * @return the listSubSegment
     */
    public List<DbSubSegments> getListSubSegment() {
        return listSubSegment;
    }

    /**
     * @param listSubSegment the listSubSegment to set
     */
    public void setListSubSegment(List<DbSubSegments> listSubSegment) {
        this.listSubSegment = listSubSegment;
    }

}
