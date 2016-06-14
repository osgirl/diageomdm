/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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
    public List<DbOutlets> listOutletNew(String isNew) {
        return super.searchByNamedQuery(DbOutlets.class, DbOutlets.FIND_BY_NEW, isNew);
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
    public List<DbOutlets> findBySubSegment(Integer idSubSegment){
        List<DbOutlets> list
                = super.searchByNamedQuery(DbOutlets.class, DbOutlets.FIND_BY_SUB_SEGMENT, idSubSegment);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }
}
