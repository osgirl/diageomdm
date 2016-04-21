/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "permissionsegment")
@NamedQueries({
    @NamedQuery(name = Permissionsegment.FIND_BY_USER, query = "SELECT p FROM Permissionsegment p WHERE p.permissionsegmentPK.idUsuario=?1")
})
public class Permissionsegment implements Serializable {

    public static final String FIND_BY_USER = "Permissionsegment.findByUser";
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PermissionsegmentPK permissionsegmentPK;
    @Column(name = "Channel")
    private Integer channel;
    @Column(name = "SubChannel")
    private Integer subChannel;
    @Column(name = "Segmento")
    private Integer segmento;
    @Column(name = "SubSegmento")
    private Integer subSegmento;
    @Column(name = "Potencial")
    private Integer potencial;
    @JoinColumn(name = "IdDistributor", referencedColumnName = "id_distribuidor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Distribuidor distribuidor;

    @Column(name = "ChannelCheck")
    private String channelCheck;
    @Column(name = "SubChannelCheck")
    private String subChannelCheck;
    @Column(name = "SegmentoCheck")
    private String segmentoCheck;
    @Column(name = "SubSegmentCheck")
    private String subSegmentCheck;
    @Column(name = "PotentialCheck")
    private String potentialCheck;

    public Permissionsegment() {
    }

    public Permissionsegment(PermissionsegmentPK permissionsegmentPK) {
        this.permissionsegmentPK = permissionsegmentPK;
    }

    public Permissionsegment(int idUsuario, int idDistributor) {
        this.permissionsegmentPK = new PermissionsegmentPK(idUsuario, idDistributor);
    }

    public PermissionsegmentPK getPermissionsegmentPK() {
        return permissionsegmentPK;
    }

    public void setPermissionsegmentPK(PermissionsegmentPK permissionsegmentPK) {
        this.permissionsegmentPK = permissionsegmentPK;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getSubChannel() {
        return subChannel;
    }

    public void setSubChannel(Integer subChannel) {
        this.subChannel = subChannel;
    }

    public Integer getSegmento() {
        return segmento;
    }

    public void setSegmento(Integer segmento) {
        this.segmento = segmento;
    }

    public Integer getSubSegmento() {
        return subSegmento;
    }

    public void setSubSegmento(Integer subSegmento) {
        this.subSegmento = subSegmento;
    }

    public Integer getPotencial() {
        return potencial;
    }

    public void setPotencial(Integer potencial) {
        this.potencial = potencial;
    }

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    public String getChannelCheck() {
        return channelCheck;
    }

    public void setChannelCheck(String channelCheck) {
        this.channelCheck = channelCheck;
    }

    public String getSubChannelCheck() {
        return subChannelCheck;
    }

    public void setSubChannelCheck(String subChannelCheck) {
        this.subChannelCheck = subChannelCheck;
    }

    public String getSegmentoCheck() {
        return segmentoCheck;
    }

    public void setSegmentoCheck(String segmentoCheck) {
        this.segmentoCheck = segmentoCheck;
    }

    public String getSubSegmentCheck() {
        return subSegmentCheck;
    }

    public void setSubSegmentCheck(String subSegmentCheck) {
        this.subSegmentCheck = subSegmentCheck;
    }

    public String getPotentialCheck() {
        return potentialCheck;
    }

    public void setPotentialCheck(String potentialCheck) {
        this.potentialCheck = potentialCheck;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permissionsegmentPK != null ? permissionsegmentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissionsegment)) {
            return false;
        }
        Permissionsegment other = (Permissionsegment) object;
        if ((this.permissionsegmentPK == null && other.permissionsegmentPK != null) || (this.permissionsegmentPK != null && !this.permissionsegmentPK.equals(other.permissionsegmentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.Permissionsegment[ permissionsegmentPK=" + permissionsegmentPK + " ]";
    }

}
