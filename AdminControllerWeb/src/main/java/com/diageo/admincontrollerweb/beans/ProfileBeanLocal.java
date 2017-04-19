/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.DwProfiles;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class ProfileBeanLocal extends WebTransaction<DwProfiles>  {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<DwProfiles> findAll() {
        return findAll(DwProfiles.class);
    }

    public DwProfiles findById(Integer id) {
        return (DwProfiles) findById(DwProfiles.class, id);
    }

    public List<DwProfiles> findBySystem() {
        System.out.println("entro metodo");
        List<DwProfiles> list = super.findByNamedQuery(DwProfiles.class, DwProfiles.FIND_BY_SYSTEM);
        System.out.println("resultados" + list);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public List<DwProfiles> findByLevel(boolean level) {
        List<DwProfiles> list = super.findByNamedQuery(DwProfiles.class, DwProfiles.FIND_BY_LEVEL, level);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

}
