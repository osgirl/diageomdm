/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.SubChannel;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class SubChannelBean extends TransaccionesNegocio<SubChannel> implements SubChannelBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public SubChannel crearSubChannel(SubChannel subChannel) throws DiageoNegocioException {
        try {
            subChannel = super.crear(subChannel);
            return subChannel;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

    @Override
    public SubChannel modificarSubChannel(SubChannel subChannel) throws DiageoNegocioException {
        try {
            subChannel = (SubChannel) super.modificar(subChannel);
            return subChannel;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

    @Override
    public List<SubChannel> consultarTodosSubChannel() {
        List<SubChannel> lista = super.consultarTodo(SubChannel.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    @Override
    public List<SubChannel> consultarSubChannelPorChannel(Integer id) {
        return super.consultarPorNamedQuery(SubChannel.class, SubChannel.FIND_BY_CHANNEL, id);
    }

    @Override
    public SubChannel consultarId(Integer id) throws DiageoNegocioException {
        try {
            SubChannel cha = (SubChannel) super.consultarPorId(SubChannel.class, id);
            return cha;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

}
