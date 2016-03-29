/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Channel;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class ChannelBean extends TransaccionesNegocio<Channel> implements ChannelBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Channel crearChannel(Channel channel) throws DiageoNegocioException {
        try {
            channel = super.crear(channel);
            return channel;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

    @Override
    public Channel modificarChannel(Channel channel) throws DiageoNegocioException {
        try {
            channel = (Channel) super.modificar(channel);
            return channel;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

    @Override
    public List<Channel> consultarTodosChannel() {
        List<Channel> lista = super.consultarTodo(Channel.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    @Override
    public Channel consultarId(Integer id) throws DiageoNegocioException {
        try {
            Channel cha = (Channel) super.consultarPorId(Channel.class, id);
            return cha;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

}
