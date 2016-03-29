/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "link_temporal")
@NamedQueries({
    @NamedQuery(name = "LinkTemporal.findAll", query = "SELECT l FROM LinkTemporal l"),
    @NamedQuery(name = "LinkTemporal.findById", query = "SELECT l FROM LinkTemporal l WHERE l.id = :id"),
    @NamedQuery(name = "LinkTemporal.findByFechaCreacion", query = "SELECT l FROM LinkTemporal l WHERE l.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "LinkTemporal.findByFechaExpira", query = "SELECT l FROM LinkTemporal l WHERE l.fechaExpira = :fechaExpira")})
public class LinkTemporal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "fechaExpira")
    @Temporal(TemporalType.DATE)
    private Date fechaExpira;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idUsuario;

    public LinkTemporal() {
    }

    public LinkTemporal(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaExpira() {
        return fechaExpira;
    }

    public void setFechaExpira(Date fechaExpira) {
        this.fechaExpira = fechaExpira;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LinkTemporal)) {
            return false;
        }
        LinkTemporal other = (LinkTemporal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.admincontrollerweb.entities.LinkTemporal[ id=" + id + " ]";
    }
    
}
