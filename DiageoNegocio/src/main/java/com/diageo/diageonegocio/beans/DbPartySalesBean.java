/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Db3partySales;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class DbPartySalesBean extends BusinessTransaction<Db3partySales> implements DbPartySalesBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Db3partySales> searchAll() {
        return super.searchAll(Db3partySales.class);
    }

    @Override
    public Db3partySales searchById(Integer id) {
        return (Db3partySales) super.searchById(Db3partySales.class, id);
    }

    @Override
    public Db3partySales createSales(Db3partySales entity) throws DiageoBusinessException {
        try {
            entity = super.create(entity);
            return entity;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    @Override
    public Db3partySales updateSales(Db3partySales entity) throws DiageoBusinessException {
        try {
            entity = (Db3partySales) super.update(entity);
            return entity;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }
}
