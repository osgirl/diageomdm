/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Db3partyProfiles;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class Db3PartyProfilesBeanLocal extends BusinessTransaction<Db3partyProfiles> {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Db3partyProfiles> searchAll3PartyProfiles() {
        return super.searchAll(Db3partyProfiles.class);
    }

    public Db3partyProfiles searchById(Integer id) {
        return (Db3partyProfiles) super.searchById(Db3partyProfiles.class, id);
    }
}
