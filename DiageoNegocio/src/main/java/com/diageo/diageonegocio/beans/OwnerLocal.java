/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbOwners;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class OwnerLocal extends BusinessTransaction<DbOwners> {

    public List<DbOwners> finadAll() {
        return super.searchAll(DbOwners.class);
    }

    public DbOwners findById(Integer id) {
        return (DbOwners) super.searchById(DbOwners.class, id);
    }
}
