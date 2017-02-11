/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbOcs;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class OutletBean extends BusinessTransaction<DbOutlets> implements OutletBeanLocal {

    @EJB
    private CustomerBeanLocal personaBeanLocal;
    @EJB
    private PhonesBeanLocal telefonosBeanLocal;
    @EJB
    private OcsBeanLocal ocsBeanLocal;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
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

    @Override
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

    @Override
    public List<DbOutlets> findAllOutlets() {
        List<DbOutlets> lista = super.searchAll(DbOutlets.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    @Override
    public List<DbOutlets> findAllOutlets(int initial, int page, Map<String, Object> filters) {
        Query sql = getEntityManager().createNamedQuery(DbOutlets.FIND_ALL);
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
        if (filters.get("outletIdFather.outletId") != null) {
            sql.setParameter("outletIdFather", "%" + filters.get("outletIdFather.outletId").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("outletIdFather", "%%");
        }
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

    @Override
    public List<DbOutlets> findAllOutlets(Map<String, Object> filters) {
        Query sql = getEntityManager().createNamedQuery(DbOutlets.FIND_ALL);
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
        List<DbOutlets> list = sql.getResultList();
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<DbOutlets> findAllOutlets(String[] names, String[] values) {
        Query sql = getEntityManager().createNamedQuery(DbOutlets.FIND_ALL);
        boolean flagNit = false, flagBusinessName = false, flagNumberPdv = false, flagKiernanId = false, flagNameSubsegment = false, flagStatusOutlet = false, flagStatusMDM = false;
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            switch (name) {
                case "nit":
                    sql.setParameter("nit", "%" + values[i] + "%");
                    flagNit = true;
                    break;
                case "businessName":
                    sql.setParameter("businessName", "%" + values[i] + "%");
                    flagBusinessName = true;
                    break;
                case "numberPdv":
                    sql.setParameter("numberPdv", "%" + values[i] + "%");
                    flagNumberPdv = true;
                    break;
                case "kiernanId":
                    sql.setParameter("kiernanId", "%" + values[i] + "%");
                    flagKiernanId = true;
                    break;
                case "subSegmentId.nameSubsegment":
                    sql.setParameter("nameSubsegment", "%" + values[i] + "%");
                    flagNameSubsegment = true;
                    break;
                case "statusOutlet":
                    sql.setParameter("statusOutlet", "%" + values[i] + "%");
                    flagStatusOutlet = true;
                    break;
                case "statusMDM":
                    sql.setParameter("statusMDM", "%" + values[i] + "%");
                    flagStatusMDM = true;
                    break;
            }
        }
        if (!flagNit) {
            sql.setParameter("nit", "%%");
        }
        if (!flagBusinessName) {
            sql.setParameter("businessName", "%%");
        }
        if (!flagNumberPdv) {
            sql.setParameter("numberPdv", "%%");
        }
        if (!flagKiernanId) {
            sql.setParameter("kiernanId", "%%");
        }
        if (!flagNameSubsegment) {
            sql.setParameter("nameSubsegment", "%%");
        }
        if (!flagStatusOutlet) {
            sql.setParameter("statusOutlet", "%%");
        }
        if (!flagStatusMDM) {
            sql.setParameter("statusMDM", "%%");
        }
        List<DbOutlets> list = sql.getResultList();
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<DbOutlets> listOutletNew(String isNew) {
        List<DbOutlets> list = super.searchByNamedQuery(DbOutlets.class, DbOutlets.FIND_BY_NEW, isNew);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public DbOutlets findById(Integer id) throws DiageoBusinessException {
        try {
            DbOutlets outlet = (DbOutlets) super.searchById(DbOutlets.class, id);
            return outlet;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    @Override
    public List<DbOutlets> findByDistributor(Integer idDistri) {
        List<DbOutlets> list = super.searchByNamedQuery(DbOutlets.class, DbOutlets.FIND_BY_DISTRI, idDistri);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<DbOutlets> findByDistributor(Set<Integer> setDistributor, Set<Integer> setSubSegment, List<Integer> listState, String isNew) {
        List<DbOutlets> list
                = super.searchByNamedQuery(DbOutlets.class, DbOutlets.FIND_BY_DISTRI_SUBSEGMENT, setDistributor, setSubSegment, listState, isNew);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<DbOutlets> findBySubSegment(Integer idSubSegment) {
        List<DbOutlets> list
                = super.searchByNamedQuery(DbOutlets.class, DbOutlets.FIND_BY_SUB_SEGMENT, idSubSegment);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<DbOutlets> findBySegmentDb3Party(Integer subSegment, Integer db3Party) {
        List<DbOutlets> list
                = super.searchByNamedQuery(DbOutlets.class, DbOutlets.FIND_BY_SUB_SEGMENT_3PARTY, subSegment, db3Party);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<DbOutlets> findByBusinessName(String query) {
        query = query.toUpperCase().trim();
        List<DbOutlets> list = super.searchByNamedQuery(DbOutlets.class, DbOutlets.FIND_BY_BUSINESS_NAME, "%" + query + "%", "1");
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<DbOutlets> findBy3PartyPermissionSegment(Integer id3party, List<Integer> subSegment, List<String> statusMDM) {
        List<DbOutlets> list = super.searchByNamedQuery(DbOutlets.class, DbOutlets.FIND_BY_3PARTY_PERMISSION_LIST, id3party, subSegment, statusMDM);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<DbOutlets> findBy3PartyPermissionSegment(Integer id3party, List<Integer> subSegment, List<String> statusMDM, Map<String, Object> filters,
            int initial, int page) {
        Query sql = getEntityManager().createNamedQuery(DbOutlets.FIND_BY_3PARTY_PERMISSION);
        sql.setParameter("db3partyId", id3party);
        sql.setParameter("subSegmentId", subSegment);
        sql.setParameter("statusMDM", statusMDM);
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
        sql.setFirstResult(initial);
        sql.setMaxResults(page);
        List<DbOutlets> list = sql.getResultList();
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public long findBy3PartyPermissionSegmentCount(Integer id3party, List<Integer> subSegment, List<String> statusMDM, Map<String, Object> filters) {
        Query sql = getEntityManager().createNamedQuery(DbOutlets.FIND_BY_3PARTY_PERMISSION_COUNT);
        sql.setParameter("db3partyId", id3party);
        sql.setParameter("subSegmentId", subSegment);
        sql.setParameter("statusMDM", statusMDM);
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
        long size = (long) sql.getSingleResult();
        return size;
    }

    @Override
    public void deleteCustomerOutlet(Integer customerId, Integer outletId) {
        String sql = "DELETE FROM DIAGEO_BUSINESS.dbo.DB_CUSTOMERS_OUTLETS WHERE CUSTOMER_ID=? AND OUTLET_ID=?";
        Query delete = super.getEntityManager().createNativeQuery(sql);
        delete.setParameter(1, customerId);
        delete.setParameter(2, outletId);
        delete.executeUpdate();
    }

    @Override
    public long countOutlets() {
        Query sql = getEntityManager().createNamedQuery(DbOutlets.COUNT);
        long size = (long) sql.getSingleResult();
        return size;
    }

    @Override
    public long findAllOutletsCount(int initial, int page, Map<String, Object> filters) {
        Query sql = getEntityManager().createNamedQuery(DbOutlets.FIND_ALL_COUNT);
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
        if (filters.get("outletIdFather.outletId") != null) {
            sql.setParameter("outletIdFather", "%" + filters.get("outletIdFather.outletId").toString().toUpperCase() + "%");
        } else {
            sql.setParameter("outletIdFather", "%%");
        }
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

    @Override
    public long findAllOutletsCountProfiles(int initial, int page, Map<String, Object> filters, List<Integer> listOutletid) {
        Query sql = getEntityManager().createNamedQuery(DbOutlets.FIND_ALL_COUNT_PROFILES);
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
        if (!listOutletid.isEmpty()) {
            sql.setParameter("listOutletId", listOutletid);
        } else {
            listOutletid.add(0);
            sql.setParameter("listOutletId", listOutletid);
        }
        long size = (long) sql.getSingleResult();
        return size;
    }

    @Override
    public List<DbOutlets> findAllOutletsProfiles(int initial, int page, Map<String, Object> filters, List<Integer> listOutletid) {
        Query sql = getEntityManager().createNamedQuery(DbOutlets.FIND_ALL_PROFILES);
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
        sql.setParameter("listOutletId", listOutletid);
        sql.setFirstResult(initial);
        sql.setMaxResults(page);
        List<DbOutlets> list = sql.getResultList();
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

}
