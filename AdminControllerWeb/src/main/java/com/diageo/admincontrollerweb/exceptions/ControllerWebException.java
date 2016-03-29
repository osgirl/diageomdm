/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.exceptions;

/**
 *
 * @author yovanoty126
 */
public class ControllerWebException extends Exception {

    public ControllerWebException() {
        super();
    }

    public ControllerWebException(String msj) {
        super(msj);
    }
    
    public ControllerWebException(Throwable t){
        super(t);
    }
    
    public ControllerWebException(String msj,Throwable t){
        super(msj, t);
    }
}
