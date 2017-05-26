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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_LOG_TERRITORY")
public class DbLogTerritory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DB_LOG_TERRITORY_ID")
    private Integer dbLogTerritoryId;
    @Column(name = "DB_OUTLET_ID")
    private Integer dbOutletId;
    @Column(name = "OUTLET_TYPE")
    private String outletType;
    @Column(name = "FIELD_LOG")
    private String fieldLog;
    @Column(name = "VALUE_LOG")
    private String valueLog;
    @Column(name = "WAITING_STATUS")
    private String waitingStatus;
    @Column(name = "CREATION_USER")
    private String creationUser;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    public DbLogTerritory() {
    }

    public DbLogTerritory(Integer dbLogTerritoryId) {
        this.dbLogTerritoryId = dbLogTerritoryId;
    }

    public Integer getDbLogTerritoryId() {
        return dbLogTerritoryId;
    }

    public void setDbLogTerritoryId(Integer dbLogTerritoryId) {
        this.dbLogTerritoryId = dbLogTerritoryId;
    }

    public Integer getDbOutletId() {
        return dbOutletId;
    }

    public void setDbOutletId(Integer dbOutletId) {
        this.dbOutletId = dbOutletId;
    }

    public String getOutletType() {
        return outletType;
    }

    public void setOutletType(String outletType) {
        this.outletType = outletType;
    }

    public String getFieldLog() {
        return fieldLog;
    }

    public void setFieldLog(String fieldLog) {
        this.fieldLog = fieldLog;
    }

    public String getValueLog() {
        return valueLog;
    }

    public void setValueLog(String valueLog) {
        this.valueLog = valueLog;
    }

    public String getWaitingStatus() {
        return waitingStatus;
    }

    public void setWaitingStatus(String waitingStatus) {
        this.waitingStatus = waitingStatus;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.dbLogTerritoryId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DbLogTerritory other = (DbLogTerritory) obj;
        if (!Objects.equals(this.dbLogTerritoryId, other.dbLogTerritoryId)) {
            return false;
        }
        return true;
    }

}
