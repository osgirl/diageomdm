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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class OutletsUserBeanLocal extends BusinessTransaction<DbOutletsUsers> {

    public List<Integer> findByUserId(Integer userId) {
        Query sql = super.getEntityManager().createNamedQuery(DbOutletsUsers.FIND_BY_USER_ID).setParameter(1, userId);
        Vector vec = (Vector) sql.getResultList();
        List<Integer> list = new ArrayList<>(vec);
        return list;
    }

    public DbOutletsUsers findByOutletId(Integer id) {
        List<DbOutletsUsers> list = super.searchByNamedQuery(DbOutletsUsers.class, DbOutletsUsers.FIND_BY_OUTLET_ID, id);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public DbOutletsUsers findByOutletIdProfileId(Integer id) {
        List<DbOutletsUsers> list = super.searchByNamedQuery(DbOutletsUsers.class, DbOutletsUsers.FIND_BY_OUTLET_ID_PROFILE_ID, id, 3);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<DbOutletsUsers> findAllOutletsDinamic(int initial, int page, Map<String, Object> filters, Integer id, boolean isCommercialManager, List<Integer> userIdList) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DbOutletsUsers> query = cb.createQuery(DbOutletsUsers.class);
        Query sql = getEntityManager().createQuery("SELECT do FROM DbOutletsUsers o JOIN DbOutlets do ON do.outletId=o.dbOutletsUsersPK.outletId");

        Root<DbOutletsUsers> out = query.from(DbOutletsUsers.class);
        query.select(out);

        Join<DbOutletsUsers, DbOutlets> info = out.join("dbOutlets", JoinType.INNER);

        List<Predicate> crit = new ArrayList<>();
        if (isCommercialManager) {
            Expression<Integer> exp = out.get("dbOutletsUsersPK").get("userId");
            Predicate predicate = exp.in(userIdList);
            crit.add(predicate);
        } else {
            ParameterExpression<Integer> paramUser = cb.parameter(Integer.class, "userId");
            crit.add(cb.equal(out.get("dbOutletsUsersPK").get("userId"), paramUser));
        }
        if (filters != null && !filters.isEmpty()) {
            if (filters.get("nit") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nit");
                crit.add(cb.like(info.<String>get("nit"), param));
            }
            if (filters.get("businessName") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "businessName");
                crit.add(cb.like(info.<String>get("businessName"), param));
            }
            if (filters.get("outletName") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "outletName");
                crit.add(cb.like(info.<String>get("outletName"), param));
            }
            if (filters.get("numberPdv") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "numberPdv");
                crit.add(cb.equal(info.get("numberPdv"), param));
            }
            if (filters.get("kiernanId") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "kiernanId");
                crit.add(cb.like(info.<String>get("kiernanId"), param));
            }
            if (filters.get("subSegmentId.nameSubsegment") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameSubsegment");
                crit.add(cb.like(info.<String>get("subSegmentId").<String>get("nameSubsegment"), param));
            }
            if (filters.get("statusOutlet") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "statusOutlet");
                crit.add(cb.equal(info.get("statusOutlet"), param));
            }
            if (filters.get("statusMDM") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "statusMDM");
                crit.add(cb.equal(info.get("statusMDM"), param));
            }
            if (filters.get("townId.nameTown") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameTown");
                crit.add(cb.like(info.<String>get("townId").<String>get("nameTown"), param));
            }
            if (filters.get("distributorSubSegmentId.nameSubsegment") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameSubsegment");
                crit.add(cb.like(info.<String>get("distributorSubSegmentId").<String>get("nameSubsegment"), param));
            }
            if (filters.get("outletIdFather.outletId") != null) {
                try {
                    ParameterExpression<Integer> param = cb.parameter(Integer.class, "outletIdFather");
                    crit.add(cb.equal(info.get("outletIdFather").get("outletId"), param));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(OutletBeanLocal.class.getName()).log(Level.SEVERE,
                            "Par {0},{1} no corresponde a un numero", new String[]{"outletIdFather", filters.get("outletIdFather") + ""});
                }
            }
            if (filters.get("subSegmentId.segmentId.subChannelId.channelId.nameChannel") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameChannel");
                crit.add(cb.like(info.<String>get("subSegmentId").<String>get("segmentId").
                        <String>get("subChannelId").<String>get("channelId").<String>get("nameChannel"), param));
            }
            if (filters.get("journeyPlan") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "journeyPlan");
                crit.add(cb.equal(info.get("journeyPlan"), param));
            }
            if (filters.get("db3partySaleId.pdv") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "pdv");
                crit.add(cb.like(info.<String>get("db3partySaleId").<String>get("pdv"), param));
            }
            if (filters.get("db3PartyIdOld.db3partyRegionalId.nameRegional") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameRegional");
                crit.add(cb.like(info.<String>get("db3PartyIdOld").<String>get("db3partyRegionalId").<String>get("nameRegional"), param));
            }
            if (filters.get("db3PartyIdOld.name3party") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "name3partyOld");
                crit.add(cb.like(info.<String>get("db3PartyIdOld").<String>get("name3party"), param));
            }
            if (filters.get("subSegmentId.segmentId.subChannelId.nameSubChannel") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameSubChannel");
                crit.add(cb.like(info.<String>get("subSegmentId").<String>get("segmentId").<String>get("subChannelId").<String>get("nameSubChannel"), param));
            }
            if (filters.get("db3partySaleId.nameSales") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameSales");
                crit.add(cb.like(info.<String>get("db3partySaleId").<String>get("nameSales"), param));
            }
            if (filters.get("address") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "address");
                crit.add(cb.equal(info.get("address"), param));
            }
            if (filters.get("townId.departamentId.nameDepartament") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameDepartament");
                crit.add(cb.like(info.<String>get("townId").<String>get("departamentId").<String>get("nameDepartament"), param));
            }
            if (filters.get("outletId") != null) {
                try {
                    ParameterExpression<Integer> param = cb.parameter(Integer.class, "outletId");
                    crit.add(cb.equal(info.get("outletId"), param));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(OutletBeanLocal.class.getName()).log(Level.SEVERE,
                            "Par {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("subSegmentId.segmentId.nameSegment") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameSegment");
                crit.add(cb.like(info.<String>get("subSegmentId").<String>get("segmentId").<String>get("nameSegment"), param));
            }
            if (filters.get("potentialId.namePotential") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "namePotential");
                crit.add(cb.like(info.<String>get("potentialId").<String>get("namePotential"), param));
            }
            if (crit.size() == 1) {
                query.where(crit.get(0));
            } else if (crit.size() > 1) {
                query.where(cb.and(crit.toArray(new Predicate[0])));
            }
            TypedQuery<DbOutletsUsers> q = getEntityManager().createQuery(query);
            if (filters.get("nit") != null) {
                q.setParameter("nit", "%" + filters.get("nit") + "%");
            }
            if (filters.get("businessName") != null) {
                q.setParameter("businessName", "%" + filters.get("businessName") + "%");
            }
            if (filters.get("outletName") != null) {
                q.setParameter("outletName", "%" + filters.get("outletName") + "%");
            }
            if (filters.get("numberPdv") != null) {
                q.setParameter("numberPdv", filters.get("numberPdv"));
            }
            if (filters.get("kiernanId") != null) {
                q.setParameter("kiernanId", "%" + filters.get("kiernanId") + "%");
            }
            if (filters.get("subSegmentId.nameSubsegment") != null) {
                q.setParameter("nameSubsegment", "%" + filters.get("subSegmentId.nameSubsegment") + "%");
            }
            if (filters.get("statusOutlet") != null) {
                q.setParameter("statusOutlet", filters.get("statusOutlet"));
            }
            if (filters.get("statusMDM") != null) {
                q.setParameter("statusMDM", filters.get("statusMDM"));
            }
            if (filters.get("townId.nameTown") != null) {
                q.setParameter("nameTown", "%" + filters.get("townId.nameTown") + "%");
            }
            if (filters.get("distributorSubSegmentId.nameSubsegment") != null) {
                q.setParameter("nameSubsegment", "%" + filters.get("distributorSubSegmentId.nameSubsegment") + "%");
            }
            if (filters.get("outletIdFather.outletId") != null) {
                try {
                    q.setParameter("outletIdFather", filters.get("outletIdFather.outletId"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(OutletBeanLocal.class.getName()).log(Level.SEVERE,
                            "Val {0},{1} no corresponde a un numero", new String[]{"outletIdFather", filters.get("outletIdFather.outletId") + ""});
                }
            }
            if (filters.get("subSegmentId.segmentId.subChannelId.channelId.nameChannel") != null) {
                q.setParameter("nameChannel", "%" + filters.get("subSegmentId.segmentId.subChannelId.channelId.nameChannel") + "%");
            }
            if (filters.get("journeyPlan") != null) {
                q.setParameter("journeyPlan", filters.get("journeyPlan"));
            }
            if (filters.get("db3partySaleId.pdv") != null) {
                q.setParameter("pdv", "%" + filters.get("db3partySaleId.pdv") + "%");
            }
            if (filters.get("db3PartyIdOld.db3partyRegionalId.nameRegional") != null) {
                q.setParameter("nameRegional", "%" + filters.get("db3PartyIdOld.db3partyRegionalId.nameRegional") + "%");
            }
            if (filters.get("db3PartyIdOld.name3party") != null) {
                q.setParameter("name3partyOld", "%" + filters.get("db3PartyIdOld.name3party") + "%");
            }
            if (filters.get("subSegmentId.segmentId.subChannelId.nameSubChannel") != null) {
                q.setParameter("nameSubChannel", "%" + filters.get("subSegmentId.segmentId.subChannelId.nameSubChannel") + "%");
            }
            if (filters.get("db3partySaleId.nameSales") != null) {
                q.setParameter("nameSales", "%" + filters.get("db3partySaleId.nameSales") + "%");
            }
            if (filters.get("address") != null) {
                q.setParameter("address", filters.get("address"));
            }
            if (filters.get("townId.departamentId.nameDepartament") != null) {
                q.setParameter("nameDepartament", "%" + filters.get("townId.departamentId.nameDepartament") + "%");
            }
            if (filters.get("outletId") != null) {
                try {
                    q.setParameter("outletId", filters.get("outletId"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(OutletBeanLocal.class.getName()).log(Level.SEVERE,
                            "Val {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("subSegmentId.segmentId.nameSegment") != null) {
                q.setParameter("nameSegment", "%" + filters.get("subSegmentId.segmentId.nameSegment") + "%");
            }
            if (filters.get("potentialId.namePotential") != null) {
                q.setParameter("namePotential", "%" + filters.get("potentialId.namePotential") + "%");
            }
            if (!isCommercialManager) {
                q.setParameter("userId", id);
            }
            q.setFirstResult(initial);
            q.setMaxResults(page);
            return q.getResultList();
        } else {
            query.where(crit.get(0));
            TypedQuery<DbOutletsUsers> q = getEntityManager().createQuery(query);
            if (!isCommercialManager) {
                q.setParameter("userId", id);
            }
            q.setFirstResult(initial);
            q.setMaxResults(page);
            return q.getResultList();
        }
    }

    public long findAllOutletsDinamicCount(Map<String, Object> filters, Integer id, boolean isCommercialManager, List<Integer> userIdList) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<DbOutlets> info = query.from(DbOutlets.class);
        query.select(cb.count(info));
        Join<DbOutletsUsers, DbOutlets> out = info.join("dbOutletsUsersCollection", JoinType.INNER);

        List<Predicate> crit = new ArrayList<>();
        if (isCommercialManager) {
            Expression<Integer> exp = out.get("dbOutletsUsersPK").get("userId");
            Predicate predicate = exp.in(userIdList);
            crit.add(predicate);
        } else {
            ParameterExpression<Integer> paramUser = cb.parameter(Integer.class, "userId");
            crit.add(cb.equal(out.get("dbOutletsUsersPK").get("userId"), paramUser));
        }
        if (filters != null && !filters.isEmpty()) {
            if (filters.get("nit") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nit");
                crit.add(cb.like(info.<String>get("nit"), param));
            }
            if (filters.get("businessName") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "businessName");
                crit.add(cb.like(info.<String>get("businessName"), param));
            }
            if (filters.get("outletName") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "outletName");
                crit.add(cb.like(info.<String>get("outletName"), param));
            }
            if (filters.get("numberPdv") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "numberPdv");
                crit.add(cb.equal(info.get("numberPdv"), param));
            }
            if (filters.get("kiernanId") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "kiernanId");
                crit.add(cb.like(info.<String>get("kiernanId"), param));
            }
            if (filters.get("subSegmentId.nameSubsegment") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameSubsegment");
                crit.add(cb.like(info.<String>get("subSegmentId").<String>get("nameSubsegment"), param));
            }
            if (filters.get("statusOutlet") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "statusOutlet");
                crit.add(cb.equal(info.get("statusOutlet"), param));
            }
            if (filters.get("statusMDM") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "statusMDM");
                crit.add(cb.equal(info.get("statusMDM"), param));
            }
            if (filters.get("townId.nameTown") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameTown");
                crit.add(cb.like(info.<String>get("townId").<String>get("nameTown"), param));
            }
            if (filters.get("distributorSubSegmentId.nameSubsegment") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameSubsegment");
                crit.add(cb.like(info.<String>get("distributorSubSegmentId").<String>get("nameSubsegment"), param));
            }
            if (filters.get("outletIdFather.outletId") != null) {
                try {
                    ParameterExpression<Integer> param = cb.parameter(Integer.class, "outletIdFather");
                    crit.add(cb.equal(info.get("outletIdFather").get("outletId"), param));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(OutletBeanLocal.class.getName()).log(Level.SEVERE,
                            "Par {0},{1} no corresponde a un numero", new String[]{"outletIdFather", filters.get("outletIdFather") + ""});
                }
            }
            if (filters.get("subSegmentId.segmentId.subChannelId.channelId.nameChannel") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameChannel");
                crit.add(cb.like(info.<String>get("subSegmentId").<String>get("segmentId").
                        <String>get("subChannelId").<String>get("channelId").<String>get("nameChannel"), param));
            }
            if (filters.get("journeyPlan") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "journeyPlan");
                crit.add(cb.equal(info.get("journeyPlan"), param));
            }
            if (filters.get("db3partySaleId.pdv") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "pdv");
                crit.add(cb.like(info.<String>get("db3partySaleId").<String>get("pdv"), param));
            }
            if (filters.get("db3PartyIdOld.db3partyRegionalId.nameRegional") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameRegional");
                crit.add(cb.like(info.<String>get("db3PartyIdOld").<String>get("db3partyRegionalId").<String>get("nameRegional"), param));
            }
            if (filters.get("db3PartyIdOld.name3party") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "name3partyOld");
                crit.add(cb.like(info.<String>get("db3PartyIdOld").<String>get("name3party"), param));
            }
            if (filters.get("subSegmentId.segmentId.subChannelId.nameSubChannel") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameSubChannel");
                crit.add(cb.like(info.<String>get("subSegmentId").<String>get("segmentId").<String>get("subChannelId").<String>get("nameSubChannel"), param));
            }
            if (filters.get("db3partySaleId.nameSales") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameSales");
                crit.add(cb.like(info.<String>get("db3partySaleId").<String>get("nameSales"), param));
            }
            if (filters.get("address") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "address");
                crit.add(cb.equal(info.get("address"), param));
            }
            if (filters.get("townId.departamentId.nameDepartament") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameDepartament");
                crit.add(cb.like(info.<String>get("townId").<String>get("departamentId").<String>get("nameDepartament"), param));
            }
            if (filters.get("outletId") != null) {
                try {
                    ParameterExpression<Integer> param = cb.parameter(Integer.class, "outletId");
                    crit.add(cb.equal(info.get("outletId"), param));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(OutletBeanLocal.class.getName()).log(Level.SEVERE,
                            "Par {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("subSegmentId.segmentId.nameSegment") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "nameSegment");
                crit.add(cb.like(info.<String>get("subSegmentId").<String>get("segmentId").<String>get("nameSegment"), param));
            }
            if (filters.get("potentialId.namePotential") != null) {
                ParameterExpression<String> param = cb.parameter(String.class, "namePotential");
                crit.add(cb.like(info.<String>get("potentialId").<String>get("namePotential"), param));
            }
            if (crit.size() == 1) {
                query.where(crit.get(0));
            } else if (crit.size() > 1) {
                query.where(cb.and(crit.toArray(new Predicate[0])));
            }
            TypedQuery<Long> q = getEntityManager().createQuery(query);
            if (filters.get("nit") != null) {
                q.setParameter("nit", "%" + filters.get("nit") + "%");
            }
            if (filters.get("businessName") != null) {
                q.setParameter("businessName", "%" + filters.get("businessName") + "%");
            }
            if (filters.get("outletName") != null) {
                q.setParameter("outletName", "%" + filters.get("outletName") + "%");
            }
            if (filters.get("numberPdv") != null) {
                q.setParameter("numberPdv", filters.get("numberPdv"));
            }
            if (filters.get("kiernanId") != null) {
                q.setParameter("kiernanId", "%" + filters.get("kiernanId") + "%");
            }
            if (filters.get("subSegmentId.nameSubsegment") != null) {
                q.setParameter("nameSubsegment", "%" + filters.get("subSegmentId.nameSubsegment") + "%");
            }
            if (filters.get("statusOutlet") != null) {
                q.setParameter("statusOutlet", filters.get("statusOutlet"));
            }
            if (filters.get("statusMDM") != null) {
                q.setParameter("statusMDM", filters.get("statusMDM"));
            }
            if (filters.get("townId.nameTown") != null) {
                q.setParameter("nameTown", "%" + filters.get("townId.nameTown") + "%");
            }
            if (filters.get("distributorSubSegmentId.nameSubsegment") != null) {
                q.setParameter("nameSubsegment", "%" + filters.get("distributorSubSegmentId.nameSubsegment") + "%");
            }
            if (filters.get("outletIdFather.outletId") != null) {
                try {
                    q.setParameter("outletIdFather", filters.get("outletIdFather.outletId"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(OutletBeanLocal.class.getName()).log(Level.SEVERE,
                            "Val {0},{1} no corresponde a un numero", new String[]{"outletIdFather", filters.get("outletIdFather.outletId") + ""});
                }
            }
            if (filters.get("subSegmentId.segmentId.subChannelId.channelId.nameChannel") != null) {
                q.setParameter("nameChannel", "%" + filters.get("subSegmentId.segmentId.subChannelId.channelId.nameChannel") + "%");
            }
            if (filters.get("journeyPlan") != null) {
                q.setParameter("journeyPlan", filters.get("journeyPlan"));
            }
            if (filters.get("db3partySaleId.pdv") != null) {
                q.setParameter("pdv", "%" + filters.get("db3partySaleId.pdv") + "%");
            }
            if (filters.get("db3PartyIdOld.db3partyRegionalId.nameRegional") != null) {
                q.setParameter("nameRegional", "%" + filters.get("db3PartyIdOld.db3partyRegionalId.nameRegional") + "%");
            }
            if (filters.get("db3PartyIdOld.name3party") != null) {
                q.setParameter("name3partyOld", "%" + filters.get("db3PartyIdOld.name3party") + "%");
            }
            if (filters.get("subSegmentId.segmentId.subChannelId.nameSubChannel") != null) {
                q.setParameter("nameSubChannel", "%" + filters.get("subSegmentId.segmentId.subChannelId.nameSubChannel") + "%");
            }
            if (filters.get("db3partySaleId.nameSales") != null) {
                q.setParameter("nameSales", "%" + filters.get("db3partySaleId.nameSales") + "%");
            }
            if (filters.get("address") != null) {
                q.setParameter("address", filters.get("address"));
            }
            if (filters.get("townId.departamentId.nameDepartament") != null) {
                q.setParameter("nameDepartament", "%" + filters.get("townId.departamentId.nameDepartament") + "%");
            }
            if (filters.get("outletId") != null) {
                try {
                    q.setParameter("outletId", filters.get("outletId"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(OutletBeanLocal.class.getName()).log(Level.SEVERE,
                            "Val {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("subSegmentId.segmentId.nameSegment") != null) {
                q.setParameter("nameSegment", "%" + filters.get("subSegmentId.segmentId.nameSegment") + "%");
            }
            if (filters.get("potentialId.namePotential") != null) {
                q.setParameter("namePotential", "%" + filters.get("potentialId.namePotential") + "%");
            }
            if(!isCommercialManager) {
                q.setParameter("userId", id);
            }
            return q.getSingleResult();
        } else {
            TypedQuery<Long> q = getEntityManager().createQuery(query);
            if (!isCommercialManager) {
                q.setParameter("userId", id);
            }
            return q.getSingleResult();
        }
    }

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
        if (filters.get("townId.nameTown") != null) {
            sql.setParameter("nameTown", "%" + filters.get("townId.nameTown").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameTown", "%%");
        }
        if (filters.get("distributorSubSegmentId.nameSubsegment") != null) {
            sql.setParameter("nameSubSegment", "%" + filters.get("distributorSubSegmentId.nameSubsegment").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSubSegment", "%%");
        }
//        if (filters.get("outletIdFather.outletId") != null) {
//            sql.setParameter("outletIdFather", "%" + filters.get("outletIdFather.outletId").toString().toUpperCase() + "%");
//        } else {
//            sql.setParameter("outletIdFather", "%%");
//        }
        if (filters.get("subSegmentId.segmentId.subChannelId.channelId.nameChannel") != null) {
            sql.setParameter("nameChannel", "%" + filters.get("subSegmentId.segmentId.subChannelId.channelId.nameChannel").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameChannel", "%%");
        }
        if (filters.get("journeyPlan") != null) {
            sql.setParameter("journeyPlan", "%" + filters.get("journeyPlan").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("journeyPlan", "%%");
        }
        if (filters.get("db3partySaleId.pdv") != null) {
            sql.setParameter("pdv", "%" + filters.get("db3partySaleId.pdv").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("pdv", "%%");
        }
        if (filters.get("db3PartyIdOld.db3partyRegionalId.nameRegional") != null) {
            sql.setParameter("nameRegional", "%" + filters.get("db3PartyIdOld.db3partyRegionalId.nameRegional").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameRegional", "%%");
        }
        if (filters.get("db3PartyIdOld.name3party") != null) {
            sql.setParameter("name3partyOld", "%" + filters.get("db3PartyIdOld.name3party").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("name3partyOld", "%%");
        }
        if (filters.get("subSegmentId.segmentId.subChannelId.nameSubChannel") != null) {
            sql.setParameter("nameSubChannel", "%" + filters.get("subSegmentId.segmentId.subChannelId.nameSubChannel").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSubChannel", "%%");
        }
        if (filters.get("db3partySaleId.nameSales") != null) {
            sql.setParameter("nameSales", "%" + filters.get("db3partySaleId.nameSales").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSales", "%%");
        }
        if (filters.get("address") != null) {
            sql.setParameter("address", "%" + filters.get("address").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("address", "%%");
        }
        if (filters.get("townId.departamentId.nameDepartament") != null) {
            sql.setParameter("nameDepartament", "%" + filters.get("townId.departamentId.nameDepartament").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameDepartament", "%%");
        }
        if (filters.get("outletId") != null) {
            sql.setParameter("outletId", "%" + filters.get("outletId").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("outletId", "%%");
        }
        if (filters.get("subSegmentId.segmentId.nameSegment") != null) {
            sql.setParameter("nameSegment", "%" + filters.get("subSegmentId.segmentId.nameSegment").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSegment", "%%");
        }
        if (filters.get("potentialId.namePotential") != null) {
            sql.setParameter("namePotential", "%" + filters.get("potentialId.namePotential").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("namePotential", "%%");
        }
        sql.setFirstResult(initial);
        sql.setMaxResults(page);
        List<DbOutlets> list = sql.getResultList();
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

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
        if (filters.get("townId.nameTown") != null) {
            sql.setParameter("nameTown", "%" + filters.get("townId.nameTown").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameTown", "%%");
        }
        if (filters.get("distributorSubSegmentId.nameSubsegment") != null) {
            sql.setParameter("nameSubSegment", "%" + filters.get("distributorSubSegmentId.nameSubsegment").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSubSegment", "%%");
        }
//        if (filters.get("outletIdFather.outletId") != null) {
//            sql.setParameter("outletIdFather", "%" + filters.get("outletIdFather.outletId").toString().toUpperCase() + "%");
//        } else {
//            sql.setParameter("outletIdFather", "%%");
//        }
        if (filters.get("subSegmentId.segmentId.subChannelId.channelId.nameChannel") != null) {
            sql.setParameter("nameChannel", "%" + filters.get("subSegmentId.segmentId.subChannelId.channelId.nameChannel").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameChannel", "%%");
        }
        if (filters.get("journeyPlan") != null) {
            sql.setParameter("journeyPlan", "%" + filters.get("journeyPlan").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("journeyPlan", "%%");
        }
        if (filters.get("db3partySaleId.pdv") != null) {
            sql.setParameter("pdv", "%" + filters.get("db3partySaleId.pdv").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("pdv", "%%");
        }
        if (filters.get("db3PartyIdOld.db3partyRegionalId.nameRegional") != null) {
            sql.setParameter("nameRegional", "%" + filters.get("db3PartyIdOld.db3partyRegionalId.nameRegional").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameRegional", "%%");
        }
        if (filters.get("db3PartyIdOld.name3party") != null) {
            sql.setParameter("name3partyOld", "%" + filters.get("db3PartyIdOld.name3party").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("name3partyOld", "%%");
        }
        if (filters.get("subSegmentId.segmentId.subChannelId.nameSubChannel") != null) {
            sql.setParameter("nameSubChannel", "%" + filters.get("subSegmentId.segmentId.subChannelId.nameSubChannel").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSubChannel", "%%");
        }
        if (filters.get("db3partySaleId.nameSales") != null) {
            sql.setParameter("nameSales", "%" + filters.get("db3partySaleId.nameSales").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSales", "%%");
        }
        if (filters.get("address") != null) {
            sql.setParameter("address", "%" + filters.get("address").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("address", "%%");
        }
        if (filters.get("townId.departamentId.nameDepartament") != null) {
            sql.setParameter("nameDepartament", "%" + filters.get("townId.departamentId.nameDepartament").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameDepartament", "%%");
        }
        if (filters.get("outletId") != null) {
            sql.setParameter("outletId", "%" + filters.get("outletId").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("outletId", "%%");
        }
        if (filters.get("subSegmentId.segmentId.nameSegment") != null) {
            sql.setParameter("nameSegment", "%" + filters.get("subSegmentId.segmentId.nameSegment").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSegment", "%%");
        }
        if (filters.get("potentialId.namePotential") != null) {
            sql.setParameter("namePotential", "%" + filters.get("potentialId.namePotential").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("namePotential", "%%");
        }
        long size = (long) sql.getSingleResult();
        return size;
    }

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
        if (filters.get("townId.nameTown") != null) {
            sql.setParameter("nameTown", "%" + filters.get("townId.nameTown").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameTown", "%%");
        }
        if (filters.get("distributorSubSegmentId.nameSubsegment") != null) {
            sql.setParameter("nameSubSegment", "%" + filters.get("distributorSubSegmentId.nameSubsegment").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSubSegment", "%%");
        }
//        if (filters.get("outletIdFather.outletId") != null) {
//            sql.setParameter("outletIdFather", "%" + filters.get("outletIdFather.outletId").toString().toUpperCase() + "%");
//        } else {
//            sql.setParameter("outletIdFather", "%%");
//        }
        if (filters.get("subSegmentId.segmentId.subChannelId.channelId.nameChannel") != null) {
            sql.setParameter("nameChannel", "%" + filters.get("subSegmentId.segmentId.subChannelId.channelId.nameChannel").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameChannel", "%%");
        }
        if (filters.get("journeyPlan") != null) {
            sql.setParameter("journeyPlan", "%" + filters.get("journeyPlan").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("journeyPlan", "%%");
        }
        if (filters.get("db3partySaleId.pdv") != null) {
            sql.setParameter("pdv", "%" + filters.get("db3partySaleId.pdv").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("pdv", "%%");
        }
        if (filters.get("db3PartyIdOld.db3partyRegionalId.nameRegional") != null) {
            sql.setParameter("nameRegional", "%" + filters.get("db3PartyIdOld.db3partyRegionalId.nameRegional").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameRegional", "%%");
        }
        if (filters.get("db3PartyIdOld.name3party") != null) {
            sql.setParameter("name3partyOld", "%" + filters.get("db3PartyIdOld.name3party").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("name3partyOld", "%%");
        }
        if (filters.get("subSegmentId.segmentId.subChannelId.nameSubChannel") != null) {
            sql.setParameter("nameSubChannel", "%" + filters.get("subSegmentId.segmentId.subChannelId.nameSubChannel").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSubChannel", "%%");
        }
        if (filters.get("db3partySaleId.nameSales") != null) {
            sql.setParameter("nameSales", "%" + filters.get("db3partySaleId.nameSales").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSales", "%%");
        }
        if (filters.get("address") != null) {
            sql.setParameter("address", "%" + filters.get("address").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("address", "%%");
        }
        if (filters.get("townId.departamentId.nameDepartament") != null) {
            sql.setParameter("nameDepartament", "%" + filters.get("townId.departamentId.nameDepartament").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameDepartament", "%%");
        }
        if (filters.get("outletId") != null) {
            sql.setParameter("outletId", "%" + filters.get("outletId").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("outletId", "%%");
        }
        if (filters.get("subSegmentId.segmentId.nameSegment") != null) {
            sql.setParameter("nameSegment", "%" + filters.get("subSegmentId.segmentId.nameSegment").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSegment", "%%");
        }
        if (filters.get("potentialId.namePotential") != null) {
            sql.setParameter("namePotential", "%" + filters.get("potentialId.namePotential").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("namePotential", "%%");
        }
        long size = (long) sql.getSingleResult();
        return size;
    }

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
        if (filters.get("townId.nameTown") != null) {
            sql.setParameter("nameTown", "%" + filters.get("townId.nameTown").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameTown", "%%");
        }
        if (filters.get("distributorSubSegmentId.nameSubsegment") != null) {
            sql.setParameter("nameSubSegment", "%" + filters.get("distributorSubSegmentId.nameSubsegment").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSubSegment", "%%");
        }
//        if (filters.get("outletIdFather.outletId") != null) {
//            sql.setParameter("outletIdFather", "%" + filters.get("outletIdFather.outletId").toString().toUpperCase() + "%");
//        } else {
//            sql.setParameter("outletIdFather", "%%");
//        }
        if (filters.get("subSegmentId.segmentId.subChannelId.channelId.nameChannel") != null) {
            sql.setParameter("nameChannel", "%" + filters.get("subSegmentId.segmentId.subChannelId.channelId.nameChannel").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameChannel", "%%");
        }
        if (filters.get("journeyPlan") != null) {
            sql.setParameter("journeyPlan", "%" + filters.get("journeyPlan").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("journeyPlan", "%%");
        }
        if (filters.get("db3partySaleId.pdv") != null) {
            sql.setParameter("pdv", "%" + filters.get("db3partySaleId.pdv").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("pdv", "%%");
        }
        if (filters.get("db3PartyIdOld.db3partyRegionalId.nameRegional") != null) {
            sql.setParameter("nameRegional", "%" + filters.get("db3PartyIdOld.db3partyRegionalId.nameRegional").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameRegional", "%%");
        }
        if (filters.get("db3PartyIdOld.name3party") != null) {
            sql.setParameter("name3partyOld", "%" + filters.get("db3PartyIdOld.name3party").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("name3partyOld", "%%");
        }
        if (filters.get("subSegmentId.segmentId.subChannelId.nameSubChannel") != null) {
            sql.setParameter("nameSubChannel", "%" + filters.get("subSegmentId.segmentId.subChannelId.nameSubChannel").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSubChannel", "%%");
        }
        if (filters.get("db3partySaleId.nameSales") != null) {
            sql.setParameter("nameSales", "%" + filters.get("db3partySaleId.nameSales").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSales", "%%");
        }
        if (filters.get("address") != null) {
            sql.setParameter("address", "%" + filters.get("address").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("address", "%%");
        }
        if (filters.get("townId.departamentId.nameDepartament") != null) {
            sql.setParameter("nameDepartament", "%" + filters.get("townId.departamentId.nameDepartament").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameDepartament", "%%");
        }
        if (filters.get("outletId") != null) {
            sql.setParameter("outletId", "%" + filters.get("outletId").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("outletId", "%%");
        }
        if (filters.get("subSegmentId.segmentId.nameSegment") != null) {
            sql.setParameter("nameSegment", "%" + filters.get("subSegmentId.segmentId.nameSegment").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("nameSegment", "%%");
        }
        if (filters.get("potentialId.namePotential") != null) {
            sql.setParameter("namePotential", "%" + filters.get("potentialId.namePotential").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("namePotential", "%%");
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
     * @param stateToFilter
     */
    public void updateOutlet(Integer id, String state, String stateToFilter, String email) {
        String sqlStr = "UPDATE  O "
                + "SET O.STATUS_MDM='" + state + "', MODIFICATION_DATE=GETDATE()"
                + "FROM  DIAGEO_BUSINESS.DBO.DB_OUTLETS O "
                + "INNER JOIN DIAGEO_BUSINESS.DBO.DB_OUTLETS_USERS OU ON OU.OUTLET_ID = O.OUTLET_ID "
                + "INNER JOIN DIAGEO_BUSINESS.DBO.DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID=O.SUB_SEGMENT_ID "
                + "WHERE O.STATUS_MDM='" + stateToFilter + "' AND OU.USER_ID=" + id + " ";
        Query sql = getEntityManager().createNativeQuery(sqlStr);
        int quantity = sql.executeUpdate();
    }

    public void updateOutletCommercialManager(List<Integer> id, String state, String stateToFilter, String email) {
        String sqlStr = "UPDATE  O "
                + "SET O.STATUS_MDM='" + state + "', MODIFICATION_DATE=GETDATE(),MODIFICATION_USER='" + email + "'"
                + "FROM DIAGEO_WEB.DBO.DW_RELATION_USERS RU "
                + "INNER JOIN DIAGEO_BUSINESS.DBO.DB_OUTLETS_USERS OU ON OU.USER_ID=RU.USER_ID "
                + "INNER JOIN DIAGEO_BUSINESS.DBO.DB_OUTLETS O ON O.OUTLET_ID=OU.OUTLET_ID "
                + "INNER JOIN DIAGEO_BUSINESS.DBO.DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID=O.SUB_SEGMENT_ID "
                + "WHERE RU.USER_ID IN(" + buildIdUserParam(id) + ") "
                + "AND RU.STATE_APPROVED=1 "
                + "AND O.STATUS_MDM='PENDING_APPROVAL'";
        Query sql = getEntityManager().createNativeQuery(sqlStr);
        int quantity = sql.executeUpdate();
    }

    public long notificationPendingOutlet(Integer userId, String status) {
        Query sql = getEntityManager().createNamedQuery(DbOutletsUsers.COUNT_PENDING_OUTLETS);
        sql.setParameter(1, userId);
        sql.setParameter(2, status);
        Object obj = sql.getSingleResult();
        if (obj == null) {
            return 0;
        }
        long size = (long) obj;
        return size;
    }

    public long notificationPendingOutlet(List<String> status) {
        Query sql = getEntityManager().createNamedQuery(DbOutletsUsers.COUNT_PENDING_OUTLETS_IN);
        sql.setParameter(1, status);
        Object obj = sql.getSingleResult();
        if (obj == null) {
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
