/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_3PARTY_TERRITORY")
@NamedQueries({
    @NamedQuery(name = Db3partyTerritory.FIND_BY_NAME_TERRITORY_LIKE, query = "SELECT d FROM Db3partyTerritory d WHERE d.nameTerritory LIKE ?1")
    ,
    @NamedQuery(name = Db3partyTerritory.FIND_BY_NAME_TERRITORY, query = "SELECT d FROM Db3partyTerritory d WHERE d.nameTerritory = ?1"),})
public class Db3partyTerritory implements Serializable {

    public static final String FIND_BY_NAME_TERRITORY = "Db3partyTerritory.findByNameTerritory";
    public static final String FIND_BY_NAME_TERRITORY_LIKE = "Db3partyTerritory.findByNameTerritoryLike";
    @Id
    @Column(name = "DB_3PARTY_TERRITORY_ID")
    private Integer db3PartyTerritoryId;
    @Column(name = "NAME_TERRITORY")
    private String nameTerritory;
    @ManyToOne
    @JoinColumn(name = "DB_3PARTY_ID")
    private Db3party db3PartyId;
    @Embedded
    private Audit audit;
    @OneToMany(mappedBy = "db3partyTerritory")
    private List<Db3partySales> db3partySales;    

    public Db3partyTerritory() {
    }

    public List<Db3partySales> getDb3partySales() {
        return db3partySales;
    }

    public void setDb3partySales(List<Db3partySales> db3partySales) {
        this.db3partySales = db3partySales;
    }

    public Db3partyTerritory(Integer db3PartyTerritoryId) {
        this.db3PartyTerritoryId = db3PartyTerritoryId;
    }

    public Integer getDb3PartyTerritoryId() {
        return db3PartyTerritoryId;
    }

    public void setDb3PartyTerritoryId(Integer db3PartyTerritoryId) {
        this.db3PartyTerritoryId = db3PartyTerritoryId;
    }

    public String getNameTerritory() {
        return nameTerritory;
    }

    public void setNameTerritory(String nameTerritory) {
        this.nameTerritory = nameTerritory;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public Db3party getDb3PartyId() {
        return db3PartyId;
    }

    public void setDb3PartyId(Db3party db3PartyId) {
        this.db3PartyId = db3PartyId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.db3PartyTerritoryId.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Db3partyTerritory) {
            Db3partyTerritory d = (Db3partyTerritory) obj;
            if (this.db3PartyTerritoryId != null && d.db3PartyTerritoryId != null) {
                return this.db3PartyTerritoryId.equals(d.db3PartyTerritoryId);
            }
            return false;
        }
        return false;
    }

}
