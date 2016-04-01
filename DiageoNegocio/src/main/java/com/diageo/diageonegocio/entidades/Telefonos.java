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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "telefonos")
@NamedQueries({
    @NamedQuery(name = "Telefonos.findAll", query = "SELECT t FROM Telefonos t"),
    @NamedQuery(name = "Telefonos.findByIdtelefonos", query = "SELECT t FROM Telefonos t WHERE t.idtelefonos = :idtelefonos"),
    @NamedQuery(name = "Telefonos.findByNumeroTel", query = "SELECT t FROM Telefonos t WHERE t.numeroTel = :numeroTel")})
public class Telefonos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtelefonos")
    private Integer idtelefonos;
    @Size(max = 45)
    @Column(name = "numeroTel")
    private String numeroTel;
    @ManyToMany(mappedBy = "telefonosList")
    private List<Outlet> establecimientoList;
    @JoinColumn(name = "tipoTelefono", referencedColumnName = "idtipo_telefono")
    @ManyToOne
    private TipoTelefono tipoTelefono;

    public Telefonos() {
    }

    public Telefonos(Integer idtelefonos) {
        this.idtelefonos = idtelefonos;
    }

    public Integer getIdtelefonos() {
        return idtelefonos;
    }

    public void setIdtelefonos(Integer idtelefonos) {
        this.idtelefonos = idtelefonos;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public List<Outlet> getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(List<Outlet> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }

    public TipoTelefono getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(TipoTelefono tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtelefonos != null ? idtelefonos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefonos)) {
            return false;
        }
        Telefonos other = (Telefonos) object;
        if ((this.idtelefonos == null && other.idtelefonos != null) || (this.idtelefonos != null && !this.idtelefonos.equals(other.idtelefonos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.Telefonos[ idtelefonos=" + idtelefonos + " ]";
    }
    
}
