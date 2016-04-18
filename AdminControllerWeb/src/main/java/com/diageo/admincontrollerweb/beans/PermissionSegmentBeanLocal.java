/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.PermissionSegment;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface PermissionSegmentBeanLocal {

    public void createPermissionSegment(PermissionSegment entity,Integer idUser);
    
}
