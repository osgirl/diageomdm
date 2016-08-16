/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class PermissionsegmentBean extends BusinessTransaction<DbPermissionSegments> implements PermissionsegmentBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void createPermissionSegmentList(List<DbPermissionSegments> entity) throws DiageoBusinessException {
        try {
            for (DbPermissionSegments entity1 : entity) {
                super.update(entity1);
            }
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    @Override
    public List<DbPermissionSegments> findByUser(Integer idUser) {
        List<DbPermissionSegments> list = super.searchByNamedQuery(DbPermissionSegments.class, DbPermissionSegments.FIND_BY_USER, idUser);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Set<DbPermissionSegments> findByUserDistributor(Integer idUser, Integer distributor) {
        List<DbPermissionSegments> list = super.searchByNamedQuery(DbPermissionSegments.class, DbPermissionSegments.FIND_BY_USER_DISTRIBUTOR, idUser, distributor);
        Set<DbPermissionSegments> listSet = new HashSet<>();
        for (DbPermissionSegments list1 : list) {
            listSet.add(list1);
        }
        if (list == null) {
            return new HashSet<>();
        }
        return listSet;
    }

    @Override
    public void remove(DbPermissionSegments ps) {
        super.delete(ps);
    }
}
