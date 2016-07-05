/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Db3partyRegional;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface RegionalBeanLocal {

    public List<Db3partyRegional> findAll();

    public Db3partyRegional createRegional(Db3partyRegional entity) throws DiageoBusinessException;

    public Db3partyRegional updateRegional(Db3partyRegional entity) throws DiageoBusinessException;

    public Db3partyRegional findById(Integer id) throws DiageoBusinessException;

}
