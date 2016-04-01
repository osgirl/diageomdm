/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "channel")
@NamedQueries({
    @NamedQuery(name = "Channel.findAll", query = "SELECT c FROM Channel c"),
    @NamedQuery(name = "Channel.findByIdchannel", query = "SELECT c FROM Channel c WHERE c.idchannel = :idchannel"),
    @NamedQuery(name = "Channel.findByNombre", query = "SELECT c FROM Channel c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Channel.findByEstado", query = "SELECT c FROM Channel c WHERE c.estado = :estado")})
public class Channel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idchannel")
    private Integer idchannel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "channelIdchannel")
    private List<SubChannel> subChannelList;

    public Channel() {
    }

    public Channel(Integer idchannel) {
        this.idchannel = idchannel;
    }

    public Channel(Integer idchannel, String nombre, String estado) {
        this.idchannel = idchannel;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getIdchannel() {
        return idchannel;
    }

    public void setIdchannel(Integer idchannel) {
        this.idchannel = idchannel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<SubChannel> getSubChannelList() {
        return subChannelList;
    }

    public void setSubChannelList(List<SubChannel> subChannelList) {
        this.subChannelList = subChannelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idchannel != null ? idchannel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Channel)) {
            return false;
        }
        Channel other = (Channel) object;
        if ((this.idchannel == null && other.idchannel != null) || (this.idchannel != null && !this.idchannel.equals(other.idchannel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.Channel[ idchannel=" + idchannel + " ]";
    }
    
}
