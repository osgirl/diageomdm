/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.Db3PartyProfilesBeanLocal;
import com.diageo.diageonegocio.entidades.Db3partyProfiles;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author EDUARDO
 */
@FacesConverter(value = "db3PartyProfilesConverter")
public class Db3PartyProfilesConverter implements Converter {

    @EJB
    private Db3PartyProfilesBeanLocal db3PartyProfilesBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        return db3PartyProfilesBeanLocal.searchById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Db3partyProfiles) {
            Db3partyProfiles d = (Db3partyProfiles) value;
            return d.getDb3partyProfileId() + "";
        }
        return "";
    }

}
