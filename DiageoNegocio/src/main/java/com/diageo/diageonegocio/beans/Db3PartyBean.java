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
public class Db3PartyBean extends BusinessTransaction<Db3party> implements Db3PartyBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Db3party createDistributor(Db3party distri) throws DiageoBusinessException {
        try {
            distri = super.create(distri);
            return distri;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    @Override
    public Db3party updateDistributor(Db3party distri) throws DiageoBusinessException {
        try {
            distri = (Db3party) super.update(distri);
            return distri;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    @Override
    public Db3party searchId(Integer id) {
        return (Db3party) super.searchById(Db3party.class, id);
    }

    @Override
    public List<Db3party> searchAllDistributor() {
        List<Db3party> lista = super.searchAll(Db3party.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    @Override
    public List<Db3party> searchDistributorFather(String isPadre) {
        List<Db3party> list = super.searchByNamedQuery(Db3party.class, Db3party.FIND_BY_IS_PADRE, isPadre);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<Db3party> searchDistributorByFather(Integer padre) {
        List<Db3party> list = super.searchByNamedQuery(Db3party.class, Db3party.FIND_BY_PADRE, padre);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

}