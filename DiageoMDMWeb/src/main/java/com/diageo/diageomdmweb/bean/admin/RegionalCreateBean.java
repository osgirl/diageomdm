/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "regionalCreateBean")
@ViewScoped
public class RegionalCreateBean extends RegionalSearchBean {

    /**
     * Creates a new instance of RegionalCreateBean
     */
    public RegionalCreateBean() {
    }

    private void cleanUpFields() {
        setRegionalName("");
        setAthenaCode("");
    }

    @Override
    public void updateRegional() {
        super.updateRegional();
        cleanUpFields();
    }

}
