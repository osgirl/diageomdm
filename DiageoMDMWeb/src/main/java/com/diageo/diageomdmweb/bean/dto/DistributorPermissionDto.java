/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.dto;

import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author EDUARDO
 */
public class DistributorPermissionDto {

    private Db3party distributor;
    private Set<DbPermissionSegments> listPermissionSegment;

    public DistributorPermissionDto() {
    }

    public DistributorPermissionDto(Db3party distributor, Set<DbPermissionSegments> listPermissionSegment) {
        this.distributor = distributor;
        this.listPermissionSegment = listPermissionSegment;
    }

    public Db3party getDistributor() {
        return distributor;
    }

    public void setDistributor(Db3party distributor) {
        this.distributor = distributor;
    }

    public Set<DbPermissionSegments> getListPermissionSegment() {
        return listPermissionSegment;
    }

    public void setListPermissionSegment(Set<DbPermissionSegments> listPermissionSegment) {
        this.listPermissionSegment = listPermissionSegment;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(obj instanceof DistributorPermissionDto){
            DistributorPermissionDto dto=(DistributorPermissionDto)obj;
            return this.getDistributor().getDb3partyId().equals(dto.getDistributor().getDb3partyId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.distributor);
        return hash;
    }

}
