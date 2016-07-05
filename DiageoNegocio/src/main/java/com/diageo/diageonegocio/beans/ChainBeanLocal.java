/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbChains;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface ChainBeanLocal {

    public DbChains createChain(DbChains entity);

    public DbChains updateChain(DbChains entity);

    public List<DbChains> findAllChains();

    public DbChains findById(Integer id);
    
}
