/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.perfil;

import com.diageo.admincontrollerweb.beans.PassContainerBeanLocal;
import com.diageo.admincontrollerweb.entities.PassContainer;
import com.diageo.admincontrollerweb.enums.UserEntryEnum;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageomdmweb.constant.PatternConstant;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import org.apache.commons.codec.digest.DigestUtils;
import com.diageo.admincontrollerweb.beans.UserBeanLocal;

/**
 *
 * @author yovanoty126
 */
@Named(value = "cambiarContraseniaBean")
@ViewScoped
public class CambiarContraseniaBean extends DiageoRootBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(CambiarContraseniaBean.class.getName());
    @EJB
    private UserBeanLocal usuarioBean;
    @EJB
    private PassContainerBeanLocal passContainerBeanLocal;
    @Inject
    private LoginBean loginBean;
    private String contrasenaActual;
    @Pattern(regexp = PatternConstant.PASSWORD_RULES, message = "{password.rules}")
    private String contrseniaNueva;
    private List<PassContainer> listPassContainer;

    @PostConstruct
    public void init() {
        setListPassContainer(passContainerBeanLocal.findPassContainerByUser(getLoginBean().getUsuario().getIdusuario()));
    }

    public void cambiarContrasenia() {
        if (getContrasenaActual().equals(getContrseniaNueva())) {
            setContrasenaActual("");
            setContrseniaNueva("");
            showWarningMessage(capturarValor("cam_pass_actual_nueva_no_iguales"));
        } else {
            try {
                if (!getListPassContainer().isEmpty()) {
                    for (PassContainer pc : getListPassContainer()) {
                        if (pc.getPassContainerPK().getPassword().equals(DigestUtils.md5Hex(getContrseniaNueva()))) {
                            showWarningMessage(capturarValor("cam_pass_used"));
                            return;
                        }
                    }
                }
                getLoginBean().getUsuario().setPrimerIngreso(UserEntryEnum.NOT_FIRST_ENTRY.getState());
                String pass = DigestUtils.md5Hex(getContrseniaNueva());
                getLoginBean().getUsuario().setContraseina(pass);
                usuarioBean.updateUser(getLoginBean().getUsuario());
                if (getListPassContainer().size() >= 12) {
                    PassContainer pc = passContainerBeanLocal.findFirstRecordSaved(getListPassContainer());
                    System.out.println(pc.getModificationDate());
                    passContainerBeanLocal.deletePassContainer(pc.getPassContainerPK().getIdUser(), pc.getPassContainerPK().getPassword());
                    getListPassContainer().remove(pc);
                }
                PassContainer pc = passContainerBeanLocal.createPassContainer(getLoginBean().getUsuario().getIdusuario(), DigestUtils.md5Hex(getContrseniaNueva()));
                getListPassContainer().add(pc);
                showInfoMessage(capturarValor("cam_pass_cambio_exitoso"));
            } catch (ControllerWebException ex) {
                LOG.log(Level.SEVERE, ex.getMessage());
                showErrorMessage(capturarValor("cam_pass_cambio_fallo"));
            }
        }
    }

    /**
     * Creates a new instance of CambiarContraseniaBean
     */
    public CambiarContraseniaBean() {
    }

    /**
     * @return the contrasenaActual
     */
    public String getContrasenaActual() {
        return contrasenaActual;
    }

    /**
     * @param contrasenaActual the contrasenaActual to set
     */
    public void setContrasenaActual(String contrasenaActual) {
        this.contrasenaActual = contrasenaActual;
    }

    /**
     * @return the contrseniaNueva
     */
    public String getContrseniaNueva() {
        return contrseniaNueva;
    }

    /**
     * @param contrseniaNueva the contrseniaNueva to set
     */
    public void setContrseniaNueva(String contrseniaNueva) {
        this.contrseniaNueva = contrseniaNueva;
    }

    /**
     * @return the loginBean
     */
    public LoginBean getLoginBean() {
        return loginBean;
    }

    /**
     * @param loginBean the loginBean to set
     */
    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    /**
     * @return the listPassContainer
     */
    public List<PassContainer> getListPassContainer() {
        return listPassContainer;
    }

    /**
     * @param listPassContainer the listPassContainer to set
     */
    public void setListPassContainer(List<PassContainer> listPassContainer) {
        this.listPassContainer = listPassContainer;
    }

}
