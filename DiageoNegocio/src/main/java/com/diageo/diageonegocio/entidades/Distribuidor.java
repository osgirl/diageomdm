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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "distribuidor")
@NamedQueries({
    @NamedQuery(name = "Distribuidor.findAll", query = "SELECT d FROM Distribuidor d"),
    @NamedQuery(name = "Distribuidor.findByIdDistribuidor", query = "SELECT d FROM Distribuidor d WHERE d.idDistribuidor = ?1"),
    @NamedQuery(name = Distribuidor.FIND_BY_IS_PADRE, query = "SELECT d FROM Distribuidor d WHERE d.isPadre = ?1"),
    @NamedQuery(name = Distribuidor.FIND_BY_PADRE, query = "SELECT d FROM Distribuidor d WHERE d.padreIdDistribuidor.idDistribuidor = ?1"),
    @NamedQuery(name = "Distribuidor.findByNombre", query = "SELECT d FROM Distribuidor d WHERE d.nombre = ?1")})
public class Distribuidor implements Serializable {

    public static final String FIND_BY_IS_PADRE = "Distribuidor.findByIsPadre";
    public static final String FIND_BY_PADRE = "Distribuidor.findByPadre";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_distribuidor")
    private Integer idDistribuidor;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1)
    @Column(name = "isPadre")
    private String isPadre;
    @OneToMany(mappedBy = "idDistribuidor")
    private List<Outlet> outletList;
    @OneToMany(mappedBy = "padreIdDistribuidor")
    private List<Distribuidor> distribuidorList;
    @JoinColumn(name = "PadreIdDistribuidor", referencedColumnName = "id_distribuidor")
    @ManyToOne
    private Distribuidor padreIdDistribuidor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "distribuidor")
    private List<Permissionsegment> permissionsegmentList;

    public Distribuidor() {
    }

    public Distribuidor(Integer idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public Integer getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(Integer idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIsPadre() {
        return isPadre;
    }

    public void setIsPadre(String isPadre) {
        this.isPadre = isPadre;
    }

    public List<Outlet> getOutletList() {
        return outletList;
    }

    public void setOutletList(List<Outlet> outletList) {
        this.outletList = outletList;
    }

    public List<Distribuidor> getDistribuidorList() {
        return distribuidorList;
    }

    public void setDistribuidorList(List<Distribuidor> distribuidorList) {
        this.distribuidorList = distribuidorList;
    }

    public Distribuidor getPadreIdDistribuidor() {
        return padreIdDistribuidor;
    }

    public void setPadreIdDistribuidor(Distribuidor padreIdDistribuidor) {
        this.padreIdDistribuidor = padreIdDistribuidor;
    }

    public List<Permissionsegment> getPermissionsegmentList() {
        return permissionsegmentList;
    }

    public void setPermissionsegmentList(List<Permissionsegment> permissionsegmentList) {
        this.permissionsegmentList = permissionsegmentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDistribuidor != null ? idDistribuidor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distribuidor)) {
            return false;
        }
        Distribuidor other = (Distribuidor) object;
        if ((this.idDistribuidor == null && other.idDistribuidor != null) || (this.idDistribuidor != null && !this.idDistribuidor.equals(other.idDistribuidor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.Distribuidor[ idDistribuidor=" + idDistribuidor + " ]";
    }

}
