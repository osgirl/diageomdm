/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "DW_USERS", schema = "DIAGEO_WEB")
@NamedQueries({
    @NamedQuery(name = DwUsers.FIND_MAIL, query = "SELECT u FROM DwUsers u WHERE u.emailUser = ?1"),
    @NamedQuery(name = DwUsers.FIND_MAIL_PASS, query = "SELECT u FROM DwUsers u WHERE u.passwordUser = ?1 AND u.emailUser = ?2")})
public class DwUsers implements Serializable {

    public static final String FIND_MAIL_PASS = "DwUsers.findByMailPass";
    public static final String FIND_MAIL = "DwUsers.findByMail";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "SQ_DW_USERS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DW_USERS", sequenceName = "SQ_DW_USERS", allocationSize = 1)
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "DISTRIBUTOR_ID")
    private Integer distributorId;
    @Size(max = 120)
    @Column(name = "EMAIL_USER")
    private String emailUser;
    @Size(max = 45)
    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME_USER")
    private String nameUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Size(max = 32)
    @Column(name = "PASSWORD_USER")
    private String passwordUser;
    @Size(max = 1)
    @Column(name = "STATE_USER")
    private String stateUser;
    @Size(max = 1)
    @Column(name = "FIRST_ENTRY")
    private String firstEntry;
    @Column(name = "FAILED_ATTEMPT")
    private Integer failedAttempt;
    @Column(name = "SUCCESFUL_LOGIN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date succesfulLoginDate;
    @Column(name = "FAILED_LOGIN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date failedLoginDate;
    @Column(name = "LAST_SUCCESFUL_LOGIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSuccesfulLogin;
    @ManyToMany(mappedBy = "dwUsersList")
    private List<DwModules> dwModulesList;
    @JoinColumn(name = "PROFILE_ID", referencedColumnName = "PROFILE_ID")
    @ManyToOne(optional = false)
    private DwProfiles profileId;
    @JoinColumn(name = "DOCUMENT_TYPE_ID", referencedColumnName = "DOCUMENT_TYPE_ID")
    @ManyToOne(optional = false)
    private DwDocumentTypes documentTypeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<DwPasscontainers> dwPasscontainersList;
    @Column(name = "DISTRI_1")
    private String distri1;
    @Embedded    
    private Audit audit;

    public DwUsers() {
    }

    public DwUsers(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getStateUser() {
        return stateUser;
    }

    public void setStateUser(String stateUser) {
        this.stateUser = stateUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public DwDocumentTypes getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(DwDocumentTypes documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public List<DwModules> getDwModulesList() {
        return dwModulesList;
    }

    public void setDwModulesList(List<DwModules> dwModulesList) {
        this.dwModulesList = dwModulesList;
    }

    public DwProfiles getProfileId() {
        return profileId;
    }

    public void setProfileId(DwProfiles profileId) {
        this.profileId = profileId;
    }

    public String getFirstEntry() {
        return firstEntry;
    }

    public void setFirstEntry(String firstEntry) {
        this.firstEntry = firstEntry;
    }

    public Date getSuccesfulLoginDate() {
        return succesfulLoginDate;
    }

    public void setSuccesfulLoginDate(Date succesfulLoginDate) {
        this.succesfulLoginDate = succesfulLoginDate;
    }

    public Date getFailedLoginDate() {
        return failedLoginDate;
    }

    public void setFailedLoginDate(Date failedLoginDate) {
        this.failedLoginDate = failedLoginDate;
    }

    public Integer getFailedAttempt() {
        return failedAttempt;
    }

    public void setFailedAttempt(Integer failedAttempt) {
        this.failedAttempt = failedAttempt;
    }

    public Date getLastSuccesfulLogin() {
        return lastSuccesfulLogin;
    }

    public void setLastSuccesfulLogin(Date lastSuccesfulLogin) {
        this.lastSuccesfulLogin = lastSuccesfulLogin;
    }

    public List<DwPasscontainers> getDwPasscontainersList() {
        return dwPasscontainersList;
    }

    public void setDwPasscontainersList(List<DwPasscontainers> dwPasscontainersList) {
        this.dwPasscontainersList = dwPasscontainersList;
    }

    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer distributorId) {
        this.distributorId = distributorId;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    /**
     * @return the distri1
     */
    public String getDistri1() {
        return distri1;
    }

    /**
     * @param distri1 the distri1 to set
     */
    public void setDistri1(String distri1) {
        this.distri1 = distri1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DwUsers)) {
            return false;
        }
        DwUsers other = (DwUsers) object;
        return !((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId)));
    }

    @Override
    public String toString() {
        return "com.diageo.admincontrollerweb.entities.DwUsers[ userId=" + userId + " ]";
    }

}
