/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface Db3PartyBeanLocal {

    public Db3party searchId(Integer id);

    public List<Db3party> searchAllDistributor();

    public Db3party createDistributor(Db3party distri) throws DiageoBusinessException;

    public Db3party updateDistributor(Db3party distri) throws DiageoBusinessException;

    public List<Db3party> searchDistributorFather(String isFather);

    public List<Db3party> searchDistributorByFather(Integer father);

    public List<Db3party> searchDistributorByIsChain(String isChain, String status);

    public List<Db3party> searchAll();

}
