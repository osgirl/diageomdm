/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface SubSegmentoBeanLocal {

    public DbSubSegments createSubSegment(DbSubSegments subSegment) throws DiageoBusinessException;

    public DbSubSegments updateSubSegment(DbSubSegments subSegment) throws DiageoBusinessException;

    public List<DbSubSegments> findAllSubSegment();

    public DbSubSegments findById(Integer id) throws DiageoBusinessException;

    public List<DbSubSegments> findSubSegment(Integer id);
    
}
