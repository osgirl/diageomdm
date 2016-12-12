/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.Db3PartyManagerBeanLocal;
import com.diageo.diageonegocio.entidades.Db3partyManagers;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author EDUARDO
 */
@FacesConverter(value = "partyManagerConverter")
public class PartyManagerConverter implements Converter {

    @EJB
    private Db3PartyManagerBeanLocal db3PartyManagerBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        return db3PartyManagerBeanLocal.searchById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value==null){
            return "";
        }
        if(value instanceof Db3partyManagers){
            Db3partyManagers m=(Db3partyManagers)value;
            return m.getDb3partyManagerId()+"";
        }
        return "";
    }

}
