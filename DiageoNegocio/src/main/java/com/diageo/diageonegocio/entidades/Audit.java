/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.diageo.diageonegocio.entidades;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author EDUARDO
 */
@Embeddable
@Access(AccessType.FIELD)
public class Audit {
    
    @Column(name = "CREATION_USER")
    private String creationUser;
    @Column(name = "MODIFICATION_USER")
    private String modificationUser;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "MODIFICATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;

    public Audit() {
    }

    public Audit(String creationUser, String modificationUser, Date creationDate, Date modificationDate) {
        this.creationUser = creationUser;
        this.modificationUser = modificationUser;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public String getModificationUser() {
        return modificationUser;
    }

    public void setModificationUser(String modificationUser) {
        this.modificationUser = modificationUser;
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
    
    
}
