/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Sateoutlet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface StateOutletBeanLocal {

    public List<Sateoutlet> findAll();
    
}
