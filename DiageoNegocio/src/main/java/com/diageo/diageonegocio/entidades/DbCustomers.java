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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

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
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 20)
    @Column(name = "NIT")
    private String nit;
    @Size(max = 200)
    @Column(name = "BUSINESS_NAME")
    private String businessName;
    @Size(max = 100)
    @Column(name = "BUSINESS_LINE")
    private String businessLine;
    @Size(max = 200)
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Size(max = 100)
    @Column(name = "ASSOCIATED_MARKET")
    private String associatedMarket;
    @Size(max = 20)
    @Column(name = "EAN_CODE")
    private String eanCode;
    @Size(max = 20)
    @Column(name = "NUMBER_PDV")
    private String numberPdv;
    @Size(max = 1)
    @Column(name = "TYPE_CUSTOMER")
    private String typeCustomer;
    @Column(name = "STATE_CUSTOMER_ID")
    private Integer stateCustomerId;
    @Size(max = 1)
    @Column(name = "IS_NEW_CUSTOMER")
    private String isNewCustomer;
    @Size(max = 255)
    @Column(name = "REJECT_MESSAGE")
    private String rejectMessage;
    @Size(max = 255)
    @Column(name = "KIERNAN_ID")
    private String kiernanId;
    @Size(max = 1)
    @Column(name = "RLA")
    private String rla;
    @Size(max = 1)
    @Column(name = "TLA")
    private String tla;
    @Size(max = 200)
    @Column(name = "WEBSITE")
    private String website;
    @Size(max = 2)
    @Column(name = "VERIFICATION_NUMBER")
    private String verificationNumber;
    @Size(max = 20)
    @Column(name = "STATUS_ZRT")
    private String statusZrt;
    @Size(max = 100)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 100)
    @Column(name = "NEIGHBORHOOD")
    private String neighborhood;
    @Size(max = 45)
    @Column(name = "GEOGRAPHIC_LOCATION")
    private String geographicLocation;
    @Column(name = "LATITUDE")
    private Integer latitude;
    @Column(name = "LONGITUDE")
    private Integer longitude;
    @Size(max = 1)
    @Column(name = "JOURNEY_PLAN")
    private String journeyPlan;
    @Size(max = 1)
    @Column(name = "IS_FATHER")
    private String isFather;
    @Size(max = 1)
    @Column(name = "STATUS_CUSTOMER")
    private String statusCustomer;
    @JoinTable(name = "DB_CUSTOMERS_OUTLETS", joinColumns = {
        @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "OUTLET_ID", referencedColumnName = "OUTLET_ID")})
    @ManyToMany
    private List<DbOutlets> dbOutletsList;
    @JoinColumn(name = "DB_3PARTY_SALE_ID", referencedColumnName = "DB_3PARTY_SALE_ID")
    @ManyToOne
    private Db3partySales db3partySaleId;
    @JoinColumn(name = "TOWN_ID", referencedColumnName = "TOWN_ID")
    @ManyToOne
    private DbTowns townId;
    @JoinColumn(name = "SUB_SEGMENT_ID", referencedColumnName = "SUB_SEGMENT_ID")
    @ManyToOne(optional = false)
    private DbSubSegments subSegmentId;
    @JoinColumn(name = "POTENTIAL_ID", referencedColumnName = "POTENTIAL_ID")
    @ManyToOne(optional = false)
    private DbPotentials potentialId;
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "OWNER_ID")
    @ManyToOne
    private DbOwners ownerId;
    @JoinColumn(name = "OCS_SECONDARY", referencedColumnName = "OCS_ID")
    @ManyToOne
    private DbOcs ocsSecondary;
    @JoinColumn(name = "OCS_PRIMARY", referencedColumnName = "OCS_ID")
    @ManyToOne
    private DbOcs ocsPrimary;    
    @ManyToMany(mappedBy = "dbCustomerList")
    private List<DbChains> sbChainsList;

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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessLine() {
        return businessLine;
    }

    public void setBusinessLine(String businessLine) {
        this.businessLine = businessLine;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAssociatedMarket() {
        return associatedMarket;
    }

    public void setAssociatedMarket(String associatedMarket) {
        this.associatedMarket = associatedMarket;
    }

    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    public String getNumberPdv() {
        return numberPdv;
    }

    public void setNumberPdv(String numberPdv) {
        this.numberPdv = numberPdv;
    }

    public String getTypeCustomer() {
        return typeCustomer;
    }

    public void setTypeCustomer(String typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    public Integer getStateCustomerId() {
        return stateCustomerId;
    }

    public void setStateCustomerId(Integer stateCustomerId) {
        this.stateCustomerId = stateCustomerId;
    }

    public String getIsNewCustomer() {
        return isNewCustomer;
    }

    public void setIsNewCustomer(String isNewCustomer) {
        this.isNewCustomer = isNewCustomer;
    }

    public String getRejectMessage() {
        return rejectMessage;
    }

    public void setRejectMessage(String rejectMessage) {
        this.rejectMessage = rejectMessage;
    }

    public String getKiernanId() {
        return kiernanId;
    }

    public void setKiernanId(String kiernanId) {
        this.kiernanId = kiernanId;
    }

    public String getRla() {
        return rla;
    }

    public void setRla(String rla) {
        this.rla = rla;
    }

    public String getTla() {
        return tla;
    }

    public void setTla(String tla) {
        this.tla = tla;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getVerificationNumber() {
        return verificationNumber;
    }

    public void setVerificationNumber(String verificationNumber) {
        this.verificationNumber = verificationNumber;
    }

    public String getStatusZrt() {
        return statusZrt;
    }

    public void setStatusZrt(String statusZrt) {
        this.statusZrt = statusZrt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getGeographicLocation() {
        return geographicLocation;
    }

    public void setGeographicLocation(String geographicLocation) {
        this.geographicLocation = geographicLocation;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public String getJourneyPlan() {
        return journeyPlan;
    }

    public void setJourneyPlan(String journeyPlan) {
        this.journeyPlan = journeyPlan;
    }

    public String getIsFather() {
        return isFather;
    }

    public void setIsFather(String isFather) {
        this.isFather = isFather;
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

    public Db3partySales getDb3partySaleId() {
        return db3partySaleId;
    }

    public void setDb3partySaleId(Db3partySales db3partySaleId) {
        this.db3partySaleId = db3partySaleId;
    }

    public DbTowns getTownId() {
        return townId;
    }

    public void setTownId(DbTowns townId) {
        this.townId = townId;
    }

    public DbSubSegments getSubSegmentId() {
        return subSegmentId;
    }

    public void setSubSegmentId(DbSubSegments subSegmentId) {
        this.subSegmentId = subSegmentId;
    }

    public DbPotentials getPotentialId() {
        return potentialId;
    }

    public void setPotentialId(DbPotentials potentialId) {
        this.potentialId = potentialId;
    }

    public DbOwners getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(DbOwners ownerId) {
        this.ownerId = ownerId;
    }

    public DbOcs getOcsSecondary() {
        return ocsSecondary;
    }

    public void setOcsSecondary(DbOcs ocsSecondary) {
        this.ocsSecondary = ocsSecondary;
    }

    public DbOcs getOcsPrimary() {
        return ocsPrimary;
    }

    public void setOcsPrimary(DbOcs ocsPrimary) {
        this.ocsPrimary = ocsPrimary;
    }

    public List<DbChains> getSbChainsList() {
        return sbChainsList;
    }

    public void setSbChainsList(List<DbChains> sbChainsList) {
        this.sbChainsList = sbChainsList;
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
