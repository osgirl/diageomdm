/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Departamento;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class DepartamentoBean extends TransaccionesNegocio<Departamento> implements DepartamentoBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Departamento> consultarTodoDepartamento() {
        List<Departamento> lista = super.consultarTodo(Departamento.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    @Override
    public Departamento consultarId(Integer id) {
        return (Departamento) super.consultarPorId(Departamento.class, id);
    }
}
