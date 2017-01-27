/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbChains;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class ChainBean extends BusinessTransaction<DbChains> implements ChainBeanLocal {

    @Override
    public DbChains createChain(DbChains entity) throws DiageoBusinessException {
        try {
            entity = super.create(entity);
            return entity;
        } catch (Exception e) {
            throw new DiageoBusinessException(e);
        }
    }

    @Override
    public DbChains updateChain(DbChains entity) throws DiageoBusinessException {
        try {
            entity = (DbChains) super.update(entity);
            return entity;
        } catch (Exception e) {
            throw new DiageoBusinessException(e);
        }
    }

    @Override
    public List<DbChains> findAllChains() {
        return super.searchAll(DbChains.class);
    }

    @Override
    public DbChains findById(Integer id) {
        return (DbChains) super.searchById(DbChains.class, id);
    }

    @Override
    public List<DbChains> findBySegment3Party(Integer subSegment, Integer db3Party) {
        List<DbChains> list = super.searchByNamedQuery(DbChains.class, DbChains.FIND_BY_SEGMENT_3PARTY, subSegment, db3Party);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<DbChains> findByNameChain(String nameChain) {
        nameChain = nameChain.toUpperCase();
        List<DbChains> list = super.searchByNamedQuery(DbChains.class, DbChains.FIND_BY_NAME_CHAIN, "%" + nameChain + "%");
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<DbChains> findBy3PartyPermissionSegment(Integer id3party, List<Integer> subSegment, List<String> statusMDM) {
        List<DbChains> list = super.searchByNamedQuery(DbChains.class, DbChains.FIND_BY_3PARTY_PERMISSION, id3party, subSegment, statusMDM);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public void deleteCustomerChain(Integer customerId, Integer chainId) {
        String sql = "DELETE FROM DIAGEO_BUSINESS.dbo.DB_CUSTOMERS_CHAINS WHERE CUSTOMER_ID=? AND CHAIN_ID=?";
        Query delete = super.getEntityManager().createNativeQuery(sql);
        delete.setParameter(1, customerId);
        delete.setParameter(2, chainId);
        delete.executeUpdate();
    }

    @Override
    public List<DbChains> findAllChainsProfiles(List<Integer> listChainId) {
        List<DbChains> list = super.searchByNamedQuery(DbChains.class, DbChains.FIND_CHAIN_BY_CHAIN_ID_LIST, listChainId);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

}
