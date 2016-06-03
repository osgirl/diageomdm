/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.enums;

/**
 *
 * @author yovanoty126
 */
public enum StateDiageo {
    ACTIVO("1"), INACTIVO("0");

    private final String id;

    private StateDiageo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
