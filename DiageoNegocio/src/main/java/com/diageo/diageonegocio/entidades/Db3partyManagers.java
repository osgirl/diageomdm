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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_3PARTY_MANAGERS")
@NamedQueries({
    @NamedQuery(name = "Db3partyManagers.findAll", query = "SELECT d FROM Db3partyManagers d"),
    @NamedQuery(name = "Db3partyManagers.findByDb3partyManagerId", query = "SELECT d FROM Db3partyManagers d WHERE d.db3partyManagerId = :db3partyManagerId"),
    @NamedQuery(name = "Db3partyManagers.findByNameManager", query = "SELECT d FROM Db3partyManagers d WHERE d.nameManager = :nameManager")})
public class Db3partyManagers implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(generator = "SQ_DB_3PARTY_MANAGERS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DB_3PARTY_MANAGERS", sequenceName = "SQ_DB_3PARTY_MANAGERS", allocationSize = 1)
    @Column(name = "DB_3PARTY_MANAGER_ID")
    private Integer db3partyManagerId;
    @Size(max = 500)
    @Column(name = "NAME_MANAGER")
    private String nameManager;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "db3partyManagerId")
    private List<Db3partySales> db3partySalesList;
    @JoinColumn(name = "DB_3PARTY_ID", referencedColumnName = "DB_3PARTY_ID")
    @ManyToOne(optional = false)
    private Db3party db3partyId;

    public Db3partyManagers() {
    }

    public Db3partyManagers(Integer db3partyManagerId) {
        this.db3partyManagerId = db3partyManagerId;
    }

    public Integer getDb3partyManagerId() {
        return db3partyManagerId;
    }

    public void setDb3partyManagerId(Integer db3partyManagerId) {
        this.db3partyManagerId = db3partyManagerId;
    }

    public String getNameManager() {
        return nameManager;
    }

    public void setNameManager(String nameManager) {
        this.nameManager = nameManager;
    }

    public List<Db3partySales> getDb3partySalesList() {
        return db3partySalesList;
    }

    public void setDb3partySalesList(List<Db3partySales> db3partySalesList) {
        this.db3partySalesList = db3partySalesList;
    }

    public Db3party getDb3partyId() {
        return db3partyId;
    }

    public void setDb3partyId(Db3party db3partyId) {
        this.db3partyId = db3partyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (db3partyManagerId != null ? db3partyManagerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Db3partyManagers)) {
            return false;
        }
        Db3partyManagers other = (Db3partyManagers) object;
        if ((this.db3partyManagerId == null && other.db3partyManagerId != null) || (this.db3partyManagerId != null && !this.db3partyManagerId.equals(other.db3partyManagerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.Db3partyManagers[ db3partyManagerId=" + db3partyManagerId + " ]";
    }

}
