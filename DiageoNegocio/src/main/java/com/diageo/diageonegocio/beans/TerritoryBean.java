/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Db3partyTerritory;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class TerritoryBean extends BusinessTransaction<Db3partyTerritory> implements TerritoryBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Db3partyTerritory> findByNameTerritory(String name) {
        return searchByNamedQuery(Db3partyTerritory.class, Db3partyTerritory.FIND_BY_NAME_TERRITORY, "%" + name + "%");
    }

    @Override
    public List<Db3partyTerritory> findAll() {
        return super.searchAll(Db3partyTerritory.class);
    }

    @Override
    public Db3partyTerritory findByName(String name) {
        List<Db3partyTerritory> list = super.searchByNamedQuery(Db3partyTerritory.class, Db3partyTerritory.FIND_BY_NAME_TERRITORY, name);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public Db3partyTerritory findById(Integer id) {
        return (Db3partyTerritory) super.searchById(Db3partyTerritory.class, id);
    }
}
