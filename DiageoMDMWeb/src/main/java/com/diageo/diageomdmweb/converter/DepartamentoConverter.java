/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.DepartamentoBeanLocal;
import com.diageo.diageonegocio.entidades.Departamento;
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
    private DepartamentoBeanLocal departamentoBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        Departamento depto = departamentoBeanLocal.consultarId(id);
        return depto;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Departamento) {
            Departamento d = (Departamento) value;
            return d.getIddepartamento() + "";
        }
        return "";
    }

}
