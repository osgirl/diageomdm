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
public class PotentialBeanLocal extends BusinessTransaction<DbPotentials> {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void createPotential(DbPotentials pot) throws DiageoBusinessException {
        try {
            super.create(pot);
        } catch (Exception e) {
            throw new DiageoBusinessException(e.getMessage());
        }

    }

    public DbPotentials updatePotential(DbPotentials pot) throws DiageoBusinessException {
        try {
            return (DbPotentials) super.update(pot);
        } catch (Exception e) {
            throw new DiageoBusinessException(e.getMessage());
        }
    }

    public DbPotentials findById(Integer id) {
        DbPotentials pot = (DbPotentials) super.searchById(DbPotentials.class, id);
        return pot;
    }

    public List<DbPotentials> findAll() {
        List<DbPotentials> list = super.searchAll(DbPotentials.class);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public List<DbPotentials> findBySubSegment(Integer id) {
        List<DbPotentials> list = super.searchByNamedQuery(DbPotentials.class, DbPotentials.FIND_BY_SUBSEGMENT, id);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public DbPotentials findByNamePotentialAndSubSegmentId(Integer subSegmentId, String namePotential) {
        List<DbPotentials> list = super.searchByNamedQuery(DbPotentials.class, DbPotentials.FIND_BY_SUBSEGMENT_NAME_SUBSEGMENT, subSegmentId, namePotential);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
