/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.reports;

import com.diageo.admincontrollerweb.beans.ParameterBeanLocal;
import com.diageo.admincontrollerweb.entities.DwParameters;
import com.diageo.admincontrollerweb.enums.ParameterKeyEnum;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.bean.dto.reports.DuplicatesDto;
import com.diageo.diageomdmweb.jdbc.ConecctionJDBC;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "duplicatesBean")
@ViewScoped
public class DuplicatesBean extends DiageoRootBean {

    @EJB
    protected ParameterBeanLocal parameterBeanLocal;
    private List<DuplicatesDto> duplicatesList;
    private List<SelectItem> itemList;
    private String itemSelected;
    private String ipDatabase;
    private String userDatabase;
    private String passDatabase;

    @PostConstruct
    public void init() {
        duplicatesList = new ArrayList<>();
        itemList = new ArrayList<>();
        itemList.add(new SelectItem("NIT", "NIT"));
        itemList.add(new SelectItem("BUSINESS_NAME", "BUSINESS_NAME"));
        itemList.add(new SelectItem("OUTLET_NAME", "OUTLET_NAME"));
        itemList.add(new SelectItem("NUMBER_PDV", "NUMBER_PDV"));
        itemList.add(new SelectItem("ADDRESS", "ADDRESS"));
        ipDatabase = parameterBeanLocal.findByKey(ParameterKeyEnum.DATABASE_IP.name()).get(0).getParameterValue();
        userDatabase = parameterBeanLocal.findByKey(ParameterKeyEnum.USER_DATABASE.name()).get(0).getParameterValue();
        passDatabase = parameterBeanLocal.findByKey(ParameterKeyEnum.PASS_DATABASE.name()).get(0).getParameterValue();
    }

    public void loadTable() {
        Connection con = ConecctionJDBC.conexionSQLServer(ipDatabase, userDatabase, passDatabase);
        duplicatesList=ConecctionJDBC.callStoreProcedureDuplicatesReport(con, itemSelected);
    }

    public String getItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(String itemSelected) {
        this.itemSelected = itemSelected;
    }

    public List<DuplicatesDto> getDuplicatesList() {
        return duplicatesList;
    }

    public void setDuplicatesList(List<DuplicatesDto> duplicatesList) {
        this.duplicatesList = duplicatesList;
    }

    public List<SelectItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SelectItem> itemList) {
        this.itemList = itemList;
    }

}
