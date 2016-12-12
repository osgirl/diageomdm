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
@Table(name = "DB_3PARTY_ADMIN")
@NamedQueries({
    @NamedQuery(name = "Db3partyAdmin.findAll", query = "SELECT d FROM Db3partyAdmin d"),
    @NamedQuery(name = "Db3partyAdmin.findByDb3partyAdminId", query = "SELECT d FROM Db3partyAdmin d WHERE d.db3partyAdminId = :db3partyAdminId"),
    @NamedQuery(name = "Db3partyAdmin.findByAdminName", query = "SELECT d FROM Db3partyAdmin d WHERE d.adminName = :adminName"),
    @NamedQuery(name = "Db3partyAdmin.findByDistri1", query = "SELECT d FROM Db3partyAdmin d WHERE d.distri1 = :distri1")})
public class Db3partyAdmin implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DB_3PARTY_ADMIN_ID")
    private Integer db3partyAdminId;
    @Size(max = 100)
    @Column(name = "ADMIN_NAME")
    private String adminName;
    @Size(max = 20)
    @Column(name = "DISTRI_1")
    private String distri1;
    @OneToMany(mappedBy = "db3PartyAdmin")
    private List<Db3party> list3Party;

    public List<Db3party> getList3Party() {
        return list3Party;
    }

    public void setList3Party(List<Db3party> list3Party) {
        this.list3Party = list3Party;
    }

    public Db3partyAdmin() {
    }

    public Db3partyAdmin(Integer db3partyAdminId) {
        this.db3partyAdminId = db3partyAdminId;
    }

    public Integer getDb3partyAdminId() {
        return db3partyAdminId;
    }

    public void setDb3partyAdminId(Integer db3partyAdminId) {
        this.db3partyAdminId = db3partyAdminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getDistri1() {
        return distri1;
    }

    public void setDistri1(String distri1) {
        this.distri1 = distri1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (db3partyAdminId != null ? db3partyAdminId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Db3partyAdmin)) {
            return false;
        }
        Db3partyAdmin other = (Db3partyAdmin) object;
        if ((this.db3partyAdminId == null && other.db3partyAdminId != null) || (this.db3partyAdminId != null && !this.db3partyAdminId.equals(other.db3partyAdminId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.Db3partyAdmin[ db3partyAdminId=" + db3partyAdminId + " ]";
    }
    
}
