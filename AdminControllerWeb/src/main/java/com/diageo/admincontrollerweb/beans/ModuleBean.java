/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.DwModules;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class ModuleBean extends WebTransaction<DwModules> implements ModuleBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void createUserModule(DwModules mod) {
        super.update(mod);
    }

    @Override
    public void deleteModuleUser(Integer userId) {
        String delete="DELETE FROM DW_USERS_MODULES WHERE USER_ID = ?";
        Query sql=super.getEntityManager().createNativeQuery(delete);
        sql.setParameter(1, userId);
        sql.executeUpdate();
    }

}
