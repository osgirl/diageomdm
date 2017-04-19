/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbClusters;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class ClusterBeanLocal extends BusinessTransaction<DbClusters> {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<DbClusters> findAll() {
        return super.searchAll(DbClusters.class);
    }

    public DbClusters findById(Integer id) {
        return (DbClusters) super.searchById(DbClusters.class, id);
    }
}
