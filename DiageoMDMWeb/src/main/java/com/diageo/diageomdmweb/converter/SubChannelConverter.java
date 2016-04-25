/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.entidades.SubChannel;
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
            SubChannel subChannel = (SubChannel) subChannelBeanLocal.consultarId(id);
            return subChannel;
        } catch (DiageoNegocioException ex) {
            Logger.getLogger(SubChannelConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }        
        if (value instanceof SubChannel) {
            SubChannel sc = (SubChannel) value;
            return sc.getIdsubchannel() + "";
        }
        return "";
    }

}
