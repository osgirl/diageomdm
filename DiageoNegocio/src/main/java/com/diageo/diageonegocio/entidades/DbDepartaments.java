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
@Table(name = "DB_DEPARTAMENTS")
@NamedQueries({
    @NamedQuery(name = "DbDepartaments.findAll", query = "SELECT d FROM DbDepartaments d"),
    @NamedQuery(name = "DbDepartaments.findByDepartamentId", query = "SELECT d FROM DbDepartaments d WHERE d.departamentId = :departamentId"),
    @NamedQuery(name = "DbDepartaments.findByNameDepartament", query = "SELECT d FROM DbDepartaments d WHERE d.nameDepartament = :nameDepartament")})
public class DbDepartaments implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEPARTAMENT_ID")
    private Integer departamentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME_DEPARTAMENT")
    private String nameDepartament;
    @JoinColumn(name = "COUNTRY_ID", referencedColumnName = "COUNTRY_ID")
    @ManyToOne(optional = false)
    private DbCountries countryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamentId")
    private List<DbTowns> dbTownsList;

    public DbDepartaments() {
    }

    public DbDepartaments(Integer departamentId) {
        this.departamentId = departamentId;
    }

    public DbDepartaments(Integer departamentId, String nameDepartament) {
        this.departamentId = departamentId;
        this.nameDepartament = nameDepartament;
    }

    public Integer getDepartamentId() {
        return departamentId;
    }

    public void setDepartamentId(Integer departamentId) {
        this.departamentId = departamentId;
    }

    public String getNameDepartament() {
        return nameDepartament;
    }

    public void setNameDepartament(String nameDepartament) {
        this.nameDepartament = nameDepartament;
    }

    public DbCountries getCountryId() {
        return countryId;
    }

    public void setCountryId(DbCountries countryId) {
        this.countryId = countryId;
    }

    public List<DbTowns> getDbTownsList() {
        return dbTownsList;
    }

    public void setDbTownsList(List<DbTowns> dbTownsList) {
        this.dbTownsList = dbTownsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (departamentId != null ? departamentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbDepartaments)) {
            return false;
        }
        DbDepartaments other = (DbDepartaments) object;
        if ((this.departamentId == null && other.departamentId != null) || (this.departamentId != null && !this.departamentId.equals(other.departamentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbDepartaments[ departamentId=" + departamentId + " ]";
    }
    
}
