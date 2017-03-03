/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbChains;
import com.diageo.diageonegocio.entidades.DbChainsUsers;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface ChainUserBeanLocal {

    public List<Integer> findByUserId(Integer userId);

    public List<DbChains> findByUserIdJoin(Integer userId);

    public void updateAllChains(Map<String, Object> filters, Integer id, String state);

    public List<DbChains> findByUserIdJoinIn(List<Integer> userId);

    public void updateAllChainsIn(Map<String, Object> filters, List<Integer> id, String state);

    public DbChainsUsers findByChainId(Integer id);

    public long notificationPendingChain(Integer userId, String status);

    public long notificationPendingChainIn(List<String> status);

}
