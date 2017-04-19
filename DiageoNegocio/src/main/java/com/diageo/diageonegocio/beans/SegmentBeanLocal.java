/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class SegmentBeanLocal extends BusinessTransaction<DbSegments>  {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public DbSegments createSegment(DbSegments segmento) throws DiageoBusinessException {
        try {
            segmento = super.create(segmento);
            return segmento;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    public DbSegments updateSegment(DbSegments segmento) throws DiageoBusinessException {
        try {
            segmento = (DbSegments) super.update(segmento);
            return segmento;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    public List<DbSegments> findAllSegment() {
        List<DbSegments> lista = super.searchAll(DbSegments.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    public List<DbSegments> findBySubChannel(Integer id) {
        return super.searchByNamedQuery(DbSegments.class, DbSegments.FIND_BY_SUBCHANNEL, id);
    }

    public DbSegments findById(Integer id) throws DiageoBusinessException {
        try {
            DbSegments seg = (DbSegments) super.searchById(DbSegments.class, id);
            return seg;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }
}
