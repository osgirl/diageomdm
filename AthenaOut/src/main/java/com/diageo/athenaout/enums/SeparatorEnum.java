/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.athenaout.enums;

/**
 *
 * @author EDUARDO
 */
public enum SeparatorEnum {

    SEPARATOR("|"),ENTER("\n");

    private final String separator;

    private SeparatorEnum(String separator) {
        this.separator = separator;
    }

    public String getSeparator() {
        return this.separator;
    }

}
