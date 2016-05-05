/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.DwUsers;
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

    public DwUsers updateUser(DwUsers user) throws ControllerWebException;

    public List<DwUsers> findAll() throws ControllerWebException;

    public DwUsers validateUserPassword(String user, String pass);

    public DwUsers findEmail(String email) throws ControllerWebException;

    public DwUsers createUser(DwUsers user, List<Permissionsegment> per) throws ControllerWebException;

}
