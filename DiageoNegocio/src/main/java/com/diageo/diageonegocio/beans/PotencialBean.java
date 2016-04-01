/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Potencial;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class PotencialBean extends TransaccionesNegocio<Potencial> implements PotencialBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Potencial> constultarTodosPotenciales() {
        List<Potencial> lista = super.consultarTodo(Potencial.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    @Override
    public Potencial consultarId(Integer id) {
        return (Potencial) super.consultarPorId(Potencial.class, id);
    }
}
