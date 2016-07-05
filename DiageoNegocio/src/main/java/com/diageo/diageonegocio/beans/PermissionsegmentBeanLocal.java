/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface PermissionsegmentBeanLocal {

    public void createPermissionSegmentList(List<DbPermissionSegments> entity) throws DiageoBusinessException;

    public List<DbPermissionSegments> findByUser(Integer idUser);

    public Set<DbPermissionSegments> findByUserDistributor(Integer idUser, Integer distributor);

    public void remove(DbPermissionSegments ps);
    
}
