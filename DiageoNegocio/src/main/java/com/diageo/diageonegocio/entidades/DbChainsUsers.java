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
@Table(name = "DB_CHAINS_USERS")
@NamedQueries({
    @NamedQuery(name = DbChainsUsers.FIND_BY_USER_ID,
            query = "SELECT o.dbChainsUsersPK.chainId FROM DbChainsUsers o WHERE o.dbChainsUsersPK.userId = ?1")
})
public class DbChainsUsers implements Serializable {

    public static final String FIND_BY_USER_ID = "DbChainsUsers.findByUserId";
    @EmbeddedId
    private DbChainsUsersPK dbChainsUsersPK;
    @Column(name = "PARETO")
    private boolean pareto;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "MODIFICATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date modificationDate;

    public DbChainsUsers() {
    }

    public DbChainsUsersPK getDbChainsUsersPK() {
        return dbChainsUsersPK;
    }

    public void setDbChainsUsersPK(DbChainsUsersPK dbChainsUsersPK) {
        this.dbChainsUsersPK = dbChainsUsersPK;
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
        hash = 79 * hash + Objects.hashCode(this.dbChainsUsersPK);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof DbChainsUsers) {
            DbChainsUsers d = (DbChainsUsers) obj;
            return d.dbChainsUsersPK.getChainId().equals(this.dbChainsUsersPK.getChainId())
                    && d.dbChainsUsersPK.getUserId().equals(this.dbChainsUsersPK.getUserId());
        }
        return false;
    }

}