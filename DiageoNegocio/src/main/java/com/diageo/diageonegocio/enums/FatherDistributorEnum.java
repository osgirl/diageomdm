/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.enums;

/**
 *
 * @author EDUARDO
 */
public enum FatherDistributorEnum {

    FATHER("1"), NOT_FATHER("0");

    private final String isPadre;

    private FatherDistributorEnum(String isPadre) {
        this.isPadre = isPadre;
    }

    public String getIsFather() {
        return isPadre;
    }

}
