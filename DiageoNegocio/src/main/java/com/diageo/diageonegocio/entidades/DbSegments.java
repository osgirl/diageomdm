/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_SEGMENTS")
@NamedQueries({
    @NamedQuery(name = DbSegments.FIND_BY_SUBCHANNEL, query = "SELECT s FROM DbSegments s WHERE s.subChannelId.subChannelId = ?1")
})
public class DbSegments implements Serializable {
    
    public static final String FIND_BY_SUBCHANNEL = "DbSegments.findBySubChannel";
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(generator = "SQ_DB_SEGMENTS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DB_SEGMENTS", sequenceName = "SQ_DB_SEGMENTS", allocationSize = 1)
    @Column(name = "SEGMENT_ID")
    private Integer segmentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME_SEGMENT")
    private String nameSegment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "segmentId")
    private List<DbSubSegments> dbSubSegmentsList;
    @JoinColumn(name = "SUB_CHANNEL_ID", referencedColumnName = "SUB_CHANNEL_ID")
    @ManyToOne(optional = false)
    private DbSubChannels subChannelId;
    @Column(name = "STATE_SEGMENT")
    private String stateSegment;
    @Column(name = "DISTRI_1")
    private String distri_1;

    public DbSegments() {
    }

    public DbSegments(Integer segmentId) {
        this.segmentId = segmentId;
    }

    public DbSegments(Integer segmentId, String nameSegment) {
        this.segmentId = segmentId;
        this.nameSegment = nameSegment;
    }

    public Integer getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Integer segmentId) {
        this.segmentId = segmentId;
    }

    public String getNameSegment() {
        return nameSegment;
    }

    public void setNameSegment(String nameSegment) {
        this.nameSegment = nameSegment;
    }

    public List<DbSubSegments> getDbSubSegmentsList() {
        return dbSubSegmentsList;
    }

    public void setDbSubSegmentsList(List<DbSubSegments> dbSubSegmentsList) {
        this.dbSubSegmentsList = dbSubSegmentsList;
    }

    public DbSubChannels getSubChannelId() {
        return subChannelId;
    }

    public void setSubChannelId(DbSubChannels subChannelId) {
        this.subChannelId = subChannelId;
    }

    public String getStateSegment() {
        return stateSegment;
    }

    public void setStateSegment(String stateSegment) {
        this.stateSegment = stateSegment;
    }

    public String getDistri_1() {
        return distri_1;
    }

    public void setDistri_1(String distri_1) {
        this.distri_1 = distri_1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (segmentId != null ? segmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbSegments)) {
            return false;
        }
        DbSegments other = (DbSegments) object;
        if ((this.segmentId == null && other.segmentId != null) || (this.segmentId != null && !this.segmentId.equals(other.segmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbSegments[ segmentId=" + segmentId + " ]";
    }
    
}
