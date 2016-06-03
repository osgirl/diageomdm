/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.Db3PartyBeanLocal;
import com.diageo.diageonegocio.entidades.Db3party;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author yovanoty126
 */
@FacesConverter(value = "distribuidorConverter")
public class DistribuidorConverter implements Converter {

    @EJB
    private Db3PartyBeanLocal distribuidorBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        Db3party distri = distribuidorBeanLocal.searchId(id);
        return distri;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Db3party) {
            Db3party dis = (Db3party) value;
            return dis.getDb3partyId() + "";
        }
        return "";
    }

}
