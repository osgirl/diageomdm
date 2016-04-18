/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Distribuidor;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface DistributorBeanLocal {

    public Distribuidor searchId(Integer id);

    public List<Distribuidor> searchAllDistributor();

    public Distribuidor createDistributor(Distribuidor distri) throws DiageoNegocioException;

    public Distribuidor updateDistributor(Distribuidor distri) throws DiageoNegocioException;

    public List<Distribuidor> searchADistributorPadre(String isPadre);
    
}
