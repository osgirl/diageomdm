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
public class DbChainsUsersPK implements Serializable {

    @Column(name = "CHAIN_ID")
    private Integer chainId;
    @Column(name = "USER_ID")
    private Integer userId;

    public DbChainsUsersPK() {
    }

    public DbChainsUsersPK(Integer outletId, Integer userId) {
        this.chainId = outletId;
        this.userId = userId;
    }

    public Integer getChainId() {
        return chainId;
    }

    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
}
