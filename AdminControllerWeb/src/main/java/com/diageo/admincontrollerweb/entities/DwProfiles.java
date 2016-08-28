/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "DW_PROFILES")
@NamedQueries({
    @NamedQuery(name = "DwProfiles.findAll", query = "SELECT p FROM DwProfiles p"),
    @NamedQuery(name = DwProfiles.FIND_BY_SYSTEM, query = "SELECT p FROM DwProfiles p WHERE p.systemMDM = 1")
})
public class DwProfiles implements Serializable {

    public static final String FIND_BY_SYSTEM = "DwProfiles.findBySystem";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "SQ_DW_PROFILES", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DW_PROFILES", sequenceName = "SQ_DW_PROFILES", allocationSize = 1)
    @Column(name = "PROFILE_ID")
    private Integer profileId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NAME_PROFILE")
    private String nameProfile;
    @Column(name = "ATTEMPT")
    private Integer attempt;
    @ManyToMany(mappedBy = "dwProfilesList")
    private List<DwModules> dwModulesList;
    @OneToMany(mappedBy = "profileId")
    private List<DwUsers> dwUsersList;
    @Column(name = "SYSTEM_MDM")
    private String systemMDM;

    public DwProfiles() {
    }

    public DwProfiles(Integer idperfil) {
        this.profileId = idperfil;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getNameProfile() {
        return nameProfile;
    }

    public void setNameProfile(String nameProfile) {
        this.nameProfile = nameProfile;
    }

    public List<DwModules> getDwModulesList() {
        return dwModulesList;
    }

    public void setDwModulesList(List<DwModules> dwModulesList) {
        this.dwModulesList = dwModulesList;
    }

    public List<DwUsers> getDwUsersList() {
        return dwUsersList;
    }

    public void setDwUsersList(List<DwUsers> dwUsersList) {
        this.dwUsersList = dwUsersList;
    }

    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    public String getSystemMDM() {
        return systemMDM;
    }

    public void setSystemMDM(String systemMDM) {
        this.systemMDM = systemMDM;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profileId != null ? profileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DwProfiles)) {
            return false;
        }
        DwProfiles other = (DwProfiles) object;
        return !((this.profileId == null && other.profileId != null) || (this.profileId != null && !this.profileId.equals(other.profileId)));
    }

    @Override
    public String toString() {
        return "com.diageo.admincontrollerweb.entities.DwProfiles[ profileId=" + profileId + " ]";
    }

}
