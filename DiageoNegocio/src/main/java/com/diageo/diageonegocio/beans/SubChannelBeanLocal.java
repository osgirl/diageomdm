/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface SubChannelBeanLocal {

    public DbSubChannels crearSubChannel(DbSubChannels subChannel) throws DiageoBusinessException;

    public DbSubChannels modificarSubChannel(DbSubChannels subChannel) throws DiageoBusinessException;

    public List<DbSubChannels> consultarTodosSubChannel();

    public DbSubChannels consultarId(Integer id) throws DiageoBusinessException;

    public List<DbSubChannels> consultarSubChannelPorChannel(Integer id);
    
}
