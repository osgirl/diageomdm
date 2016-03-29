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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "tipo_telefono")
@NamedQueries({
    @NamedQuery(name = "TipoTelefono.findAll", query = "SELECT t FROM TipoTelefono t"),
    @NamedQuery(name = "TipoTelefono.findByIdtipoTelefono", query = "SELECT t FROM TipoTelefono t WHERE t.idtipoTelefono = :idtipoTelefono"),
    @NamedQuery(name = "TipoTelefono.findByNombre", query = "SELECT t FROM TipoTelefono t WHERE t.nombre = :nombre")})
public class TipoTelefono implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipo_telefono")
    private Integer idtipoTelefono;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "tipoTelefono")
    private List<Telefonos> telefonosList;

    public TipoTelefono() {
    }

    public TipoTelefono(Integer idtipoTelefono) {
        this.idtipoTelefono = idtipoTelefono;
    }

    public Integer getIdtipoTelefono() {
        return idtipoTelefono;
    }

    public void setIdtipoTelefono(Integer idtipoTelefono) {
        this.idtipoTelefono = idtipoTelefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Telefonos> getTelefonosList() {
        return telefonosList;
    }

    public void setTelefonosList(List<Telefonos> telefonosList) {
        this.telefonosList = telefonosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoTelefono != null ? idtipoTelefono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTelefono)) {
            return false;
        }
        TipoTelefono other = (TipoTelefono) object;
        if ((this.idtipoTelefono == null && other.idtipoTelefono != null) || (this.idtipoTelefono != null && !this.idtipoTelefono.equals(other.idtipoTelefono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.TipoTelefono[ idtipoTelefono=" + idtipoTelefono + " ]";
    }
    
}
