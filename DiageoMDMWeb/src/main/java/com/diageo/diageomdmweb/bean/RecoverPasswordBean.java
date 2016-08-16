/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean;

import com.diageo.admincontrollerweb.beans.TemporalLinkBeanLocal;
import com.diageo.admincontrollerweb.entities.DwTemporalLink;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author EDUARDO
 */
@Named(value = "recoverPasswordBean")
@ViewScoped
public class RecoverPasswordBean extends DiageoRootBean implements Serializable {

    @EJB
    private TemporalLinkBeanLocal temporalLinkBeanLocal;
    private String msg;

    /**
     * Creates a new instance of RecoverPasswordBean
     */
    public RecoverPasswordBean() {
    }

    @PostConstruct
    public void init() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String token = req.getParameter("token");
        if (token != null && !token.isEmpty()) {
            DwTemporalLink tl = temporalLinkBeanLocal.findByToken(token);
            if (tl == null) {
                msg = "Ingreso invalido";
            } else {
                msg = "Ingreso valido :)";
            }
        } else {
            msg = "Ingreso invalido";
        }
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
