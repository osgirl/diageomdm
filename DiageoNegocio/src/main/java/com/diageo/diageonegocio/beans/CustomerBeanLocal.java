/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbCustomers;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface CustomerBeanLocal {

    public DbCustomers createCustomer(DbCustomers per) throws DiageoBusinessException;

    public List<DbCustomers> findByNameCustomer(String query);

    public DbCustomers findById(Integer id);
    
}
