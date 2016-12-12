/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Db3partySales;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface DbPartySalesBeanLocal {

    public List<Db3partySales> searchAll();

    public Db3partySales searchById(Integer id);

    public Db3partySales createSales(Db3partySales entity) throws DiageoBusinessException;

    public Db3partySales updateSales(Db3partySales entity) throws DiageoBusinessException;
    
}
