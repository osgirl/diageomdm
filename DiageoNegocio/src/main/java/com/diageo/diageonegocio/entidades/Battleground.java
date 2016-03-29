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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "battleground")
@NamedQueries({
    @NamedQuery(name = "Battleground.findAll", query = "SELECT b FROM Battleground b"),
    @NamedQuery(name = "Battleground.findByIdbattleground", query = "SELECT b FROM Battleground b WHERE b.idbattleground = :idbattleground"),
    @NamedQuery(name = "Battleground.findByNombre", query = "SELECT b FROM Battleground b WHERE b.nombre = :nombre"),
    @NamedQuery(name = "Battleground.findByEstado", query = "SELECT b FROM Battleground b WHERE b.estado = :estado")})
public class Battleground implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbattleground")
    private Integer idbattleground;
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
    @OneToMany(mappedBy = "idbattledground")
    private List<Establecimiento> establecimientoList;

    public Battleground() {
    }

    public Battleground(Integer idbattleground) {
        this.idbattleground = idbattleground;
    }

    public Battleground(Integer idbattleground, String nombre, String estado) {
        this.idbattleground = idbattleground;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getIdbattleground() {
        return idbattleground;
    }

    public void setIdbattleground(Integer idbattleground) {
        this.idbattleground = idbattleground;
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

    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbattleground != null ? idbattleground.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Battleground)) {
            return false;
        }
        Battleground other = (Battleground) object;
        if ((this.idbattleground == null && other.idbattleground != null) || (this.idbattleground != null && !this.idbattleground.equals(other.idbattleground))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.Battleground[ idbattleground=" + idbattleground + " ]";
    }
    
}
