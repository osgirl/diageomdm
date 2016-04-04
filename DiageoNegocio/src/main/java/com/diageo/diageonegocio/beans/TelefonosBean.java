/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Telefonos;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class TelefonosBean extends BusinessTransaction<Telefonos> implements TelefonosBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Telefonos> crearTelefonos(List<Telefonos> tel) {
        for (Telefonos telefonos : tel) {
            telefonos=super.create(telefonos);            
        }
        return tel;
    }
}
