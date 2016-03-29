/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.beans.ModuloBeanLocal;
import com.diageo.admincontrollerweb.beans.PerfilBeanLocal;
import com.diageo.admincontrollerweb.beans.TipoDocumentoBeanLocal;
import com.diageo.admincontrollerweb.beans.UsuarioBeanLocal;
import com.diageo.admincontrollerweb.entities.Modulo;
import com.diageo.admincontrollerweb.entities.Perfil;
import com.diageo.admincontrollerweb.entities.TipoDoc;
import com.diageo.admincontrollerweb.entities.Usuario;
import com.diageo.admincontrollerweb.enums.EstadoUsuarioEnum;
import com.diageo.admincontrollerweb.enums.UsuarioIngresoEnum;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.constantes.PatternConstantes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.validation.constraints.Pattern;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Bean encargado de la gestion de creación de usuario
 *
 * @author Jhovany Ardila
 */
@Named(value = "gestionarUsuarioCreacion")
@ViewScoped
public class GestionarUsuarioCreacion extends DiageoRootBean implements Serializable {

    /**
     * Logger del bean
     */
    private static final Logger LOG = Logger.getLogger(GestionarUsuarioCreacion.class.getName());
    /**
     * Ejb UsuarioBeanLocal
     */
    @EJB
    private UsuarioBeanLocal usuarioBean;
    /**
     * Ejb ModuloBeanLocal
     */
    @EJB
    private ModuloBeanLocal moduloBean;
    /**
     * Nombres
     */
    private String nombres;
    /**
     * Apellidos
     */
    private String apellidos;
    /**
     * Correo electrónico
     */
    @Pattern(regexp = PatternConstantes.EMAIL_VALIDADOR, message = "{correo.pattern}")
    private String correo;
    /**
     * Numero de documento
     */
    private String numDoc;
    /**
     * Tipo de documentl
     */
    private TipoDoc tipoDocumento;
    /**
     * Perfil que se le asignará al usuario creado
     */
    private Perfil perfil;
    /**
     * Bandeera que indica si el usuario puede o no después de ser creado
     * ingresar al sistema
     */
    private boolean activo;

    /**
     * Creates a new instance of GestionarUsuarioCreacion
     */
    public GestionarUsuarioCreacion() {
    }

    /**
     * Metodo que inicializa algunos atributos del bean
     */
    @PostConstruct
    public void init() {
        setPerfil(new Perfil());
        setTipoDocumento(new TipoDoc());
    }

    /**
     * Crea un usuario del sistema
     */
    public void crearUsuario() {
        if (!validarExisteciaCorreo()) {
            try {
                Usuario usu = new Usuario();
                usu.setNombres(getNombres());
                usu.setApellidos(getApellidos());
                usu.setCorreo(getCorreo());
                usu.setContraseina(DigestUtils.md5Hex(getTipoDocumento().getNombre().toLowerCase() + getNumDoc()));
                usu.setNumDoc(getNumDoc());
                usu.setFechaCreacion(getFechaActual());
                usu.setEstado(isActivo() ? EstadoUsuarioEnum.ACTIVO.getEstado() : EstadoUsuarioEnum.INACTIVO.getEstado());
                usu.setTipoDoc(getTipoDocumento().getIdtipoDoc());
                usu.setIdPerfil(getPerfil());
                usu.setIntentosFallidos(0);
                usu.setPrimerIngreso(UsuarioIngresoEnum.PRIMER_INGRESO.getEstado());
                usu.setModuloList(perfil.getModuloList());
                usuarioBean.crearUsuario(usu);
                for (Modulo mod : getPerfil().getModuloList()) {
                    mod.getUsuarioList().add(usu);
                    moduloBean.crearUsuarioModulo(mod);
                }
                showInfoMessage(capturarValor("usu_creado_exito"));
            } catch (ControllerWebException ex) {
                showErrorMessage(capturarValor("usu_creado_fallo"));
                LOG.log(Level.SEVERE, ex.getMessage());
            }
        }
    }

    /**
     * Valida por correo si existe o no el usuario
     *
     * @return true si el usuario ya existe con ese correo, false por lo
     * contrario
     */
    private boolean validarExisteciaCorreo() {
        try {
            usuarioBean.consultarCorreo(correo);
            showWarningMessage(capturarValor("usu_correo_existe"));
            return true;
        } catch (ControllerWebException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            return false;
        }
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
     * @return the numDoc
     */
    public String getNumDoc() {
        return numDoc;
    }

    /**
     * @param numDoc the numDoc to set
     */
    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    /**
     * @return the tipoDocumento
     */
    public TipoDoc getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(TipoDoc tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the perfil
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

}
