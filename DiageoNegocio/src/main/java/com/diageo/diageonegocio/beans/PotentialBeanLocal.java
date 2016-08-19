/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbPotentials;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface PotentialBeanLocal {

    public void createPotential(DbPotentials pot) throws DiageoBusinessException;

    public DbPotentials findById(Integer id);

    public List<DbPotentials> findAll();

    public List<DbPotentials> findBySubSegment(Integer id);

    public DbPotentials updatePotential(DbPotentials pot) throws DiageoBusinessException;
    
}
