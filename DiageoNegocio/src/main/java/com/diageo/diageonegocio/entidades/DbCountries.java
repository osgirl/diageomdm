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
@Table(name = "DB_COUNTRIES")
@NamedQueries({
    @NamedQuery(name = "DbCountries.findAll", query = "SELECT d FROM DbCountries d"),
    @NamedQuery(name = "DbCountries.findByCountryId", query = "SELECT d FROM DbCountries d WHERE d.countryId = :countryId"),
    @NamedQuery(name = "DbCountries.findByNameCountry", query = "SELECT d FROM DbCountries d WHERE d.nameCountry = :nameCountry")})
public class DbCountries implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COUNTRY_ID")
    private Integer countryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME_COUNTRY")
    private String nameCountry;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryId")
    private List<DbDepartaments> dbDepartamentsList;
    @Column(name = "DISTRI_1")
    private String distri_1;

    public DbCountries() {
    }

    public DbCountries(Integer countryId) {
        this.countryId = countryId;
    }

    public DbCountries(Integer countryId, String nameCountry) {
        this.countryId = countryId;
        this.nameCountry = nameCountry;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public List<DbDepartaments> getDbDepartamentsList() {
        return dbDepartamentsList;
    }

    public void setDbDepartamentsList(List<DbDepartaments> dbDepartamentsList) {
        this.dbDepartamentsList = dbDepartamentsList;
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
        hash += (countryId != null ? countryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbCountries)) {
            return false;
        }
        DbCountries other = (DbCountries) object;
        if ((this.countryId == null && other.countryId != null) || (this.countryId != null && !this.countryId.equals(other.countryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbCountries[ countryId=" + countryId + " ]";
    }

}
