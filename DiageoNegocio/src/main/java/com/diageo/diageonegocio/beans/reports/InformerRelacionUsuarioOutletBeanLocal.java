/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans.reports;

import com.diageo.diageonegocio.entidades.view.InformeRelacionUsuarioOutlet;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface InformerRelacionUsuarioOutletBeanLocal {

    public List<InformeRelacionUsuarioOutlet> findAll(int initial, int page, Map<String, Object> filters);
    
}
