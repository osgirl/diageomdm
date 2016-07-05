/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_3PARTY_REGIONAL")
@NamedQueries({
    @NamedQuery(name = "Db3partyRegional.findAll", query = "SELECT d FROM Db3partyRegional d"),
    @NamedQuery(name = "Db3partyRegional.findByDb3partyRegionalId", query = "SELECT d FROM Db3partyRegional d WHERE d.db3partyRegionalId = :db3partyRegionalId"),
    @NamedQuery(name = "Db3partyRegional.findByNameRegional", query = "SELECT d FROM Db3partyRegional d WHERE d.nameRegional = :nameRegional")})
public class Db3partyRegional implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(generator = "SQ_DB_3PARTY_REGIONAL", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DB_3PARTY_REGIONAL", sequenceName = "SQ_DB_3PARTY_REGIONAL", allocationSize = 1)
    @Column(name = "DB_3PARTY_REGIONAL_ID")
    private Integer db3partyRegionalId;
    @Size(max = 50)
    @Column(name = "NAME_REGIONAL")
    private String nameRegional;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "db3partyRegionalId")
    private List<Db3party> db3partyList;
    @Column(name = "DISTRI_1")
    private String distri_1;

    public Db3partyRegional() {
    }

    public Db3partyRegional(Integer db3partyRegionalId) {
        this.db3partyRegionalId = db3partyRegionalId;
    }

    public Integer getDb3partyRegionalId() {
        return db3partyRegionalId;
    }

    public void setDb3partyRegionalId(Integer db3partyRegionalId) {
        this.db3partyRegionalId = db3partyRegionalId;
    }

    public String getNameRegional() {
        return nameRegional;
    }

    public void setNameRegional(String nameRegional) {
        this.nameRegional = nameRegional;
    }

    public List<Db3party> getDb3partyList() {
        return db3partyList;
    }

    public void setDb3partyList(List<Db3party> db3partyList) {
        this.db3partyList = db3partyList;
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
        hash += (db3partyRegionalId != null ? db3partyRegionalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Db3partyRegional)) {
            return false;
        }
        Db3partyRegional other = (Db3partyRegional) object;
        if ((this.db3partyRegionalId == null && other.db3partyRegionalId != null) || (this.db3partyRegionalId != null && !this.db3partyRegionalId.equals(other.db3partyRegionalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.Db3partyRegional[ db3partyRegionalId=" + db3partyRegionalId + " ]";
    }
    
}
