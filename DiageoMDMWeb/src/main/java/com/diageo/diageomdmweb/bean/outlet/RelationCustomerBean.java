/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.outlet;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "relationCustomerBean")
@ViewScoped
public class RelationCustomerBean extends DiageoRootBean implements Serializable {

    private static final String OUTLET = "O";
    private static final String CHAIN = "CHA";
    private String codeOutlet;
    private String typeOutlet;

    /**
     * Creates a new instance of RelationCustomerBean
     */
    public RelationCustomerBean() {
    }

    public String getCodeOutlet() {
        return codeOutlet;
    }

    public void setCodeOutlet(String codeOutlet) {
        this.codeOutlet = codeOutlet;
    }

    public String getTypeOutlet() {
        return typeOutlet;
    }

    public void setTypeOutlet(String typeOutlet) {
        this.typeOutlet = typeOutlet;
    }

}
