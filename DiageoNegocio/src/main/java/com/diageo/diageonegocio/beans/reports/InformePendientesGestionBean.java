/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans.reports;

import com.diageo.diageonegocio.beans.BusinessTransaction;
import com.diageo.diageonegocio.entidades.view.InformePendientesGestion;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class InformePendientesGestionBean extends BusinessTransaction<InformePendientesGestion> implements InformePendientesGestionBeanLocal {

    @Override
    public List<InformePendientesGestion> findAll(){
        return super.searchAll(InformePendientesGestion.class);
    }
}
