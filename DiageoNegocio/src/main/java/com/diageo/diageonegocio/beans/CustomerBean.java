/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbCustomers;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class CustomerBean extends BusinessTransaction<DbCustomers> implements CustomerBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public DbCustomers createCustomer(DbCustomers per) throws DiageoBusinessException {
        try {
            per = super.create(per);
            return per;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    @Override
    public List<DbCustomers> findByNameCustomer(String query) {
        query = query.toUpperCase();
        List<DbCustomers> list = super.searchByNamedQuery(DbCustomers.class, DbCustomers.FIND_BY_NAME, "%" + query + "%");
        if (list == null) {
            new ArrayList<DbCustomers>();
        }
        return list;
    }

    @Override
    public DbCustomers findById(Integer id) {
        return (DbCustomers) super.searchById(DbCustomers.class, id);
    }
}
