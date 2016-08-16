/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author yovanoty126
 */
@ManagedBean
public abstract class DiageoRootBean {

    protected final static String EMPTY_FIELD="";
    /**
     * One Hour represented in seconds
     */
    protected final static int HOUR_SECOND = 3600;

    /**
     * Constant cookie mail
     */
    protected final static String COOKIE_MAIL = "mail";
    /**
     * Constant cookie rememberme
     */
    protected final static String COOKIE_REMEMBER = "rememberme";
    /**
     * Constante con el parámetro user
     */
    public final static String USUARIO = "user";

    protected Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    public static String capturarValor(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = ResourceBundle.getBundle("com.diageo.diageomdmweb.label.Labels", getLocale(context));
        return bundle.getString(key);
    }

    /**
     * Metodo encargado de cambiar el idioma de las etiquetas de la aplicacion
     * con el valor se captura la llave, y esta se utiliza para traducir la
     * etiqueta al idioma seleccionado
     *
     * @param valor el texto traducido
     * @return llave
     */
    protected static String capturarLlave(String valor) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = ResourceBundle.getBundle("com.diageo.diageomdmweb.label.Labels", getLocale(context));
        Set<String> setLlave = bundle.keySet();
        for (String llave : setLlave) {
            String s = bundle.getString(llave);
            if (s.equals(valor)) {
                return llave;
            }
        }
        return "";
    }

    /**
     * Metodo encargado de cambiar el idioma de las etiquetas de la aplicacion
     * con el valor se captura la llave, y esta se utiliza para traducir la
     * etiqueta al idioma seleccionado
     *
     * @param valor el texto traducido
     * @param language
     * @return llave
     */
    protected static String capturarLlave(String valor, String language) {
        ResourceBundle bundle = ResourceBundle.getBundle("com.diageo.diageomdmweb.label.Labels", new Locale(language));
        Set<String> setLlave = bundle.keySet();
        for (String llave : setLlave) {
            String s = bundle.getString(llave);
            if (s.equals(valor)) {
                return llave;
            }
        }
        return "";
    }

    protected static Locale getLocale(FacesContext context) {
        Locale locale = null;
        UIViewRoot viewRoot = context.getViewRoot();
        if (viewRoot != null) {
            locale = viewRoot.getLocale();
        } //end of if (viewRoot != null)
        if (locale == null) {
            locale = Locale.getDefault();
        } //end of if (locale == null)
        return locale;
    } //end of getLocale()

    public void showErrorMessage(String detail) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, capturarValor("error"), detail));
    }

    public void showInfoMessage(String detail) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, capturarValor("info"), detail));
    }

    public void showWarningMessage(String detail) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, capturarValor("advertencia"), detail));
    }

    public String formatoFechaIngreso(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-y k:m:s");
            return sdf.format(fecha);
        }
        return null;
    }

}
