/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.mail.templates;

import com.diageo.admincontrollerweb.beans.ParameterBeanLocal;
import com.diageo.admincontrollerweb.entities.DwParameters;
import com.diageo.admincontrollerweb.enums.ParameterKeyEnum;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 *
 * @author EDUARDO
 */
public class VelocityTemplate extends DiageoRootBean {

    public static String capturarValor(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = ResourceBundle.getBundle("com.diageo.diageomdmweb.properties.propertiesMDM", getLocale(context));
        return bundle.getString(key);
    }

    private static Properties loadProperties(String pathMail) {
        Properties props = new Properties();
        props.setProperty("resource.loader", "file");
        props.setProperty("webapp.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        //props.setProperty("file.resource.loader.path", capturarValor("template_mail_path"));
        props.setProperty("file.resource.loader.path", pathMail);
        return props;
    }

    public static String recoverPassword(String link, String pathMail) {
        Properties props = loadProperties(pathMail);
        VelocityEngine ve = new VelocityEngine();
        ve.init(props);
        Template t = ve.getTemplate("RecoverPassword.vm", "UTF-8");
        VelocityContext context = new VelocityContext();
        context.put("link", link);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }

    public static String userCreation(String link, String mail, String pass, String pathMail) {
        Properties props = loadProperties(pathMail);
        VelocityEngine ve = new VelocityEngine();
        ve.init(props);
        Template t = ve.getTemplate("UserCreation.vm", "UTF-8");
        VelocityContext context = new VelocityContext();
        context.put("link", link);
        context.put("mail", mail);
        context.put("password", pass);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }

    public static String notificationOutlet(String kiernan, String pathMail) {
        Properties props = loadProperties(pathMail);
        VelocityEngine ve = new VelocityEngine();
        ve.init(props);
        Template t = ve.getTemplate("NotificationOutlet.vm", "UTF-8");
        VelocityContext context = new VelocityContext();
        context.put("kiernan", kiernan);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }
    
    public static String notificationQuantity(String name,String rol,long quantity,String pathMail){
        Properties props = loadProperties(pathMail);
        VelocityEngine ve = new VelocityEngine();
        ve.init(props);
        Template t = ve.getTemplate("QuantityNotification.vm", "UTF-8");
        VelocityContext context = new VelocityContext();
        context.put("name", name);
        context.put("rol", rol);
        context.put("quantityOutlets", quantity);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }
    
    public static String notificationQuantityZero(String name,String rol,String pathMail){
        Properties props = loadProperties(pathMail);
        VelocityEngine ve = new VelocityEngine();
        ve.init(props);
        Template t = ve.getTemplate("QuantityNotificationZero.vm", "UTF-8");
        VelocityContext context = new VelocityContext();
        context.put("name", name);
        context.put("rol", rol);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }

}
