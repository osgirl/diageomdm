/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.dto;

import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import java.util.List;

/**
 *
 * @author EDUARDO
 */
public class DistributorPermissionDto {

    private Db3party distributor;
    private List<DbPermissionSegments> listPermissionSegment;

    public DistributorPermissionDto() {
    }

    public DistributorPermissionDto(Db3party distributor, List<DbPermissionSegments> listPermissionSegment) {
        this.distributor = distributor;
        this.listPermissionSegment = listPermissionSegment;
    }

    public Db3party getDistributor() {
        return distributor;
    }

    public void setDistributor(Db3party distributor) {
        this.distributor = distributor;
    }

    public List<DbPermissionSegments> getListPermissionSegment() {
        return listPermissionSegment;
    }

    public void setListPermissionSegment(List<DbPermissionSegments> listPermissionSegment) {
        this.listPermissionSegment = listPermissionSegment;
    }

}
