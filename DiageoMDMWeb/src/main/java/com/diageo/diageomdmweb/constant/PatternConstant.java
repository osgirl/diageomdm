/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.constant;

/**
 *
 * @author yovanoty126
 */
public class PatternConstant {

    /**
     * Patter to validate a email format
     */
    public static final String EMAIL_VALIDADOR = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    /**
     * Patter to validate a password
     */
    public static final String PASSWORD_RULES = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@%_\\-#<>$&\\(\\);,!¡?¿.=*])(?=\\S+$).{8,}$";
    /**
     * Number
     */
    public static final String NUMBER="\\d+";

}
