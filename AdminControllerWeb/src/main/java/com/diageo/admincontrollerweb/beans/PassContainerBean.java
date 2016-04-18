/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.PassContainer;
import com.diageo.admincontrollerweb.entities.PassContainerPK;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class PassContainerBean extends WebTransaction<PassContainer> implements PassContainerBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public PassContainer createPassContainer(Integer userId, String password) {
        PassContainerPK pk = new PassContainerPK(userId, password);
        PassContainer entity = new PassContainer(pk);
        entity.setModificationDate(Calendar.getInstance().getTime());
        PassContainer pc = super.create(entity);
        return pc;
    }

    @Override
    public List<PassContainer> findPassContainerByUser(Integer id) {
        List<PassContainer> list = super.findByNamedQuery(PassContainer.class, PassContainer.FIND_BY_USER, id);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public void deletePassContainer(Integer idUser, String password) {
        PassContainer pc = new PassContainer(idUser, password);
        super.delete(pc);
    }

    @Override
    public PassContainer findFirstRecordSaved(List<PassContainer> list) {
        PassContainer[] array = new PassContainer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            PassContainer get = list.get(i);
            array[i] = get;
        }
        Arrays.sort(array, new Comparator<PassContainer>() {
            @Override
            public int compare(PassContainer o1, PassContainer o2) {
                return o1.getModificationDate().compareTo(o2.getModificationDate());
            }
        });
        List<PassContainer> ps = super.findByNamedQuery(PassContainer.class, PassContainer.FIND_BY_USER_PASS, array[0].getPassContainerPK().getIdUser(), array[0].getPassContainerPK().getPassword());
        return ps.get(0);
    }
}
