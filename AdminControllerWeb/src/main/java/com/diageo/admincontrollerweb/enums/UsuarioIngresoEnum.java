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
public enum UsuarioIngresoEnum {

    PRIMER_INGRESO("1"), NO_PRIMER_INGRESO("0");

    private final String estado;

    private UsuarioIngresoEnum(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }

}
