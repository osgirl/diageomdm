/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbLocations;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class LocationBean extends BusinessTransaction<DbLocations> implements LocationBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public DbLocations createLocation(DbLocations u) throws DiageoBusinessException {
        try {
            u = super.create(u);
            return u;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }
}
