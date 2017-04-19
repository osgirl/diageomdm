/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class Db3PartyBeanLocal extends BusinessTransaction<Db3party> {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Db3party createDistributor(Db3party distri) throws DiageoBusinessException {
        try {
            distri = super.create(distri);
            return distri;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    public Db3party updateDistributor(Db3party distri) throws DiageoBusinessException {
        try {
            distri = (Db3party) super.update(distri);
            return distri;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    public Db3party searchId(Integer id) {
        try {
            return (Db3party) super.searchById(Db3party.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Db3party> searchAllDistributor() {
        List<Db3party> list = super.searchByNamedQuery(Db3party.class, Db3party.FIND_ALL, null);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public List<Db3party> searchAll() {
        List<Db3party> list = super.searchAll(Db3party.class);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public List<Db3party> searchDistributorFather(String isPadre) {
        List<Db3party> list = super.searchByNamedQuery(Db3party.class, Db3party.FIND_BY_IS_PADRE, isPadre);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public List<Db3party> searchDistributorFatherIsChain(String isPadre, String isChain) {
        List<Db3party> list = super.searchByNamedQuery(Db3party.class, Db3party.FIND_BY_IS_PADRE_IS_CHAIN, isPadre, isChain);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public List<Db3party> searchDistributorByFather(Integer padre) {
        List<Db3party> list = super.searchByNamedQuery(Db3party.class, Db3party.FIND_BY_PADRE, padre);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public List<Db3party> searchDistributorByIsChain(String isChain, String status) {
        List<Db3party> list = super.searchByNamedQuery(Db3party.class, Db3party.FIND_BY_IS_CHAIN, isChain, status);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

}
