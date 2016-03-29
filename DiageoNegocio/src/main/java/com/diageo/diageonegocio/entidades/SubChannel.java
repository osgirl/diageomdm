/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sub_channel")
@NamedQueries({
    @NamedQuery(name = SubChannel.FIND_BY_CHANNEL, query = "SELECT s FROM SubChannel s WHERE s.channelIdchannel.idchannel = ?1")
})
public class SubChannel implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_BY_CHANNEL = "SubChannel.findByChannel";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsubchannel")
    private Integer idsubchannel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "channel_idchannel", referencedColumnName = "idchannel")
    @ManyToOne(optional = false)
    private Channel channelIdchannel;
    @OneToMany(mappedBy = "idsubchannel")
    private List<Segmento> segmentoList;

    public SubChannel() {
    }

    public SubChannel(Integer idsubchannel) {
        this.idsubchannel = idsubchannel;
    }

    public SubChannel(Integer idsubchannel, String nombre, String estado) {
        this.idsubchannel = idsubchannel;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getIdsubchannel() {
        return idsubchannel;
    }

    public void setIdsubchannel(Integer idsubchannel) {
        this.idsubchannel = idsubchannel;
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

    public Channel getChannelIdchannel() {
        return channelIdchannel;
    }

    public void setChannelIdchannel(Channel channelIdchannel) {
        this.channelIdchannel = channelIdchannel;
    }

    public List<Segmento> getSegmentoList() {
        return segmentoList;
    }

    public void setSegmentoList(List<Segmento> segmentoList) {
        this.segmentoList = segmentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsubchannel != null ? idsubchannel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubChannel)) {
            return false;
        }
        SubChannel other = (SubChannel) object;
        if ((this.idsubchannel == null && other.idsubchannel != null) || (this.idsubchannel != null && !this.idsubchannel.equals(other.idsubchannel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.SubChannel[ idsubchannel=" + idsubchannel + " ]";
    }

}
