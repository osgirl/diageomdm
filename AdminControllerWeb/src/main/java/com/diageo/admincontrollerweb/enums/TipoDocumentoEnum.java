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
public enum TipoDocumentoEnum {

    CC(1), NIT(2);

    private final int id;

    private TipoDocumentoEnum(int id) {
        this.id = id;
    }

    public static TipoDocumentoEnum valueOf(int id) {
        for (TipoDocumentoEnum object : TipoDocumentoEnum.values()) {
            if (object.getId() == id) {
                return object;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

}
