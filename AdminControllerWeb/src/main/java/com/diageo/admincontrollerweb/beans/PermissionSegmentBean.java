/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.PermissionSegment;
import com.diageo.admincontrollerweb.entities.Usuario;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class PermissionSegmentBean extends WebTransaction<PermissionSegment> implements PermissionSegmentBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void createPermissionSegment(PermissionSegment entity,Integer idUser){
        entity.setIdUser(new Usuario(idUser));
        super.update(entity);
    }
}
