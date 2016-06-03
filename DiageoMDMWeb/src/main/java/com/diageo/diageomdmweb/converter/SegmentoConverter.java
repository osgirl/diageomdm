/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.SegmentBeanLocal;
import com.diageo.diageonegocio.entidades.DbSegments;
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
@FacesConverter(value = "segmentoConverter")
public class SegmentoConverter implements Converter {

    @EJB
    private SegmentBeanLocal segmentoBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (value == null || value.isEmpty()) {
                return null;
            }
            Integer id = Integer.parseInt(value);
            DbSegments seg = segmentoBeanLocal.findById(id);
            return seg;
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(SegmentoConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof DbSegments) {
            DbSegments seg = (DbSegments) value;
            return seg.getSegmentId() + "";
        }
        return "";
    }

}
