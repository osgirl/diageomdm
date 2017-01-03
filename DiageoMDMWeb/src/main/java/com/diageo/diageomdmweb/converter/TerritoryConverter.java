/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.TerritoryBeanLocal;
import com.diageo.diageonegocio.entidades.Db3partyTerritory;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author EDUARDO
 */
@FacesConverter(value = "territoryConverter")
public class TerritoryConverter implements Converter {

    @EJB
    private TerritoryBeanLocal territoryBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return territoryBeanLocal.findByName(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Db3partyTerritory) {
            Db3partyTerritory d = (Db3partyTerritory) value;
            return d.getNameTerritory();
        }
        return "";
    }

}
