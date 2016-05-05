/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "DW_DOCUMENT_TYPES")
@NamedQueries({
    @NamedQuery(name = "DwDocumentTypes.findAll", query = "SELECT d FROM DwDocumentTypes d")})
public class DwDocumentTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "SQ_DW_DOCUMENT_TYPES",strategy = GenerationType.SEQUENCE)   
    @SequenceGenerator(name = "SQ_DW_DOCUMENT_TYPES", sequenceName = "SQ_DW_DOCUMENT_TYPES",allocationSize = 1)
    @Column(name = "DOCUMENT_TYPE_ID")
    private Integer documentTypeId;
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NAME_DOCUMENT_TYPE")
    private String nameDocumentType;
    @Size(max = 45)
    @Column(name = "DESCRIPTION_DOC_TYPE")
    private String descriptionDocType;
    @OneToMany(mappedBy = "documentTypeId")
    private List<DwUsers> dwUsersList;

    public DwDocumentTypes() {
    }

    public DwDocumentTypes(Integer idtipoDoc) {
        this.documentTypeId = idtipoDoc;
    }

    public Integer getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Integer documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getNameDocumentType() {
        return nameDocumentType;
    }

    public void setNameDocumentType(String nameDocumentType) {
        this.nameDocumentType = nameDocumentType;
    }

    public String getDescriptionDocType() {
        return descriptionDocType;
    }

    public void setDescriptionDocType(String descriptionDocType) {
        this.descriptionDocType = descriptionDocType;
    }

    public List<DwUsers> getDwUsersList() {
        return dwUsersList;
    }

    public void setDwUsersList(List<DwUsers> dwUsersList) {
        this.dwUsersList = dwUsersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentTypeId != null ? documentTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DwDocumentTypes)) {
            return false;
        }
        DwDocumentTypes other = (DwDocumentTypes) object;
        return !((this.documentTypeId == null && other.documentTypeId != null) || (this.documentTypeId != null && !this.documentTypeId.equals(other.documentTypeId)));
    }

    @Override
    public String toString() {
        return "com.diageo.admincontrollerweb.entities.DwDocumentTypes[ documentTypeId=" + documentTypeId + " ]";
    }

}
