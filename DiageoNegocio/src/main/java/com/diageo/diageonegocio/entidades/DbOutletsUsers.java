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
    @NamedQuery(name = DbOutletsUsers.FIND_BY_USER_ID,
            query = "SELECT o.dbOutletsUsersPK.outletId FROM DbOutletsUsers o WHERE o.dbOutletsUsersPK.userId = ?1")
})
public class DbOutletsUsers implements Serializable {

    public static final String FIND_BY_USER_ID = "DbOutletsUsers.findByUserId";
    @EmbeddedId
    private DbOutletsUsersPK dbOutletsUsersPK;
    @Column(name = "PARETO")
    private boolean pareto;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "MODIFICATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date modificationDate;

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

    public boolean isPareto() {
        return pareto;
    }

    public void setPareto(boolean pareto) {
        this.pareto = pareto;
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

}