/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.DwParameters;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class ParameterBeanLocal extends WebTransaction<DwParameters>  {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public DwParameters createParameter(DwParameters entity) {
        return super.create(entity);
    }

    public DwParameters updateParameter(DwParameters entity) {
        return (DwParameters) super.update(entity);
    }

    public List<DwParameters> findByKey(String key) {
        List<DwParameters> list = super.findByNamedQuery(DwParameters.class, DwParameters.FIND_KEY, key);
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        return list;
    }

    public void deleteParameter(DwParameters entity) {
        super.delete(entity);
    }

}
