/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.exceptions;

/**
 *
 * @author yovanoty126
 */
public class DiageoNegocioException extends Exception{

    public DiageoNegocioException() {
        super();
    }
    
    public DiageoNegocioException(String msg){
        super(msg);
    }
    
    public DiageoNegocioException(Throwable t){
        super(t);
    }
    
    public DiageoNegocioException(Throwable t,String msg){
        super(msg, t);
    }
}
