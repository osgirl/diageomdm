/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_CUSTOMERS")
public class DbCustomers implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(generator = "SQ_DB_CUSTOMERS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DB_CUSTOMERS", sequenceName = "SQ_DB_CUSTOMERS", allocationSize = 1)
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Column(name = "NUMBER_PDV")
    private String numberPdv;
    @Column(name = "KIERNAN_ID")
    private String kiernanId;
    @Column(name = "ADDRESS")
    private String address;
    @JoinColumn(name = "TOWN_ID", referencedColumnName = "TOWN_ID")
    @ManyToOne
    private DbTowns townId;
    @Column(name = "STATUS_CUSTOMER")
    private String statusCustomer;
    @JoinTable(name = "DB_CUSTOMERS_OUTLETS", joinColumns = {
        @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "OUTLET_ID", referencedColumnName = "OUTLET_ID")})
    @ManyToMany
    private List<DbOutlets> dbOutletsList;
    @ManyToMany(mappedBy = "dbCustomerList")
    private List<DbChains> sbChainsList;
    @Column(name = "NIT")
    private String nit;
    @Column(name = "VERIFICATION_NUMBER")
    private String verificationNumber;

    public DbCustomers() {
    }

    public DbCustomers(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNumberPdv() {
        return numberPdv;
    }

    public void setNumberPdv(String numberPdv) {
        this.numberPdv = numberPdv;
    }

    public String getKiernanId() {
        return kiernanId;
    }

    public void setKiernanId(String kiernanId) {
        this.kiernanId = kiernanId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatusCustomer() {
        return statusCustomer;
    }

    public void setStatusCustomer(String statusCustomer) {
        this.statusCustomer = statusCustomer;
    }

    public List<DbOutlets> getDbOutletsList() {
        return dbOutletsList;
    }

    public void setDbOutletsList(List<DbOutlets> dbOutletsList) {
        this.dbOutletsList = dbOutletsList;
    }

    public DbTowns getTownId() {
        return townId;
    }

    public void setTownId(DbTowns townId) {
        this.townId = townId;
    }

    public List<DbChains> getSbChainsList() {
        return sbChainsList;
    }

    public void setSbChainsList(List<DbChains> sbChainsList) {
        this.sbChainsList = sbChainsList;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getVerificationNumber() {
        return verificationNumber;
    }

    public void setVerificationNumber(String verificationNumber) {
        this.verificationNumber = verificationNumber;
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
