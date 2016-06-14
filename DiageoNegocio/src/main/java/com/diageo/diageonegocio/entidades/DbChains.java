/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "DB_CHAINS")
@NamedQueries({
    @NamedQuery(name = "DbChains.findAll", query = "SELECT d FROM DbChains d"),
    @NamedQuery(name = "DbChains.findByChainId", query = "SELECT d FROM DbChains d WHERE d.chainId = :chainId"),
    @NamedQuery(name = "DbChains.findByNameChain", query = "SELECT d FROM DbChains d WHERE d.nameChain = :nameChain"),
    @NamedQuery(name = "DbChains.findByCodePdv", query = "SELECT d FROM DbChains d WHERE d.codePdv = :codePdv"),
    @NamedQuery(name = "DbChains.findByCodeEan", query = "SELECT d FROM DbChains d WHERE d.codeEan = :codeEan"),
    @NamedQuery(name = "DbChains.findByIsFather", query = "SELECT d FROM DbChains d WHERE d.isFather = :isFather"),
    @NamedQuery(name = "DbChains.findByKiernanId", query = "SELECT d FROM DbChains d WHERE d.kiernanId = :kiernanId")})
public class DbChains implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(generator = "SQ_DB_OUTLETS_CHAINS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DB_OUTLETS_CHAINS", sequenceName = "SQ_DB_OUTLETS_CHAINS", allocationSize = 1)
    @Column(name = "CHAIN_ID")
    private Integer chainId;
    @Size(max = 100)
    @Column(name = "NAME_CHAIN")
    private String nameChain;
    @Size(max = 20)
    @Column(name = "CODE_PDV")
    private String codePdv;
    @Size(max = 20)
    @Column(name = "CODE_EAN")
    private String codeEan;
    @Size(max = 1)
    @Column(name = "IS_FATHER")
    private String isFather;
    @Size(max = 255)
    @Column(name = "KIERNAN_ID")
    private String kiernanId;
    @ManyToMany(mappedBy = "dbChainsList")
    private List<Db3party> db3partyList;
    @JoinTable(name = "DB_CHAINS_PHONES", joinColumns = {
        @JoinColumn(name = "CHAIN_ID", referencedColumnName = "CHAIN_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PHONE_ID", referencedColumnName = "PHONE_ID")})
    @ManyToMany
    private List<DbPhones> dbPhonesList;
    @JoinColumn(name = "DB_3PARTY_SALE_ID", referencedColumnName = "DB_3PARTY_SALE_ID")
    @ManyToOne(optional = false)
    private Db3partySales db3partySaleId;
    @JoinColumn(name = "SUB_SEGMENT_ID", referencedColumnName = "SUB_SEGMENT_ID")
    @ManyToOne(optional = false)
    private DbSubSegments subSegmentId;
    @JoinColumn(name = "POTENTIAL_ID", referencedColumnName = "POTENTIAL_ID")
    @ManyToOne(optional = false)
    private DbPotentials potentialId;    
    @JoinColumn(name = "DB_CLUSTER_ID", referencedColumnName = "DB_CLUSTER_ID")
    @ManyToOne(optional = false)
    private DbClusters dbClusterId;
    @OneToMany(mappedBy = "chainIdFather")
    private List<DbChains> dbChainsList;
    @JoinColumn(name = "CHAIN_ID_FATHER", referencedColumnName = "CHAIN_ID")
    @ManyToOne
    private DbChains chainIdFather;

    public DbChains() {
    }

    public DbChains(Integer chainId) {
        this.chainId = chainId;
    }

    public Integer getChainId() {
        return chainId;
    }

    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }

    public String getNameChain() {
        return nameChain;
    }

    public void setNameChain(String nameChain) {
        this.nameChain = nameChain;
    }

    public String getCodePdv() {
        return codePdv;
    }

    public void setCodePdv(String codePdv) {
        this.codePdv = codePdv;
    }

    public String getCodeEan() {
        return codeEan;
    }

    public void setCodeEan(String codeEan) {
        this.codeEan = codeEan;
    }

    public String getIsFather() {
        return isFather;
    }

    public void setIsFather(String isFather) {
        this.isFather = isFather;
    }

    public String getKiernanId() {
        return kiernanId;
    }

    public void setKiernanId(String kiernanId) {
        this.kiernanId = kiernanId;
    }

    public List<Db3party> getDb3partyList() {
        return db3partyList;
    }

    public void setDb3partyList(List<Db3party> db3partyList) {
        this.db3partyList = db3partyList;
    }

    public List<DbPhones> getDbPhonesList() {
        return dbPhonesList;
    }

    public void setDbPhonesList(List<DbPhones> dbPhonesList) {
        this.dbPhonesList = dbPhonesList;
    }

    public Db3partySales getDb3partySaleId() {
        return db3partySaleId;
    }

    public void setDb3partySaleId(Db3partySales db3partySaleId) {
        this.db3partySaleId = db3partySaleId;
    }

    public DbSubSegments getSubSegmentId() {
        return subSegmentId;
    }

    public void setSubSegmentId(DbSubSegments subSegmentId) {
        this.subSegmentId = subSegmentId;
    }

    public DbPotentials getPotentialId() {
        return potentialId;
    }

    public void setPotentialId(DbPotentials potentialId) {
        this.potentialId = potentialId;
    }

    public DbClusters getDbClusterId() {
        return dbClusterId;
    }

    public void setDbClusterId(DbClusters dbClusterId) {
        this.dbClusterId = dbClusterId;
    }

    public List<DbChains> getDbChainsList() {
        return dbChainsList;
    }

    public void setDbChainsList(List<DbChains> dbChainsList) {
        this.dbChainsList = dbChainsList;
    }

    public DbChains getChainIdFather() {
        return chainIdFather;
    }

    public void setChainIdFather(DbChains chainIdFather) {
        this.chainIdFather = chainIdFather;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chainId != null ? chainId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbChains)) {
            return false;
        }
        DbChains other = (DbChains) object;
        if ((this.chainId == null && other.chainId != null) || (this.chainId != null && !this.chainId.equals(other.chainId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbChains[ chainId=" + chainId + " ]";
    }
    
}
