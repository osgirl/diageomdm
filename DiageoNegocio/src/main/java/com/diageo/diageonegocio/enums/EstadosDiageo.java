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
public enum EstadosDiageo {
    ACTIVO("1"), INACTIVO("0");

    private final String id;

    private EstadosDiageo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
