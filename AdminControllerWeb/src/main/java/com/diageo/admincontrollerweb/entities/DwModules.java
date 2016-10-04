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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "DW_MODULES")
@NamedQueries({
    @NamedQuery(name = "DwModules.findAll", query = "SELECT m FROM DwModules m")})
public class DwModules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "SQ_DW_MODULES", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DW_MODULES", sequenceName = "SQ_DW_MODULES", allocationSize = 1)
    @Column(name = "MODULE_ID")
    private BigDecimal moduleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "KEK_MODULE")
    private String kekModule;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NAME_MODULE")
    private String nameModule;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "IS_FATHER")
    private String isFather;
    @JoinColumn(name = "FATHER_MODULE_ID", referencedColumnName = "MODULE_ID")
    @ManyToOne
    private DwModules fatherModuleId;
    @Size(max = 150)
    @Column(name = "URL_MODULE")
    private String urlModule;
    @JoinTable(name = "DW_MODULES_PROFILES", joinColumns = {
        @JoinColumn(name = "MODULE_ID", referencedColumnName = "MODULE_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PROFILE_ID", referencedColumnName = "PROFILE_ID")})
    @ManyToMany
    private List<DwProfiles> dwProfilesList;
    @JoinTable(name = "DW_USERS_MODULES", joinColumns = {
        @JoinColumn(name = "MODULE_ID", referencedColumnName = "MODULE_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")})
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<DwUsers> dwUsersList;
    @OneToMany(mappedBy = "fatherModuleId")
    private List<DwModules> dwModulesList;

    public DwModules() {
    }

    public DwModules(BigDecimal moduleId) {
        this.moduleId = moduleId;
    }

    public BigDecimal getModuleId() {
        return moduleId;
    }

    public void setModuleId(BigDecimal moduleId) {
        this.moduleId = moduleId;
    }

    public String getNameModule() {
        return nameModule;
    }

    public void setNameModule(String nameModule) {
        this.nameModule = nameModule;
    }

    public String getIsFather() {
        return isFather;
    }

    public void setIsFather(String isFather) {
        this.isFather = isFather;
    }

    public String getUrlModule() {
        return urlModule;
    }

    public void setUrlModule(String urlModule) {
        this.urlModule = urlModule;
    }

    public List<DwUsers> getDwUsersList() {
        return dwUsersList;
    }

    public void setDwUsersList(List<DwUsers> dwUsersList) {
        this.dwUsersList = dwUsersList;
    }

    public List<DwProfiles> getDwProfilesList() {
        return dwProfilesList;
    }

    public void setDwProfilesList(List<DwProfiles> dwProfilesList) {
        this.dwProfilesList = dwProfilesList;
    }

    public List<DwModules> getDwModulesList() {
        return dwModulesList;
    }

    public void setDwModulesList(List<DwModules> dwModulesList) {
        this.dwModulesList = dwModulesList;
    }

    public DwModules getFatherModuleId() {
        return fatherModuleId;
    }

    public void setFatherModuleId(DwModules fatherModuleId) {
        this.fatherModuleId = fatherModuleId;
    }

    public String getKekModule() {
        return kekModule;
    }

    public void setKekModule(String kekModule) {
        this.kekModule = kekModule;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moduleId != null ? moduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DwModules)) {
            return false;
        }
        DwModules other = (DwModules) object;
        return !((this.moduleId == null && other.moduleId != null) || (this.moduleId != null && !this.moduleId.equals(other.moduleId)));
    }

    @Override
    public String toString() {
        return "com.diageo.admincontrollerweb.entities.DwModules[ moduleId=" + moduleId + " ]";
    }

}
