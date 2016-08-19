/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.mail.templates;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 *
 * @author EDUARDO
 */
public class VelocityTemplate {

    public static String recoverPassword(String link) {
        Properties props = new Properties();
//        props.setProperty("resource.loader", "webapp");
//        props.setProperty("webapp.resource.loader.class", "org.apache.velocity.tools.view.WebappResourceLoader");
//        
//        props.setProperty("webapp.resource.loader.path", "/WEB-INF/mailtemplates/");
        props.setProperty("resource.loader", "file");
        props.setProperty("webapp.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        props.setProperty("file.resource.loader.path", "C:\\Users\\EDUARDO\\Documents\\JHOVANY\\LATINOBI\\DIAGEO\\SOFTWARE\\diageomdm\\DiageoMDMWeb\\src\\main\\java\\com\\diageo\\diageomdmweb\\mail\\templates");
        VelocityEngine ve = new VelocityEngine();
//        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//        ve.setApplicationAttribute("javax.servlet.ServletContext", servletContext);
        ve.init(props);
        Template t = ve.getTemplate("RecoverPassword.vm","UTF-8");
        VelocityContext context = new VelocityContext();
        context.put("link", link);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }
}
