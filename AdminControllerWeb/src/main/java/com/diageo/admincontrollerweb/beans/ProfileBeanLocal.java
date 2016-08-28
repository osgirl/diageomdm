/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.DwProfiles;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface ProfileBeanLocal {

    public List<DwProfiles> findAll();

    public DwProfiles findById(Integer id);

    public List<DwProfiles> findBySystem();
    
}
