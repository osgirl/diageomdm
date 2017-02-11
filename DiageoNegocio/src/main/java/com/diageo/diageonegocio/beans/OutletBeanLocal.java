/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbOutletsUsers;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import java.util.Map;
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

    /**
     * Busca por business_name y kiernan_id
     * @param query
     * @return 
     */
    public List<DbOutlets> findByBusinessName(String query);

    public List<DbOutlets> findBy3PartyPermissionSegment(Integer id3party, List<Integer> subSegment, List<String> statusMDM);

    public void deleteCustomerOutlet(Integer customerId, Integer outletId);

    public long countOutlets();

    public List<DbOutlets> findAllOutlets(int initial, int page, Map<String, Object> filters);

    public long findAllOutletsCount(int initial, int page, Map<String, Object> filters);

    public List<DbOutlets> findBy3PartyPermissionSegment(Integer id3party, List<Integer> subSegment, List<String> statusMDM, Map<String, Object> filters, int initial, int page);

    public long findBy3PartyPermissionSegmentCount(Integer id3party, List<Integer> subSegment, List<String> statusMDM, Map<String, Object> filters);

    public List<DbOutlets> findAllOutlets(String[] names, String[] values);

    public List<DbOutlets> findAllOutlets(Map<String, Object> filters);

    public long findAllOutletsCountProfiles(int initial, int page, Map<String, Object> filters, List<Integer> listOutletid);

    public List<DbOutlets> findAllOutletsProfiles(int initial, int page,Map<String, Object> filters, List<Integer> listOutletid);

}
