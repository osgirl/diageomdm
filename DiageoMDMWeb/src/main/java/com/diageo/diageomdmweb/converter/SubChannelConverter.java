/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author yovanoty126
 */
@FacesConverter(value = "subChannelConverter")
public class SubChannelConverter implements Converter {

    @EJB
    private SubChannelBeanLocal subChannelBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (value == null || value.isEmpty()) {
                return null;
            }
            Integer id = Integer.parseInt(value);
            DbSubChannels subChannel = (DbSubChannels) subChannelBeanLocal.findById(id);
            return subChannel;
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(SubChannelConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof DbSubChannels) {
            DbSubChannels sc = (DbSubChannels) value;
            return sc.getSubChannelId() + "";
        }
        return "";
    }

}
