/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.Db3PartyAdminBeanLocal;
import com.diageo.diageonegocio.entidades.Db3partyAdmin;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author EDUARDO
 */
@FacesConverter(value = "adminConverter")
public class AdminConverter implements Converter {

    @EJB
    private Db3PartyAdminBeanLocal db3PartyAdminBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        return db3PartyAdminBeanLocal.findById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Db3partyAdmin) {
            Db3partyAdmin en = (Db3partyAdmin) value;
            return en.getDb3partyAdminId() + "";
        }
        return "";
    }

}
