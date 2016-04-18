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
    TMC(3,"tmc/kam"),COMMERCIAL_LEADER(4,"commercial leader"),CP_A(5,"CP&A"),
    KAM(6,"KAM");

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
