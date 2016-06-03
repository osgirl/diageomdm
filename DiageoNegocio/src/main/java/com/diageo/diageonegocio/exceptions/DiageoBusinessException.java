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
public class DiageoBusinessException extends Exception{

    public DiageoBusinessException() {
        super();
    }
    
    public DiageoBusinessException(String msg){
        super(msg);
    }
    
    public DiageoBusinessException(Throwable t){
        super(t);
    }
    
    public DiageoBusinessException(Throwable t,String msg){
        super(msg, t);
    }
}
