/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.entidades.Channel;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
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
@FacesConverter(value = "channelConverter")
public class ChannelConverter implements Converter {

    @EJB
    private ChannelBeanLocal channelBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        try {
            Channel cha = channelBeanLocal.consultarId(id);
            return cha;
        } catch (DiageoNegocioException ex) {
            Logger.getLogger(ChannelConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Channel) {
            Channel cha = (Channel) value;
            return cha.getIdchannel() + "";
        }
        return "";
    }

}
