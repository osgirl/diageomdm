/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.athenaout.timer;

import javax.ejb.Local;
import javax.ejb.Timer;

/**
 *
 * @author EDUARDO
 */
@Local
public interface IBuildFIlesTimer {
    
    public void execute(Timer t) ;
    
}
