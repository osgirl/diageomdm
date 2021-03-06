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
public class ChainBeanLocal extends BusinessTransaction<DbChains> {

    public DbChains createChain(DbChains entity) throws DiageoBusinessException {
        try {
            entity = super.create(entity);
            return entity;
        } catch (Exception e) {
            throw new DiageoBusinessException(e);
        }
    }

    public DbChains updateChain(DbChains entity) throws DiageoBusinessException {
        try {
            entity = (DbChains) super.update(entity);
            return entity;
        } catch (Exception e) {
            throw new DiageoBusinessException(e);
        }
    }

    public List<DbChains> findAllChains() {
        return super.searchAll(DbChains.class);
    }

    public DbChains findById(Integer id) {
        return (DbChains) super.searchById(DbChains.class, id);
    }

    public List<DbChains> findBySegment3Party(Integer subSegment, Integer db3Party) {
        List<DbChains> list = super.searchByNamedQuery(DbChains.class, DbChains.FIND_BY_SEGMENT_3PARTY, subSegment, db3Party);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public List<DbChains> findByNameChain(String nameChain) {
        nameChain = nameChain.toUpperCase();
        List<DbChains> list = super.searchByNamedQuery(DbChains.class, DbChains.FIND_BY_NAME_CHAIN, "%" + nameChain + "%");
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public List<DbChains> findBy3PartyPermissionSegment(Integer id3party, List<Integer> subSegment, List<String> statusMDM) {
        List<DbChains> list = super.searchByNamedQuery(DbChains.class, DbChains.FIND_BY_3PARTY_PERMISSION, id3party, subSegment, statusMDM);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public void deleteCustomerChain(Integer customerId, Integer chainId) {
        String sql = "DELETE FROM DIAGEO_BUSINESS.dbo.DB_CUSTOMERS_CHAINS WHERE CUSTOMER_ID=?1 AND CHAIN_ID=?2";
        Query delete = super.getEntityManager().createNativeQuery(sql);
        delete.setParameter(1, customerId);
        delete.setParameter(2, chainId);
        delete.executeUpdate();
    }

    public List<DbChains> findAllChainsProfiles(List<Integer> listChainId) {
        List<DbChains> list = super.searchByNamedQuery(DbChains.class, DbChains.FIND_CHAIN_BY_CHAIN_ID_LIST, listChainId);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public DbChains findByCodeEAN(String codeEan) {
        List<DbChains> list = super.searchByNamedQuery(DbChains.class, DbChains.FIND_BY_CODE_EAN, codeEan);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}
