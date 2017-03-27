/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.datamodel;

import com.diageo.diageonegocio.beans.reports.InformeVendedoreBean;
import com.diageo.diageonegocio.entidades.view.InformeBlancos;
import com.diageo.diageonegocio.entidades.view.InformeVendedores;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author EDUARDO
 */
public class InfoSellerDataModel extends LazyDataModel<InformeVendedores> {

    @EJB
    private final InformeVendedoreBean informeVendedoreBean;;

    public InfoSellerDataModel(InformeVendedoreBean informeVendedoreBean) {
        this.informeVendedoreBean = informeVendedoreBean;
    }

    @Override
    public List<InformeVendedores> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<InformeVendedores> list = informeVendedoreBean.findSeller(first, pageSize, filters);
        long count = informeVendedoreBean.findSellerCount(filters);
        setRowCount((int) count);
        return list;
    }

    @Override
    public void setRowCount(int rowCount) {
        super.setRowCount(rowCount); //To change body of generated methods, choose Tools | Templates.
    }

}
