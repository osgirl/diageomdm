/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.DwParameters;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface ParameterBeanLocal {

    public DwParameters createParameter(DwParameters entity);

    public DwParameters updateParameter(DwParameters entity);

    public List<DwParameters> findByKey(String key);

    public void deleteParameter(DwParameters entity);
    
}