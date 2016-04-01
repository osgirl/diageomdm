/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Ubicacion;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class UbicacionBean extends TransaccionesNegocio<Ubicacion> implements UbicacionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Ubicacion crearUbicacion(Ubicacion u) throws DiageoNegocioException {
        try {
            u = super.crear(u);
            return u;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }
}
