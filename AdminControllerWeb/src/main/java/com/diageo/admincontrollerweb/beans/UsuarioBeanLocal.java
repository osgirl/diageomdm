/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.Usuario;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface UsuarioBeanLocal {

    public Usuario guardarUsuario(Usuario user) throws ControllerWebException;

    public List<Usuario> consultarTodo() throws ControllerWebException;

    public Usuario validarUsuarioContrasena(String user, String pass);

    public Usuario consultarCorreo(String correo) throws ControllerWebException;

    public Usuario crearUsuario(Usuario user) throws ControllerWebException;
    
}
