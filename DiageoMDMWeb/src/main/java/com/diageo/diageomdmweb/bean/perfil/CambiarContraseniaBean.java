/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.perfil;

import com.diageo.admincontrollerweb.beans.UsuarioBeanLocal;
import com.diageo.admincontrollerweb.enums.UsuarioIngresoEnum;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.bean.LoginBean;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author yovanoty126
 */
@Named(value = "cambiarContraseniaBean")
@ViewScoped
public class CambiarContraseniaBean extends DiageoRootBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(CambiarContraseniaBean.class.getName());
    @EJB
    private UsuarioBeanLocal usuarioBean;
    @Inject
    private LoginBean loginBean;
    private String contrasenaActual;
    private String contrseniaNueva;

    @PostConstruct
    public void init() {

    }

    public void cambiarContrasenia() {
        if (getContrasenaActual().equals(getContrseniaNueva())) {
            setContrasenaActual("");
            setContrseniaNueva("");
            showWarningMessage(capturarValor("cam_pass_actual_nueva_no_iguales"));
        } else {
            try {
                getLoginBean().getUsuario().setPrimerIngreso(UsuarioIngresoEnum.NO_PRIMER_INGRESO.getEstado());
                String pass = DigestUtils.md5Hex(getContrseniaNueva());
                getLoginBean().getUsuario().setContraseina(pass);
                usuarioBean.guardarUsuario(getLoginBean().getUsuario());
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

}
