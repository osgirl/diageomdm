/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.PassContainer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface PassContainerBeanLocal {

    public PassContainer createPassContainer(Integer userId, String password);

    public List<PassContainer> findPassContainerByUser(Integer id);

    public void deletePassContainer(Integer idUser, String password);

    public PassContainer findFirstRecordSaved(List<PassContainer> list);
    
}
