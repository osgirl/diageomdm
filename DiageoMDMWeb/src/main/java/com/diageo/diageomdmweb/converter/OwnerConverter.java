/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.Db3PartyAdminBeanLocal;
import com.diageo.diageonegocio.beans.FasciaLocal;
import com.diageo.diageonegocio.beans.OwnerLocal;
import com.diageo.diageonegocio.entidades.Db3partyAdmin;
import com.diageo.diageonegocio.entidades.DbFascias;
import com.diageo.diageonegocio.entidades.DbOwners;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author EDUARDO
 */
@FacesConverter(value = "ownerConverter")
public class OwnerConverter implements Converter {

    @EJB
    private OwnerLocal ownerLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        return ownerLocal.findById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof DbOwners) {
            DbOwners en = (DbOwners) value;
            return en.getOwnerId() + "";
        }
        return "";
    }

}
