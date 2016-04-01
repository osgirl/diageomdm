/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Persona;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class PersonaBean extends TransaccionesNegocio<Persona> implements PersonaBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Persona crearPersona(Persona per) throws DiageoNegocioException{
         try {
            per = super.crear(per);
            return per;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }
}
