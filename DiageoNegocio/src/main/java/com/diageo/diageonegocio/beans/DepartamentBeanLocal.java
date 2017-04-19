/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbDepartaments;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class DepartamentBeanLocal extends BusinessTransaction<DbDepartaments>  {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<DbDepartaments> findAllDepartament() {
        List<DbDepartaments> lista = super.searchAll(DbDepartaments.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    public DbDepartaments findById(Integer id) {
        return (DbDepartaments) super.searchById(DbDepartaments.class, id);
    }
}
