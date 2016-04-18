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
public enum StateEnum {

    ACTIVE("1"), INACTIVE("0");

    private final String state;

    private StateEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

}
