/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbFascias;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class FasciaLocal extends BusinessTransaction<DbFascias> {

    public List<DbFascias> finadAll() {
        return super.searchAll(DbFascias.class);
    }

    public DbFascias findById(Integer id) {
        return (DbFascias) super.searchById(DbFascias.class, id);
    }
}
