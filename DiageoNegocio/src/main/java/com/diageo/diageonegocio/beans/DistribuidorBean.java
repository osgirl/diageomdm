/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Distribuidor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class DistribuidorBean extends TransaccionesNegocio<Distribuidor> implements DistribuidorBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Distribuidor consultarId(Integer id) {
        return (Distribuidor) super.consultarPorId(Distribuidor.class, id);
    }

    @Override
    public List<Distribuidor> constularTodosDistribuidores() {
        List<Distribuidor> lista = super.consultarTodo(Distribuidor.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

}
