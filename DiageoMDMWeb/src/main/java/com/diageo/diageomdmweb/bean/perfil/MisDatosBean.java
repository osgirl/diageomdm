/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.perfil;

import com.diageo.admincontrollerweb.beans.UsuarioBeanLocal;
import com.diageo.admincontrollerweb.entities.Usuario;
import com.diageo.admincontrollerweb.enums.DocumentTypeEnum;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.bean.LoginBean;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author yovanoty126
 */
@Named(value = "misDatosBean")
@ViewScoped
public class MisDatosBean extends DiageoRootBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(MisDatosBean.class.getName());
    @Inject
    private LoginBean loginBean;
    @EJB
    private UsuarioBeanLocal usuarioBeanLocal;
    private String nombres;
    private String apellidos;
    private String correo;
    private String numDocumento;
    private String perfil;
    private Integer tipoDoc;

    /**
     * Creates a new instance of MisDatosBean
     */
    public MisDatosBean() {
    }

    @PostConstruct
    public void init() {
        setApellidos(getLoginBean().getUsuario().getApellidos());
        setNombres(getLoginBean().getUsuario().getNombres());
        setCorreo(getLoginBean().getUsuario().getCorreo());
        setNumDocumento(getLoginBean().getUsuario().getNumDoc());
        setTipoDoc(getLoginBean().getUsuario().getTipoDoc());
        setPerfil(getLoginBean().getUsuario().getIdPerfil().getNombre());
    }

    public String getTipoDocumento() {
        return DocumentTypeEnum.valueOf(getTipoDoc()).name();
    }

    public void modificarDatos() {
        try {
            Usuario usuario = getLoginBean().getUsuario();
            usuario.setNombres(getNombres());
            usuario.setApellidos(getApellidos());
            usuario=usuarioBeanLocal.updateUser(usuario);
            getLoginBean().setUsuario(usuario);
            super.showInfoMessage(capturarValor("usu_mis_datos"));
        } catch (ControllerWebException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            super.showErrorMessage(capturarValor("usu_erro_mis_datos"));
        }
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
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the numDocumento
     */
    public String getNumDocumento() {
        return numDocumento;
    }

    /**
     * @param numDocumento the numDocumento to set
     */
    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    /**
     * @return the tipoDoc
     */
    public Integer getTipoDoc() {
        return tipoDoc;
    }

    /**
     * @param tipoDoc the tipoDoc to set
     */
    public void setTipoDoc(Integer tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    /**
     * @return the perfil
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

}
