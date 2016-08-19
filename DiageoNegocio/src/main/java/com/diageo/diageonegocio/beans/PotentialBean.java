/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbPotentials;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class PotentialBean extends BusinessTransaction<DbPotentials> implements PotentialBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void createPotential(DbPotentials pot) throws DiageoBusinessException {
        try {
            super.create(pot);
        } catch (Exception e) {
            throw new DiageoBusinessException(e.getMessage());
        }

    }

    @Override
    public DbPotentials updatePotential(DbPotentials pot) throws DiageoBusinessException {
        try {
            return (DbPotentials) super.update(pot);
        } catch (Exception e) {
            throw new DiageoBusinessException(e.getMessage());
        }
    }

    @Override
    public DbPotentials findById(Integer id) {
        DbPotentials pot = (DbPotentials) super.searchById(DbPotentials.class, id);
        return pot;
    }

    @Override
    public List<DbPotentials> findAll() {
        List<DbPotentials> list = super.searchAll(DbPotentials.class);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<DbPotentials> findBySubSegment(Integer id) {
        List<DbPotentials> list = super.searchByNamedQuery(DbPotentials.class, DbPotentials.FIND_BY_SUBSEGMENT, id);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

}
