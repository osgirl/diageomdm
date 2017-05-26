/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.Db3PartyAdminBeanLocal;
import com.diageo.diageonegocio.beans.FasciaLocal;
import com.diageo.diageonegocio.entidades.Db3partyAdmin;
import com.diageo.diageonegocio.entidades.DbFascias;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author EDUARDO
 */
@FacesConverter(value = "fasciaConverter")
public class FasciaConverter implements Converter {

    @EJB
    private FasciaLocal fasciaLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        return fasciaLocal.findById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof DbFascias) {
            DbFascias en = (DbFascias) value;
            return en.getFasciaId() + "";
        }
        return "";
    }

}
