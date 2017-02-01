/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbOutletsUsers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class OutletsUserBean extends BusinessTransaction<DbOutletsUsers> implements OutletsUserBeanLocal {

    @Override
    public List<Integer> findByUserId(Integer userId) {
        Query sql = super.getEntityManager().createNamedQuery(DbOutletsUsers.FIND_BY_USER_ID).setParameter(1, userId);
        Vector vec = (Vector) sql.getResultList();
        List<Integer> list = new ArrayList<>(vec);
        return list;
    }

    @Override
    public DbOutletsUsers findByOutletId(Integer id) {
        List<DbOutletsUsers> list = super.searchByNamedQuery(DbOutletsUsers.class, DbOutletsUsers.FIND_BY_OUTLET_ID, id);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DbOutlets> findOutletByUser(int initial, int page, Map<String, Object> filters, Integer id) {
        Query sql = getEntityManager().createNamedQuery(DbOutletsUsers.FIND_BY_USER_OUTLETS_JOIN);
        sql.setParameter("userId", id);
        if (filters == null) {
            filters = new HashMap<>();
        }
        if (filters.get("nit") != null) {
            sql.setParameter("nit", "%" + filters.get("nit").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nit", "%%");
        }
        if (filters.get("businessName") != null) {
            sql.setParameter("businessName", "%" + filters.get("businessName").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("businessName", "%%");
        }
        if (filters.get("numberPdv") != null) {
            sql.setParameter("numberPdv", "%" + filters.get("numberPdv").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("numberPdv", "%%");
        }
        if (filters.get("kiernanId") != null) {
            sql.setParameter("kiernanId", "%" + filters.get("kiernanId").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("kiernanId", "%%");
        }
        if (filters.get("subSegmentId.nameSubsegment") != null) {
            sql.setParameter("nameSubsegment", "%" + filters.get("subSegmentId.nameSubsegment").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSubsegment", "%%");
        }
        if (filters.get("statusOutlet") != null) {
            sql.setParameter("statusOutlet", "%" + filters.get("statusOutlet").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("statusOutlet", "%%");
        }
        if (filters.get("statusMDM") != null) {
            sql.setParameter("statusMDM", "%" + filters.get("statusMDM").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("statusMDM", "%%");
        }
        sql.setFirstResult(initial);
        sql.setMaxResults(page);
        List<DbOutlets> list = sql.getResultList();
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public long findOutletByUserCount(int initial, int page, Map<String, Object> filters, Integer id) {
        Query sql = getEntityManager().createNamedQuery(DbOutletsUsers.FIND_BY_USER_OUTLETS_JOIN_COUNT);
        sql.setParameter("userId", id);
        if (filters == null) {
            filters = new HashMap<>();
        }
        if (filters.get("nit") != null) {
            sql.setParameter("nit", "%" + filters.get("nit").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nit", "%%");
        }
        if (filters.get("businessName") != null) {
            sql.setParameter("businessName", "%" + filters.get("businessName").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("businessName", "%%");
        }
        if (filters.get("numberPdv") != null) {
            sql.setParameter("numberPdv", "%" + filters.get("numberPdv").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("numberPdv", "%%");
        }
        if (filters.get("kiernanId") != null) {
            sql.setParameter("kiernanId", "%" + filters.get("kiernanId").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("kiernanId", "%%");
        }
        if (filters.get("subSegmentId.nameSubsegment") != null) {
            sql.setParameter("nameSubsegment", "%" + filters.get("subSegmentId.nameSubsegment").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSubsegment", "%%");
        }
        if (filters.get("statusOutlet") != null) {
            sql.setParameter("statusOutlet", "%" + filters.get("statusOutlet").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("statusOutlet", "%%");
        }
        if (filters.get("statusMDM") != null) {
            sql.setParameter("statusMDM", "%" + filters.get("statusMDM").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("statusMDM", "%%");
        }
        long size = (long) sql.getSingleResult();
        return size;
    }

    @Override
    public long findOutletByUserCountIn(int initial, int page, Map<String, Object> filters, List<Integer> id) {
        Query sql = getEntityManager().createNamedQuery(DbOutletsUsers.FIND_BY_USER_OUTLETS_JOIN_COUNT_IN);
        if (id.isEmpty()) {
            id.add(0);
        }
        sql.setParameter("userId", id);
        if (filters == null) {
            filters = new HashMap<>();
        }
        if (filters.get("nit") != null) {
            sql.setParameter("nit", "%" + filters.get("nit").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nit", "%%");
        }
        if (filters.get("businessName") != null) {
            sql.setParameter("businessName", "%" + filters.get("businessName").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("businessName", "%%");
        }
        if (filters.get("numberPdv") != null) {
            sql.setParameter("numberPdv", "%" + filters.get("numberPdv").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("numberPdv", "%%");
        }
        if (filters.get("kiernanId") != null) {
            sql.setParameter("kiernanId", "%" + filters.get("kiernanId").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("kiernanId", "%%");
        }
        if (filters.get("subSegmentId.nameSubsegment") != null) {
            sql.setParameter("nameSubsegment", "%" + filters.get("subSegmentId.nameSubsegment").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSubsegment", "%%");
        }
        if (filters.get("statusOutlet") != null) {
            sql.setParameter("statusOutlet", "%" + filters.get("statusOutlet").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("statusOutlet", "%%");
        }
        if (filters.get("statusMDM") != null) {
            sql.setParameter("statusMDM", "%" + filters.get("statusMDM").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("statusMDM", "%%");
        }
        long size = (long) sql.getSingleResult();
        return size;
    }

    @Override
    public List<DbOutlets> findOutletByUserIn(int initial, int page, Map<String, Object> filters, List<Integer> id) {
        Query sql = getEntityManager().createNamedQuery(DbOutletsUsers.FIND_BY_USER_OUTLETS_JOIN_IN);
        if (id.isEmpty()) {
            id.add(0);
        }
        sql.setParameter("userId", id);
        if (filters == null) {
            filters = new HashMap<>();
        }
        if (filters.get("nit") != null) {
            sql.setParameter("nit", "%" + filters.get("nit").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nit", "%%");
        }
        if (filters.get("businessName") != null) {
            sql.setParameter("businessName", "%" + filters.get("businessName").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("businessName", "%%");
        }
        if (filters.get("numberPdv") != null) {
            sql.setParameter("numberPdv", "%" + filters.get("numberPdv").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("numberPdv", "%%");
        }
        if (filters.get("kiernanId") != null) {
            sql.setParameter("kiernanId", "%" + filters.get("kiernanId").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("kiernanId", "%%");
        }
        if (filters.get("subSegmentId.nameSubsegment") != null) {
            sql.setParameter("nameSubsegment", "%" + filters.get("subSegmentId.nameSubsegment").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSubsegment", "%%");
        }
        if (filters.get("statusOutlet") != null) {
            sql.setParameter("statusOutlet", "%" + filters.get("statusOutlet").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("statusOutlet", "%%");
        }
        if (filters.get("statusMDM") != null) {
            sql.setParameter("statusMDM", "%" + filters.get("statusMDM").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("statusMDM", "%%");
        }
        sql.setFirstResult(initial);
        sql.setMaxResults(page);
        List<DbOutlets> list = sql.getResultList();
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    /**
     * Actualiza todos los registros de los tmc
     *
     * @param id
     * @param state
     * @param filters
     */
    @Override
    public void updateOutlet(Integer id, String state, Map<String, Object> filters) {
        String sqlStr = "UPDATE  O "
                + "SET O.STATUS_MDM='" + state + "' "
                + "FROM  DIAGEO_BUSINESS.DBO.DB_OUTLETS O "
                + "INNER JOIN DIAGEO_BUSINESS.DBO.DB_OUTLETS_USERS OU ON OU.OUTLET_ID = O.OUTLET_ID "
                + "INNER JOIN DIAGEO_BUSINESS.DBO.DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID=O.SUB_SEGMENT_ID "
                + "WHERE OU.USER_ID=" + id + " ";
        StringBuilder sqlUpdate = new StringBuilder(sqlStr);
        if (filters == null) {
            filters = new HashMap<>();
        }
        if (filters.get("nit") != null) {
            sqlUpdate.append("AND O.NIT LIKE '%").append(filters.get("nit").toString().toUpperCase()).append("%'");
        } else {
            sqlUpdate.append("AND O.NIT LIKE '%%'");
        }
        if (filters.get("businessName") != null) {
            sqlUpdate.append("AND O.BUSINESS_NAME LIKE '%").append(filters.get("businessName").toString().toUpperCase()).append("%'");
        } else {
            sqlUpdate.append("AND O.BUSINESS_NAME LIKE '%%'");
        }
        if (filters.get("numberPdv") != null) {
            sqlUpdate.append("AND O.NUMBER_PDV LIKE '%").append(filters.get("numberPdv").toString().toUpperCase()).append("%'");
        } else {
            sqlUpdate.append("AND O.NUMBER_PDV LIKE '%%'");
        }
        if (filters.get("kiernanId") != null) {
            sqlUpdate.append("AND O.KIERNAN_ID LIKE '%").append(filters.get("kiernanId").toString().toUpperCase()).append("%'");
        } else {
            sqlUpdate.append("AND O.KIERNAN_ID LIKE '%%'");
        }
        if (filters.get("subSegmentId.nameSubsegment") != null) {
            sqlUpdate.append("AND SS.NAME_SUBSEGMENT LIKE '%").append(filters.get("subSegmentId.nameSubsegment").toString().toUpperCase()).append("%'");
        } else {
            sqlUpdate.append("AND SS.NAME_SUBSEGMENT LIKE '%%'");
        }
        if (filters.get("statusOutlet") != null) {
            sqlUpdate.append("AND O.STATUS_OUTLET LIKE '%").append(filters.get("statusOutlet").toString().toUpperCase()).append("%'");
        } else {
            sqlUpdate.append("AND O.STATUS_OUTLET LIKE '%%'");
        }
        if (filters.get("statusMDM") != null) {
            sqlUpdate.append("AND O.STATUS_MDM LIKE '%").append(filters.get("statusMDM").toString().toUpperCase()).append("%'");
        } else {
            sqlUpdate.append("AND O.STATUS_MDM LIKE '%%'");
        }

        Query sql = getEntityManager().createNativeQuery(sqlUpdate.toString());
        int quantity = sql.executeUpdate();
    }

    @Override
    public void updateOutletCommercialManager(List<Integer> id, String state, Map<String, Object> filters) {
        String sqlStr = "UPDATE  O "
                + "SET O.STATUS_MDM='" + state + "' "
                + "FROM DIAGEO_WEB.DBO.DW_RELATION_USERS RU "
                + "INNER JOIN DIAGEO_BUSINESS.DBO.DB_OUTLETS_USERS OU ON OU.USER_ID=RU.USER_ID "
                + "INNER JOIN DIAGEO_BUSINESS.DBO.DB_OUTLETS O ON O.OUTLET_ID=OU.OUTLET_ID "
                + "INNER JOIN DIAGEO_BUSINESS.DBO.DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID=O.SUB_SEGMENT_ID "
                + "WHERE RU.USER_ID IN(" + buildIdUserParam(id) + ") "
                + "AND RU.STATE_APPROVED=1 ";
        StringBuilder sqlUpdate = new StringBuilder(sqlStr);
        if (filters == null) {
            filters = new HashMap<>();
        }
        if (filters.get("nit") != null) {
            sqlUpdate.append("AND O.NIT LIKE '%").append(filters.get("nit").toString().toUpperCase()).append("%'");
        } else {
            sqlUpdate.append("AND O.NIT LIKE '%%'");
        }
        if (filters.get("businessName") != null) {
            sqlUpdate.append("AND O.BUSINESS_NAME LIKE '%").append(filters.get("businessName").toString().toUpperCase()).append("%'");
        } else {
            sqlUpdate.append("AND O.BUSINESS_NAME LIKE '%%'");
        }
        if (filters.get("numberPdv") != null) {
            sqlUpdate.append("AND O.NUMBER_PDV LIKE '%").append(filters.get("numberPdv").toString().toUpperCase()).append("%'");
        } else {
            sqlUpdate.append("AND O.NUMBER_PDV LIKE '%%'");
        }
        if (filters.get("kiernanId") != null) {
            sqlUpdate.append("AND O.KIERNAN_ID LIKE '%").append(filters.get("kiernanId").toString().toUpperCase()).append("%'");
        } else {
            sqlUpdate.append("AND O.KIERNAN_ID LIKE '%%'");
        }
        if (filters.get("subSegmentId.nameSubsegment") != null) {
            sqlUpdate.append("AND SS.NAME_SUBSEGMENT LIKE '%").append(filters.get("subSegmentId.nameSubsegment").toString().toUpperCase()).append("%'");
        } else {
            sqlUpdate.append("AND SS.NAME_SUBSEGMENT LIKE '%%'");
        }
        if (filters.get("statusOutlet") != null) {
            sqlUpdate.append("AND O.STATUS_OUTLET LIKE '%").append(filters.get("statusOutlet").toString().toUpperCase()).append("%'");
        } else {
            sqlUpdate.append("AND O.STATUS_OUTLET LIKE '%%'");
        }
        if (filters.get("statusMDM") != null) {
            sqlUpdate.append("AND O.STATUS_MDM LIKE '%").append(filters.get("statusMDM").toString().toUpperCase()).append("%'");
        } else {
            sqlUpdate.append("AND O.STATUS_MDM LIKE '%%'");
        }

        Query sql = getEntityManager().createNativeQuery(sqlUpdate.toString());
        int quantity = sql.executeUpdate();
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
