/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbOcs;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class OcsBeanLocal extends BusinessTransaction<DbOcs>{

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<DbOcs> findAll() {
        return super.searchAll(DbOcs.class);
    }

    public DbOcs findById(Integer id) {
        return (DbOcs) super.searchById(DbOcs.class, id);
    }
}
