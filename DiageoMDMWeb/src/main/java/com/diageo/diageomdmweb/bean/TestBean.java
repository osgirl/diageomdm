/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean;

import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.entidades.DbOutlets;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EDUARDO
 */
@Named(value = "testBean")
@ViewScoped
public class TestBean {

    @EJB
    private OutletBeanLocal outletBeanLocal;
    private String nombre;

    @PostConstruct
    public void init() {
        nombre = "pagina prueba";
    }

    public void descargar() {
        List<DbOutlets> list = outletBeanLocal.findAllOutlets();
        System.out.println(list.size());
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        ec.setResponseContentType("application/xml"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
        //ec.setResponseContentLength(10124); // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.
        ec.setResponseHeader("Content-Disposition", "attachment; filename=test.csv");
        try {
            OutputStream output = ec.getResponseOutputStream();
            try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(output))) {
                String texto = "hola mundo";
                OutputStream out = output;
                byte[] buffer = new byte[4096];
                for (int i = 0; i < list.size(); i++) {
                    DbOutlets outlet = list.get(i);
                    dos.writeBytes(outlet.getStatusMDM());
                    dos.writeBytes("|");
                    dos.writeBytes(outlet.getKiernanId());
                    dos.writeBytes("\n");
                }
                out.flush();
                fc.responseComplete();
            }
        } catch (IOException ex) {
            Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
