/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.PotentialBeanLocal;
import com.diageo.diageonegocio.entidades.Channel;
import com.diageo.diageonegocio.entidades.Potential;
import com.diageo.diageonegocio.entidades.Segmento;
import com.diageo.diageonegocio.entidades.SubChannel;
import com.diageo.diageonegocio.entidades.SubSegmento;
import com.diageo.diageonegocio.enums.EstadosDiageo;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.io.Serializable;
import java.math.BigInteger;
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

    public static final String NEW_CLIENT = "N";
    public static final String OLD_CLIENT = "O";
    public static final String EXCLUSIVE = "EX";
    public static final String INCLUSIVE = "IN";

    @EJB
    private ChannelBeanLocal channelBeanLocal;
    @EJB
    private PotentialBeanLocal potentialBeanLocal;
    private Channel channel;
    private SubChannel subChannel;
    private Segmento segment;
    private SubSegmento subSegmento;
    private String name;
    private Integer initialValue;
    private Integer finalValue;
    private Integer valueDefineSegment;
    private String clientType;
    private String clusive;
    private boolean range;
    private List<Channel> listChannel;
    private List<SubChannel> listSubChannel;
    private List<Segmento> listSegment;
    private List<SubSegmento> listSubSegment;

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
        setInitialValue(null);
        setFinalValue(null);
        setValueDefineSegment(null);
        setClientType("");
        setRange(Boolean.FALSE);
        setListChannel(channelBeanLocal.consultarTodosChannel());
        setChannel(getListChannel().get(0));
        setSubChannel(getChannel().getSubChannelList().get(0));
        setSegment(getSubChannel().getSegmentoList().get(0));
        setSubSegmento(getSegment().getSubSegmentoList().get(0));
        setListSubChannel(getChannel().getSubChannelList());
        setListSegment(getSubChannel().getSegmentoList());
        setListSubSegment(getSegment().getSubSegmentoList());
        setClusive(EXCLUSIVE);
    }

    public void createPotential() {
        try {
            Potential pot = new Potential();            
            pot.setNamePot(getName());
            pot.setExclusive(getClusive().equals(EXCLUSIVE) ? EstadosDiageo.ACTIVO.getId() : EstadosDiageo.INACTIVO.getId());
            pot.setInclusive(getClusive().equals(INCLUSIVE) ? EstadosDiageo.ACTIVO.getId() : EstadosDiageo.INACTIVO.getId());
            pot.setIdSubSegment(getSubSegmento());
            pot.setIsNewClient(getClientType().equals(NEW_CLIENT) ? EstadosDiageo.ACTIVO.getId() : EstadosDiageo.INACTIVO.getId());
            pot.setRangePot(isRange() ? EstadosDiageo.ACTIVO.getId() : EstadosDiageo.INACTIVO.getId());
            if (!isRange()) {
                pot.setValueFinal(new BigInteger(getValueDefineSegment().toString()));
            } else {
                pot.setValueInitial(new BigInteger(getInitialValue().toString()));
                pot.setValueFinal(new BigInteger(getFinalValue().toString()));
            }
            potentialBeanLocal.createPotential(pot);
            initFields();
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));

        } catch (DiageoNegocioException ex) {
            Logger.getLogger(PotentialCreateBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void listenerChannel() {
        setListSubChannel(getChannel().getSubChannelList());
        setSubChannel(getListSubChannel().get(0));
        this.listenerSubChannel();
    }

    public void listenerSubChannel() {
        setSegment(getSubChannel().getSegmentoList().get(0));
        setListSegment(getSubChannel().getSegmentoList());
        this.listenerSegment();
    }

    public void listenerSegment() {
        setSubSegmento(getSegment().getSubSegmentoList().get(0));
        setListSubSegment(getSegment().getSubSegmentoList());
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

    /**
     * @return the subChannel
     */
    public SubChannel getSubChannel() {
        return subChannel;
    }

    /**
     * @param subChannel the subChannel to set
     */
    public void setSubChannel(SubChannel subChannel) {
        this.subChannel = subChannel;
    }

    /**
     * @return the segment
     */
    public Segmento getSegment() {
        return segment;
    }

    /**
     * @param segment the segment to set
     */
    public void setSegment(Segmento segment) {
        this.segment = segment;
    }

    /**
     * @return the subSegmento
     */
    public SubSegmento getSubSegmento() {
        return subSegmento;
    }

    /**
     * @param subSegmento the subSegmento to set
     */
    public void setSubSegmento(SubSegmento subSegmento) {
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
     * @return the initialValue
     */
    public Integer getInitialValue() {
        return initialValue;
    }

    /**
     * @param initialValue the initialValue to set
     */
    public void setInitialValue(Integer initialValue) {
        this.initialValue = initialValue;
    }

    /**
     * @return the finalValue
     */
    public Integer getFinalValue() {
        return finalValue;
    }

    /**
     * @param finalValue the finalValue to set
     */
    public void setFinalValue(Integer finalValue) {
        this.finalValue = finalValue;
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
     * @return the range
     */
    public boolean isRange() {
        return range;
    }

    /**
     * @param range the range to set
     */
    public void setRange(boolean range) {
        this.range = range;
    }

    /**
     * @return the listChannel
     */
    public List<Channel> getListChannel() {
        return listChannel;
    }

    /**
     * @param listChannel the listChannel to set
     */
    public void setListChannel(List<Channel> listChannel) {
        this.listChannel = listChannel;
    }

    /**
     * @return the clusive
     */
    public String getClusive() {
        return clusive;
    }

    /**
     * @param clusive the clusive to set
     */
    public void setClusive(String clusive) {
        this.clusive = clusive;
    }

    /**
     * @return the listSubChannel
     */
    public List<SubChannel> getListSubChannel() {
        return listSubChannel;
    }

    /**
     * @param listSubChannel the listSubChannel to set
     */
    public void setListSubChannel(List<SubChannel> listSubChannel) {
        this.listSubChannel = listSubChannel;
    }

    /**
     * @return the listSegment
     */
    public List<Segmento> getListSegment() {
        return listSegment;
    }

    /**
     * @param listSegment the listSegment to set
     */
    public void setListSegment(List<Segmento> listSegment) {
        this.listSegment = listSegment;
    }

    /**
     * @return the listSubSegment
     */
    public List<SubSegmento> getListSubSegment() {
        return listSubSegment;
    }

    /**
     * @param listSubSegment the listSubSegment to set
     */
    public void setListSubSegment(List<SubSegmento> listSubSegment) {
        this.listSubSegment = listSubSegment;
    }

}
