/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class SubSegmentoBeanLocal extends BusinessTransaction<DbSubSegments> {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public DbSubSegments createSubSegment(DbSubSegments subSegmento) throws DiageoBusinessException{
        try {
            subSegmento = super.create(subSegmento);
            return subSegmento;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }
    
    public DbSubSegments updateSubSegment(DbSubSegments subSegmento) throws DiageoBusinessException{
        try {
            subSegmento = (DbSubSegments) super.update(subSegmento);
            return subSegmento;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }
    
    public List<DbSubSegments> findAllSubSegment(){
        List<DbSubSegments> lista = super.searchAll(DbSubSegments.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }
    
    public DbSubSegments findById(Integer id) throws DiageoBusinessException{
        try {
            DbSubSegments subSegmento = (DbSubSegments) super.searchById(DbSubSegments.class, id);
            return subSegmento;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }
    
    public List<DbSubSegments> findSubSegment(Integer id){
        List<DbSubSegments> list=super.searchByNamedQuery(DbSubSegments.class, DbSubSegments.FIND_BY_SEGMENT, id);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }
}
