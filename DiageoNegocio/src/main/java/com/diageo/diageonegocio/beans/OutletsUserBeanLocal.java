/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbOutletsUsers;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface OutletsUserBeanLocal {

    public List<Integer> findByUserId(Integer userId);

    public List<DbOutlets> findOutletByUser(int initial, int page, Map<String, Object> filters, Integer id);

    public long findOutletByUserCount(int initial, int page, Map<String, Object> filters, Integer id);

    public void updateOutlet(Integer id, String state, Map<String, Object> filters);

    public long findOutletByUserCountIn(int initial, int page, Map<String, Object> filters, List<Integer> id);

    public List<DbOutlets> findOutletByUserIn(int initial, int page, Map<String, Object> filters, List<Integer> id);

    public void updateOutletCommercialManager(List<Integer> id, String state, Map<String, Object> filters);

    public DbOutletsUsers findByOutletId(Integer id);

    public long notificationPendingOutlet(Integer userId, String status); 

    public long notificationPendingOutlet(List<String> status);

    public DbOutletsUsers findByOutletIdProfileId(Integer id);
    
}
