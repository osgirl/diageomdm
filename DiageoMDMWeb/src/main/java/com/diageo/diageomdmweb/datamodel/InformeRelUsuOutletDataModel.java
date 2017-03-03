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
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author EDUARDO
 */
public class InformeRelUsuOutletDataModel extends LazyDataModel<InformeRelacionUsuarioOutlet> {

    @EJB
    private InformerRelacionUsuarioOutletBeanLocal usuarioOutletBeanLocal;

    public InformeRelUsuOutletDataModel() {
    }

    public InformeRelUsuOutletDataModel(InformerRelacionUsuarioOutletBeanLocal usuarioOutletBeanLocal) {
        this.usuarioOutletBeanLocal = usuarioOutletBeanLocal;
    }

    @Override
    public List<InformeRelacionUsuarioOutlet> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<InformeRelacionUsuarioOutlet> list = usuarioOutletBeanLocal.findAll(first, pageSize, filters);
        return list;
    }

    @Override
    public List<InformeRelacionUsuarioOutlet> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        return super.load(first, pageSize, multiSortMeta, filters); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRowCount(int rowCount) {
        super.setRowCount(rowCount); //To change body of generated methods, choose Tools | Templates.
    }

}
