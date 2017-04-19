/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Db3partyManagers;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class Db3PartyManagerBeanLocal extends BusinessTransaction<Db3partyManagers> {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Db3partyManagers> searchAllManagers() {
        return super.searchAll(Db3partyManagers.class);
    }

    public Db3partyManagers searchById(Integer id) {
        return (Db3partyManagers) super.searchById(Db3partyManagers.class, id);
    }
}
