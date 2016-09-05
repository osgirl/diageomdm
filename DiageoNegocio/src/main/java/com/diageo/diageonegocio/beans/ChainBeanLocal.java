/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbChains;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface ChainBeanLocal {

    public DbChains createChain(DbChains entity) throws DiageoBusinessException;

    public DbChains updateChain(DbChains entity) throws DiageoBusinessException;

    public List<DbChains> findAllChains();

    public DbChains findById(Integer id);

    public List<DbChains> findBySegment3Party(Integer subSegment, Integer db3Party);

    public List<DbChains> findByNameChain(String nameChain);

    public List<DbChains> findBy3PartyPermissionSegment(Integer id3party, List<Integer> subSegment, List<String> statusMDM);

    public void deleteCustomerChain(Integer customerId, Integer chainId);

}
