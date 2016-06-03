/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.CityBeanLocal;
import com.diageo.diageonegocio.entidades.DbTowns;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author yovanoty126
 */
@FacesConverter(value = "municipioConverter")
public class MunicipioConverter implements Converter {

    @EJB
    private CityBeanLocal municipioBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        DbTowns municipio = municipioBeanLocal.findById(id);
        return municipio;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof DbTowns) {
            DbTowns mun = (DbTowns) value;
            return mun.getTownId() + "";
        }
        return "";
    }

}
