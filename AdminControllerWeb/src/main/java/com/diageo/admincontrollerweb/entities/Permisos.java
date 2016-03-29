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
@Table(name = "permisos")
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p"),
    @NamedQuery(name = "Permisos.findByIdpermisos", query = "SELECT p FROM Permisos p WHERE p.idpermisos = :idpermisos"),
    @NamedQuery(name = "Permisos.findByCrear", query = "SELECT p FROM Permisos p WHERE p.crear = :crear"),
    @NamedQuery(name = "Permisos.findByModificar", query = "SELECT p FROM Permisos p WHERE p.modificar = :modificar"),
    @NamedQuery(name = "Permisos.findByConsultar", query = "SELECT p FROM Permisos p WHERE p.consultar = :consultar"),
    @NamedQuery(name = "Permisos.findByEliminar", query = "SELECT p FROM Permisos p WHERE p.eliminar = :eliminar")})
public class Permisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpermisos")
    private Integer idpermisos;
    @Size(max = 45)
    @Column(name = "crear")
    private String crear;
    @Size(max = 45)
    @Column(name = "modificar")
    private String modificar;
    @Size(max = 45)
    @Column(name = "consultar")
    private String consultar;
    @Size(max = 45)
    @Column(name = "eliminar")
    private String eliminar;

    public Permisos() {
    }

    public Permisos(Integer idpermisos) {
        this.idpermisos = idpermisos;
    }

    public Integer getIdpermisos() {
        return idpermisos;
    }

    public void setIdpermisos(Integer idpermisos) {
        this.idpermisos = idpermisos;
    }

    public String getCrear() {
        return crear;
    }

    public void setCrear(String crear) {
        this.crear = crear;
    }

    public String getModificar() {
        return modificar;
    }

    public void setModificar(String modificar) {
        this.modificar = modificar;
    }

    public String getConsultar() {
        return consultar;
    }

    public void setConsultar(String consultar) {
        this.consultar = consultar;
    }

    public String getEliminar() {
        return eliminar;
    }

    public void setEliminar(String eliminar) {
        this.eliminar = eliminar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpermisos != null ? idpermisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.idpermisos == null && other.idpermisos != null) || (this.idpermisos != null && !this.idpermisos.equals(other.idpermisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.admincontrollerweb.entities.Permisos[ idpermisos=" + idpermisos + " ]";
    }
    
}
