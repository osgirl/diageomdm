/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.Perfil;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class PerfilBean extends WebTransaction<Perfil> implements PerfilBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Perfil> capturarTodosPerfiles() {
        return consultarTodo(Perfil.class);
    }

    @Override
    public Perfil consultarId(Integer id) {
        return (Perfil) consultarPorId(Perfil.class, id);
    }
 
}
