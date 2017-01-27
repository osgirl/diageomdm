/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Db3partyTerritory;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface TerritoryBeanLocal {

    public List<Db3partyTerritory> findByNameTerritory(String name);

    public List<Db3partyTerritory> findAll();

    public Db3partyTerritory findByName(String name);

    public Db3partyTerritory findById(Integer id);
    
}