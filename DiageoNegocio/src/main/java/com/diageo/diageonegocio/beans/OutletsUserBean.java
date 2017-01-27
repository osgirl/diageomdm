/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbOutletsUsers;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class OutletsUserBean extends BusinessTransaction<DbOutletsUsers> implements OutletsUserBeanLocal {

    @Override
    public List<Integer> findByUserId(Integer userId) {
        Query sql = super.getEntityManager().createNamedQuery(DbOutletsUsers.FIND_BY_USER_ID).setParameter(1, userId);
        Vector vec=(Vector)sql.getResultList();
        List<Integer> list = new ArrayList<>(vec);        
        return list;
    }
}
