/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.dto;

import com.diageo.diageonegocio.entidades.DbChains;
import java.io.Serializable;

/**
 *
 * @author EDUARDO
 */
public class DbChainsDto implements Serializable{
    
    private DbChains chain;

    public DbChainsDto() {
    }

    public DbChainsDto(DbChains chain) {
        this.chain = chain;
    }

    public DbChains getChain() {
        return chain;
    }

    public void setChain(DbChains chain) {
        this.chain = chain;
    }
    public boolean isNotificationChangedSegmentation(DbChains cha) {
        return this.chain.getSubSegmentId().getSubSegmentId().equals(cha.getSubSegmentId().getSubSegmentId());
    }
    
}
