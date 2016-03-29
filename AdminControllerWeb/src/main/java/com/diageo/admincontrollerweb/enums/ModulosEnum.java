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
public enum ModulosEnum {

    OUT("outlet"),
    C_OUT("crear outlet"),
    S_OUT("buscar outlet"),
    LOAD_OUT("cargar outlet"),
    MAGANER("administrador"),
    USU("usuario"),
    C_USU("crear usuario"),
    S_USU("buscar usuario"),
    CHAN("canal"),
    C_CHAN("crear canal"),
    S_CHAN("buscar canal"),
    SEG("segmento"),
    C_SEG("crear segmento"),
    S_SEG("buscar segmento"),
    SUB_CHAN("subcanal"),
    C_SUB_CHAN("crear subcanal"),
    S_SUB_CHAN("buscar subcanal"),
    SUB_SEG("subsegmento"),
    C_SUB_SEG("crear subsegmento"),
    S_SUB_SEG("buscar subsegmento"),
    BATT("battleground"),
    C_BATT("crear battleground"),
    S_BATT("buscar battleground");

    private final String descripcion;

    private ModulosEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

}
