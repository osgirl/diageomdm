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

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DIAGEO_LOG")
public class DiageoLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIAGEO_LOG_ID")
    private Integer diageoLogId;
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "DIAGEO_TABLE")
    private String diageoTable;
    @Column(name = "DIAGEO_FIELD")
    private String diageoField;
    @Column(name = "OLD_VALUE")
    private String oldValue;
    @Column(name = "NEW_VALUE")
    private String newValue;
    @Column(name = "CREATION_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;
    @Column(name = "TABLE_ID")
    private Integer tableId;

    public DiageoLog() {
    }

    public DiageoLog(Integer diageoLogId, Integer userId, String diageoTable, String diageoField, String oldValue, String newValue, Date creationDate) {
        this.diageoLogId = diageoLogId;
        this.userId = userId;
        this.diageoTable = diageoTable;
        this.diageoField = diageoField;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.creationDate = creationDate;
    }

    public Integer getDiageoLogId() {
        return diageoLogId;
    }

    public void setDiageoLogId(Integer diageoLogId) {
        this.diageoLogId = diageoLogId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDiageoTable() {
        return diageoTable;
    }

    public void setDiageoTable(String diageoTable) {
        this.diageoTable = diageoTable;
    }

    public String getDiageoField() {
        return diageoField;
    }

    public void setDiageoField(String diageoField) {
        this.diageoField = diageoField;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.diageoLogId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof DiageoLog) {
            DiageoLog log = (DiageoLog) obj;
            return this.diageoLogId.equals(log.diageoLogId);
        }
        return false;
    }

}
