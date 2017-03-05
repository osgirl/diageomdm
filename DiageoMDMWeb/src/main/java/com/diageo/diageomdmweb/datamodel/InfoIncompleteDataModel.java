/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.datamodel;

import com.diageo.diageonegocio.beans.reports.InformeBlancosBeanLocal;
import com.diageo.diageonegocio.entidades.view.InformeBlancos;
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
public class InfoIncompleteDataModel extends LazyDataModel<InformeBlancos> {

    @EJB
    private final InformeBlancosBeanLocal informeBlancosBeanLocal;

    public InfoIncompleteDataModel(InformeBlancosBeanLocal informeBlancosBeanLocal) {
        this.informeBlancosBeanLocal = informeBlancosBeanLocal;
    }

    @Override
    public List<InformeBlancos> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<InformeBlancos> list = informeBlancosBeanLocal.findAll(first, pageSize, filters);
        long count = informeBlancosBeanLocal.findAllCount(first, pageSize, filters);
        setRowCount((int) count);
        return list;
    }

    @Override
    public void setRowCount(int rowCount) {
        super.setRowCount(rowCount); //To change body of generated methods, choose Tools | Templates.
    }

}
