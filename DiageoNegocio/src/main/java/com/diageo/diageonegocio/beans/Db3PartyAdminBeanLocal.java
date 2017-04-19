/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Db3partyAdmin;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class Db3PartyAdminBeanLocal extends BusinessTransaction<Db3partyAdmin> {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void createAdmin(Db3partyAdmin entity) {
        super.create(entity);
    }

    public void updateAdmin(Db3partyAdmin entity) {
        super.update(entity);
    }

    public Db3partyAdmin findById(Integer id) {
        return (Db3partyAdmin) super.searchById(Db3partyAdmin.class, id);
    }

    public List<Db3partyAdmin> findAll() {
        List<Db3partyAdmin> list = super.searchAll(Db3partyAdmin.class);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

}
