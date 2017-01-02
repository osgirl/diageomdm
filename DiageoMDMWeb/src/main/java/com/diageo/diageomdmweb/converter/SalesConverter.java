/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.DbPartySalesBeanLocal;
import com.diageo.diageonegocio.entidades.Db3partySales;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author EDUARDO
 */
@FacesConverter(value = "salesConverter")
public class SalesConverter implements Converter {

    @EJB
    private DbPartySalesBeanLocal dbPartySalesBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("getAsObject");
        if (value == null || value.isEmpty() || value.equals("null")) {
            return null;
        }
        Db3partySales seller = (Db3partySales) dbPartySalesBeanLocal.searchById(Integer.parseInt(value));
        return seller;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Db3partySales) {
            Db3partySales dto = (Db3partySales) value;
            return dto.getDb3partySaleId() + "";
        }
        return "";
    }

}
