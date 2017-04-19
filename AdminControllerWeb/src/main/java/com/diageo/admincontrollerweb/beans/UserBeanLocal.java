/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.DwUsers;
import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import com.diageo.diageonegocio.beans.PermissionsegmentBeanLocal;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PostPersist;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class UserBeanLocal extends WebTransaction<DwUsers>  {

    private static final Logger LOG = Logger.getLogger(UserBeanLocal.class.getName());
    @EJB
    private PassContainerBeanLocal passContainerBeanLocal;
    @EJB
    private PermissionsegmentBeanLocal permissionsegmentBeanLocal;
    @EJB
    private ModuleBeanLocal moduleBeanLocal;

    public DwUsers updateUser(DwUsers user) throws ControllerWebException {
        try {
            user = (DwUsers) update(user);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new ControllerWebException(e.getMessage(), e);
        }
        return user;
    }

    public DwUsers updateUser(DwUsers user, List<DbPermissionSegments> per) throws ControllerWebException {
        try {
            user = (DwUsers) update(user);
            if (per != null) {
                for (DbPermissionSegments perTemp : per) {
                    perTemp.setUserId(user.getUserId());
                }
                permissionsegmentBeanLocal.createPermissionSegmentList(per);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new ControllerWebException(e.getMessage(), e);
        }
        return user;
    }
    
    
    public List<DbPermissionSegments> updateUser_Test(DwUsers user, List<DbPermissionSegments> per) throws ControllerWebException {
        List<DbPermissionSegments> list=new ArrayList<>();
        try {
            user = (DwUsers) update(user);
            if (per != null) {
                for (DbPermissionSegments perTemp : per) {
                    perTemp.setUserId(user.getUserId());
                }
                list=permissionsegmentBeanLocal.createPermissionSegmentList(per);
            }
        } catch (DiageoBusinessException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new ControllerWebException(e.getMessage(), e);
        }
        return list;
    }

    public DwUsers createUser(DwUsers user, List<DbPermissionSegments> per) throws ControllerWebException {
        try {
            user = (DwUsers) super.create(user);
            passContainerBeanLocal.createPassContainer(user, user.getPasswordUser());
            if (per != null) {
                for (DbPermissionSegments perTemp : per) {
                    perTemp.setUserId(user.getUserId());
                }
                permissionsegmentBeanLocal.createPermissionSegmentList(per);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new ControllerWebException(e.getMessage(), e);
        }
        return user;
    }
    
    public List<DbPermissionSegments> createUser_Test(DwUsers user, List<DbPermissionSegments> per) throws ControllerWebException {
        List<DbPermissionSegments> createPermissionSegmentList=new ArrayList<>();
        try {
            user = (DwUsers) super.create(user);
            passContainerBeanLocal.createPassContainer(user, user.getPasswordUser());
            if (per != null) {
                for (DbPermissionSegments perTemp : per) {
                    perTemp.setUserId(user.getUserId());
                }
                createPermissionSegmentList = permissionsegmentBeanLocal.createPermissionSegmentList(per);
            }
        } catch (DiageoBusinessException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new ControllerWebException(e.getMessage(), e);
        }
        return createPermissionSegmentList;
    }

    public DwUsers findEmail(String correo) throws ControllerWebException {
        if (correo == null || correo.isEmpty()) {
            return null;
        }
        List<DwUsers> listaUsuario = super.findByNamedQuery(DwUsers.class, DwUsers.FIND_MAIL, correo.toUpperCase());
        if (listaUsuario == null || listaUsuario.isEmpty()) {
            throw new ControllerWebException("La consulta no arroja resultados");
        }
        return listaUsuario.get(0);
    }

    public List<DwUsers> findAll() throws ControllerWebException {
        List<DwUsers> listaUsuarios = super.findAll(DwUsers.class);
        if (listaUsuarios == null || listaUsuarios.isEmpty()) {
            throw new ControllerWebException("La consulta no arroja resultados");
        }
        return listaUsuarios;
    }

    public DwUsers validateUserPassword(String user, String pass) {
        try {
            List<DwUsers> userLogin = findByNamedQuery(DwUsers.class, DwUsers.FIND_MAIL, user);
            if (userLogin == null || userLogin.isEmpty()) {
                return null;
            }
            DwUsers usu = userLogin.get(0);
            if (usu.getStateUser().equals(StateEnum.ACTIVE.getState())) {
                if (!pass.equals(usu.getPasswordUser())) {
                    try {
                        usu.setFailedLoginDate(Calendar.getInstance().getTime());
                        usu.setFailedAttempt((usu.getFailedAttempt() + 1));
                        if (usu.getFailedAttempt() >= usu.getProfileId().getAttempt()) {
                            usu.setStateUser(StateEnum.INACTIVE.getState());
                            usu.setFailedAttempt(0);
                            updateUser(usu);
                            return usu;
                        }
                        updateUser(usu);
                        return null;
                    } catch (ControllerWebException ex) {
                        LOG.log(Level.SEVERE, ex.getMessage());
                    }
                }
                usu.setLastSuccesfulLogin(usu.getSuccesfulLoginDate());
                usu.setSuccesfulLoginDate(Calendar.getInstance().getTime());
                usu.setFailedAttempt(0);
                updateUser(usu);
                return usu;
            }
            return usu;
        } catch (ControllerWebException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }
        return null;
    }

    public List<DwUsers> usersByProfile(Integer profileId) {
        List<DwUsers> list = super.findByNamedQuery(DwUsers.class, DwUsers.FIND_BY_PROFILE, profileId);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public DwUsers findById(Integer id) {
        return (DwUsers) super.findById(DwUsers.class, id);
    }

    public List<DwUsers> usersByProfileLevel(boolean level) {
        List<DwUsers> list = super.findByNamedQuery(DwUsers.class, DwUsers.FIND_BY_PROFILE_LEVEL, level);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }
}
