/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbLayer;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class DbLayerBeanLocal extends BusinessTransaction<DbLayer>  {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<DbLayer> searchAll() {
        return super.searchAll(DbLayer.class);
    }

    public DbLayer searchId(Integer id) {
        return (DbLayer) super.searchById(DbLayer.class, id);
    }
}
