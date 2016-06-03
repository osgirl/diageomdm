/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbTowns;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class CityBean extends BusinessTransaction<DbTowns> implements CityBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public DbTowns findById(Integer id) {
        return (DbTowns) super.searchById(DbTowns.class, id);
    }

    @Override
    public List<DbTowns> findByDepartamentId(Integer id) {
        List<DbTowns> lista = super.searchByNamedQuery(DbTowns.class, DbTowns.FIND_BY_DEPTO, id);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }
}
