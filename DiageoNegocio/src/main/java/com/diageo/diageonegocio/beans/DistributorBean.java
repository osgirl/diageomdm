/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Distribuidor;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class DistributorBean extends BusinessTransaction<Distribuidor> implements DistributorBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Distribuidor createDistributor(Distribuidor distri) throws DiageoNegocioException {
        try {
            distri = super.create(distri);
            return distri;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

    @Override
    public Distribuidor updateDistributor(Distribuidor distri) throws DiageoNegocioException {
        try {
            distri = (Distribuidor) super.update(distri);
            return distri;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

    @Override
    public Distribuidor searchId(Integer id) {
        return (Distribuidor) super.searchById(Distribuidor.class, id);
    }

    @Override
    public List<Distribuidor> searchAllDistributor() {
        List<Distribuidor> lista = super.searchAll(Distribuidor.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    @Override
    public List<Distribuidor> searchADistributorPadre(String isPadre) {
        List<Distribuidor> list = super.searchByNamedQuery(Distribuidor.class, Distribuidor.FIND_BY_IS_PADRE, isPadre);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<Distribuidor> searchDistributorByPadre(Integer padre) {
        List<Distribuidor> list = super.searchByNamedQuery(Distribuidor.class, Distribuidor.FIND_BY_PADRE, padre);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

}
