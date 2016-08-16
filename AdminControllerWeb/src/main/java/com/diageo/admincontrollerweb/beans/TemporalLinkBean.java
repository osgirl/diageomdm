/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.DwTemporalLink;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class TemporalLinkBean extends WebTransaction<DwTemporalLink> implements TemporalLinkBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<DwTemporalLink> findbyEmail(String email) {
        List<DwTemporalLink> list = super.findByNamedQuery(DwTemporalLink.class, DwTemporalLink.FIND_BY_EMAIL, email);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public void createTemporal(DwTemporalLink entity) {
        super.create(entity);
    }

    @Override
    public DwTemporalLink findByToken(String token) {
        List<DwTemporalLink> list = super.findByNamedQuery(DwTemporalLink.class, DwTemporalLink.FIND_BY_TOKEN, token);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
