/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_TYPE_DOCUMENTS")
@NamedQueries({
    @NamedQuery(name = "DbTypeDocuments.findAll", query = "SELECT d FROM DbTypeDocuments d"),
    @NamedQuery(name = "DbTypeDocuments.findByTypeDocument", query = "SELECT d FROM DbTypeDocuments d WHERE d.typeDocument = :typeDocument"),
    @NamedQuery(name = "DbTypeDocuments.findByNameTypeDocument", query = "SELECT d FROM DbTypeDocuments d WHERE d.nameTypeDocument = :nameTypeDocument")})
public class DbTypeDocuments implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TYPE_DOCUMENT")
    private Integer typeDocument;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NAME_TYPE_DOCUMENT")
    private String nameTypeDocument;    

    public DbTypeDocuments() {
    }

    public DbTypeDocuments(Integer typeDocument) {
        this.typeDocument = typeDocument;
    }

    public DbTypeDocuments(Integer typeDocument, String nameTypeDocument) {
        this.typeDocument = typeDocument;
        this.nameTypeDocument = nameTypeDocument;
    }

    public Integer getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(Integer typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getNameTypeDocument() {
        return nameTypeDocument;
    }

    public void setNameTypeDocument(String nameTypeDocument) {
        this.nameTypeDocument = nameTypeDocument;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeDocument != null ? typeDocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbTypeDocuments)) {
            return false;
        }
        DbTypeDocuments other = (DbTypeDocuments) object;
        if ((this.typeDocument == null && other.typeDocument != null) || (this.typeDocument != null && !this.typeDocument.equals(other.typeDocument))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbTypeDocuments[ typeDocument=" + typeDocument + " ]";
    }
    
}
