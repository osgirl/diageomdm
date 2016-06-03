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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_CLUSTERS")
@NamedQueries({
    @NamedQuery(name = "DbClusters.findAll", query = "SELECT d FROM DbClusters d"),
    @NamedQuery(name = "DbClusters.findByDbClusterId", query = "SELECT d FROM DbClusters d WHERE d.dbClusterId = :dbClusterId"),
    @NamedQuery(name = "DbClusters.findByNameCluster", query = "SELECT d FROM DbClusters d WHERE d.nameCluster = :nameCluster")})
public class DbClusters implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DB_CLUSTER_ID")
    private Integer dbClusterId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME_CLUSTER")
    private String nameCluster;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dbClusterId")
    private List<DbChains> dbChainsList;

    public DbClusters() {
    }

    public DbClusters(Integer dbClusterId) {
        this.dbClusterId = dbClusterId;
    }

    public DbClusters(Integer dbClusterId, String nameCluster) {
        this.dbClusterId = dbClusterId;
        this.nameCluster = nameCluster;
    }

    public Integer getDbClusterId() {
        return dbClusterId;
    }

    public void setDbClusterId(Integer dbClusterId) {
        this.dbClusterId = dbClusterId;
    }

    public String getNameCluster() {
        return nameCluster;
    }

    public void setNameCluster(String nameCluster) {
        this.nameCluster = nameCluster;
    }

    public List<DbChains> getDbChainsList() {
        return dbChainsList;
    }

    public void setDbChainsList(List<DbChains> dbChainsList) {
        this.dbChainsList = dbChainsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dbClusterId != null ? dbClusterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbClusters)) {
            return false;
        }
        DbClusters other = (DbClusters) object;
        if ((this.dbClusterId == null && other.dbClusterId != null) || (this.dbClusterId != null && !this.dbClusterId.equals(other.dbClusterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.DbClusters[ dbClusterId=" + dbClusterId + " ]";
    }
    
}
