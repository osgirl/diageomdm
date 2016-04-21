/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Sateoutlet;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class StateOutletBean extends BusinessTransaction<Sateoutlet> implements StateOutletBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Sateoutlet> findAll() {
        List<Sateoutlet> list = super.searchAll(Sateoutlet.class);
        return list;
    }

}