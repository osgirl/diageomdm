/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.enums;

/**
 *
 * @author EDUARDO
 */
public enum StateOutletChain {

    OUTLET_TMC("1"), PENDING_APPROVAL("2"), APPROVED("3"), REJECTED("4"),
    ACTIVE("A"),INACTIVE("I"),LOCKED("B");

    private final String id;

    public static StateOutletChain value(String id) {
        for (StateOutletChain value : StateOutletChain.values()) {
            if (id.equals(value.getId())) {
                return value;
            }
        }
        return null;
    }

    private StateOutletChain(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

}
