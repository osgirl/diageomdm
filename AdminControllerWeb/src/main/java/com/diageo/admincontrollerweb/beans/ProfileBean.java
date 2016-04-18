/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.Perfil;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class ProfileBean extends WebTransaction<Perfil> implements ProfileBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Perfil> findAll() {
        return findAll(Perfil.class);
    }

    @Override
    public Perfil findById(Integer id) {
        return (Perfil) findById(Perfil.class, id);
    }
 
}
