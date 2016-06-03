/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.enums.FatherDistributorEnum;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "distributorCreate")
@ViewScoped
public class DistributorCreate extends DistributorSearch {

    /**
     * Creates a new instance of DistributorCreate
     */
    public DistributorCreate() {
    }

    public void create() {
        getSelectedDistributor().setIsFather(FatherDistributorEnum.FATHER.getIsPadre());
        super.update();
        setName("");
        setSelectedDistributor(new Db3party());
    }

}
