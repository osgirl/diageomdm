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
@Table(name = "DB_CHANNELS")
@NamedQueries({
    @NamedQuery(name = "DbChannels.findAll", query = "SELECT d FROM DbChannels d"),
    @NamedQuery(name = "DbChannels.findByChannelId", query = "SELECT d FROM DbChannels d WHERE d.channelId = :channelId"),
    @NamedQuery(name = "DbChannels.findByNameChannel", query = "SELECT d FROM DbChannels d WHERE d.nameChannel = :nameChannel")})
public class DbChannels implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(generator = "SQ_DB_CHANNELS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DB_CHANNELS", sequenceName = "SQ_DB_CHANNELS", allocationSize = 1)
    @Column(name = "CHANNEL_ID")
    private Integer channelId;
    @Size(max = 100)
    @Column(name = "NAME_CHANNEL")
    private String nameChannel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "channelId")
    private List<DbSubChannels> dbSubChannelsList;
    @Column(name = "STATE_CHANNEL")
    private String stateChannel;
    @Column(name = "DISTRI_1")
    private String distri_1;
    @Embedded
    private Audit audit;

    public DbChannels() {
    }

    public DbChannels(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getNameChannel() {
        return nameChannel;
    }

    public void setNameChannel(String nameChannel) {
        this.nameChannel = nameChannel;
    }

    public List<DbSubChannels> getDbSubChannelsList() {
        return dbSubChannelsList;
    }

    public void setDbSubChannelsList(List<DbSubChannels> dbSubChannelsList) {
        this.dbSubChannelsList = dbSubChannelsList;
    }

    public String getStateChannel() {
        return stateChannel;
    }

    public void setStateChannel(String stateChannel) {
        this.stateChannel = stateChannel;
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
        hash += (channelId != null ? channelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbChannels)) {
            return false;
        }
        DbChannels other = (DbChannels) object;
        if ((this.channelId == null && other.channelId != null) || (this.channelId != null && !this.channelId.equals(other.channelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbChannels[ channelId=" + channelId + " ]";
    }
    
}
