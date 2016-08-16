/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbPhones;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class PhoneBean extends BusinessTransaction<DbPhones> implements PhonesBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<DbPhones> createPhones(List<DbPhones> pho) {
        for (DbPhones phones : pho) {
            phones = super.create(phones);
        }
        return pho;
    }

    @Override
    public DbPhones createPhones(DbPhones pho) {
        return super.create(pho);
    }

    @Override
    public void deletePhoneList(List<DbPhones> list) {
        for (DbPhones dbPhones : list) {
            if (dbPhones.getPhoneId() != null) {
                super.delete(dbPhones);
            }
        }
    }
}
