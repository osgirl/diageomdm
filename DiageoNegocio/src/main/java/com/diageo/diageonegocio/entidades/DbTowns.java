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
@Table(name = "DB_TOWNS")
@NamedQueries({
    @NamedQuery(name = "DbTowns.findAll", query = "SELECT d FROM DbTowns d"),
    @NamedQuery(name = DbTowns.FIND_BY_DEPTO, query = "SELECT m FROM DbTowns m WHERE m.departamentId.departamentId=?1")})
public class DbTowns implements Serializable {

    public static final String FIND_BY_DEPTO = "DbTowns.findByDepto";
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOWN_ID")
    private Integer townId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME_TOWN")
    private String nameTown;
    @JoinColumn(name = "DEPARTAMENT_ID", referencedColumnName = "DEPARTAMENT_ID")
    @ManyToOne(optional = false)
    private DbDepartaments departamentId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "townId")
    private List<DbLocations> dbLocationsList;
    @Column(name = "DISTRI_1")
    private String distri_1;

    public DbTowns() {
    }

    public DbTowns(Integer townId) {
        this.townId = townId;
    }

    public DbTowns(Integer townId, String nameTown) {
        this.townId = townId;
        this.nameTown = nameTown;
    }

    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    public String getNameTown() {
        return nameTown;
    }

    public void setNameTown(String nameTown) {
        this.nameTown = nameTown;
    }

    public DbDepartaments getDepartamentId() {
        return departamentId;
    }

    public void setDepartamentId(DbDepartaments departamentId) {
        this.departamentId = departamentId;
    }

    public List<DbLocations> getDbLocationsList() {
        return dbLocationsList;
    }

    public void setDbLocationsList(List<DbLocations> dbLocationsList) {
        this.dbLocationsList = dbLocationsList;
    }

    public String getDistri_1() {
        return distri_1;
    }

    public void setDistri_1(String distri_1) {
        this.distri_1 = distri_1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (townId != null ? townId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbTowns)) {
            return false;
        }
        DbTowns other = (DbTowns) object;
        return !((this.townId == null && other.townId != null) || (this.townId != null && !this.townId.equals(other.townId)));
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbTowns[ townId=" + townId + " ]";
    }

}
