/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Db3partyProfiles;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface Db3PartyProfilesBeanLocal {

    public List<Db3partyProfiles> searchAll3PartyProfiles();

    public Db3partyProfiles searchById(Integer id);
    
}
