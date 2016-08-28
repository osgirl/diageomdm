/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean;

import com.diageo.admincontrollerweb.beans.PassContainerBeanLocal;
import com.diageo.admincontrollerweb.beans.TemporalLinkBeanLocal;
import com.diageo.admincontrollerweb.beans.UserBeanLocal;
import com.diageo.admincontrollerweb.entities.DwPasscontainers;
import com.diageo.admincontrollerweb.entities.DwTemporalLink;
import com.diageo.admincontrollerweb.entities.DwUsers;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import com.diageo.diageomdmweb.constant.PatternConstant;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author EDUARDO
 */
@Named(value = "recoverPasswordBean")
@ViewScoped
public class RecoverPasswordBean extends DiageoRootBean implements Serializable {

    @EJB
    private TemporalLinkBeanLocal temporalLinkBeanLocal;
    @EJB
    private UserBeanLocal usuarioLocal;
    @EJB
    private PassContainerBeanLocal passContainerBeanLocal;
    private List<DwPasscontainers> listPassContainer;
    private boolean renderForm;
    private String message;
    @Pattern(regexp = PatternConstant.PASSWORD_RULES, message = "{password.rules}")
    private String password;
    private DwUsers user;

    /**
     * Creates a new instance of RecoverPasswordBean
     */
    public RecoverPasswordBean() {
    }

    @PostConstruct
    public void init() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String token = req.getParameter(TOKEN);
        if (token != null && !token.isEmpty()) {
            DwTemporalLink tl = temporalLinkBeanLocal.findByToken(token);
            if (tl == null) {
                setRenderForm(Boolean.FALSE);
                setMessage(capturarValor("invalid_entry"));
            } else {
                int ex = tl.getExpiration().compareTo(getCurrentDate());
                if (ex < 0) {
                    setRenderForm(Boolean.FALSE);
                    setMessage(capturarValor("invalid_link"));
                } else {
                    try {
                        user = usuarioLocal.findEmail(tl.getEmail());
                        listPassContainer = passContainerBeanLocal.findPassContainerByUser(user.getUserId());
                        setRenderForm(Boolean.TRUE);
                    } catch (ControllerWebException ex1) {
                        Logger.getLogger(RecoverPasswordBean.class.getName()).log(Level.SEVERE, null, ex1);
                        setRenderForm(Boolean.FALSE);
                        setMessage(capturarValor("invalid_entry"));
                    }
                }
            }
        } else {
            setRenderForm(Boolean.FALSE);
            setMessage(capturarValor("invalid_entry"));
        }
    }

    public void changePassword() {
        try {
            String pass = DigestUtils.md5Hex(getPassword());
            if (!listPassContainer.isEmpty()) {
                for (DwPasscontainers pc : listPassContainer) {
                    if (pc.getPasswordUser().equals(pass)) {
                        showWarningMessage(capturarValor("cam_pass_used"));
                        return;
                    }
                }
            }
            user.setPasswordUser(pass);
            usuarioLocal.updateUser(user);
            showInfoMessage(capturarValor("cam_pass_msg_update_success"));
        } catch (ControllerWebException ex) {
            Logger.getLogger(RecoverPasswordBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public String backLogin() {
        return "/login?faces-redirect=true";
    }

    /**
     * @return the renderForm
     */
    public boolean isRenderForm() {
        return renderForm;
    }

    /**
     * @param renderForm the renderForm to set
     */
    public void setRenderForm(boolean renderForm) {
        this.renderForm = renderForm;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
