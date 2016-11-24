/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_POTENTIALS")
@NamedQueries({
     @NamedQuery(name = DbPotentials.FIND_BY_SUBSEGMENT, query = "SELECT p FROM DbPotentials p WHERE p.subSegmentId.subSegmentId=?1")
})
public class DbPotentials implements Serializable {
    
    public static final String FIND_BY_SUBSEGMENT = "DbPotentials.findBySubsegment";
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "SQ_DB_POTENTIALS", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "SQ_DB_POTENTIALS", sequenceName = "SQ_DB_POTENTIALS", allocationSize = 1)
    @Column(name = "POTENTIAL_ID")
    private Integer potentialId;
    @Size(max = 100)
    @Column(name = "NAME_POTENTIAL")
    private String namePotential;
    @Size(max = 1)
    @Column(name = "NEW_CLIENT")
    private String newClient;
    @Size(max = 1)
    @Column(name = "IS_BATTLEGROUND")
    private String isBattleground;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "potentialId")
    private List<DbOutlets> dbOutletsList;
    @JoinColumn(name = "SUB_SEGMENT_ID", referencedColumnName = "SUB_SEGMENT_ID")
    @ManyToOne(optional = false)
    private DbSubSegments subSegmentId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "potentialId")
    private List<DbChains> dbChainsList;
    @Column(name = "DISTRI_1")
    private String distri_1;
    @Column(name = "LOW_POTENTIAL")
    private String lowPotential;
    @Embedded
    private Audit audit;

    public DbPotentials() {
    }

    public DbPotentials(Integer potentialId) {
        this.potentialId = potentialId;
    }

    public Integer getPotentialId() {
        return potentialId;
    }

    public void setPotentialId(Integer potentialId) {
        this.potentialId = potentialId;
    }

    public String getNamePotential() {
        return namePotential;
    }

    public void setNamePotential(String namePotential) {
        this.namePotential = namePotential;
    }

    public String getNewClient() {
        return newClient;
    }

    public void setNewClient(String newClient) {
        this.newClient = newClient;
    }

    public String getIsBattleground() {
        return isBattleground;
    }

    public void setIsBattleground(String isBattleground) {
        this.isBattleground = isBattleground;
    }

    public List<DbOutlets> getDbOutletsList() {
        return dbOutletsList;
    }

    public void setDbOutletsList(List<DbOutlets> dbOutletsList) {
        this.dbOutletsList = dbOutletsList;
    }

    public DbSubSegments getSubSegmentId() {
        return subSegmentId;
    }

    public void setSubSegmentId(DbSubSegments subSegmentId) {
        this.subSegmentId = subSegmentId;
    }

    public List<DbChains> getDbChainsList() {
        return dbChainsList;
    }

    public void setDbChainsList(List<DbChains> dbChainsList) {
        this.dbChainsList = dbChainsList;
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

    public String getLowPotential() {
        return lowPotential;
    }

    public void setLowPotential(String lowPotential) {
        this.lowPotential = lowPotential;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (potentialId != null ? potentialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbPotentials)) {
            return false;
        }
        DbPotentials other = (DbPotentials) object;
        if ((this.potentialId == null && other.potentialId != null) || (this.potentialId != null && !this.potentialId.equals(other.potentialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbPotentials[ potentialId=" + potentialId + " ]";
    }
    
}
