/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.admincontrollerweb.entities.Perfil;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.diageo.admincontrollerweb.beans.ProfileBeanLocal;

/**
 *
 * @author yovanoty126
 */
@FacesConverter(value = "perfilConverter")
public class PerfilConverter implements Converter {

    @EJB
    private ProfileBeanLocal transaccion;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        Perfil p = (Perfil) transaccion.findById(id);
        return p;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        Perfil perfil = (Perfil) value;
        return perfil.getIdperfil() + "";
    }

}
