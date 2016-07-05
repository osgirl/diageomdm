/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.enums.FatherDistributorEnum;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "distributorCreate")
@ViewScoped
public class DistributorCreate extends DistributorSearch implements Serializable{

    /**
     * Creates a new instance of DistributorCreate
     */
    public DistributorCreate() {
    }

    private void cleanUpFields() {
        setName("");
        setNameAdmin("");
        setAthenaCode("");
        setIsFather(Boolean.FALSE);
    }

    public void create() {
        super.update();
        setSelectedDistributor(new Db3party());
        cleanUpFields();
    }

}
