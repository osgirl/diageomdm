/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans.reports;

import com.diageo.diageonegocio.beans.BusinessTransaction;
import com.diageo.diageonegocio.entidades.view.InformeVendedores;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author EDUARDO
 */
@Stateless
@LocalBean
public class InformeVendedoreBean extends BusinessTransaction<InformeVendedores> {

    public long findSellerCount(Map<String, Object> filters) {
        CriteriaBuilder criteria = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = criteria.createQuery(Long.class);
        Root<InformeVendedores> info = query.from(InformeVendedores.class);
        query.select(criteria.count(info));
        List<Predicate> crit = new ArrayList<>();
         if (filters != null && !filters.isEmpty()) {
            if (filters.get("outletId") != null) {
                try {
                    ParameterExpression<Integer> param = criteria.parameter(Integer.class, "outletId");
                    crit.add(criteria.equal(info.get("outletId"), param));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformeVendedoreBean.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("outletIdFather") != null) {
                try {
                    ParameterExpression<Integer> param = criteria.parameter(Integer.class, "outletIdFather");
                    crit.add(criteria.equal(info.get("outletIdFather"), param));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformeVendedoreBean.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletIdFather", filters.get("outletIdFather") + ""});
                }
            }
            if (filters.get("territorio") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "territorio");
                crit.add(criteria.like(info.<String>get("territorio"), param));
            }
            if (filters.get("sucursalDistribuidor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "sucursalDistribuidor");
                crit.add(criteria.like(info.<String>get("sucursalDistribuidor"), param));
            }
            if (filters.get("codigoVendedor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "codigoVendedor");
                crit.add(criteria.like(info.<String>get("codigoVendedor"), param));
            }
            if (filters.get("kiernanId") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "kiernanId");
                crit.add(criteria.like(info.<String>get("kiernanId"), param));
            }
            if (filters.get("nombreComercial") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreComercial");
                crit.add(criteria.like(info.<String>get("nombreComercial"), param));
            }
            if (filters.get("modificationUser") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "modificationUser");
                crit.add(criteria.like(info.<String>get("modificationUser"), param));
            }
            if (filters.get("statusOutlet") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "statusOutlet");
                crit.add(criteria.equal(info.get("statusOutlet"), param));
            }
            if (filters.get("journeyPlan") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "journeyPlan");
                crit.add(criteria.equal(info.get("journeyPlan"), param));
            }
            if (filters.get("modificationDate") != null) {
                ParameterExpression<Date> param = criteria.parameter(Date.class, "modificationDate");
                crit.add(criteria.equal(info.get("modificationDate"), param));
            }
            if (crit.size() == 1) {
                query.where(crit.get(0));
            } else if (crit.size() > 1) {
                query.where(criteria.and(crit.toArray(new Predicate[0])));
            }
            TypedQuery<Long> q = getEntityManager().createQuery(query);
             if (filters.get("outletId") != null) {
                try {
                    q.setParameter("outletId", filters.get("outletId"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformeVendedoreBean.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("outletIdFather") != null) {
                try {
                    q.setParameter("outletIdFather", filters.get("outletIdFather"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformeVendedoreBean.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletIdFather", filters.get("outletIdFather") + ""});
                }
            }
            if (filters.get("territorio") != null) {
                q.setParameter("territorio", "%" + filters.get("territorio") + "%");
            }
            if (filters.get("sucursalDistribuidor") != null) {
                q.setParameter("sucursalDistribuidor", "%" + filters.get("sucursalDistribuidor") + "%");
            }
            if (filters.get("codigoVendedor") != null) {
                q.setParameter("codigoVendedor", "%" + filters.get("codigoVendedor") + "%");
            }
            if (filters.get("kiernanId") != null) {
                q.setParameter("kiernanId", "%" + filters.get("kiernanId") + "%");
            }
            if (filters.get("nombreComercial") != null) {
                q.setParameter("nombreComercial", "%" + filters.get("nombreComercial") + "%");
            }
            if (filters.get("modificationUser") != null) {
                q.setParameter("modificationUser", "%" + filters.get("modificationUser") + "%");
            }
            if (filters.get("statusOutlet") != null) {
                q.setParameter("statusOutlet", filters.get("statusOutlet"));
            }
            if (filters.get("journeyPlan") != null) {
                q.setParameter("journeyPlan", filters.get("journeyPlan"));
            }
            if (filters.get("modificationDate") != null) {
                q.setParameter("modificationDate", filters.get("modificationDate"));
            }
            return q.getSingleResult();
        } else {
            TypedQuery<Long> q = getEntityManager().createQuery(query);
            return q.getSingleResult();
        }
        
    }
    public List<InformeVendedores> findSeller(int initial, int page, Map<String, Object> filters) {
        CriteriaBuilder criteria = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<InformeVendedores> query = criteria.createQuery(InformeVendedores.class);
        Root<InformeVendedores> info = query.from(InformeVendedores.class);
        query.select(info);
        List<Predicate> crit = new ArrayList<>();
        if (filters != null && !filters.isEmpty()) {
            if (filters.get("outletId") != null) {
                try {
                    ParameterExpression<Integer> param = criteria.parameter(Integer.class, "outletId");
                    crit.add(criteria.equal(info.get("outletId"), param));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformeVendedoreBean.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("outletIdFather") != null) {
                try {
                    ParameterExpression<Integer> param = criteria.parameter(Integer.class, "outletIdFather");
                    crit.add(criteria.equal(info.get("outletIdFather"), param));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformeVendedoreBean.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletIdFather", filters.get("outletIdFather") + ""});
                }
            }
            if (filters.get("territorio") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "territorio");
                crit.add(criteria.like(info.<String>get("territorio"), param));
            }
            if (filters.get("sucursalDistribuidor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "sucursalDistribuidor");
                crit.add(criteria.like(info.<String>get("sucursalDistribuidor"), param));
            }
            if (filters.get("codigoVendedor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "codigoVendedor");
                crit.add(criteria.like(info.<String>get("codigoVendedor"), param));
            }
            if (filters.get("kiernanId") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "kiernanId");
                crit.add(criteria.like(info.<String>get("kiernanId"), param));
            }
            if (filters.get("nombreComercial") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreComercial");
                crit.add(criteria.like(info.<String>get("nombreComercial"), param));
            }
            if (filters.get("modificationUser") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "modificationUser");
                crit.add(criteria.like(info.<String>get("modificationUser"), param));
            }
            if (filters.get("statusOutlet") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "statusOutlet");
                crit.add(criteria.equal(info.get("statusOutlet"), param));
            }
            if (filters.get("journeyPlan") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "journeyPlan");
                crit.add(criteria.equal(info.get("journeyPlan"), param));
            }
            if (filters.get("modificationDate") != null) {
                ParameterExpression<Date> param = criteria.parameter(Date.class, "modificationDate");
                crit.add(criteria.equal(info.get("modificationDate"), param));
            }
            if (crit.size() == 1) {
                query.where(crit.get(0));
            } else if (crit.size() > 1) {
                query.where(criteria.and(crit.toArray(new Predicate[0])));
            }
            TypedQuery<InformeVendedores> q = getEntityManager().createQuery(query);
             if (filters.get("outletId") != null) {
                try {
                    q.setParameter("outletId", filters.get("outletId"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformeVendedoreBean.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("outletIdFather") != null) {
                try {
                    q.setParameter("outletIdFather", filters.get("outletIdFather"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformeVendedoreBean.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletIdFather", filters.get("outletIdFather") + ""});
                }
            }
            if (filters.get("territorio") != null) {
                q.setParameter("territorio", "%" + filters.get("territorio") + "%");
            }
            if (filters.get("sucursalDistribuidor") != null) {
                q.setParameter("sucursalDistribuidor", "%" + filters.get("sucursalDistribuidor") + "%");
            }
            if (filters.get("codigoVendedor") != null) {
                q.setParameter("codigoVendedor", "%" + filters.get("codigoVendedor") + "%");
            }
            if (filters.get("kiernanId") != null) {
                q.setParameter("kiernanId", "%" + filters.get("kiernanId") + "%");
            }
            if (filters.get("nombreComercial") != null) {
                q.setParameter("nombreComercial", "%" + filters.get("nombreComercial") + "%");
            }
            if (filters.get("modificationUser") != null) {
                q.setParameter("modificationUser", "%" + filters.get("modificationUser") + "%");
            }
            if (filters.get("statusOutlet") != null) {
                q.setParameter("statusOutlet", filters.get("statusOutlet"));
            }
            if (filters.get("journeyPlan") != null) {
                q.setParameter("journeyPlan", filters.get("journeyPlan"));
            }
            if (filters.get("modificationDate") != null) {
                q.setParameter("modificationDate", filters.get("modificationDate"));
            }
            q.setFirstResult(initial);
            q.setMaxResults(page);
            return q.getResultList();
        } else {
            TypedQuery<InformeVendedores> q = getEntityManager().createQuery(query);
            q.setFirstResult(initial);
            q.setMaxResults(page);
            return q.getResultList();
        }
    }
}
