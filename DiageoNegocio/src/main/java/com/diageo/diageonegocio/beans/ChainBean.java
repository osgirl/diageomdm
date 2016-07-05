/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbChains;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class ChainBean extends BusinessTransaction<DbChains> implements ChainBeanLocal {

    @Override
    public DbChains createChain(DbChains entity) {
        entity = super.create(entity);
        return entity;
    }

    @Override
    public DbChains updateChain(DbChains entity) {
        entity = (DbChains) super.update(entity);
        return entity;
    }

    @Override
    public List<DbChains> findAllChains() {
        return super.searchAll(DbChains.class);
    }

    @Override
    public DbChains findById(Integer id) {
        return (DbChains) super.searchById(DbChains.class, id);
    }

}
