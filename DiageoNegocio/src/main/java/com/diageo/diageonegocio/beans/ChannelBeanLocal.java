/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Channel;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface ChannelBeanLocal {

    public Channel crearChannel(Channel channel) throws DiageoNegocioException;

    public Channel modificarChannel(Channel channel) throws DiageoNegocioException;

    public List<Channel> consultarTodosChannel();

    public Channel consultarId(Integer id) throws DiageoNegocioException;
    
}
