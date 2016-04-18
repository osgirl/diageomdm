/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.Modulo;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class ModuleBean extends WebTransaction<Modulo>implements ModuleBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void createUserModule(Modulo mod){
        super.update(mod);        
    }
    
}
