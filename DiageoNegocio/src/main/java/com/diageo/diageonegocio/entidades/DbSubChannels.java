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
@Table(name = "DB_SUB_CHANNELS")
@NamedQueries({
    @NamedQuery(name = DbSubChannels.FIND_BY_CHANNEL, query = "SELECT s FROM DbSubChannels s WHERE s.channelId.channelId = ?1")
})
public class DbSubChannels implements Serializable {
    
    public static final String FIND_BY_CHANNEL = "DbSubChannels.findByChannel";
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "SQ_DB_SUB_CHANNELS", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "SQ_DB_SUB_CHANNELS", sequenceName = "SQ_DB_SUB_CHANNELS", allocationSize = 1)
    @Column(name = "SUB_CHANNEL_ID")
    private Integer subChannelId;
    @Size(max = 100)
    @Column(name = "NAME_SUB_CHANNEL")
    private String nameSubChannel;
    @JoinColumn(name = "CHANNEL_ID", referencedColumnName = "CHANNEL_ID")
    @ManyToOne(optional = false)
    private DbChannels channelId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subChannelId")
    private List<DbSegments> dbSegmentsList;
    @Column(name = "STATE_SUB_CHANNEL")
    private String stateSubChannel;
    @Column(name = "DISTRI_1")
    private String distri_1;
    @Embedded
    private Audit audit;

    public DbSubChannels() {
    }

    public DbSubChannels(Integer subChannelId) {
        this.subChannelId = subChannelId;
    }

    public Integer getSubChannelId() {
        return subChannelId;
    }

    public void setSubChannelId(Integer subChannelId) {
        this.subChannelId = subChannelId;
    }

    public String getNameSubChannel() {
        return nameSubChannel;
    }

    public void setNameSubChannel(String nameSubChannel) {
        this.nameSubChannel = nameSubChannel;
    }

    public DbChannels getChannelId() {
        return channelId;
    }

    public void setChannelId(DbChannels channelId) {
        this.channelId = channelId;
    }

    public List<DbSegments> getDbSegmentsList() {
        return dbSegmentsList;
    }

    public void setDbSegmentsList(List<DbSegments> dbSegmentsList) {
        this.dbSegmentsList = dbSegmentsList;
    }

    public String getStateSubChannel() {
        return stateSubChannel;
    }

    public void setStateSubChannel(String stateSubChannel) {
        this.stateSubChannel = stateSubChannel;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subChannelId != null ? subChannelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbSubChannels)) {
            return false;
        }
        DbSubChannels other = (DbSubChannels) object;
        if ((this.subChannelId == null && other.subChannelId != null) || (this.subChannelId != null && !this.subChannelId.equals(other.subChannelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbSubChannels[ subChannelId=" + subChannelId + " ]";
    }
    
}
