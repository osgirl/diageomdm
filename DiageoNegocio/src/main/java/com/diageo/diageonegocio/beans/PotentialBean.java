/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Potential;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class PotentialBean extends BusinessTransaction<Potential> implements PotentialBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void createPotential(Potential pot) throws DiageoNegocioException {
        try {
            super.create(pot);
        } catch (Exception e) {
            throw new DiageoNegocioException(e.getMessage());
        }

    }

    @Override
    public Potential findById(Integer id) {
        Potential pot = (Potential) super.searchById(Potential.class, id);
        return pot;
    }

    @Override
    public List<Potential> findAll() {
        List<Potential> list = super.searchAll(Potential.class);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<Potential> findBySubSegment(Integer id) {
        List<Potential> list = super.searchByNamedQuery(Potential.class, Potential.FIND_BY_SUBSEGMENT, id);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

}
