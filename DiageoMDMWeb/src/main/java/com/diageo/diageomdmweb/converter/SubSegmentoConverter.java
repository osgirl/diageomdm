/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.SubSegmentoBeanLocal;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author yovanoty126
 */
@FacesConverter(value = "subSegmentoConverter")
public class SubSegmentoConverter implements Converter {

    @EJB
    private SubSegmentoBeanLocal subSegmentoBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (value == null || value.isEmpty()) {
                return null;
            }
            Integer id = Integer.parseInt(value);
            DbSubSegments seg = subSegmentoBeanLocal.findById(id);
            return seg;
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(SubSegmentoConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof DbSubSegments) {
            DbSubSegments seg = (DbSubSegments) value;
            return seg.getSubSegmentId() + "";
        }
        return "";
    }

}
