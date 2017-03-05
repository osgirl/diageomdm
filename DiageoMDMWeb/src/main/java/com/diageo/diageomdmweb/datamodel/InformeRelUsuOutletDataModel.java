/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.datamodel;

import com.diageo.diageonegocio.beans.reports.InformerRelacionUsuarioOutletBeanLocal;
import com.diageo.diageonegocio.entidades.view.InformeRelacionUsuarioOutlet;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author EDUARDO
 */
public class InformeRelUsuOutletDataModel extends LazyDataModel<InformeRelacionUsuarioOutlet> {

    @EJB
    private final InformerRelacionUsuarioOutletBeanLocal usuarioOutletBeanLocal;   

    public InformeRelUsuOutletDataModel(InformerRelacionUsuarioOutletBeanLocal usuarioOutletBeanLocal) {
        this.usuarioOutletBeanLocal = usuarioOutletBeanLocal;
    }

    @Override
    public List<InformeRelacionUsuarioOutlet> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<InformeRelacionUsuarioOutlet> list = usuarioOutletBeanLocal.findAllDinamic(first, pageSize, filters);
        long findAllCount = usuarioOutletBeanLocal.findAllDinamicCount(first, pageSize, filters);
        setRowCount((int)findAllCount);
        return list;
    }
    
    @Override
    public void setRowCount(int rowCount) {
        super.setRowCount(rowCount); //To change body of generated methods, choose Tools | Templates.
    }

}
