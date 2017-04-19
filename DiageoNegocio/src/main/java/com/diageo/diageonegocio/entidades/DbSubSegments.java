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
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "DB_SUB_SEGMENTS")
@NamedQueries({
    @NamedQuery(name = DbSubSegments.FIND_BY_SEGMENT, query = "SELECT s FROM DbSubSegments s WHERE s.segmentId.segmentId = ?1")
})
public class DbSubSegments implements Serializable {

    public static final String FIND_BY_SEGMENT = "DbSubSegments.findBySegment";
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "SQ_DB_SUB_SEGMENTS", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "SQ_DB_SUB_SEGMENTS", sequenceName = "SQ_DB_SUB_SEGMENTS", allocationSize = 1)
    @Column(name = "SUB_SEGMENT_ID")
    private Integer subSegmentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME_SUBSEGMENT")
    private String nameSubsegment;
    @JoinColumn(name = "SEGMENT_ID", referencedColumnName = "SEGMENT_ID")
    @ManyToOne(optional = false)
    private DbSegments segmentId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subSegmentId")
    private List<DbOutlets> dbOutletsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "distributorSubSegmentId")
    private List<DbOutlets> dbOutletsListDistributor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subSegmentId",fetch = FetchType.EAGER)
    private List<DbPotentials> dbPotentialsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subSegmentId")
    private List<DbChains> dbChainsList;
    @Column(name = "STATE_SUB_SEGMENT")
    private String stateSubSegment;
    @Column(name = "DISTRI_1")
    private String distri_1;
    @Embedded
    private Audit audit;
    @Embedded
    private CodeAthena codeAthena;

    public DbSubSegments() {
    }

    public DbSubSegments(Integer subSegmentId) {
        this.subSegmentId = subSegmentId;
    }

    public DbSubSegments(Integer subSegmentId, String nameSubsegment) {
        this.subSegmentId = subSegmentId;
        this.nameSubsegment = nameSubsegment;
    }

    public Integer getSubSegmentId() {
        return subSegmentId;
    }

    public void setSubSegmentId(Integer subSegmentId) {
        this.subSegmentId = subSegmentId;
    }

    public String getNameSubsegment() {
        return nameSubsegment;
    }

    public void setNameSubsegment(String nameSubsegment) {
        this.nameSubsegment = nameSubsegment;
    }

    public DbSegments getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(DbSegments segmentId) {
        this.segmentId = segmentId;
    }

    public List<DbOutlets> getDbOutletsList() {
        return dbOutletsList;
    }

    public void setDbOutletsList(List<DbOutlets> dbOutletsList) {
        this.dbOutletsList = dbOutletsList;
    }

    public List<DbPotentials> getDbPotentialsList() {
        return dbPotentialsList;
    }

    public void setDbPotentialsList(List<DbPotentials> dbPotentialsList) {
        this.dbPotentialsList = dbPotentialsList;
    }

    public List<DbChains> getDbChainsList() {
        return dbChainsList;
    }

    public void setDbChainsList(List<DbChains> dbChainsList) {
        this.dbChainsList = dbChainsList;
    }

    public String getStateSubSegment() {
        return stateSubSegment;
    }

    public void setStateSubSegment(String stateSubSegment) {
        this.stateSubSegment = stateSubSegment;
    }

    public String getDistri_1() {
        return distri_1;
    }

    public void setDistri_1(String distri_1) {
        this.distri_1 = distri_1;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public List<DbOutlets> getDbOutletsListDistributor() {
        return dbOutletsListDistributor;
    }

    public void setDbOutletsListDistributor(List<DbOutlets> dbOutletsListDistributor) {
        this.dbOutletsListDistributor = dbOutletsListDistributor;
    }

    public CodeAthena getCodeAthena() {
        return codeAthena;
    }

    public void setCodeAthena(CodeAthena codeAthena) {
        this.codeAthena = codeAthena;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subSegmentId != null ? subSegmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbSubSegments)) {
            return false;
        }
        DbSubSegments other = (DbSubSegments) object;
        if ((this.subSegmentId == null && other.subSegmentId != null) || (this.subSegmentId != null && !this.subSegmentId.equals(other.subSegmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageonegocio.entidades[ subSegmentId=" + subSegmentId + " ]";
    }

    }
