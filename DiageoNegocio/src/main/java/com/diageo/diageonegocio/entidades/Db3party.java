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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "DB_3PARTY")
@NamedQueries({
    @NamedQuery(name = "Db3party.findAll", query = "SELECT d FROM Db3party d"),
    @NamedQuery(name = Db3party.FIND_BY_IS_PADRE, query = "SELECT d FROM Db3party d WHERE d.isFather = ?1"),
    @NamedQuery(name = Db3party.FIND_BY_PADRE, query = "SELECT d FROM Db3party d WHERE d.db3partyIdFather.db3partyId = ?1"),})
public class Db3party implements Serializable {

    public static final String FIND_BY_IS_PADRE = "Db3party.findByIsFather";
    public static final String FIND_BY_PADRE = "Db3party.findByFather";
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(generator = "SQ_DB_3PARTY", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DB_3PARTY", sequenceName = "SQ_DB_3PARTY", allocationSize = 1)
    @Column(name = "DB_3PARTY_ID")
    private Integer db3partyId;
    @Size(max = 50)
    @Column(name = "NAME_3PARTY")
    private String name3party;
    @Size(max = 1)
    @Column(name = "IS_FATHER")
    private String isFather;    
    @ManyToMany(mappedBy = "db3partyList")
    private List<DbOutlets> dbOutletsList;

    @JoinColumn(name = "DB_3PARTY_REGIONAL_ID", referencedColumnName = "DB_3PARTY_REGIONAL_ID")
    @ManyToOne(optional = false)
    private Db3partyRegional db3partyRegionalId;

    @OneToMany(mappedBy = "db3partyIdFather")
    private List<Db3party> db3partyList;

    @JoinColumn(name = "DB_3PARTY_ID_FATHER", referencedColumnName = "DB_3PARTY_ID")
    @ManyToOne
    private Db3party db3partyIdFather;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "db3partyId")
    private List<DbPermissionSegments> dbPermissionSegmentsList;

    @OneToMany(mappedBy = "dbPartyId")
    private List<DbChains> dbChainsList;
    @Column(name = "DISTRI_1")
    private String distri_1;
    @Column(name = "KIERNAN")
    private String kiernan;
    @ManyToOne
    @JoinColumn(name = "DB_3PARTY_ADMIN_ID", referencedColumnName = "DB_3PARTY_ADMIN_ID")
    private Db3partyAdmin db3PartyAdmin;

    public Db3party() {
    }

    public Db3party(Integer db3partyId) {
        this.db3partyId = db3partyId;
    }

    public Integer getDb3partyId() {
        return db3partyId;
    }

    public void setDb3partyId(Integer db3partyId) {
        this.db3partyId = db3partyId;
    }

    public String getName3party() {
        return name3party;
    }

    public void setName3party(String name3party) {
        this.name3party = name3party;
    }

    public String getIsFather() {
        return isFather;
    }

    public void setIsFather(String isFather) {
        this.isFather = isFather;
    }

    public List<DbOutlets> getDbOutletsList() {
        return dbOutletsList;
    }

    public void setDbOutletsList(List<DbOutlets> dbOutletsList) {
        this.dbOutletsList = dbOutletsList;
    }

    public Db3partyRegional getDb3partyRegionalId() {
        return db3partyRegionalId;
    }

    public void setDb3partyRegionalId(Db3partyRegional db3partyRegionalId) {
        this.db3partyRegionalId = db3partyRegionalId;
    }

    public List<Db3party> getDb3partyList() {
        return db3partyList;
    }

    public void setDb3partyList(List<Db3party> db3partyList) {
        this.db3partyList = db3partyList;
    }

    public Db3party getDb3partyIdFather() {
        return db3partyIdFather;
    }

    public void setDb3partyIdFather(Db3party db3partyIdFather) {
        this.db3partyIdFather = db3partyIdFather;
    }

    public List<DbPermissionSegments> getDbPermissionSegmentsList() {
        return dbPermissionSegmentsList;
    }

    public void setDbPermissionSegmentsList(List<DbPermissionSegments> dbPermissionSegmentsList) {
        this.dbPermissionSegmentsList = dbPermissionSegmentsList;
    }

    public List<DbChains> getDbChainsList() {
        return dbChainsList;
    }

    public void setDbChainsList(List<DbChains> dbChainsList) {
        this.dbChainsList = dbChainsList;
    }

    public String getDistri1() {
        return distri_1;
    }

    public void setDistri1(String distri1) {
        this.distri_1 = distri1;
    }

    public String getKiernan() {
        return kiernan;
    }

    public void setKiernan(String kiernan) {
        this.kiernan = kiernan;
    }

    public String getDistri_1() {
        return distri_1;
    }

    public void setDistri_1(String distri_1) {
        this.distri_1 = distri_1;
    }

    public Db3partyAdmin getDb3PartyAdmin() {
        return db3PartyAdmin;
    }

    public void setDb3PartyAdmin(Db3partyAdmin db3PartyAdmin) {
        this.db3PartyAdmin = db3PartyAdmin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (db3partyId != null ? db3partyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Db3party)) {
            return false;
        }
        Db3party other = (Db3party) object;
        if ((this.db3partyId == null && other.db3partyId != null) || (this.db3partyId != null && !this.db3partyId.equals(other.db3partyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.Db3party[ db3partyId=" + db3partyId + " ]";
    }

}
