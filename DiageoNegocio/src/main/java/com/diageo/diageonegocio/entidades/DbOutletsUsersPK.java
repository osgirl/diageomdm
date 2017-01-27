/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author EDUARDO
 */
@Embeddable
public class DbOutletsUsersPK implements Serializable {

    @Column(name = "OUTLET_ID")
    private Integer outletId;
    @Column(name = "USER_ID")
    private Integer userId;

    public DbOutletsUsersPK() {
    }

    public DbOutletsUsersPK(Integer outletId, Integer userId) {
        this.outletId = outletId;
        this.userId = userId;
    }

    public Integer getOutletId() {
        return outletId;
    }

    public void setOutletId(Integer outletId) {
        this.outletId = outletId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
}
