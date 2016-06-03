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
@Table(name = "DB_FASCIAS")
@NamedQueries({
    @NamedQuery(name = "DbFascias.findAll", query = "SELECT d FROM DbFascias d"),
    @NamedQuery(name = "DbFascias.findByFasciaId", query = "SELECT d FROM DbFascias d WHERE d.fasciaId = :fasciaId"),
    @NamedQuery(name = "DbFascias.findByNameFascia", query = "SELECT d FROM DbFascias d WHERE d.nameFascia = :nameFascia")})
public class DbFascias implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FASCIA_ID")
    private Integer fasciaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME_FASCIA")
    private String nameFascia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fasciaId")
    private List<DbOwners> dbOwnersList;

    public DbFascias() {
    }

    public DbFascias(Integer fasciaId) {
        this.fasciaId = fasciaId;
    }

    public DbFascias(Integer fasciaId, String nameFascia) {
        this.fasciaId = fasciaId;
        this.nameFascia = nameFascia;
    }

    public Integer getFasciaId() {
        return fasciaId;
    }

    public void setFasciaId(Integer fasciaId) {
        this.fasciaId = fasciaId;
    }

    public String getNameFascia() {
        return nameFascia;
    }

    public void setNameFascia(String nameFascia) {
        this.nameFascia = nameFascia;
    }

    public List<DbOwners> getDbOwnersList() {
        return dbOwnersList;
    }

    public void setDbOwnersList(List<DbOwners> dbOwnersList) {
        this.dbOwnersList = dbOwnersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fasciaId != null ? fasciaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbFascias)) {
            return false;
        }
        DbFascias other = (DbFascias) object;
        if ((this.fasciaId == null && other.fasciaId != null) || (this.fasciaId != null && !this.fasciaId.equals(other.fasciaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbFascias[ fasciaId=" + fasciaId + " ]";
    }
    
}
