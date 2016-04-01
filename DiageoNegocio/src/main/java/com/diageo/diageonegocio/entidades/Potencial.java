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
@Table(name = "potencial")
@NamedQueries({
    @NamedQuery(name = "Potencial.findAll", query = "SELECT p FROM Potencial p"),
    @NamedQuery(name = "Potencial.findByIdPotencial", query = "SELECT p FROM Potencial p WHERE p.idPotencial = :idPotencial"),
    @NamedQuery(name = "Potencial.findByNombre", query = "SELECT p FROM Potencial p WHERE p.nombre = :nombre")})
public class Potencial implements Serializable {

    @OneToMany(mappedBy = "idPotencial")
    private List<Outlet> outletList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_potencial")
    private Integer idPotencial;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;

    public Potencial() {
    }

    public Potencial(Integer idPotencial) {
        this.idPotencial = idPotencial;
    }

    public Integer getIdPotencial() {
        return idPotencial;
    }

    public void setIdPotencial(Integer idPotencial) {
        this.idPotencial = idPotencial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPotencial != null ? idPotencial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Potencial)) {
            return false;
        }
        Potencial other = (Potencial) object;
        if ((this.idPotencial == null && other.idPotencial != null) || (this.idPotencial != null && !this.idPotencial.equals(other.idPotencial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.Potencial[ idPotencial=" + idPotencial + " ]";
    }

    public List<Outlet> getOutletList() {
        return outletList;
    }

    public void setOutletList(List<Outlet> outletList) {
        this.outletList = outletList;
    }
    
}
