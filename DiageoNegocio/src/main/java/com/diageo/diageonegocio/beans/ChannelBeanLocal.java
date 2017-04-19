/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class ChannelBeanLocal extends BusinessTransaction<DbChannels> {

    public DbChannels createChannel(DbChannels channel) throws DiageoBusinessException {
        try {
            channel = super.create(channel);
            return channel;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    public DbChannels updateChannel(DbChannels channel) throws DiageoBusinessException {
        try {
            channel = (DbChannels) super.update(channel);
            return channel;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

    public List<DbChannels> findAllChannel() {
        List<DbChannels> lista = super.searchAll(DbChannels.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    public List<DbChannels> findAllChannelActive() {
        List<DbChannels> list = super.searchByNamedQuery(DbChannels.class, DbChannels.FIND_ACTIVE);
        return list;
    }

    public DbChannels findById(Integer id) throws DiageoBusinessException {
        try {
            DbChannels cha = (DbChannels) super.searchById(DbChannels.class, id);
            return cha;
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
    }

}
