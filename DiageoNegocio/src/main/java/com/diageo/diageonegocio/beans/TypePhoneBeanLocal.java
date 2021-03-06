/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbTypePhones;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class TypePhoneBeanLocal extends BusinessTransaction<DbTypePhones>  {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<DbTypePhones> findAll() {
        return super.searchAll(DbTypePhones.class);
    }

    public DbTypePhones findById(Integer id) {
        return (DbTypePhones) super.searchById(DbTypePhones.class, id);
    }
}
