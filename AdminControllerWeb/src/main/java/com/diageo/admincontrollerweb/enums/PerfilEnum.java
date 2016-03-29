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
public enum PerfilEnum {
    ADMINISTRADOR(1, "admin"), COORDINADOR(2, "coordinador");

    private final Integer id;
    private final String nombre;

    private PerfilEnum(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public static PerfilEnum valueOf(Integer id) {
        for (PerfilEnum p : PerfilEnum.values()) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}
