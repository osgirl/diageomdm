/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_3PARTY_SALES")
@NamedQueries({
    @NamedQuery(name = Db3partySales.FIND_BY_NAME_SALES, query = "SELECT d FROM Db3partySales d WHERE d.nameSales LIKE ?1 AND d.dbeParty = ?2")
})
public class Db3partySales implements Serializable {

    public static final String FIND_BY_NAME_SALES = "Db3partySales.findByNameSales";
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "SQ_DB_3PARTY_SALES", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "SQ_DB_3PARTY_SALES", sequenceName = "SQ_DB_3PARTY_SALES", allocationSize = 1)
    @Column(name = "DB_3PARTY_SALE_ID")
    private Integer db3partySaleId;
    @Size(max = 50)
    @Column(name = "NAME_SALES")
    private String nameSales;
    @JoinColumn(name = "DB_3PARTY_MANAGER_ID", referencedColumnName = "DB_3PARTY_MANAGER_ID")
    @ManyToOne(optional = false)
    private Db3partyManagers db3partyManagerId;
    @OneToMany(mappedBy = "db3partySaleId")
    private List<DbOutlets> dbOutletsList;
    @OneToMany(mappedBy = "db3partySaleId")
    private List<DbChains> dbChainsList;
    @ManyToOne
    @JoinColumn(name = "DB_3PARTY_PROFILE_ID")
    private Db3partyProfiles db3PartyProfileId;
    @Column(name = "DB_3PARTY_ID")
    private Integer dbeParty;
    @Column(name = "DB_3PARTY_PDV")
    private String pdv;
    @ManyToOne
    @JoinColumn(name = "DB_3PARTY_TERRITORY_ID")
    private Db3partyTerritory db3partyTerritory;
    @Column(name = "FOCALIZADO")
    private String focalizado;
    @Embedded
    private Audit audit;

    public Db3partySales() {
    }

    public Db3partyTerritory getDb3partyTerritory() {
        return db3partyTerritory;
    }

    public void setDb3partyTerritory(Db3partyTerritory db3partyTerritory) {
        this.db3partyTerritory = db3partyTerritory;
    }

    public Db3partySales(Integer db3partySaleId) {
        this.db3partySaleId = db3partySaleId;
    }

    public Integer getDb3partySaleId() {
        return db3partySaleId;
    }

    public void setDb3partySaleId(Integer db3partySaleId) {
        this.db3partySaleId = db3partySaleId;
    }

    public String getNameSales() {
        return nameSales;
    }

    public void setNameSales(String nameSales) {
        this.nameSales = nameSales;
    }

    public Db3partyManagers getDb3partyManagerId() {
        return db3partyManagerId;
    }

    public void setDb3partyManagerId(Db3partyManagers db3partyManagerId) {
        this.db3partyManagerId = db3partyManagerId;
    }

    public List<DbOutlets> getDbOutletsList() {
        return dbOutletsList;
    }

    public void setDbOutletsList(List<DbOutlets> dbOutletsList) {
        this.dbOutletsList = dbOutletsList;
    }

    public Db3partyProfiles getDb3PartyProfileId() {
        return db3PartyProfileId;
    }

    public void setDb3PartyProfileId(Db3partyProfiles db3PartyProfileId) {
        this.db3PartyProfileId = db3PartyProfileId;
    }

    public Integer getDbeParty() {
        return dbeParty;
    }

    public void setDbeParty(Integer dbeParty) {
        this.dbeParty = dbeParty;
    }

    public String getPdv() {
        return pdv;
    }

    public void setPdv(String pdv) {
        this.pdv = pdv;
    }

    public String getFocalizado() {
        return focalizado;
    }

    public void setFocalizado(String focalizado) {
        this.focalizado = focalizado;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public List<DbChains> getDbChainsList() {
        return dbChainsList;
    }

    public void setDbChainsList(List<DbChains> dbChainsList) {
        this.dbChainsList = dbChainsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (db3partySaleId != null ? db3partySaleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Db3partySales)) {
            return false;
        }
        Db3partySales other = (Db3partySales) object;
        if ((this.db3partySaleId == null && other.db3partySaleId != null) || (this.db3partySaleId != null && !this.db3partySaleId.equals(other.db3partySaleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.Db3partySales[ db3partySaleId=" + db3partySaleId + " ]";
    }

}
