/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Db3partyRegional;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class RegionalBeanLocal extends BusinessTransaction<Db3partyRegional> {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Db3partyRegional> findAll() {
        return super.searchAll(Db3partyRegional.class);
    }

    public Db3partyRegional createRegional(Db3partyRegional entity) throws DiageoBusinessException {
        try {
            entity = super.create(entity);
            return entity;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    public Db3partyRegional updateRegional(Db3partyRegional entity) throws DiageoBusinessException {
        try {
            entity = (Db3partyRegional) super.update(entity);
            return entity;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    public Db3partyRegional findById(Integer id) throws DiageoBusinessException {
        try {
            return (Db3partyRegional) super.searchById(Db3partyRegional.class, id);
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

}
