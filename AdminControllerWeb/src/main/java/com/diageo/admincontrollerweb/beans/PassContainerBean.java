/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.DwPasscontainers;
import com.diageo.admincontrollerweb.entities.DwUsers;
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
public class PassContainerBean extends WebTransaction<DwPasscontainers> implements PassContainerBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public DwPasscontainers createPassContainer(Integer userId, String password) {
        DwPasscontainers entity = new DwPasscontainers();
        entity.setPasswordUser(password);
        entity.setUserId(new DwUsers(userId));
        entity.setUpdatePassword(Calendar.getInstance().getTime());
        DwPasscontainers pc = super.create(entity);
        return pc;
    }

    @Override
    public List<DwPasscontainers> findPassContainerByUser(Integer id) {
        List<DwPasscontainers> list = super.findByNamedQuery(DwPasscontainers.class, DwPasscontainers.FIND_BY_USER, id);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public void deletePassContainer(Integer idUser, String password) {
        List<DwPasscontainers> ps = super.findByNamedQuery(DwPasscontainers.class, DwPasscontainers.FIND_BY_USER_PASS, idUser, password);        
        DwPasscontainers pc = ps.get(0);
        super.delete(pc);
    }

    @Override
    public DwPasscontainers findFirstRecordSaved(List<DwPasscontainers> list) {
        DwPasscontainers[] array = new DwPasscontainers[list.size()];
        for (int i = 0; i < list.size(); i++) {
            DwPasscontainers get = list.get(i);
            array[i] = get;
        }
        Arrays.sort(array, new Comparator<DwPasscontainers>() {
            @Override
            public int compare(DwPasscontainers o1, DwPasscontainers o2) {
                return o1.getUpdatePassword().compareTo(o2.getUpdatePassword());
            }
        });
        List<DwPasscontainers> ps = super.findByNamedQuery(DwPasscontainers.class, DwPasscontainers.FIND_BY_USER_PASS, array[0].getPasscontainerId(), array[0].getPasswordUser());
        return ps.get(0);
    }
}
