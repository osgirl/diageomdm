/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DiageoLog;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class DiageoLogBeanLocal extends BusinessTransaction<DiageoLog> {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void createLog(DiageoLog log) {
        super.create(log);
    }
}
