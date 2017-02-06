/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "DB_PERMISSION_SEGMENTS")
@NamedQueries({
    @NamedQuery(name = DbPermissionSegments.FIND_BY_USER, query = "SELECT p FROM DbPermissionSegments p WHERE p.userId = ?1"),
    @NamedQuery(name = DbPermissionSegments.FIND_BY_USER_DISTRIBUTOR, query = "SELECT p FROM DbPermissionSegments p WHERE p.userId = ?1 AND "
            + "p.db3partyId.db3partyId = ?2"),
    @NamedQuery(name = DbPermissionSegments.FIND_BY_USERS_3PARTY_LIST, 
            query = "SELECT DISTINCT  p.userId FROM DbPermissionSegments p WHERE p.userId IN ?1 AND p.db3partyId.db3partyId IN ?2")
})
public class DbPermissionSegments implements Serializable {

    public static final String FIND_BY_USER = "DbPermissionSegments.findByUser";
    public static final String FIND_BY_USERS_3PARTY_LIST = "DbPermissionSegments.findByUsers3pary";
    public static final String FIND_BY_USER_DISTRIBUTOR = "DbPermissionSegments.findByUserDistributor";
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "SQ_DB_PERMISSION_SEGMENTS", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "SQ_DB_PERMISSION_SEGMENTS", sequenceName = "SQ_DB_PERMISSION_SEGMENTS", allocationSize = 1)
    @Column(name = "PERMISSION_SEGMENT")
    private Integer permissionSegment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "CHANNEL_ID")
    private Integer channelId;
    @Column(name = "SUB_CHANNEL_ID")
    private Integer subChannelId;
    @Column(name = "SEGMENT_ID")
    private Integer segmentId;
    @Column(name = "SUB_SEGMENT_ID")
    private Integer subSegmentId;
    @Column(name = "POTENTIAL_ID")
    private Integer potentialId;
    @Size(max = 1)
    @Column(name = "CHANNEL_CHECK")
    private String channelCheck;
    @Size(max = 1)
    @Column(name = "SUB_CHANNEL_CHECK")
    private String subChannelCheck;
    @Size(max = 1)
    @Column(name = "SEGMENT_CHECK")
    private String segmentCheck;
    @Size(max = 1)
    @Column(name = "SUB_SEGMENT_CHECK")
    private String subSegmentCheck;
    @Size(max = 1)
    @Column(name = "POTENTIAL_CHECK")
    private String potentialCheck;
    @JoinColumn(name = "DB_3PARTY_ID", referencedColumnName = "DB_3PARTY_ID")
    @ManyToOne(optional = false)
    private Db3party db3partyId;
    @Embedded
    private Audit audit;

    public DbPermissionSegments() {
    }

    public DbPermissionSegments(Integer permissionSegment) {
        this.permissionSegment = permissionSegment;
    }

    public DbPermissionSegments(Integer permissionSegment, Integer userId) {
        this.permissionSegment = permissionSegment;
        this.userId = userId;
    }

    public Integer getPermissionSegment() {
        return permissionSegment;
    }

    public void setPermissionSegment(Integer permissionSegment) {
        this.permissionSegment = permissionSegment;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getSubChannelId() {
        return subChannelId;
    }

    public void setSubChannelId(Integer subChannelId) {
        this.subChannelId = subChannelId;
    }

    public Integer getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Integer segmentId) {
        this.segmentId = segmentId;
    }

    public Integer getSubSegmentId() {
        return subSegmentId;
    }

    public void setSubSegmentId(Integer subSegmentId) {
        this.subSegmentId = subSegmentId;
    }

    public Integer getPotentialId() {
        return potentialId;
    }

    public void setPotentialId(Integer potentialId) {
        this.potentialId = potentialId;
    }

    public String getChannelCheck() {
        return channelCheck;
    }

    public void setChannelCheck(String channelCheck) {
        this.channelCheck = channelCheck;
    }

    public String getSubChannelCheck() {
        return subChannelCheck;
    }

    public void setSubChannelCheck(String subChannelCheck) {
        this.subChannelCheck = subChannelCheck;
    }

    public String getSegmentCheck() {
        return segmentCheck;
    }

    public void setSegmentCheck(String segmentCheck) {
        this.segmentCheck = segmentCheck;
    }

    public String getSubSegmentCheck() {
        return subSegmentCheck;
    }

    public void setSubSegmentCheck(String subSegmentCheck) {
        this.subSegmentCheck = subSegmentCheck;
    }

    public String getPotentialCheck() {
        return potentialCheck;
    }

    public void setPotentialCheck(String potentialCheck) {
        this.potentialCheck = potentialCheck;
    }

    public Db3party getDb3partyId() {
        return db3partyId;
    }

    public void setDb3partyId(Db3party db3partyId) {
        this.db3partyId = db3partyId;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permissionSegment != null ? permissionSegment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbPermissionSegments)) {
            return false;
        }
        DbPermissionSegments other = (DbPermissionSegments) object;
        boolean conditionEquals = (Objects.equals(this.permissionSegment, other.permissionSegment))
                && (Objects.equals(this.channelId, other.channelId))
                && (Objects.equals(this.subChannelId, other.subChannelId))
                && (Objects.equals(this.segmentId, other.segmentId))
                && (Objects.equals(this.subSegmentId, other.subSegmentId))
                && (Objects.equals(this.potentialId, other.potentialId));
        return conditionEquals;
    }

    @Override
    public String toString() {
        return "com.diageo.diageobusienss.entidades.DbPermissionSegments[ permissionSegment=" + permissionSegment + " ]";
    }

}
