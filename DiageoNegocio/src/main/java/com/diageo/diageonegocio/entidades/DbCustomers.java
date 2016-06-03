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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_CUSTOMERS")
@NamedQueries({
    @NamedQuery(name = "DbCustomers.findAll", query = "SELECT d FROM DbCustomers d"),
    @NamedQuery(name = "DbCustomers.findByCustomerId", query = "SELECT d FROM DbCustomers d WHERE d.customerId = :customerId"),
    @NamedQuery(name = "DbCustomers.findByDocumentNumber", query = "SELECT d FROM DbCustomers d WHERE d.documentNumber = :documentNumber"),
    @NamedQuery(name = "DbCustomers.findByNamePerson", query = "SELECT d FROM DbCustomers d WHERE d.namePerson = :namePerson"),
    @NamedQuery(name = "DbCustomers.findByLastName", query = "SELECT d FROM DbCustomers d WHERE d.lastName = :lastName"),
    @NamedQuery(name = "DbCustomers.findByNatural", query = "SELECT d FROM DbCustomers d WHERE d.natural = :natural"),
    @NamedQuery(name = "DbCustomers.findByLegal", query = "SELECT d FROM DbCustomers d WHERE d.legal = :legal")})
public class DbCustomers implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(generator = "SQ_DB_CUSTOMERS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DB_CUSTOMERS", sequenceName = "SQ_DB_CUSTOMERS", allocationSize = 1)
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;
    @Size(max = 50)
    @Column(name = "NAME_PERSON")
    private String namePerson;
    @Size(max = 50)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Size(max = 1)
    @Column(name = "NATURAL")
    private String natural;
    @Size(max = 1)
    @Column(name = "LEGAL")
    private String legal;
    @ManyToMany(mappedBy = "dbCustomersList")
    private List<DbOutlets> dbOutletsList;
    @JoinColumn(name = "TYPE_DOCUMENT", referencedColumnName = "TYPE_DOCUMENT")
    @ManyToOne(optional = false)
    private DbTypeDocuments typeDocument;

    public DbCustomers() {
    }

    public DbCustomers(Integer customerId) {
        this.customerId = customerId;
    }

    public DbCustomers(Integer customerId, String documentNumber) {
        this.customerId = customerId;
        this.documentNumber = documentNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNatural() {
        return natural;
    }

    public void setNatural(String natural) {
        this.natural = natural;
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    public List<DbOutlets> getDbOutletsList() {
        return dbOutletsList;
    }

    public void setDbOutletsList(List<DbOutlets> dbOutletsList) {
        this.dbOutletsList = dbOutletsList;
    }

    public DbTypeDocuments getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(DbTypeDocuments typeDocument) {
        this.typeDocument = typeDocument;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbCustomers)) {
            return false;
        }
        DbCustomers other = (DbCustomers) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbCustomers[ customerId=" + customerId + " ]";
    }
    
}
