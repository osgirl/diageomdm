/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "PermissionSegment")
@NamedQueries({
    @NamedQuery(name = "PermissionSegment.findAll", query = "SELECT p FROM PermissionSegment p"),
    @NamedQuery(name = "PermissionSegment.findByIdPermissionSegment", query = "SELECT p FROM PermissionSegment p WHERE p.idPermissionSegment = :idPermissionSegment"),
    @NamedQuery(name = "PermissionSegment.findByChannel", query = "SELECT p FROM PermissionSegment p WHERE p.channel = :channel"),
    @NamedQuery(name = "PermissionSegment.findBySubChannel", query = "SELECT p FROM PermissionSegment p WHERE p.subChannel = :subChannel"),
    @NamedQuery(name = "PermissionSegment.findBySegmento", query = "SELECT p FROM PermissionSegment p WHERE p.segmento = :segmento"),
    @NamedQuery(name = "PermissionSegment.findBySubSegmento", query = "SELECT p FROM PermissionSegment p WHERE p.subSegmento = :subSegmento"),
    @NamedQuery(name = "PermissionSegment.findByPotencial", query = "SELECT p FROM PermissionSegment p WHERE p.potencial = :potencial")})
public class PermissionSegment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPermissionSegment")
    private Integer idPermissionSegment;
    @Size(max = 1)
    @Column(name = "Channel")
    private String channel;
    @Size(max = 1)
    @Column(name = "SubChannel")
    private String subChannel;
    @Size(max = 1)
    @Column(name = "Segmento")
    private String segmento;
    @Size(max = 1)
    @Column(name = "SubSegmento")
    private String subSegmento;
    @Size(max = 1)
    @Column(name = "Potencial")
    private String potencial;
    @JoinColumn(name = "IdUsuario")
    @OneToOne
    private Usuario idUser;

    public PermissionSegment() {
    }

    public PermissionSegment(Integer idPermissionSegment) {
        this.idPermissionSegment = idPermissionSegment;
    }

    public Integer getIdPermissionSegment() {
        return idPermissionSegment;
    }

    public void setIdPermissionSegment(Integer idPermissionSegment) {
        this.idPermissionSegment = idPermissionSegment;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSubChannel() {
        return subChannel;
    }

    public void setSubChannel(String subChannel) {
        this.subChannel = subChannel;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getSubSegmento() {
        return subSegmento;
    }

    public void setSubSegmento(String subSegmento) {
        this.subSegmento = subSegmento;
    }

    public String getPotencial() {
        return potencial;
    }

    public void setPotencial(String potencial) {
        this.potencial = potencial;
    }

    public Usuario getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuario idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermissionSegment != null ? idPermissionSegment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermissionSegment)) {
            return false;
        }
        PermissionSegment other = (PermissionSegment) object;
        if ((this.idPermissionSegment == null && other.idPermissionSegment != null) || (this.idPermissionSegment != null && !this.idPermissionSegment.equals(other.idPermissionSegment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.admincontrollerweb.entities.PermissionSegment[ idPermissionSegment=" + idPermissionSegment + " ]";
    }
    
}
