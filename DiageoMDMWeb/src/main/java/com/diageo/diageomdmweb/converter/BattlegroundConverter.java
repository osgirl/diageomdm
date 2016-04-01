/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.BattleGroundBeanLocal;
import com.diageo.diageonegocio.entidades.Battleground;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
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
@FacesConverter(value = "battlegroundConverter")
public class BattlegroundConverter implements Converter {

    @EJB
    private BattleGroundBeanLocal battleGroundBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        try {
            Battleground b = battleGroundBeanLocal.consultarId(id);
            return b;
        } catch (DiageoNegocioException ex) {
            Logger.getLogger(BattlegroundConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Battleground) {
            Battleground b = (Battleground) value;
            return b.getIdbattleground() + "";
        }
        return "";
    }

}
