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
public enum DocumentTypeEnum {

    CC(1), NIT(2), CE(3), PASSPORT(4);

    private final int id;

    private DocumentTypeEnum(int id) {
        this.id = id;
    }

    public static DocumentTypeEnum valueOf(int id) {
        for (DocumentTypeEnum object : DocumentTypeEnum.values()) {
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
