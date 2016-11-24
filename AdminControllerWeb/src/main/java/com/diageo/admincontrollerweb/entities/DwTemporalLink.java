/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DW_TEMPORAL_LINK", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "DwTemporalLink.findByEmail", query = "SELECT d FROM DwTemporalLink d WHERE d.email = ?1 ORDER BY d.creattionDate"),
    @NamedQuery(name = "DwTemporalLink.findByToken", query = "SELECT d FROM DwTemporalLink d WHERE d.token = ?1")
})
public class DwTemporalLink implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_BY_EMAIL = "DwTemporalLink.findByEmail";
    public static final String FIND_BY_TOKEN = "DwTemporalLink.findByToken";
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "SQ_DW_TEMPORAL_LINK", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "SQ_DW_TEMPORAL_LINK", sequenceName = "SQ_DW_TEMPORAL_LINK", allocationSize = 1)
    @Column(name = "TEMPORAL_LINK_ID")
    private BigDecimal temporalLinkId;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 120)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 66)
    @Column(name = "TOKEN")
    private String token;
    @Column(name = "CREATTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creattionDate;
    @Column(name = "EXPIRATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration;
    @Size(max = 1)
    @Column(name = "STATUS")
    private String status;

    public DwTemporalLink() {
    }

    public DwTemporalLink(BigDecimal temporalLinkId) {
        this.temporalLinkId = temporalLinkId;
    }

    public BigDecimal getTemporalLinkId() {
        return temporalLinkId;
    }

    public void setTemporalLinkId(BigDecimal temporalLinkId) {
        this.temporalLinkId = temporalLinkId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreattionDate() {
        return creattionDate;
    }

    public void setCreattionDate(Date creattionDate) {
        this.creattionDate = creattionDate;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (temporalLinkId != null ? temporalLinkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DwTemporalLink)) {
            return false;
        }
        DwTemporalLink other = (DwTemporalLink) object;
        if ((this.temporalLinkId == null && other.temporalLinkId != null) || (this.temporalLinkId != null && !this.temporalLinkId.equals(other.temporalLinkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.admincontrollerweb.entities.DwTemporalLink[ temporalLinkId=" + temporalLinkId + " ]";
    }

}
