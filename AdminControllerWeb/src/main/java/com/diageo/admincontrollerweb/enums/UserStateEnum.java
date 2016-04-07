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
public enum UserStateEnum {

    ACTIVE("1"), INACTIVE("0");

    private final String state;

    private UserStateEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

}
