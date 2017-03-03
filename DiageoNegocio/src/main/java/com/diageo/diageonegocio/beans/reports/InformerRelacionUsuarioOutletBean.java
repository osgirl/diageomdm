/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans.reports;

import com.diageo.diageonegocio.beans.BusinessTransaction;
import com.diageo.diageonegocio.entidades.view.InformeRelacionUsuarioOutlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class InformerRelacionUsuarioOutletBean extends BusinessTransaction<InformeRelacionUsuarioOutlet> implements InformerRelacionUsuarioOutletBeanLocal {

    @Override
    public List<InformeRelacionUsuarioOutlet> findAll(int initial, int page, Map<String, Object> filters) {
        Query sql = getEntityManager().createNamedQuery(InformeRelacionUsuarioOutlet.FIND_ALL);
        sql.setFirstResult(initial);
        sql.setMaxResults(page);
        List<InformeRelacionUsuarioOutlet> list = sql.getResultList();
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }
}
