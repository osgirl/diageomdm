/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbOcs;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.enums.StateDiageo;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class OutletBeanLocal extends BusinessTransaction<DbOutlets> {

    @EJB
    private CustomerBeanLocal personaBeanLocal;
    @EJB
    private PhonesBeanLocal telefonosBeanLocal;
    @EJB
    private OcsBeanLocal ocsBeanLocal;

    /**
     * Crea un outlet
     *
     * @param out
     * @return
     * @throws DiageoBusinessException
     */
    public DbOutlets createOutlet(DbOutlets out) throws DiageoBusinessException {
        try {
            //out.setOwnerId(personaBeanLocal.createCustomer());
            out.setDbPhonesList(telefonosBeanLocal.createPhones(out.getDbPhonesList()));
            out = super.create(out);
            return out;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    /**
     * actualiza un outlet
     *
     * @param outlet
     * @return
     * @throws DiageoBusinessException
     */
    public DbOutlets updateOutlet(DbOutlets outlet) throws DiageoBusinessException {
        try {
            if (outlet.getOcsPrimary() == null || outlet.getOcsPrimary().getOcsId() == null) {
                DbOcs ocsPrimary = ocsBeanLocal.findById(0);
                outlet.setOcsPrimary(ocsPrimary);
            }
            if (outlet.getOcsSecondary() == null || outlet.getOcsSecondary().getOcsId() == null) {
                DbOcs ocsSecondary = ocsBeanLocal.findById(0);
                outlet.setOcsSecondary(ocsSecondary);
            }
            outlet = (DbOutlets) super.update(outlet);
            return outlet;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    /**
     * Busca todos los registros de la tabla DbOutlets sin usar consulta de tipo
     * Lazy
     *
     * @return
     */
    public List<DbOutlets> findAllOutlets() {
        List<DbOutlets> lista = super.searchAll(DbOutlets.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    /**
     * Busca por id el outlet
     *
     * @param id
     * @return
     * @throws DiageoBusinessException
     */
    public DbOutlets findById(Integer id) throws DiageoBusinessException {
        try {
            DbOutlets outlet = (DbOutlets) super.searchById(DbOutlets.class, id);
            return outlet;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    /**
     * Busca por la razón social del outlet
     *
     * @param query
     * @return
     */
    public List<DbOutlets> findByBusinessName(String query) {
        query = query.toUpperCase().trim();
        List<DbOutlets> list = super.searchByNamedQuery(DbOutlets.class, DbOutlets.FIND_BY_BUSINESS_NAME, "%" + query + "%", "1");
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    /**
     * Borra de la tabla customer outlets la relacion
     *
     * @param customerId
     * @param outletId
     */
    public void deleteCustomerOutlet(Integer customerId, Integer outletId) {
        String sql = "DELETE FROM DIAGEO_BUSINESS.dbo.DB_CUSTOMERS_OUTLETS WHERE CUSTOMER_ID=? AND OUTLET_ID=?";
        Query delete = super.getEntityManager().createNativeQuery(sql);
        delete.setParameter(1, customerId);
        delete.setParameter(2, outletId);
        delete.executeUpdate();
    }

    public List<DbOutlets> findAllOutletsDinamic(int initial, int page, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DbOutlets> query = cb.createQuery(DbOutlets.class);
        Root<DbOutlets> info = query.from(DbOutlets.class);
        query.select(info);
        List<Predicate> crit = new ArrayList<>();

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
            TypedQuery<DbOutlets> q = getEntityManager().createQuery(query);
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

            q.setFirstResult(initial);
            q.setMaxResults(page);
            return q.getResultList();
        } else {
            TypedQuery<DbOutlets> q = getEntityManager().createQuery(query);
            q.setFirstResult(initial);
            q.setMaxResults(page);
            return q.getResultList();
        }
    }

    public long findAllOutletsDinamicCount(Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<DbOutlets> info = query.from(DbOutlets.class);
        query.select(cb.count(info));
        List<Predicate> crit = new ArrayList<>();

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
            return q.getSingleResult();
        } else {
            TypedQuery<Long> q = getEntityManager().createQuery(query);
            return q.getSingleResult();
        }
    }

    /**
     * Buscar hijos del outlet seleccionado
     *
     * @param idOutlet
     * @return List DbOutlets
     */
    public List<DbOutlets> findByIdSons(Integer idOutlet) {
        List<DbOutlets> list = super.searchByNamedQuery(DbOutlets.class, DbOutlets.FIND_SONS_BY_OUTLET_ID, idOutlet);
        if (list != null && !list.isEmpty()) {
            return list;
        }
        return new ArrayList<>();
    }

    /**
     * Busca hijos basandose en la siguiente regla. Para seleccionar hijos solo
     * saldrán aquellos Outlets marcados en la base de datos como padre y cuyos
     * hijo sea solo el mismo, lo cual significa que el Outlet hijo a buscar no
     * es hijo de otro ni padre de otros hijos.
     *
     * @param param de la búsqueda
     * @return
     */
    public List<DbOutlets> findSons(String param) {
        if (param != null) {
            List<DbOutlets> list
                    = super.searchByNamedQuery(DbOutlets.class, DbOutlets.FIND_BY_NAME_KIERNAN_NIT_LIKE, "%" + param.trim() + "%");
            if (list != null && !list.isEmpty()) {
                return list;
            }
        }
        return new ArrayList<>();
    }

    public void relateOutlets(List<DbOutlets> listOutlets, DbOutlets outletFather) {
        for (DbOutlets listOutlet : listOutlets) {
            listOutlet.setOutletIdFather(outletFather);
            listOutlet.setIsFather(StateDiageo.INACTIVO.getId());
            super.update(listOutlet);
        }
    }

    public void toBreakRelationSonFather(List<DbOutlets> listOutlets) {
        for (DbOutlets listOutlet : listOutlets) {
            listOutlet.setOutletIdFather(listOutlet);
            listOutlet.setIsFather(StateDiageo.ACTIVO.getId());
            super.update(listOutlet);
        }
    }
    
    public List<DbOutlets> findBySeller(Integer id){
        List<DbOutlets> list = super.searchByNamedQuery(DbOutlets.class, DbOutlets.FIND_BY_SELLER, id);
        if (list != null && !list.isEmpty()) {
            return list;
        }
        return new ArrayList<>();
    }
}
