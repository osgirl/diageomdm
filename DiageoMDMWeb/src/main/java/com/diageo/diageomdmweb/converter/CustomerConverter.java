/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.converter;

import com.diageo.diageonegocio.beans.ChainBeanLocal;
import com.diageo.diageonegocio.beans.ClusterBeanLocal;
import com.diageo.diageonegocio.beans.CustomerBeanLocal;
import com.diageo.diageonegocio.entidades.DbChains;
import com.diageo.diageonegocio.entidades.DbClusters;
import com.diageo.diageonegocio.entidades.DbCustomers;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author EDUARDO
 */
@FacesConverter(value = "customerConverter")
public class CustomerConverter implements Converter {

    @EJB
    private CustomerBeanLocal customerBeanLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("null")) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        return customerBeanLocal.findById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof DbCustomers) {
            DbCustomers obj = (DbCustomers) value;
            return obj.getCustomerId() + "";
        }
        return "";
    }

}
