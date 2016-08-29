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
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_PHONES")
@NamedQueries({
    @NamedQuery(name = "DbPhones.findAll", query = "SELECT d FROM DbPhones d"),
    @NamedQuery(name = "DbPhones.findByPhoneId", query = "SELECT d FROM DbPhones d WHERE d.phoneId = :phoneId"),
    @NamedQuery(name = "DbPhones.findByNumberPhone", query = "SELECT d FROM DbPhones d WHERE d.numberPhone = :numberPhone")})
public class DbPhones implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(generator = "SQ_DB_PHONES", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DB_PHONES", sequenceName = "SQ_DB_PHONES", allocationSize = 1)
    @Column(name = "PHONE_ID")
    private Integer phoneId;
    @Size(max = 20)
    @Column(name = "NUMBER_PHONE")
    private String numberPhone;
    @ManyToMany(mappedBy = "dbPhonesList")
    private List<DbOutlets> dbOutletsList;
    @ManyToMany(mappedBy = "dbPhonesList")
    private List<DbChains> dbChainsList;
    @JoinColumn(name = "TYPE_PHONE_ID", referencedColumnName = "TYPE_PHONE_ID")
    @ManyToOne(optional = false)
    private DbTypePhones typePhoneId;
    @Transient
    private boolean deleteId;
    @Embedded
    private Audit audit;

    public DbPhones() {
    }

    public DbPhones(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public List<DbOutlets> getDbOutletsList() {
        return dbOutletsList;
    }

    public void setDbOutletsList(List<DbOutlets> dbOutletsList) {
        this.dbOutletsList = dbOutletsList;
    }

    public List<DbChains> getDbChainsList() {
        return dbChainsList;
    }

    public void setDbChainsList(List<DbChains> dbChainsList) {
        this.dbChainsList = dbChainsList;
    }

    public DbTypePhones getTypePhoneId() {
        return typePhoneId;
    }

    public void setTypePhoneId(DbTypePhones typePhoneId) {
        this.typePhoneId = typePhoneId;
    }

    /**
     * @return the deleteId
     */
    public boolean isDeleteId() {
        return deleteId;
    }

    /**
     * @param deleteId the deleteId to set
     */
    public void setDeleteId(boolean deleteId) {
        this.deleteId = deleteId;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phoneId != null ? phoneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbPhones)) {
            return false;
        }
        DbPhones other = (DbPhones) object;
        if (this.phoneId == null || other.phoneId == null) {
            return false;
        }
        return this.phoneId.equals(other.phoneId);
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades[ phoneId=" + phoneId + " ]";
    }

}
