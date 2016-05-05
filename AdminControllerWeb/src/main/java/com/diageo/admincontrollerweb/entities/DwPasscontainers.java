/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "DW_PASSCONTAINERS")
@NamedQueries({
    @NamedQuery(name = DwPasscontainers.FIND_BY_USER, query = "SELECT p FROM DwPasscontainers p WHERE p.userId.userId = ?1"),
    @NamedQuery(name = DwPasscontainers.FIND_BY_USER_PASS, query = "SELECT p FROM DwPasscontainers p WHERE p.userId.userId = ?1 AND p.passwordUser = ?2")
})
public class DwPasscontainers implements Serializable {

    public static final String FIND_BY_USER = "DwPasscontainers.findByIdUser";
    public static final String FIND_BY_USER_PASS = "DwPasscontainers.findByIdUserPass";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "SQ_DW_PASSCONTAINERS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DW_PASSCONTAINERS", sequenceName = "SQ_DW_PASSCONTAINERS", allocationSize = 1)
    @Column(name = "PASSCONTAINER_ID")
    private Integer passcontainerId;
    @Size(max = 32)
    @Column(name = "PASSWORD_USER")
    private String passwordUser;
    @Column(name = "UPDATE_PASSWORD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatePassword;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private DwUsers userId;

    public DwPasscontainers() {
    }

    public DwPasscontainers(Integer passContainerPK) {
        this.passcontainerId = passContainerPK;
    }

    public Integer getPasscontainerId() {
        return passcontainerId;
    }

    public void setPasscontainerId(Integer passcontainerId) {
        this.passcontainerId = passcontainerId;
    }

    public Date getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(Date updatePassword) {
        this.updatePassword = updatePassword;
    }

    public DwUsers getUserId() {
        return userId;
    }

    public void setUserId(DwUsers userId) {
        this.userId = userId;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (passcontainerId != null ? passcontainerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DwPasscontainers)) {
            return false;
        }
        DwPasscontainers other = (DwPasscontainers) object;
        return !((this.passcontainerId == null && other.passcontainerId != null) || (this.passcontainerId != null && !this.passcontainerId.equals(other.passcontainerId)));
    }

    @Override
    public String toString() {
        return "com.diageo.admincontrollerweb.entities.DwPasscontainers[ passcontainerId=" + passcontainerId + " ]";
    }

}
