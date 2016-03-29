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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "tipo_doc")
@NamedQueries({
    @NamedQuery(name = "TipoDoc.findAll", query = "SELECT t FROM TipoDoc t"),
    @NamedQuery(name = "TipoDoc.findByIdtipoDoc", query = "SELECT t FROM TipoDoc t WHERE t.idtipoDoc = :idtipoDoc"),
    @NamedQuery(name = "TipoDoc.findByNombre", query = "SELECT t FROM TipoDoc t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoDoc.findByDescripcion", query = "SELECT t FROM TipoDoc t WHERE t.descripcion = :descripcion")})
public class TipoDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtipo_doc")
    private Integer idtipoDoc;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;

    public TipoDoc() {
    }

    public TipoDoc(Integer idtipoDoc) {
        this.idtipoDoc = idtipoDoc;
    }

    public Integer getIdtipoDoc() {
        return idtipoDoc;
    }

    public void setIdtipoDoc(Integer idtipoDoc) {
        this.idtipoDoc = idtipoDoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoDoc != null ? idtipoDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDoc)) {
            return false;
        }
        TipoDoc other = (TipoDoc) object;
        if ((this.idtipoDoc == null && other.idtipoDoc != null) || (this.idtipoDoc != null && !this.idtipoDoc.equals(other.idtipoDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.admincontrollerweb.entities.TipoDoc[ idtipoDoc=" + idtipoDoc + " ]";
    }
    
}
