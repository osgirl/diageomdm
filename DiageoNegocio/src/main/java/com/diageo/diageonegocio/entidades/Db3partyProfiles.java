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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_3PARTY_PROFILES")
public class Db3partyProfiles implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "DB_3PARTY_PROFILE_ID")
    private Integer db3partyProfileId;
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(mappedBy = "db3PartyProfileId")
    private List<Db3partySales> db3partySalesList;

    public Db3partyProfiles() {
    }

    public Db3partyProfiles(Integer db3partyProfileId) {
        this.db3partyProfileId = db3partyProfileId;
    }

    public Integer getDb3partyProfileId() {
        return db3partyProfileId;
    }

    public void setDb3partyProfileId(Integer db3partyProfileId) {
        this.db3partyProfileId = db3partyProfileId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Db3partySales> getDb3partySalesList() {
        return db3partySalesList;
    }

    public void setDb3partySalesList(List<Db3partySales> db3partySalesList) {
        this.db3partySalesList = db3partySalesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (db3partyProfileId != null ? db3partyProfileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Db3partyProfiles)) {
            return false;
        }
        Db3partyProfiles other = (Db3partyProfiles) object;
        if ((this.db3partyProfileId == null && other.db3partyProfileId != null) || (this.db3partyProfileId != null && !this.db3partyProfileId.equals(other.db3partyProfileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.Db3partyProfiles[ db3partyProfileId=" + db3partyProfileId + " ]";
    }

    }
