/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbLocations;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface LocationBeanLocal {

    public DbLocations createLocation(DbLocations u) throws DiageoBusinessException;
    
}
