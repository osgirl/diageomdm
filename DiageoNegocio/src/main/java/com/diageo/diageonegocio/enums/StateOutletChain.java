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

    OUTLET_TMC(1), PENDING_APPROVAL(2), APPROVED(3), REJECTED(4);

    private final int id;

    public static StateOutletChain valueOf(Integer id) {
        for (StateOutletChain value : StateOutletChain.values()) {
            if (id.equals(value.getId())) {
                return value;
            }
        }
        return null;
    }

    private StateOutletChain(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
