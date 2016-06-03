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
@Table(name = "DB_OCS")
@NamedQueries({
    @NamedQuery(name = "DbOcs.findAll", query = "SELECT d FROM DbOcs d"),
    @NamedQuery(name = "DbOcs.findByOcsId", query = "SELECT d FROM DbOcs d WHERE d.ocsId = :ocsId"),
    @NamedQuery(name = "DbOcs.findByNameOcs", query = "SELECT d FROM DbOcs d WHERE d.nameOcs = :nameOcs")})
public class DbOcs implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "OCS_ID")
    private Integer ocsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME_OCS")
    private String nameOcs;
    @OneToMany(mappedBy = "ocsPrimary")
    private List<DbOutlets> dbOutletsList;
    @OneToMany(mappedBy = "ocsSecondary")
    private List<DbOutlets> dbOutletsList1;

    public DbOcs() {
    }

    public DbOcs(Integer ocsId) {
        this.ocsId = ocsId;
    }

    public DbOcs(Integer ocsId, String nameOcs) {
        this.ocsId = ocsId;
        this.nameOcs = nameOcs;
    }

    public Integer getOcsId() {
        return ocsId;
    }

    public void setOcsId(Integer ocsId) {
        this.ocsId = ocsId;
    }

    public String getNameOcs() {
        return nameOcs;
    }

    public void setNameOcs(String nameOcs) {
        this.nameOcs = nameOcs;
    }

    public List<DbOutlets> getDbOutletsList() {
        return dbOutletsList;
    }

    public void setDbOutletsList(List<DbOutlets> dbOutletsList) {
        this.dbOutletsList = dbOutletsList;
    }

    public List<DbOutlets> getDbOutletsList1() {
        return dbOutletsList1;
    }

    public void setDbOutletsList1(List<DbOutlets> dbOutletsList1) {
        this.dbOutletsList1 = dbOutletsList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ocsId != null ? ocsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbOcs)) {
            return false;
        }
        DbOcs other = (DbOcs) object;
        if ((this.ocsId == null && other.ocsId != null) || (this.ocsId != null && !this.ocsId.equals(other.ocsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbOcs[ ocsId=" + ocsId + " ]";
    }
    
}
