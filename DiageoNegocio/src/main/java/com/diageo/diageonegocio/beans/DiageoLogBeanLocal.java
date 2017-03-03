/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DiageoLog;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface DiageoLogBeanLocal {

    public void createLog(DiageoLog log);
    
}
