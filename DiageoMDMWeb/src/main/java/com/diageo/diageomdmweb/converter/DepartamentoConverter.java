/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.DepartamentBeanLocal;
import com.diageo.diageonegocio.entidades.DbDepartaments;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author yovanoty126
 */
@FacesConverter(value = "departamentoConverter")
public class DepartamentoConverter implements Converter {

    @EJB
    private DepartamentBeanLocal departamentoBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        DbDepartaments depto = departamentoBeanLocal.findById(id);
        return depto;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof DbDepartaments) {
            DbDepartaments d = (DbDepartaments) value;
            return d.getDepartamentId() + "";
        }
        return "";
    }

}
