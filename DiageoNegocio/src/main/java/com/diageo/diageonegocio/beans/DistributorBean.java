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
    public Distribuidor createDistributor(Distribuidor distri) throws DiageoNegocioException {
        try {
            distri = super.create(distri);
            return distri;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

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

}
