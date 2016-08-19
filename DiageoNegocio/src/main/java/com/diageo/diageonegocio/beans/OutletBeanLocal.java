/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface OutletBeanLocal {

    public DbOutlets createOutlet(DbOutlets out) throws DiageoBusinessException;

    public DbOutlets updateOutlet(DbOutlets outlet) throws DiageoBusinessException;

    public List<DbOutlets> findAllOutlets();

    public DbOutlets findById(Integer id) throws DiageoBusinessException;

    public List<DbOutlets> findByDistributor(Integer idDistri);

    public List<DbOutlets> listOutletNew(String isNew);

    public List<DbOutlets> findByDistributor(Set<Integer> setDistributor, Set<Integer> setSubSegment, List<Integer> listState, String isNew);

    public List<DbOutlets> findBySubSegment(Integer idSubSegment);

    public List<DbOutlets> findBySegmentDb3Party(Integer subSegment, Integer db3Party);

    public List<DbOutlets> findByBusinessName(String query);

    public List<DbOutlets> findBy3PartyPermissionSegment(Integer id3party, List<Integer> subSegment,String statusMDM);
    
}
