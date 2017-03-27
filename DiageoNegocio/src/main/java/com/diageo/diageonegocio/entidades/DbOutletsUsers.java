/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_OUTLETS_USERS")
@NamedQueries({
    @NamedQuery(name = DbOutletsUsers.FIND_BY_OUTLET_ID,
            query = "SELECT o FROM DbOutletsUsers o WHERE o.dbOutletsUsersPK.outletId = ?1")
    ,
    @NamedQuery(name = DbOutletsUsers.FIND_BY_OUTLET_ID_PROFILE_ID,
            query = "SELECT o FROM DbOutletsUsers o WHERE o.dbOutletsUsersPK.outletId = ?1 AND o.profileId = ?2")
    ,
    @NamedQuery(name = DbOutletsUsers.FIND_BY_USER_ID,
            query = "SELECT o.dbOutletsUsersPK.outletId FROM DbOutletsUsers o WHERE o.dbOutletsUsersPK.userId = ?1")
    ,
    @NamedQuery(name = DbOutletsUsers.COUNT_PENDING_OUTLETS, query = "SELECT COUNT(o) FROM DbOutlets o JOIN DbOutletsUsers do ON do.dbOutletsUsersPK.outletId=o.outletId "
            + "WHERE do.dbOutletsUsersPK.userId = ?1 AND o.statusMDM = ?2")
    ,
    @NamedQuery(name = DbOutletsUsers.COUNT_PENDING_OUTLETS_IN, query = "SELECT COUNT(o) FROM DbOutlets o WHERE o.statusMDM IN ?1")
    ,
    @NamedQuery(name = DbOutletsUsers.FIND_BY_USER_OUTLETS_JOIN, query = "SELECT do FROM DbOutletsUsers o "
            + "JOIN DbOutlets do ON do.outletId=o.dbOutletsUsersPK.outletId "
            + "WHERE o.dbOutletsUsersPK.userId = :userId "
            + "AND do.nit LIKE :nit "
            + "AND do.businessName LIKE :businessName "
            + "AND do.numberPdv LIKE :numberPdv "
            + "AND do.kiernanId LIKE :kiernanId "
            + "AND do.subSegmentId.nameSubsegment LIKE :nameSubsegment "
            + "AND do.statusOutlet LIKE :statusOutlet "
            + "AND do.statusMDM LIKE :statusMDM "
            + "AND do.townId.nameTown LIKE :nameTown "
            + "AND do.distributorSubSegmentId.nameSubsegment LIKE :nameSubSegment "
            + "AND do.subSegmentId.segmentId.subChannelId.channelId.nameChannel LIKE :nameChannel "
            + "AND do.journeyPlan LIKE :journeyPlan "
            + "AND do.db3partySaleId.pdv LIKE :pdv "
            + "AND do.db3PartyIdOld.db3partyRegionalId.nameRegional LIKE :nameRegional "
            + "AND do.db3PartyIdOld.name3party LIKE :name3partyOld "
            + "AND do.subSegmentId.segmentId.subChannelId.nameSubChannel LIKE :nameSubChannel "
            + "AND do.db3partySaleId.nameSales LIKE :nameSales "
            + "AND do.address LIKE :address "
            + "AND do.townId.departamentId.nameDepartament LIKE :nameDepartament "
            + "AND CAST(do.outletId AS VARCHAR(12)) LIKE :outletId "
            + "AND do.subSegmentId.segmentId.nameSegment LIKE :nameSegment "
            + "AND do.potentialId.namePotential LIKE :namePotential "
            + "")
    ,
    @NamedQuery(name = DbOutletsUsers.FIND_BY_USER_OUTLETS_JOIN_COUNT, query = "SELECT COUNT(do) FROM DbOutletsUsers o "
            + "JOIN DbOutlets do ON do.outletId=o.dbOutletsUsersPK.outletId "
            + "WHERE o.dbOutletsUsersPK.userId = :userId "
            + "AND do.nit LIKE :nit "
            + "AND do.businessName LIKE :businessName "
            + "AND do.numberPdv LIKE :numberPdv "
            + "AND do.kiernanId LIKE :kiernanId "
            + "AND do.subSegmentId.nameSubsegment LIKE :nameSubsegment "
            + "AND do.statusOutlet LIKE :statusOutlet "
            + "AND do.statusMDM LIKE :statusMDM "
            + "AND do.townId.nameTown LIKE :nameTown "
            + "AND do.distributorSubSegmentId.nameSubsegment LIKE :nameSubSegment "
            + "AND do.subSegmentId.segmentId.subChannelId.channelId.nameChannel LIKE :nameChannel "
            + "AND do.journeyPlan LIKE :journeyPlan "
            + "AND do.db3partySaleId.pdv LIKE :pdv "
            + "AND do.db3PartyIdOld.db3partyRegionalId.nameRegional LIKE :nameRegional "
            + "AND do.db3PartyIdOld.name3party LIKE :name3partyOld "
            + "AND do.subSegmentId.segmentId.subChannelId.nameSubChannel LIKE :nameSubChannel "
            + "AND do.db3partySaleId.nameSales LIKE :nameSales "
            + "AND do.address LIKE :address "
            + "AND do.townId.departamentId.nameDepartament LIKE :nameDepartament "
            + "AND CAST(do.outletId AS VARCHAR(12)) LIKE :outletId "
            + "AND do.subSegmentId.segmentId.nameSegment LIKE :nameSegment "
            + "AND do.potentialId.namePotential LIKE :namePotential "
            + "")
    ,
    @NamedQuery(name = DbOutletsUsers.FIND_BY_USER_OUTLETS_JOIN_IN, query = "SELECT do FROM DbOutletsUsers o "
            + "JOIN DbOutlets do ON do.outletId=o.dbOutletsUsersPK.outletId "
            + "WHERE o.dbOutletsUsersPK.userId IN :userId "
            + "AND do.nit LIKE :nit "
            + "AND do.businessName LIKE :businessName "
            + "AND do.numberPdv LIKE :numberPdv "
            + "AND do.kiernanId LIKE :kiernanId "
            + "AND do.subSegmentId.nameSubsegment LIKE :nameSubsegment "
            + "AND do.statusOutlet LIKE :statusOutlet "
            + "AND do.statusMDM LIKE :statusMDM "
            + "AND do.townId.nameTown LIKE :nameTown "
            + "AND do.distributorSubSegmentId.nameSubsegment LIKE :nameSubSegment "
            + "AND do.subSegmentId.segmentId.subChannelId.channelId.nameChannel LIKE :nameChannel "
            + "AND do.journeyPlan LIKE :journeyPlan "
            + "AND do.db3partySaleId.pdv LIKE :pdv "
            + "AND do.db3PartyIdOld.db3partyRegionalId.nameRegional LIKE :nameRegional "
            + "AND do.db3PartyIdOld.name3party LIKE :name3partyOld "
            + "AND do.subSegmentId.segmentId.subChannelId.nameSubChannel LIKE :nameSubChannel "
            + "AND do.db3partySaleId.nameSales LIKE :nameSales "
            + "AND do.address LIKE :address "
            + "AND do.townId.departamentId.nameDepartament LIKE :nameDepartament "
            + "AND CAST(do.outletId AS VARCHAR(12)) LIKE :outletId "
            + "AND do.subSegmentId.segmentId.nameSegment LIKE :nameSegment "
            + "AND do.potentialId.namePotential LIKE :namePotential "
            + "")
    ,
    @NamedQuery(name = DbOutletsUsers.FIND_BY_USER_OUTLETS_JOIN_COUNT_IN, query = "SELECT COUNT(do) FROM DbOutletsUsers o "
            + "JOIN DbOutlets do ON do.outletId=o.dbOutletsUsersPK.outletId "
            + "WHERE o.dbOutletsUsersPK.userId IN :userId "
            + "AND do.nit LIKE :nit "
            + "AND do.businessName LIKE :businessName "
            + "AND do.numberPdv LIKE :numberPdv "
            + "AND do.kiernanId LIKE :kiernanId "
            + "AND do.subSegmentId.nameSubsegment LIKE :nameSubsegment "
            + "AND do.statusOutlet LIKE :statusOutlet "
            + "AND do.statusMDM LIKE :statusMDM "
            + "AND do.townId.nameTown LIKE :nameTown "
            + "AND do.distributorSubSegmentId.nameSubsegment LIKE :nameSubSegment "
            + "AND do.subSegmentId.segmentId.subChannelId.channelId.nameChannel LIKE :nameChannel "
            + "AND do.journeyPlan LIKE :journeyPlan "
            + "AND do.db3partySaleId.pdv LIKE :pdv "
            + "AND do.db3PartyIdOld.db3partyRegionalId.nameRegional LIKE :nameRegional "
            + "AND do.db3PartyIdOld.name3party LIKE :name3partyOld "
            + "AND do.subSegmentId.segmentId.subChannelId.nameSubChannel LIKE :nameSubChannel "
            + "AND do.db3partySaleId.nameSales LIKE :nameSales "
            + "AND do.address LIKE :address "
            + "AND do.townId.departamentId.nameDepartament LIKE :nameDepartament "
            + "AND CAST(do.outletId AS VARCHAR(12)) LIKE :outletId "
            + "AND do.subSegmentId.segmentId.nameSegment LIKE :nameSegment "
            + "AND do.potentialId.namePotential LIKE :namePotential "
            + "")
})
public class DbOutletsUsers implements Serializable {

    @Column(name = "PERMISSION_SEGMENT")
    private Integer permissionSegment;
    @Column(name = "PARETO")
    private Boolean pareto;
    @JoinColumn(name = "OUTLET_ID", referencedColumnName = "OUTLET_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DbOutlets dbOutlets;

    public static final String FIND_BY_USER_ID = "DbOutletsUsers.findByUserId";
    public static final String FIND_BY_OUTLET_ID = "DbOutletsUsers.findByOutletId";
    public static final String FIND_BY_OUTLET_ID_PROFILE_ID = "DbOutletsUsers.findByOutletIdProfileId";
    public static final String FIND_BY_USER_OUTLETS_JOIN = "DbOutletsUsers.findByUserOutletsJoin";
    public static final String FIND_BY_USER_OUTLETS_JOIN_IN = "DbOutletsUsers.findByUserOutletsJoinIn";
    public static final String FIND_BY_USER_OUTLETS_JOIN_COUNT = "DbOutletsUsers.findByUserOutletsJoinCount";
    public static final String FIND_BY_USER_OUTLETS_JOIN_COUNT_IN = "DbOutletsUsers.findByUserOutletsJoinCountIn";
    public static final String COUNT_PENDING_OUTLETS = "DbOutletsUsers.countPendingOutlets";
    public static final String COUNT_PENDING_OUTLETS_IN = "DbOutletsUsers.countPendingOutletsIn";
    @EmbeddedId
    private DbOutletsUsersPK dbOutletsUsersPK;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "MODIFICATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date modificationDate;
    @Column(name = "PROFILE_ID")
    private Integer profileId;

    public DbOutletsUsers() {
    }

    public DbOutletsUsers(DbOutletsUsersPK dbOutletsUsersPK, boolean pareto, Date creationDate, Date modificationDate) {
        this.dbOutletsUsersPK = dbOutletsUsersPK;
        this.pareto = pareto;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public DbOutletsUsersPK getDbOutletsUsersPK() {
        return dbOutletsUsersPK;
    }

    public void setDbOutletsUsersPK(DbOutletsUsersPK dbOutletsUsersPK) {
        this.dbOutletsUsersPK = dbOutletsUsersPK;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.dbOutletsUsersPK);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof DbOutletsUsers) {
            DbOutletsUsers d = (DbOutletsUsers) obj;
            return d.dbOutletsUsersPK.getOutletId().equals(this.dbOutletsUsersPK.getOutletId())
                    && d.dbOutletsUsersPK.getUserId().equals(this.dbOutletsUsersPK.getUserId());
        }
        return false;
    }

    public Integer getPermissionSegment() {
        return permissionSegment;
    }

    public void setPermissionSegment(Integer permissionSegment) {
        this.permissionSegment = permissionSegment;
    }

    public Boolean getPareto() {
        return pareto;
    }

    public void setPareto(Boolean pareto) {
        this.pareto = pareto;
    }

    public DbOutlets getDbOutlets() {
        return dbOutlets;
    }

    public void setDbOutlets(DbOutlets dbOutlets) {
        this.dbOutlets = dbOutlets;
    }

}
