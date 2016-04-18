/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.Usuario;
import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class UserBean extends WebTransaction<Usuario> implements UserBeanLocal {

    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());
    @EJB
    private PassContainerBeanLocal passContainerBeanLocal;
    @EJB
    private PermissionSegmentBeanLocal permissionSegmentBeanLocal;

    @Override
    public Usuario updateUser(Usuario user) throws ControllerWebException {
        try {
            user = (Usuario) update(user);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new ControllerWebException(e.getMessage(), e);
        }
        return user;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Usuario createUser(Usuario user) throws ControllerWebException {
        try {
            user = super.create(user);
            passContainerBeanLocal.createPassContainer(user.getIdusuario(), user.getContraseina());
            permissionSegmentBeanLocal.createPermissionSegment(user.getPermissionSegment(),user.getIdusuario());
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new ControllerWebException(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public Usuario findEmail(String correo) throws ControllerWebException {
        List<Usuario> listaUsuario = super.findByNamedQuery(Usuario.class, Usuario.FIND_CORREO, correo);
        if (listaUsuario == null || listaUsuario.isEmpty()) {
            throw new ControllerWebException("La consulta no arroja resultados");
        }
        return listaUsuario.get(0);
    }

    @Override
    public List<Usuario> findAll() throws ControllerWebException {
        List<Usuario> listaUsuarios = super.findAll(Usuario.class);
        if (listaUsuarios == null || listaUsuarios.isEmpty()) {
            throw new ControllerWebException("La consulta no arroja resultados");
        }
        return listaUsuarios;
    }

    @Override
    public Usuario validateUserPassword(String user, String pass) {
        try {
            List<Usuario> userLogin = findByNamedQuery(Usuario.class, Usuario.FIND_CORREO, user);
            if (userLogin == null || userLogin.isEmpty()) {
                return null;
            }
            Usuario usu = userLogin.get(0);
            if (usu.getEstado().equals(StateEnum.ACTIVE.getState())) {
                if (!pass.equals(usu.getContraseina())) {
                    try {
                        usu.setIngresoFallido(Calendar.getInstance().getTime());
                        usu.setIntentosFallidos((usu.getIntentosFallidos() + 1));
                        if (usu.getIntentosFallidos() >= usu.getIdPerfil().getIntentos()) {
                            usu.setEstado(StateEnum.INACTIVE.getState());
                            usu.setIntentosFallidos(0);
                            updateUser(usu);
                            return usu;
                        }
                        updateUser(usu);
                        return null;
                    } catch (ControllerWebException ex) {
                        LOG.log(Level.SEVERE, ex.getMessage());
                    }
                }
                usu.setUltimoIngresoExitoso(usu.getIngresoExitoso());
                usu.setIngresoExitoso(Calendar.getInstance().getTime());
                usu.setIntentosFallidos(0);
                updateUser(usu);
                return usu;
            }
            return usu;
        } catch (ControllerWebException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }
        return null;
    }
}
