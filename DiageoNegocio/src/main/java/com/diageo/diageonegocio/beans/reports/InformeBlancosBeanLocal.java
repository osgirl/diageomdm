/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans.reports;

import com.diageo.diageonegocio.beans.BusinessTransaction;
import com.diageo.diageonegocio.entidades.view.InformeBlancos;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Reporte Informacion incompleta
 *
 * @author EDUARDO
 */
@Stateless
public class InformeBlancosBeanLocal extends BusinessTransaction<InformeBlancos> {

    public long findAllCount(int initial, int page, Map<String, Object> filters) {
        CriteriaBuilder criteria = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = criteria.createQuery(Long.class);
        Root<InformeBlancos> info = query.from(InformeBlancos.class);
        query.select(criteria.count(info));
        List<Predicate> crit = new ArrayList<>();
        if (filters != null && !filters.isEmpty()) {
            if (filters.get("outletId") != null) {
                try {
                    if (filters.get("outletId") instanceof Integer) {
                        ParameterExpression<Integer> param = criteria.parameter(Integer.class, "outletId");
                        crit.add(criteria.equal(info.get("outletId"), param));
                    }
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("outletIdFather") != null) {
                try {
                    if (filters.get("outletIdFather") instanceof Integer) {
                        ParameterExpression<Integer> param = criteria.parameter(Integer.class, "outletIdFather");
                        crit.add(criteria.equal(info.get("outletIdFather"), param));
                    }
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletIdFather", filters.get("outletIdFather") + ""});
                }
            }
            if (filters.get("origen") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "origen");
                crit.add(criteria.like(info.<String>get("origen"), param));
            }
            if (filters.get("kiernanId") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "kiernanId");
                crit.add(criteria.like(info.<String>get("kiernanId"), param));
            }
            if (filters.get("gerencia") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "gerencia");
                crit.add(criteria.like(info.<String>get("gerencia"), param));
            }
            if (filters.get("codigoDistribuidor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "codigoDistribuidor");
                crit.add(criteria.like(info.<String>get("codigoDistribuidor"), param));
            }
            if (filters.get("nombreDistribuidor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreDistribuidor");
                crit.add(criteria.like(info.<String>get("nombreDistribuidor"), param));
            }
            if (filters.get("numberPDV") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "numberPDV");
                crit.add(criteria.like(info.<String>get("numberPDV"), param));
            }
            if (filters.get("outletName") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "outletName");
                crit.add(criteria.like(info.<String>get("outletName"), param));
            }
            if (filters.get("businessName") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "businessName");
                crit.add(criteria.like(info.<String>get("businessName"), param));
            }
            if (filters.get("nitEan") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nitEan");
                crit.add(criteria.like(info.<String>get("nitEan"), param));
            }
            if (filters.get("direccion") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "direccion");
                crit.add(criteria.like(info.<String>get("direccion"), param));
            }
            if (filters.get("nombreCiudad") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreCiudad");
                crit.add(criteria.like(info.<String>get("nombreCiudad"), param));
            }
            if (filters.get("nombreDepartamento") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreDepartamento");
                crit.add(criteria.like(info.<String>get("nombreDepartamento"), param));
            }
            if (filters.get("codigoVendedor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "codigoVendedor");
                crit.add(criteria.like(info.<String>get("codigoVendedor"), param));
            }
            if (filters.get("nombreVendedor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreVendedor");
                crit.add(criteria.like(info.<String>get("nombreVendedor"), param));
            }
            if (filters.get("tmcKam") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "tmcKam");
                crit.add(criteria.like(info.<String>get("tmcKam"), param));
            }
            if (filters.get("descripcion") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "descripcion");
                crit.add(criteria.like(info.<String>get("descripcion"), param));
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
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Val {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("outletIdFather") != null) {
                try {
                    q.setParameter("outletIdFather", filters.get("outletIdFather"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Val {0},{1} no corresponde a un numero", new String[]{"outletIdFather", filters.get("outletIdFather") + ""});
                }
            }
            if (filters.get("origen") != null) {
                q.setParameter("origen", "%" + filters.get("origen") + "%");
            }
            if (filters.get("kiernanId") != null) {
                q.setParameter("kiernanId", "%" + filters.get("kiernanId") + "%");
            }
            if (filters.get("gerencia") != null) {
                q.setParameter("gerencia", "%" + filters.get("gerencia") + "%");
            }
            if (filters.get("codigoDistribuidor") != null) {
                q.setParameter("codigoDistribuidor", "%" + filters.get("codigoDistribuidor") + "%");
            }
            if (filters.get("nombreDistribuidor") != null) {
                q.setParameter("nombreDistribuidor", "%" + filters.get("nombreDistribuidor") + "%");
            }
            if (filters.get("numberPDV") != null) {
                q.setParameter("numberPDV", "%" + filters.get("numberPDV") + "%");
            }
            if (filters.get("outletName") != null) {
                q.setParameter("outletName", "%" + filters.get("outletName") + "%");
            }
            if (filters.get("businessName") != null) {
                q.setParameter("businessName", "%" + filters.get("businessName") + "%");
            }
            if (filters.get("nitEan") != null) {
                q.setParameter("nitEan", "%" + filters.get("nitEan") + "%");
            }
            if (filters.get("direccion") != null) {
                q.setParameter("direccion", "%" + filters.get("direccion") + "%");
            }
            if (filters.get("nombreCiudad") != null) {
                q.setParameter("nombreCiudad", "%" + filters.get("nombreCiudad") + "%");
            }
            if (filters.get("nombreDepartamento") != null) {
                q.setParameter("nombreDepartamento", "%" + filters.get("nombreDepartamento") + "%");
            }
            if (filters.get("codigoVendedor") != null) {
                q.setParameter("codigoVendedor", "%" + filters.get("codigoVendedor") + "%");
            }
            if (filters.get("nombreVendedor") != null) {
                q.setParameter("nombreVendedor", "%" + filters.get("nombreVendedor") + "%");
            }
            if (filters.get("tmcKam") != null) {
                q.setParameter("tmcKam", "%" + filters.get("tmcKam") + "%");
            }
            if (filters.get("descripcion") != null) {
                q.setParameter("descripcion", "%" + filters.get("descripcion") + "%");
            }
            return q.getSingleResult();
        } else {
            TypedQuery<Long> q = getEntityManager().createQuery(query);
            return q.getSingleResult();
        }
    }

    public List<InformeBlancos> findAll(int initial, int page, Map<String, Object> filters) {
        CriteriaBuilder criteria = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<InformeBlancos> query = criteria.createQuery(InformeBlancos.class);
        Root<InformeBlancos> info = query.from(InformeBlancos.class);
        query.select(info);
        List<Predicate> crit = new ArrayList<>();
        if (filters != null && !filters.isEmpty()) {
            if (filters.get("outletId") != null) {
                try {
                    if (filters.get("outletId") instanceof Integer) {
                        ParameterExpression<Integer> param = criteria.parameter(Integer.class, "outletId");
                        crit.add(criteria.equal(info.get("outletId"), param));
                    }
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("outletIdFather") != null) {
                try {
                    if (filters.get("outletIdFather") instanceof Integer) {
                        ParameterExpression<Integer> param = criteria.parameter(Integer.class, "outletIdFather");
                        crit.add(criteria.equal(info.get("outletIdFather"), param));
                    }
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletIdFather", filters.get("outletIdFather") + ""});
                }
            }
            if (filters.get("origen") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "origen");
                crit.add(criteria.like(info.<String>get("origen"), param));
            }
            if (filters.get("kiernanId") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "kiernanId");
                crit.add(criteria.like(info.<String>get("kiernanId"), param));
            }
            if (filters.get("gerencia") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "gerencia");
                crit.add(criteria.like(info.<String>get("gerencia"), param));
            }
            if (filters.get("codigoDistribuidor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "codigoDistribuidor");
                crit.add(criteria.like(info.<String>get("codigoDistribuidor"), param));
            }
            if (filters.get("nombreDistribuidor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreDistribuidor");
                crit.add(criteria.like(info.<String>get("nombreDistribuidor"), param));
            }
            if (filters.get("numberPDV") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "numberPDV");
                crit.add(criteria.like(info.<String>get("numberPDV"), param));
            }
            if (filters.get("outletName") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "outletName");
                crit.add(criteria.like(info.<String>get("outletName"), param));
            }
            if (filters.get("businessName") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "businessName");
                crit.add(criteria.like(info.<String>get("businessName"), param));
            }
            if (filters.get("nitEan") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nitEan");
                crit.add(criteria.like(info.<String>get("nitEan"), param));
            }
            if (filters.get("direccion") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "direccion");
                crit.add(criteria.like(info.<String>get("direccion"), param));
            }
            if (filters.get("nombreCiudad") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreCiudad");
                crit.add(criteria.like(info.<String>get("nombreCiudad"), param));
            }
            if (filters.get("nombreDepartamento") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreDepartamento");
                crit.add(criteria.like(info.<String>get("nombreDepartamento"), param));
            }
            if (filters.get("codigoVendedor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "codigoVendedor");
                crit.add(criteria.like(info.<String>get("codigoVendedor"), param));
            }
            if (filters.get("nombreVendedor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreVendedor");
                crit.add(criteria.like(info.<String>get("nombreVendedor"), param));
            }
            if (filters.get("tmcKam") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "tmcKam");
                crit.add(criteria.like(info.<String>get("tmcKam"), param));
            }
            if (filters.get("descripcion") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "descripcion");
                crit.add(criteria.like(info.<String>get("descripcion"), param));
            }
            if (crit.size() == 1) {
                query.where(crit.get(0));
            } else if (crit.size() > 1) {
                query.where(criteria.and(crit.toArray(new Predicate[0])));
            }
            TypedQuery<InformeBlancos> q = getEntityManager().createQuery(query);
            if (filters.get("outletId") != null) {
                try {
                    q.setParameter("outletId", filters.get("outletId"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Val {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("outletIdFather") != null) {
                try {
                    q.setParameter("outletIdFather", filters.get("outletIdFather"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Val {0},{1} no corresponde a un numero", new String[]{"outletIdFather", filters.get("outletIdFather") + ""});
                }
            }
            if (filters.get("origen") != null) {
                q.setParameter("origen", "%" + filters.get("origen") + "%");
            }
            if (filters.get("kiernanId") != null) {
                q.setParameter("kiernanId", "%" + filters.get("kiernanId") + "%");
            }
            if (filters.get("gerencia") != null) {
                q.setParameter("gerencia", "%" + filters.get("gerencia") + "%");
            }
            if (filters.get("codigoDistribuidor") != null) {
                q.setParameter("codigoDistribuidor", "%" + filters.get("codigoDistribuidor") + "%");
            }
            if (filters.get("nombreDistribuidor") != null) {
                q.setParameter("nombreDistribuidor", "%" + filters.get("nombreDistribuidor") + "%");
            }
            if (filters.get("numberPDV") != null) {
                q.setParameter("numberPDV", "%" + filters.get("numberPDV") + "%");
            }
            if (filters.get("outletName") != null) {
                q.setParameter("outletName", "%" + filters.get("outletName") + "%");
            }
            if (filters.get("businessName") != null) {
                q.setParameter("businessName", "%" + filters.get("businessName") + "%");
            }
            if (filters.get("nitEan") != null) {
                q.setParameter("nitEan", "%" + filters.get("nitEan") + "%");
            }
            if (filters.get("direccion") != null) {
                q.setParameter("direccion", "%" + filters.get("direccion") + "%");
            }
            if (filters.get("nombreCiudad") != null) {
                q.setParameter("nombreCiudad", "%" + filters.get("nombreCiudad") + "%");
            }
            if (filters.get("nombreDepartamento") != null) {
                q.setParameter("nombreDepartamento", "%" + filters.get("nombreDepartamento") + "%");
            }
            if (filters.get("codigoVendedor") != null) {
                q.setParameter("codigoVendedor", "%" + filters.get("codigoVendedor") + "%");
            }
            if (filters.get("nombreVendedor") != null) {
                q.setParameter("nombreVendedor", "%" + filters.get("nombreVendedor") + "%");
            }
            if (filters.get("tmcKam") != null) {
                q.setParameter("tmcKam", "%" + filters.get("tmcKam") + "%");
            }
            if (filters.get("descripcion") != null) {
                q.setParameter("descripcion", "%" + filters.get("descripcion") + "%");
            }
            q.setFirstResult(initial);
            q.setMaxResults(page);
            return q.getResultList();
        } else {
            TypedQuery<InformeBlancos> q = getEntityManager().createQuery(query);
            q.setFirstResult(initial);
            q.setMaxResults(page);
            return q.getResultList();
        }
    }
}
