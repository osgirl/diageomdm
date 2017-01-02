/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.DbLayerBeanLocal;
import com.diageo.diageonegocio.entidades.DbLayer;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author EDUARDO
 */
@FacesConverter(value = "layerConverter")
public class LayerConverter implements Converter {

    @EJB
    private DbLayerBeanLocal dbLayerBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        return (DbLayer) dbLayerBeanLocal.searchId(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof DbLayer) {
            DbLayer d = (DbLayer) value;
            return d.getLayerId() + "";
        }
        return "";
    }

}
