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
public enum ProfileEnum {

    ADMINISTRATOR(1, "admin"), DATA_STEWARD(2, "data steward"),
    TMC_DISTRIBUIDORES(3, "tmc"), COMMERCIAL_MANAGER(4, "commercial manager"),
    KAM(5, "KAM"), CP_A_DISTRIBUIDORES(6, "CP&A DISTRIBUIDORES"), NAM(8, "NAM"),TMC_CADENAS(9,"TMC CADENAS"),
    KAM_CADENAS(10,"KAM CADENAS"),CP_A_CADENAS(11, "CP&A CADENAS");

    private final Integer id;
    private final String name;

    private ProfileEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ProfileEnum valueOf(Integer id) {
        for (ProfileEnum p : ProfileEnum.values()) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
