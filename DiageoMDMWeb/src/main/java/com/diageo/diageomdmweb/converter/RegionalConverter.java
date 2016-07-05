/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.RegionalBeanLocal;
import com.diageo.diageonegocio.entidades.Db3partyRegional;
import com.diageo.diageonegocio.entidades.DbChannels;
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
@FacesConverter(value = "regionalConverter")
public class RegionalConverter implements Converter {

    @EJB
    private RegionalBeanLocal regionalBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        try {
            Db3partyRegional cha = regionalBeanLocal.findById(id);
            return cha;
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(RegionalConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Db3partyRegional) {
            Db3partyRegional regional = (Db3partyRegional) value;
            return regional.getDb3partyRegionalId() + "";
        }
        return "";
    }

}
