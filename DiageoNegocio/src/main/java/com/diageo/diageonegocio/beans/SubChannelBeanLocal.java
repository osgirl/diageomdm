/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.SubChannel;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface SubChannelBeanLocal {

    public SubChannel crearSubChannel(SubChannel subChannel) throws DiageoNegocioException;

    public SubChannel modificarSubChannel(SubChannel subChannel) throws DiageoNegocioException;

    public List<SubChannel> consultarTodosSubChannel();

    public SubChannel consultarId(Integer id) throws DiageoNegocioException;

    public List<SubChannel> consultarSubChannelPorChannel(Integer id);
    
}
