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
public enum UserEntryEnum {

    FIRST_ENTRY("1"), NOT_FIRST_ENTRY("0");

    private final String state;

    private UserEntryEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

}
