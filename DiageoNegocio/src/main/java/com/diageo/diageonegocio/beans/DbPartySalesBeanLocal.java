/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Db3partySales;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class DbPartySalesBeanLocal extends BusinessTransaction<Db3partySales> {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Db3partySales> searchAll() {
        return super.searchAll(Db3partySales.class);
    }

    public Db3partySales searchById(Integer id) {
        return (Db3partySales) super.searchById(Db3partySales.class, id);
    }

    public Db3partySales createSales(Db3partySales entity) throws DiageoBusinessException {
        try {
            entity = super.create(entity);
            return entity;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    public Db3partySales updateSales(Db3partySales entity) throws DiageoBusinessException {
        try {
            entity = (Db3partySales) super.update(entity);
            return entity;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    public List<Db3partySales> findByNameSeller(String query, Integer idParty) {
        query = query.toUpperCase();
        List<Db3partySales> list = super.searchByNamedQuery(Db3partySales.class, Db3partySales.FIND_BY_NAME_SALES, "%" + query + "%", idParty);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public Db3partySales getFirstResult() {
        List<Db3partySales> list = super.searchFirstResult(Db3partySales.class);
        if (list == null || list.isEmpty()) {
            return new Db3partySales();
        }
        return list.get(0);
    }
}
