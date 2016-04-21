/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Permissionsegment;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class PermissionsegmentBean extends BusinessTransaction<Permissionsegment> implements PermissionsegmentBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void createPermissionSegmentList(List<Permissionsegment> entity) throws DiageoNegocioException {
        try {
            for (Permissionsegment entity1 : entity) {
                super.create(entity1);
            }
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

    @Override
    public List<Permissionsegment> findByUser(Integer idUser) {
        List<Permissionsegment> list = super.searchByNamedQuery(Permissionsegment.class, Permissionsegment.FIND_BY_USER, idUser);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }
}
