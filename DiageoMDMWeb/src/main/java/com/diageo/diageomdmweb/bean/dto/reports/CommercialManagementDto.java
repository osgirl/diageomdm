/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.dto.reports;

import java.io.Serializable;

/**
 *
 * @author EDUARDO
 */
public class CommercialManagementDto implements Serializable{
    
    private Integer outletCode;
    private String kiernan;
    private String athenaCode;
    private String nit;
    private String businessName;
    private String channel;
    private String subChannel;
    private String segment;
    private String subSegment;   
    private String source;
    private String pos;

    public CommercialManagementDto() {
    }

    /**
     * @return the outletCode
     */
    public Integer getOutletCode() {
        return outletCode;
    }

    /**
     * @param outletCode the outletCode to set
     */
    public void setOutletCode(Integer outletCode) {
        this.outletCode = outletCode;
    }

    /**
     * @return the kiernan
     */
    public String getKiernan() {
        return kiernan;
    }

    /**
     * @param kiernan the kiernan to set
     */
    public void setKiernan(String kiernan) {
        this.kiernan = kiernan;
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
     * @return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return the businessName
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * @param businessName the businessName to set
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return the subChannel
     */
    public String getSubChannel() {
        return subChannel;
    }

    /**
     * @param subChannel the subChannel to set
     */
    public void setSubChannel(String subChannel) {
        this.subChannel = subChannel;
    }

    /**
     * @return the segment
     */
    public String getSegment() {
        return segment;
    }

    /**
     * @param segment the segment to set
     */
    public void setSegment(String segment) {
        this.segment = segment;
    }

    /**
     * @return the subSegment
     */
    public String getSubSegment() {
        return subSegment;
    }

    /**
     * @param subSegment the subSegment to set
     */
    public void setSubSegment(String subSegment) {
        this.subSegment = subSegment;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the pos
     */
    public String getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(String pos) {
        this.pos = pos;
    }
    
    
    
}
