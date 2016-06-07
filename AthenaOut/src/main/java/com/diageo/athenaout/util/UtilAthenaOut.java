/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.athenaout.util;

/**
 *
 * @author EDUARDO
 */
public class UtilAthenaOut {

    /**
     * Transform a chain from 'nombre apellido' to 'NOMBRE.APELLIDO'
     *
     * @param name
     * @return
     */
    public static String castName(String name) {
        String str = name.toUpperCase().trim();
        str = str.replaceAll(" ", ".");
        return str;
    }

}
