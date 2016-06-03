/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface SegmentBeanLocal {

    public DbSegments createSegment(DbSegments segment) throws DiageoBusinessException;

    public DbSegments updateSegment(DbSegments segment) throws DiageoBusinessException;

    public List<DbSegments> findAllSegment();

    public DbSegments findById(Integer id) throws DiageoBusinessException;

    public List<DbSegments> findBySubChannel(Integer id);
    
}
