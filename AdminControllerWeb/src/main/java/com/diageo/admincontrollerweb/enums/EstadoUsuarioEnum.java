/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.enums;

/**
 *
 * @author yovanoty126
 */
public enum EstadoUsuarioEnum {

    ACTIVO("1"), INACTIVO("0");

    private final String estado;

    private EstadoUsuarioEnum(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }

}
