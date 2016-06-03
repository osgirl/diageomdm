/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class SubChannelBean extends BusinessTransaction<DbSubChannels> implements SubChannelBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public DbSubChannels crearSubChannel(DbSubChannels subChannel) throws DiageoBusinessException {
        try {
            subChannel = super.create(subChannel);
            return subChannel;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    @Override
    public DbSubChannels modificarSubChannel(DbSubChannels subChannel) throws DiageoBusinessException {
        try {
            subChannel = (DbSubChannels) super.update(subChannel);
            return subChannel;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    @Override
    public List<DbSubChannels> consultarTodosSubChannel() {
        List<DbSubChannels> lista = super.searchAll(DbSubChannels.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    @Override
    public List<DbSubChannels> consultarSubChannelPorChannel(Integer id) {
        return super.searchByNamedQuery(DbSubChannels.class, DbSubChannels.FIND_BY_CHANNEL, id);
    }

    @Override
    public DbSubChannels consultarId(Integer id) throws DiageoBusinessException {
        try {
            DbSubChannels cha = (DbSubChannels) super.searchById(DbSubChannels.class, id);
            return cha;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

}
