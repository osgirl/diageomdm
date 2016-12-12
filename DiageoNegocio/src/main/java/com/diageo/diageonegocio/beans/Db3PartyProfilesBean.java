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
public class Db3PartyProfilesBean extends BusinessTransaction<Db3partyProfiles> implements Db3PartyProfilesBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Db3partyProfiles> searchAll3PartyProfiles() {
        return super.searchAll(Db3partyProfiles.class);
    }

    @Override
    public Db3partyProfiles searchById(Integer id) {
        return (Db3partyProfiles) super.searchById(Db3partyProfiles.class, id);
    }
}
