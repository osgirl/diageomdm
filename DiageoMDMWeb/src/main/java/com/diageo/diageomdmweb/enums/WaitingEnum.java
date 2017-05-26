/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.enums;

/**
 *
 * @author EDUARDO
 */
public enum WaitingEnum {
    ACTIVATION("Waiting Activation"), REMOVAL("Waiting Removal");

    private final String state;

    private WaitingEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}
