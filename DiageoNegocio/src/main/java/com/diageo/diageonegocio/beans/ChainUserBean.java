/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbChains;
import com.diageo.diageonegocio.entidades.DbChainsUsers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class ChainUserBean extends BusinessTransaction<DbChainsUsers> implements ChainUserBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Integer> findByUserId(Integer userId) {
        Query sql = super.getEntityManager().createNamedQuery(DbChainsUsers.FIND_BY_USER_ID).setParameter(1, userId);
        Vector vec = (Vector) sql.getResultList();
        List<Integer> list = new ArrayList<>(vec);
        return list;
    }
    
    @Override
    public DbChainsUsers findByChainId(Integer id){
        List<DbChainsUsers> list = super.searchByNamedQuery(DbChainsUsers.class, DbChainsUsers.FIND_BY_CHAIN_ID, id);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DbChains> findByUserIdJoin(Integer userId) {
        Query sql = getEntityManager().createNamedQuery(DbChainsUsers.FIND_BY_USER_ID_JOIN);
        sql.setParameter(1, userId);
        List<DbChains> list = sql.getResultList();
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<DbChains> findByUserIdJoinIn(List<Integer> userId) {
        Query sql = getEntityManager().createNamedQuery(DbChainsUsers.FIND_BY_USER_ID_JOIN_IN);
        if (userId.isEmpty()) {
            userId.add(0);
        }
        sql.setParameter(1, userId);
        List<DbChains> list = sql.getResultList();
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public void updateAllChains(Map<String, Object> filters, Integer id, String state) {
        String sql = "UPDATE c "
                + "SET c.STATUS_MDM='" + state + "' "
                + "FROM DIAGEO_BUSINESS.DBO.DB_CHAINS c "
                + "INNER JOIN DIAGEO_BUSINESS.DBO.DB_CHAINS_USERS OU ON OU.CHAIN_ID = C.CHAIN_ID "
                + "INNER JOIN DIAGEO_BUSINESS.DBO.DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID=C.SUB_SEGMENT_ID "
                + "WHERE OU.USER_ID=" + id + " ";
        StringBuilder sb = new StringBuilder(sql);
        if (filters == null) {
            filters = new HashMap<>();
        }
        if (filters.get("chainId") != null) {
            sb.append("AND C.CHAIN_ID LIKE '%").append(filters.get("chainId").toString().toUpperCase()).append("%' ");
        } else {
            sb.append("AND C.CHAIN_ID LIKE  '%%' ");
        }
        if (filters.get("subSegmentId.nameSubsegment") != null) {
            sb.append("AND SS.NAME_SUBSEGMENT LIKE  '%").append(filters.get("subSegmentId.nameSubsegment").toString().toUpperCase()).append("%' ");
        } else {
            sb.append("AND SS.NAME_SUBSEGMENT LIKE  '%%' ");
        }
        if (filters.get("nameChain") != null) {
            sb.append("AND C.NAME_CHAIN LIKE  '%").append(filters.get("nameChain").toString().toUpperCase()).append("%' ");
        } else {
            sb.append("AND C.NAME_CHAIN LIKE  '%%' ");
        }
        if (filters.get("codeEan") != null) {
            sb.append("AND C.CODE_EAN LIKE  '%").append(filters.get("codeEan").toString().toUpperCase()).append("%' ");
        } else {
            sb.append("AND C.CODE_EAN LIKE  '%%' ");
        }
        if (filters.get("kiernanId") != null) {
            sb.append("AND C.KIERNAN_ID LIKE  '%").append(filters.get("kiernanId").toString().toUpperCase()).append("%' ");
        } else {
            sb.append("AND C.KIERNAN_ID LIKE  '%%' ");
        }
        Query sqlQuery = getEntityManager().createNativeQuery(sb.toString());
        int quantity = sqlQuery.executeUpdate();
    }

    @Override
    public void updateAllChainsIn(Map<String, Object> filters, List<Integer> id, String state) {
        String sql = "UPDATE c "
                + "SET c.STATUS_MDM='" + state + "' "
                + "FROM DIAGEO_BUSINESS.DBO.DB_CHAINS c "
                + "INNER JOIN DIAGEO_BUSINESS.DBO.DB_CHAINS_USERS OU ON OU.CHAIN_ID = C.CHAIN_ID "
                + "INNER JOIN DIAGEO_BUSINESS.DBO.DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID=C.SUB_SEGMENT_ID "
                + "WHERE OU.USER_ID=" + buildIdUserParam(id) + " ";
        StringBuilder sb = new StringBuilder(sql);
        if (filters == null) {
            filters = new HashMap<>();
        }
        if (filters.get("chainId") != null) {
            sb.append("AND C.CHAIN_ID LIKE '%").append(filters.get("chainId").toString().toUpperCase()).append("%' ");
        } else {
            sb.append("AND C.CHAIN_ID LIKE  '%%' ");
        }
        if (filters.get("subSegmentId.nameSubsegment") != null) {
            sb.append("AND SS.NAME_SUBSEGMENT LIKE  '%").append(filters.get("subSegmentId.nameSubsegment").toString().toUpperCase()).append("%' ");
        } else {
            sb.append("AND SS.NAME_SUBSEGMENT LIKE  '%%' ");
        }
        if (filters.get("nameChain") != null) {
            sb.append("AND C.NAME_CHAIN LIKE  '%").append(filters.get("nameChain").toString().toUpperCase()).append("%' ");
        } else {
            sb.append("AND C.NAME_CHAIN LIKE  '%%' ");
        }
        if (filters.get("codeEan") != null) {
            sb.append("AND C.CODE_EAN LIKE  '%").append(filters.get("codeEan").toString().toUpperCase()).append("%' ");
        } else {
            sb.append("AND C.CODE_EAN LIKE  '%%' ");
        }
        if (filters.get("kiernanId") != null) {
            sb.append("AND C.KIERNAN_ID LIKE  '%").append(filters.get("kiernanId").toString().toUpperCase()).append("%' ");
        } else {
            sb.append("AND C.KIERNAN_ID LIKE  '%%' ");
        }
        Query sqlQuery = getEntityManager().createNativeQuery(sb.toString());
        int quantity = sqlQuery.executeUpdate();
    }
    
    @Override
    public long notificationPendingChain(Integer userId,String status){
        Query sql = getEntityManager().createNamedQuery(DbChainsUsers.COUNT_PENDING_OUTLETS);
        sql.setParameter(1, userId);
        sql.setParameter(2, status);
        Object obj=sql.getSingleResult();
        if(obj==null){
            return 0;
        }
        long size = (long) obj;
        return size;
    }
    
    @Override
    public long notificationPendingChainIn(List<String> status){
        Query sql = getEntityManager().createNamedQuery(DbChainsUsers.COUNT_PENDING_OUTLETS_IN);
        sql.setParameter(1, status);
        Object obj=sql.getSingleResult();
        if(obj==null){
            return 0;
        }
        long size = (long) obj;
        return size;
    }

    /**
     * Construye la cadena de texto de user_id, que serán enviados al update
     *
     * @return
     */
    private String buildIdUserParam(List<Integer> userId) {
        if (!userId.isEmpty()) {
            String s = userId.toString();
            s = s.substring(1, s.length() - 1);
            return s;
        }
        return "0";
    }
}
