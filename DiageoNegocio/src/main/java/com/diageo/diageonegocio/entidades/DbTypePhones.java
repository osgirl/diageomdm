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
@Table(name = "DB_TYPE_PHONES")
@NamedQueries({
    @NamedQuery(name = "DbTypePhones.findAll", query = "SELECT d FROM DbTypePhones d"),
    @NamedQuery(name = "DbTypePhones.findByTypePhoneId", query = "SELECT d FROM DbTypePhones d WHERE d.typePhoneId = :typePhoneId"),
    @NamedQuery(name = "DbTypePhones.findByNameTypePhone", query = "SELECT d FROM DbTypePhones d WHERE d.nameTypePhone = :nameTypePhone")})
public class DbTypePhones implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TYPE_PHONE_ID")
    private Integer typePhoneId;
    @Size(max = 20)
    @Column(name = "NAME_TYPE_PHONE")
    private String nameTypePhone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typePhoneId")
    private List<DbPhones> dbPhonesList;

    public DbTypePhones() {
    }

    public DbTypePhones(Integer typePhoneId) {
        this.typePhoneId = typePhoneId;
    }

    public Integer getTypePhoneId() {
        return typePhoneId;
    }

    public void setTypePhoneId(Integer typePhoneId) {
        this.typePhoneId = typePhoneId;
    }

    public String getNameTypePhone() {
        return nameTypePhone;
    }

    public void setNameTypePhone(String nameTypePhone) {
        this.nameTypePhone = nameTypePhone;
    }

    public List<DbPhones> getDbPhonesList() {
        return dbPhonesList;
    }

    public void setDbPhonesList(List<DbPhones> dbPhonesList) {
        this.dbPhonesList = dbPhonesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typePhoneId != null ? typePhoneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbTypePhones)) {
            return false;
        }
        DbTypePhones other = (DbTypePhones) object;
        if ((this.typePhoneId == null && other.typePhoneId != null) || (this.typePhoneId != null && !this.typePhoneId.equals(other.typePhoneId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbTypePhones[ typePhoneId=" + typePhoneId + " ]";
    }
    
}
