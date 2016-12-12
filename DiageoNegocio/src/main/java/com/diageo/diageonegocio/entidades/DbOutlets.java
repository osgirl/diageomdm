/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_OUTLETS")
@NamedQueries({
    @NamedQuery(name = DbOutlets.FIND_ALL, query = "SELECT e FROM DbOutlets e "
            + "WHERE e.nit LIKE :nit "
            + "AND e.businessName LIKE :businessName "
            + "AND e.numberPdv LIKE :numberPdv "
            + "AND e.kiernanId LIKE :kiernanId "
            + "AND e.subSegmentId.nameSubsegment LIKE :nameSubsegment "
            + "AND e.statusOutlet LIKE :statusOutlet "
            + "AND e.statusMDM LIKE :statusMDM"),
    @NamedQuery(name = DbOutlets.FIND_ALL_COUNT, query = "SELECT COUNT(e) FROM DbOutlets e "
            + "WHERE e.nit LIKE :nit "
            + "AND e.businessName LIKE :businessName "
            + "AND e.numberPdv LIKE :numberPdv "
            + "AND e.kiernanId LIKE :kiernanId "
            + "AND e.subSegmentId.nameSubsegment LIKE :nameSubsegment "
            + "AND e.statusOutlet LIKE :statusOutlet "
            + "AND e.statusMDM LIKE :statusMDM"),
    @NamedQuery(name = DbOutlets.FIND_BY_NEW,
            query = "SELECT e FROM DbOutlets e WHERE e.isNewOutlet = ?1"),
    @NamedQuery(name = DbOutlets.FIND_BY_SUB_SEGMENT, query = "SELECT o FROM DbOutlets o WHERE o.subSegmentId.subSegmentId=?1"),
    @NamedQuery(name = DbOutlets.FIND_BY_BUSINESS_NAME, query = "SELECT o FROM DbOutlets o WHERE o.businessName LIKE ?1 AND o.isFather = ?2"),
    @NamedQuery(name = DbOutlets.FIND_BY_SUB_SEGMENT_3PARTY, query = "SELECT o FROM DbOutlets o "
            + "WHERE o.subSegmentId.subSegmentId=?1 AND o.db3PartyIdNew=?2"),
    @NamedQuery(name = DbOutlets.FIND_BY_3PARTY_PERMISSION, query = "SELECT o FROM DbOutlets o "
            + "WHERE o.db3PartyIdNew=:db3partyId AND o.subSegmentId.subSegmentId IN :subSegmentId AND o.statusMDM  IN :statusMDM "
            + "AND o.nit LIKE :nit "
            + "AND o.businessName LIKE :businessName "
            + "AND o.numberPdv LIKE :numberPdv "
            + "AND o.kiernanId LIKE :kiernanId "
            + "AND o.subSegmentId.nameSubsegment LIKE :nameSubsegment "
            + "AND o.statusOutlet LIKE :statusOutlet "),
    @NamedQuery(name = DbOutlets.FIND_BY_3PARTY_PERMISSION_LIST, query = "SELECT o FROM DbOutlets o  "
            + "WHERE o.db3PartyIdNew=?1 AND o.subSegmentId.subSegmentId IN ?2 AND o.statusMDM  IN ?3"),
    @NamedQuery(name = DbOutlets.FIND_BY_3PARTY_PERMISSION_COUNT, query = "SELECT COUNT (o) FROM DbOutlets o "
            + "WHERE o.db3PartyIdNew=:db3partyId AND o.subSegmentId.subSegmentId IN :subSegmentId AND o.statusMDM  IN :statusMDM "
            + "AND o.nit LIKE :nit "
            + "AND o.businessName LIKE :businessName "
            + "AND o.numberPdv LIKE :numberPdv "
            + "AND o.kiernanId LIKE :kiernanId "
            + "AND o.subSegmentId.nameSubsegment LIKE :nameSubsegment "
            + "AND o.statusOutlet LIKE :statusOutlet"),
    @NamedQuery(name = DbOutlets.COUNT, query = "SELECT COUNT (o) FROM DbOutlets o")
})
public class DbOutlets implements Serializable {

    public static final String FIND_ALL = "DbOutlets.findAll";
    public static final String FIND_ALL_COUNT = "DbOutlets.findAllCount";
    public static final String COUNT = "DbOutlets.count";
    public static final String FIND_BY_DISTRI = "DbOutlets.findByDistributor";
    public static final String FIND_BY_NEW = "DbOutlets.findByNew";
    public static final String FIND_BY_SUB_SEGMENT = "DbOutlets.findBySubSegment";
    public static final String FIND_BY_SUB_SEGMENT_3PARTY = "DbOutlets.findBySubSegment3Party";
    public static final String FIND_BY_BUSINESS_NAME = "DbOutlets.findByBusinessName";
    public static final String FIND_BY_3PARTY_PERMISSION = "DbOutlets.findBy3PartyPermission";
    public static final String FIND_BY_3PARTY_PERMISSION_LIST = "DbOutlets.findBy3PartyPermissionList";
    public static final String FIND_BY_3PARTY_PERMISSION_COUNT = "DbOutlets.findBy3PartyPermissionCount";
    /**
     * search for distributor, subsegment, is new, and state outlets
     */
    public static final String FIND_BY_DISTRI_SUBSEGMENT = "Outlet.findByDistriSubsegment";
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "SQ_DB_OUTLETS_CHAINS", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "SQ_DB_OUTLETS_CHAINS", sequenceName = "SQ_DB_OUTLETS_CHAINS", allocationSize = 1)
    @Column(name = "OUTLET_ID")
    private Integer outletId;
    @JoinColumn(name = "SUB_SEGMENT_ID", referencedColumnName = "SUB_SEGMENT_ID")
    @ManyToOne(optional = false)
    private DbSubSegments subSegmentId;
    @JoinColumn(name = "DISTRIBUTOR_SUB_SEGMENT_ID", referencedColumnName = "SUB_SEGMENT_ID")
    @ManyToOne(optional = false)
    private DbSubSegments distributorSubSegmentId;
    @JoinColumn(name = "POTENTIAL_ID", referencedColumnName = "POTENTIAL_ID")
    @ManyToOne(optional = false)
    private DbPotentials potentialId;
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
    @Size(max = 200)
    @Column(name = "OUTLET_NAME")
    private String outletName;
    @Size(max = 20)
    @Column(name = "NUMBER_PDV")
    private String numberPdv;
    @Size(max = 1)
    @Column(name = "TYPE_OUTLET")
    private String typeOutlet;
    @Size(max = 1)
    @Column(name = "IS_NEW_OUTLET")
    private String isNewOutlet;
    @Size(max = 255)
    @Column(name = "REJECT_MESSAGE")
    private String rejectMessage;
    @Size(max = 255)
    @Column(name = "KIERNAN_ID")
    private String kiernanId;
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "OWNER_ID")
    @ManyToOne
    private DbOwners ownerId;
    @JoinColumn(name = "OCS_PRIMARY", referencedColumnName = "OCS_ID")
    @ManyToOne
    private DbOcs ocsPrimary;
    @JoinColumn(name = "OCS_SECONDARY", referencedColumnName = "OCS_ID")
    @ManyToOne
    private DbOcs ocsSecondary;
    @JoinColumn(name = "DB_3PARTY_SALE_ID", referencedColumnName = "DB_3PARTY_SALE_ID")
    @ManyToOne(optional = false)
    private Db3partySales db3partySaleId;
    @Column(name = "WEBSITE")
    private String website;
    @Column(name = "VERIFICATION_NUMBER")
    private String verificationNumber;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "NEIGHBORHOOD")
    private String neighborhood;
    @Column(name = "LATITUDE")
    private Double latitude;
    @Column(name = "LONGITUDE")
    private Double longitude;
    @ManyToOne
    @JoinColumn(name = "TOWN_ID")
    private DbTowns townId;
    @Column(name = "JOURNEY_PLAN")
    private String journeyPlan;
    @Column(name = "IS_FATHER")
    private String isFather;
    @ManyToOne
    @JoinColumn(name = "OUTLET_ID_FATHER")
    private DbOutlets outletIdFather;
    @OneToMany(mappedBy = "outletIdFather")
    private List<DbOutlets> listOutletFather;
    @Column(name = "STATUS_OUTLET")
    private String statusOutlet;
    @JoinTable(name = "DB_CUSTOMERS_OUTLETS", joinColumns = {
        @JoinColumn(name = "OUTLET_ID", referencedColumnName = "OUTLET_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")})
    @ManyToMany
    private List<DbCustomers> dbCustomersList;
    @JoinTable(name = "DB_OUTLETS_PHONES", joinColumns = {
        @JoinColumn(name = "OUTLET_ID", referencedColumnName = "OUTLET_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PHONE_ID", referencedColumnName = "PHONE_ID")})
    @ManyToMany
    private List<DbPhones> dbPhonesList;
    @Column(name = "WINE")
    private String wine;
    @Column(name = "BEER")
    private String beer;
    @Column(name = "SPIRTIS")
    private String spirtis;
    @Column(name = "STATUS_MDM")
    private String statusMDM;
    @Column(name = "DB_3PARTY_ID_OLD")
    private Integer db3PartyIdOld;
    @Column(name = "DB_3PARTY_ID_NEW")
    private Integer db3PartyIdNew;
    @Column(name = "SUB_SEGMENT_ID_ATHENA")    
    private Integer subSegmentIdAthena;
    //TRANSIENT
    @Transient
    private boolean disabledButtonEdit;
    @Transient
    private boolean approbationMassive;
    @Transient
    private boolean renderedApprobationMassive;
    @Embedded
    private Audit audit;

    public DbOutlets() {
    }

    public DbOutlets(Integer outletId) {
        this.outletId = outletId;
    }

    public Integer getOutletId() {
        return outletId;
    }

    public void setOutletId(Integer outletId) {
        this.outletId = outletId;
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

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getNumberPdv() {
        return numberPdv;
    }

    public void setNumberPdv(String numberPdv) {
        this.numberPdv = numberPdv;
    }

    public String getTypeOutlet() {
        return typeOutlet;
    }

    public void setTypeOutlet(String typeOutlet) {
        this.typeOutlet = typeOutlet;
    }

    public String getIsNewOutlet() {
        return isNewOutlet;
    }

    public void setIsNewOutlet(String isNewOutlet) {
        this.isNewOutlet = isNewOutlet;
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

    public List<DbCustomers> getDbCustomersList() {
        return dbCustomersList;
    }

    public void setDbCustomersList(List<DbCustomers> dbCustomersList) {
        this.dbCustomersList = dbCustomersList;
    }

    public List<DbPhones> getDbPhonesList() {
        return dbPhonesList;
    }

    public void setDbPhonesList(List<DbPhones> dbPhonesList) {
        this.dbPhonesList = dbPhonesList;
    }

    public Db3partySales getDb3partySaleId() {
        return db3partySaleId;
    }

    public void setDb3partySaleId(Db3partySales db3partySaleId) {
        this.db3partySaleId = db3partySaleId;
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

    public DbOcs getOcsPrimary() {
        return ocsPrimary;
    }

    public void setOcsPrimary(DbOcs ocsPrimary) {
        this.ocsPrimary = ocsPrimary;
    }

    public DbOcs getOcsSecondary() {
        return ocsSecondary;
    }

    public void setOcsSecondary(DbOcs ocsSecondary) {
        this.ocsSecondary = ocsSecondary;
    }

    public boolean isDisabledButtonEdit() {
        return disabledButtonEdit;
    }

    public void setDisabledButtonEdit(boolean disabledButtonEdit) {
        this.disabledButtonEdit = disabledButtonEdit;
    }

    public boolean isApprobationMassive() {
        return approbationMassive;
    }

    public void setApprobationMassive(boolean approbationMassive) {
        this.approbationMassive = approbationMassive;
    }

    public boolean isRenderedApprobationMassive() {
        return renderedApprobationMassive;
    }

    public void setRenderedApprobationMassive(boolean renderedApprobationMassive) {
        this.renderedApprobationMassive = renderedApprobationMassive;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public DbTowns getTownId() {
        return townId;
    }

    public void setTownId(DbTowns townId) {
        this.townId = townId;
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

    public String getStatusOutlet() {
        return statusOutlet;
    }

    public void setStatusOutlet(String statusOutlet) {
        this.statusOutlet = statusOutlet;
    }

    public DbOutlets getOutletIdFather() {
        return outletIdFather;
    }

    public void setOutletIdFather(DbOutlets outletIdFather) {
        this.outletIdFather = outletIdFather;
    }

    public List<DbOutlets> getListOutletFather() {
        return listOutletFather;
    }

    public void setListOutletFather(List<DbOutlets> listOutletFather) {
        this.listOutletFather = listOutletFather;
    }

    /**
     * @return the wine
     */
    public String getWine() {
        return wine;
    }

    /**
     * @param wine the wine to set
     */
    public void setWine(String wine) {
        this.wine = wine;
    }

    /**
     * @return the beer
     */
    public String getBeer() {
        return beer;
    }

    /**
     * @param beer the beer to set
     */
    public void setBeer(String beer) {
        this.beer = beer;
    }

    /**
     * @return the spirtis
     */
    public String getSpirtis() {
        return spirtis;
    }

    /**
     * @param spirtis the spirtis to set
     */
    public void setSpirtis(String spirtis) {
        this.spirtis = spirtis;
    }

    public String getStatusMDM() {
        return statusMDM;
    }

    public void setStatusMDM(String statusMDM) {
        this.statusMDM = statusMDM;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public DbSubSegments getDistributorSubSegmentId() {
        return distributorSubSegmentId;
    }

    public void setDistributorSubSegmentId(DbSubSegments distributorSubSegmentId) {
        this.distributorSubSegmentId = distributorSubSegmentId;
    }

    public Integer getDb3PartyIdOld() {
        return db3PartyIdOld;
    }

    public void setDb3PartyIdOld(Integer db3PartyIdOld) {
        this.db3PartyIdOld = db3PartyIdOld;
    }

    public Integer getDb3PartyIdNew() {
        return db3PartyIdNew;
    }

    public void setDb3PartyIdNew(Integer db3PartyIdNew) {
        this.db3PartyIdNew = db3PartyIdNew;
    }

    public Integer getSubSegmentIdAthena() {
        return subSegmentIdAthena;
    }

    public void setSubSegmentIdAthena(Integer subSegmentIdAthena) {
        this.subSegmentIdAthena = subSegmentIdAthena;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (outletId != null ? outletId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbOutlets)) {
            return false;
        }
        DbOutlets other = (DbOutlets) object;
        return !((this.outletId == null && other.outletId != null) || (this.outletId != null && !this.outletId.equals(other.outletId)));
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbOutlets[ outletId=" + outletId + " ]";
    }
}
