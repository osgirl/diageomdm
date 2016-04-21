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
 * @author EDUARDO
 */
@Entity
@Table(name = "sateoutlet")
@NamedQueries({
    @NamedQuery(name = "Sateoutlet.findAll", query = "SELECT s FROM Sateoutlet s"),
    @NamedQuery(name = "Sateoutlet.findByIdSateOutlet", query = "SELECT s FROM Sateoutlet s WHERE s.idSateOutlet = :idSateOutlet"),
    @NamedQuery(name = "Sateoutlet.findByName", query = "SELECT s FROM Sateoutlet s WHERE s.name = :name")})
public class Sateoutlet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSateOutlet")
    private Integer idSateOutlet;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @OneToMany(mappedBy = "idStateOutlet")
    private List<Outlet> outletList;

    public Sateoutlet() {
    }

    public Sateoutlet(Integer idSateOutlet) {
        this.idSateOutlet = idSateOutlet;
    }

    public Integer getIdSateOutlet() {
        return idSateOutlet;
    }

    public void setIdSateOutlet(Integer idSateOutlet) {
        this.idSateOutlet = idSateOutlet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSateOutlet != null ? idSateOutlet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sateoutlet)) {
            return false;
        }
        Sateoutlet other = (Sateoutlet) object;
        if ((this.idSateOutlet == null && other.idSateOutlet != null) || (this.idSateOutlet != null && !this.idSateOutlet.equals(other.idSateOutlet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.Sateoutlet[ idSateOutlet=" + idSateOutlet + " ]";
    }

    public List<Outlet> getOutletList() {
        return outletList;
    }

    public void setOutletList(List<Outlet> outletList) {
        this.outletList = outletList;
    }

}
