/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Potential;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface PotentialBeanLocal {

    public void createPotential(Potential pot) throws DiageoNegocioException;

    public Potential findById(Integer id);

    public List<Potential> findAll();

    public List<Potential> findBySubSegment(Integer id);
    
}
