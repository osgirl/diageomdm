/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans.reports;

import com.diageo.diageonegocio.beans.BusinessTransaction;
import com.diageo.diageonegocio.entidades.view.InformeRelacionUsuarioOutlet;
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
 *
 * @author EDUARDO
 */
@Stateless
public class InformerRelacionUsuarioOutletBeanLocal extends BusinessTransaction<InformeRelacionUsuarioOutlet> {

    public List<InformeRelacionUsuarioOutlet> findAllDinamic(int initial, int page, Map<String, Object> filters) {
        CriteriaBuilder criteria = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<InformeRelacionUsuarioOutlet> query = criteria.createQuery(InformeRelacionUsuarioOutlet.class);
        Root<InformeRelacionUsuarioOutlet> info = query.from(InformeRelacionUsuarioOutlet.class);
        query.select(info);
        List<Predicate> crit = new ArrayList<>();
        if (filters != null && !filters.isEmpty()) {
            if (filters.get("origen") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "origen");
                crit.add(criteria.equal(info.get("origen"), param));
            }
            if (filters.get("estado") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "estado");
                crit.add(criteria.like(info.<String>get("estado"), param));
            }
            if (filters.get("nit") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nit");
                crit.add(criteria.like(info.<String>get("nit"), param));
            }
            if (filters.get("outletId") != null) {
                try {
                    ParameterExpression<Integer> param = criteria.parameter(Integer.class, "outletId");
                    crit.add(criteria.equal(info.get("outletId"), param));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("journeyPlan") != null) {
                try {
                    ParameterExpression<Integer> param = criteria.parameter(Integer.class, "journeyPlan");
                    crit.add(criteria.equal(info.get("journeyPlan"), param));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"journeyPlan", filters.get("journeyPlan") + ""});
                }
            }
            if (filters.get("outletIdFather") != null) {
                try {
                    ParameterExpression<Integer> param = criteria.parameter(Integer.class, "outletIdFather");
                    crit.add(criteria.equal(info.get("outletIdFather"), param));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletIdFather", filters.get("outletIdFather") + ""});
                }
            }
            if (filters.get("kiernanId") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "kiernanId");
                crit.add(criteria.like(info.<String>get("kiernanId"), param));
            }
            if (filters.get("gerenciaComercial") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "gerenciaComercial");
                crit.add(criteria.like(info.<String>get("gerenciaComercial"), param));
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
            if (filters.get("canalDiageo") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "canalDiageo");
                crit.add(criteria.like(info.<String>get("canalDiageo"), param));
            }
            if (filters.get("subCanalDiageo") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "subCanalDiageo");
                crit.add(criteria.like(info.<String>get("subCanalDiageo"), param));
            }
            if (filters.get("segmentoDiageo") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "segmentoDiageo");
                crit.add(criteria.like(info.<String>get("segmentoDiageo"), param));
            }
            if (filters.get("subSegmentoDiageo") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "subSegmentoDiageo");
                crit.add(criteria.like(info.<String>get("subSegmentoDiageo"), param));
            }
            if (filters.get("namePotential") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "namePotential");
                crit.add(criteria.like(info.<String>get("namePotential"), param));
            }
            if (filters.get("subSegmentoDistribuidor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "subSegmentoDistribuidor");
                crit.add(criteria.like(info.<String>get("subSegmentoDistribuidor"), param));
            }
            if (filters.get("codigoVendedor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "codigoVendedor");
                crit.add(criteria.like(info.<String>get("codigoVendedor"), param));
            }
            if (filters.get("nombreVendedor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreVendedor");
                crit.add(criteria.like(info.<String>get("nombreVendedor"), param));
            }
            if (filters.get("rolTmcKam") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "rolTmcKam");
                crit.add(criteria.like(info.<String>get("rolTmcKam"), param));
            }
            if (filters.get("nombreTmcKam") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreTmcKam");
                crit.add(criteria.like(info.<String>get("nombreTmcKam"), param));
            }
            if (filters.get("rolCpa") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "rolCpa");
                crit.add(criteria.like(info.<String>get("rolCpa"), param));
            }
            if (filters.get("nombreCpa") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreCpa");
                crit.add(criteria.like(info.<String>get("nombreCpa"), param));
            }
            if (filters.get("codigoAdministradorAthena") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "codigoAdministradorAthena");
                crit.add(criteria.like(info.<String>get("codigoAdministradorAthena"), param));
            }
            if (filters.get("nombreAdministrador") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreAdministrador");
                crit.add(criteria.like(info.<String>get("nombreAdministrador"), param));

            }
            if (crit.size() == 1) {
                query.where(crit.get(0));
            } else if (crit.size() > 1) {
                query.where(criteria.and(crit.toArray(new Predicate[0])));
            }
            TypedQuery<InformeRelacionUsuarioOutlet> q = getEntityManager().createQuery(query);
            if (filters.get("origen") != null) {
                q.setParameter("origen", "%" + filters.get("origen") + "%");
            }
            if (filters.get("estado") != null) {
                q.setParameter("estado", filters.get("estado").toString());
            }
            if (filters.get("nit") != null) {
                q.setParameter("nit", "%" + filters.get("nit") + "%");
            }
            if (filters.get("outletId") != null) {
                try {
                    q.setParameter("outletId", filters.get("outletId"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Val {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("journeyPlan") != null) {
                try {
                    q.setParameter("journeyPlan", filters.get("journeyPlan"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Val {0},{1} no corresponde a un numero", new String[]{"journeyPlan", filters.get("journeyPlan") + ""});
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
            if (filters.get("kiernanId") != null) {
                q.setParameter("kiernanId", "%" + filters.get("kiernanId") + "%");
            }
            if (filters.get("gerenciaComercial") != null) {
                q.setParameter("gerenciaComercial", "%" + filters.get("gerenciaComercial") + "%");
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
            if (filters.get("canalDiageo") != null) {
                q.setParameter("canalDiageo", "%" + filters.get("canalDiageo") + "%");
            }
            if (filters.get("subCanalDiageo") != null) {
                q.setParameter("subCanalDiageo", "%" + filters.get("subCanalDiageo") + "%");
            }
            if (filters.get("segmentoDiageo") != null) {
                q.setParameter("segmentoDiageo", "%" + filters.get("segmentoDiageo") + "%");
            }
            if (filters.get("subSegmentoDiageo") != null) {
                q.setParameter("subSegmentoDiageo", "%" + filters.get("subSegmentoDiageo") + "%");
            }
            if (filters.get("namePotential") != null) {
                q.setParameter("namePotential", "%" + filters.get("namePotential") + "%");
            }
            if (filters.get("subSegmentoDistribuidor") != null) {
                q.setParameter("subSegmentoDistribuidor", "%" + filters.get("subSegmentoDistribuidor") + "%");
            }
            if (filters.get("codigoVendedor") != null) {
                q.setParameter("codigoVendedor", "%" + filters.get("codigoVendedor") + "%");
            }
            if (filters.get("nombreVendedor") != null) {
                q.setParameter("nombreVendedor", "%" + filters.get("nombreVendedor") + "%");
            }
            if (filters.get("rolTmcKam") != null) {
                q.setParameter("rolTmcKam", "%" + filters.get("rolTmcKam") + "%");
            }
            if (filters.get("nombreTmcKam") != null) {
                q.setParameter("nombreTmcKam", "%" + filters.get("nombreTmcKam") + "%");
            }
            if (filters.get("rolCpa") != null) {
                q.setParameter("rolCpa", "%" + filters.get("rolCpa") + "%");
            }
            if (filters.get("nombreCpa") != null) {
                q.setParameter("nombreCpa", "%" + filters.get("nombreCpa") + "%");
            }
            if (filters.get("codigoAdministradorAthena") != null) {
                q.setParameter("codigoAdministradorAthena", "%" + filters.get("codigoAdministradorAthena") + "%");
            }
            if (filters.get("nombreAdministrador") != null) {
                q.setParameter("nombreAdministrador", "%" + filters.get("nombreAdministrador") + "%");
            }
            q.setFirstResult(initial);
            q.setMaxResults(page);
            return q.getResultList();
        } else {
            TypedQuery<InformeRelacionUsuarioOutlet> q = getEntityManager().createQuery(query);
            q.setFirstResult(initial);
            q.setMaxResults(page);
            return q.getResultList();
        }
    }

    public long findAllDinamicCount(int initial, int page, Map<String, Object> filters) {
        CriteriaBuilder criteria = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = criteria.createQuery(Long.class);
        Root<InformeRelacionUsuarioOutlet> info = query.from(InformeRelacionUsuarioOutlet.class);
        query.select(criteria.count(info));
        List<Predicate> crit = new ArrayList<>();
        if (filters != null && !filters.isEmpty()) {
            if (filters.get("origen") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "origen");
                crit.add(criteria.like(info.<String>get("origen"), param));
            }
            if (filters.get("estado") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "estado");
                crit.add(criteria.like(info.<String>get("estado"), param));
            }
            if (filters.get("nit") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nit");
                crit.add(criteria.like(info.<String>get("nit"), param));
            }
            if (filters.get("outletId") != null) {
                try {
                    ParameterExpression<Integer> param = criteria.parameter(Integer.class, "outletId");
                    crit.add(criteria.equal(info.get("outletId"), param));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("journeyPlan") != null) {
                try {
                    ParameterExpression<Integer> param = criteria.parameter(Integer.class, "journeyPlan");
                    crit.add(criteria.equal(info.get("journeyPlan"), param));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"journeyPlan", filters.get("journeyPlan") + ""});
                }
            }
            if (filters.get("outletIdFather") != null) {
                try {
                    ParameterExpression<Integer> param = criteria.parameter(Integer.class, "outletIdFather");
                    crit.add(criteria.equal(info.get("outletIdFather"), param));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Param {0},{1} no corresponde a un numero", new String[]{"outletIdFather", filters.get("outletIdFather") + ""});
                }
            }
            if (filters.get("kiernanId") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "kiernanId");
                crit.add(criteria.like(info.<String>get("kiernanId"), param));
            }
            if (filters.get("gerenciaComercial") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "gerenciaComercial");
                crit.add(criteria.like(info.<String>get("gerenciaComercial"), param));
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
            if (filters.get("canalDiageo") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "canalDiageo");
                crit.add(criteria.like(info.<String>get("canalDiageo"), param));
            }
            if (filters.get("subCanalDiageo") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "subCanalDiageo");
                crit.add(criteria.like(info.<String>get("subCanalDiageo"), param));
            }
            if (filters.get("segmentoDiageo") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "segmentoDiageo");
                crit.add(criteria.like(info.<String>get("segmentoDiageo"), param));
            }
            if (filters.get("subSegmentoDiageo") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "subSegmentoDiageo");
                crit.add(criteria.like(info.<String>get("subSegmentoDiageo"), param));
            }
            if (filters.get("namePotential") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "namePotential");
                crit.add(criteria.like(info.<String>get("namePotential"), param));
            }
            if (filters.get("subSegmentoDistribuidor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "subSegmentoDistribuidor");
                crit.add(criteria.like(info.<String>get("subSegmentoDistribuidor"), param));
            }
            if (filters.get("codigoVendedor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "codigoVendedor");
                crit.add(criteria.like(info.<String>get("codigoVendedor"), param));
            }
            if (filters.get("nombreVendedor") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreVendedor");
                crit.add(criteria.like(info.<String>get("nombreVendedor"), param));
            }
            if (filters.get("rolTmcKam") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "rolTmcKam");
                crit.add(criteria.like(info.<String>get("rolTmcKam"), param));
            }
            if (filters.get("nombreTmcKam") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreTmcKam");
                crit.add(criteria.like(info.<String>get("nombreTmcKam"), param));
            }
            if (filters.get("rolCpa") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "rolCpa");
                crit.add(criteria.like(info.<String>get("rolCpa"), param));
            }
            if (filters.get("nombreCpa") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreCpa");
                crit.add(criteria.like(info.<String>get("nombreCpa"), param));
            }
            if (filters.get("codigoAdministradorAthena") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "codigoAdministradorAthena");
                crit.add(criteria.like(info.<String>get("codigoAdministradorAthena"), param));
            }
            if (filters.get("nombreAdministrador") != null) {
                ParameterExpression<String> param = criteria.parameter(String.class, "nombreAdministrador");
                crit.add(criteria.like(info.<String>get("nombreAdministrador"), param));
            }
            if (crit.size() == 1) {
                query.where(crit.get(0));
            } else if (crit.size() > 1) {
                query.where(criteria.and(crit.toArray(new Predicate[0])));
            }
            TypedQuery<Long> q = getEntityManager().createQuery(query);
            if (filters.get("origen") != null) {
                q.setParameter("origen", "%" + filters.get("origen") + "%");
            }
            if (filters.get("estado") != null) {
                q.setParameter("estado", "%" + filters.get("estado") + "%");
            }
            if (filters.get("nit") != null) {
                q.setParameter("nit", "%" + filters.get("nit") + "%");
            }
            if (filters.get("outletId") != null) {
                try {
                    q.setParameter("outletId", filters.get("outletId"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Val {0},{1} no corresponde a un numero", new String[]{"outletId", filters.get("outletId") + ""});
                }
            }
            if (filters.get("journeyPlan") != null) {
                try {
                    q.setParameter("journeyPlan", filters.get("journeyPlan"));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(InformerRelacionUsuarioOutletBeanLocal.class.getName())
                            .log(Level.SEVERE, "Val {0},{1} no corresponde a un numero", new String[]{"journeyPlan", filters.get("journeyPlan") + ""});
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
            if (filters.get("kiernanId") != null) {
                q.setParameter("kiernanId", "%" + filters.get("kiernanId") + "%");
            }
            if (filters.get("gerenciaComercial") != null) {
                q.setParameter("gerenciaComercial", "%" + filters.get("gerenciaComercial") + "%");
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
            if (filters.get("canalDiageo") != null) {
                q.setParameter("canalDiageo", "%" + filters.get("canalDiageo") + "%");
            }
            if (filters.get("subCanalDiageo") != null) {
                q.setParameter("subCanalDiageo", "%" + filters.get("subCanalDiageo") + "%");
            }
            if (filters.get("segmentoDiageo") != null) {
                q.setParameter("segmentoDiageo", "%" + filters.get("segmentoDiageo") + "%");
            }
            if (filters.get("subSegmentoDiageo") != null) {
                q.setParameter("subSegmentoDiageo", "%" + filters.get("subSegmentoDiageo") + "%");
            }
            if (filters.get("namePotential") != null) {
                q.setParameter("namePotential", "%" + filters.get("namePotential") + "%");
            }
            if (filters.get("subSegmentoDistribuidor") != null) {
                q.setParameter("subSegmentoDistribuidor", "%" + filters.get("subSegmentoDistribuidor") + "%");
            }
            if (filters.get("codigoVendedor") != null) {
                q.setParameter("codigoVendedor", "%" + filters.get("codigoVendedor") + "%");
            }
            if (filters.get("nombreVendedor") != null) {
                q.setParameter("nombreVendedor", "%" + filters.get("nombreVendedor") + "%");
            }
            if (filters.get("rolTmcKam") != null) {
                q.setParameter("rolTmcKam", "%" + filters.get("rolTmcKam") + "%");
            }
            if (filters.get("nombreTmcKam") != null) {
                q.setParameter("nombreTmcKam", "%" + filters.get("nombreTmcKam") + "%");
            }
            if (filters.get("rolCpa") != null) {
                q.setParameter("rolCpa", "%" + filters.get("rolCpa") + "%");
            }
            if (filters.get("nombreCpa") != null) {
                q.setParameter("nombreCpa", "%" + filters.get("nombreCpa") + "%");
            }
            if (filters.get("codigoAdministradorAthena") != null) {
                q.setParameter("codigoAdministradorAthena", "%" + filters.get("codigoAdministradorAthena") + "%");
            }
            if (filters.get("nombreAdministrador") != null) {
                q.setParameter("nombreAdministrador", "%" + filters.get("nombreAdministrador") + "%");
            }
            return q.getSingleResult();
        } else {
            TypedQuery<Long> q = getEntityManager().createQuery(query);
            return q.getSingleResult();
        }
    }
}
