/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Db3partyAdmin;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface Db3PartyAdminBeanLocal {

    public void createAdmin(Db3partyAdmin entity);

    public void updateAdmin(Db3partyAdmin entity);

    public Db3partyAdmin findById(Integer id);

    public List<Db3partyAdmin> findAll();
    
}
