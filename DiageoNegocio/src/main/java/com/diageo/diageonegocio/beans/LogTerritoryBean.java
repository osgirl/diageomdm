/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbLogTerritory;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class LogTerritoryBean extends BusinessTransaction<DbLogTerritory> {

    public void createLogTerritory(DbLogTerritory enti) {
        super.create(enti);
    }
}
