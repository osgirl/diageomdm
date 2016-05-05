/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.admincontrollerweb.entities.DwDocumentTypes;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.diageo.admincontrollerweb.beans.DocumentTypeBeanLocal;

/**
 *
 * @author yovanoty126
 */
@FacesConverter(value = "tipoDocConverter")
public class TipoDocConverter implements Converter {

    @EJB
    private DocumentTypeBeanLocal transacciones;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        DwDocumentTypes tidpoDoc = (DwDocumentTypes) transacciones.findById(id);
        return tidpoDoc;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        DwDocumentTypes tipoDocumento = (DwDocumentTypes) value;
        return tipoDocumento.getDocumentTypeId() + "";
    }

}
