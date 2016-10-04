/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.diageo.diageomdmweb.bean.massiveload;

import java.nio.charset.StandardCharsets;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author EDUARDO
 */
@ManagedBean
@ViewScoped
public class MassiveLoadBean {

    /**
     * Creates a new instance of MassiveLoadBean
     */
    public MassiveLoadBean() {
    }
    
    
    public void load(FileUploadEvent file){
        UploadedFile uf=file.getFile();
        String str = new String(uf.getContents(), StandardCharsets.UTF_8);
        System.out.println("alskdjfñlaskdj");
    }
    
    
}
