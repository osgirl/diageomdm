/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.Usuario;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import com.diageo.diageonegocio.entidades.Permissionsegment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface UserBeanLocal {

    public Usuario updateUser(Usuario user) throws ControllerWebException;

    public List<Usuario> findAll() throws ControllerWebException;

    public Usuario validateUserPassword(String user, String pass);

    public Usuario findEmail(String email) throws ControllerWebException;

    public Usuario createUser(Usuario user, List<Permissionsegment> per) throws ControllerWebException;

}
