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
@Table(name = "DB_OWNERS")
@NamedQueries({
    @NamedQuery(name = "DbOwners.findAll", query = "SELECT d FROM DbOwners d"),
    @NamedQuery(name = "DbOwners.findByOwnerId", query = "SELECT d FROM DbOwners d WHERE d.ownerId = :ownerId"),
    @NamedQuery(name = "DbOwners.findByNameOwner", query = "SELECT d FROM DbOwners d WHERE d.nameOwner = :nameOwner")})
public class DbOwners implements Serializable {

    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "OWNER_ID")
    private Integer ownerId;
    @Size(max = 50)
    @Column(name = "NAME_OWNER")
    private String nameOwner;
    @OneToMany(mappedBy = "ownerId")
    private List<DbOutlets> dbOutletsList;    
    @Column(name = "DISTRI_1")
    private String distri1;

    public DbOwners() {
    }

    public DbOwners(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public List<DbOutlets> getDbOutletsList() {
        return dbOutletsList;
    }

    public void setDbOutletsList(List<DbOutlets> dbOutletsList) {
        this.dbOutletsList = dbOutletsList;
    }   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ownerId != null ? ownerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbOwners)) {
            return false;
        }
        DbOwners other = (DbOwners) object;
        if ((this.ownerId == null && other.ownerId != null) || (this.ownerId != null && !this.ownerId.equals(other.ownerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbOwners[ ownerId=" + ownerId + " ]";
    }

    public String getDistri1() {
        return distri1;
    }

    public void setDistri1(String distri1) {
        this.distri1 = distri1;
    }
    
}
