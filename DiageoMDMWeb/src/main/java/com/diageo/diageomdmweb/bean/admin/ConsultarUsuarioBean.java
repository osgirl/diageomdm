/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.beans.UsuarioBeanLocal;
import com.diageo.admincontrollerweb.entities.Perfil;
import com.diageo.admincontrollerweb.entities.TipoDoc;
import com.diageo.admincontrollerweb.entities.Usuario;
import com.diageo.admincontrollerweb.enums.UserStateEnum;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author yovanoty126
 */
@Named(value = "consultarUsuarioBean")
@ViewScoped
public class ConsultarUsuarioBean extends DiageoRootBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(ConsultarUsuarioBean.class.getName());
    @EJB
    private UsuarioBeanLocal usuarioLocal;
    private List<Usuario> listaUsuariosSistema;
    private boolean verDetalle;
    private Usuario usuarioSeleccionado;
    private Perfil perfil;
    private TipoDoc tipoDocumento;
    private boolean usuarioActivo;

    /**
     * Creates a new instance of ConsultarUsuarioBean
     */
    public ConsultarUsuarioBean() {
    }

    @PostConstruct
    public void init() {
        setVerDetalle(Boolean.TRUE);
        setPerfil(new Perfil());
        setTipoDocumento(new TipoDoc());
        consultarUsuariosSistema();
    }

    public void consultarUsuariosSistema() {
        try {
            setListaUsuariosSistema(usuarioLocal.consultarTodo());
        } catch (ControllerWebException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }

    }

    public void verDetalleUsuario(Usuario usu) {
        setVerDetalle(Boolean.FALSE);
        setUsuarioSeleccionado(usu);
        setPerfil(usu.getIdPerfil());
        setTipoDocumento(new TipoDoc(usu.getTipoDoc()));
        setUsuarioActivo(usu.getEstado().equals(UserStateEnum.ACTIVE.getState()));
    }

    public void guardarCambiosUsuario() {
        try {
            getUsuarioSeleccionado().setIdPerfil(getPerfil());
            getUsuarioSeleccionado().setTipoDoc(getTipoDocumento().getIdtipoDoc());
            getUsuarioSeleccionado().setEstado(isUsuarioActivo() ? UserStateEnum.ACTIVE.getState() : UserStateEnum.INACTIVE.getState());
            getUsuarioSeleccionado().setFechaModificaicon(super.getFechaActual());
            usuarioLocal.updateUser(getUsuarioSeleccionado());
            showInfoMessage(capturarValor("usu_mis_datos"));
        } catch (ControllerWebException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            showErrorMessage(capturarValor("usu_erro_mis_datos"));
        }
    }

    public void regresar() {
        setUsuarioSeleccionado(null);
        setPerfil(null);
        setTipoDocumento(null);
        setUsuarioActivo(Boolean.FALSE);
        setVerDetalle(Boolean.TRUE);
    }

    /**
     * @return the listaUsuariosSistema
     */
    public List<Usuario> getListaUsuariosSistema() {
        return listaUsuariosSistema;
    }

    /**
     * @param listaUsuariosSistema the listaUsuariosSistema to set
     */
    public void setListaUsuariosSistema(List<Usuario> listaUsuariosSistema) {
        this.listaUsuariosSistema = listaUsuariosSistema;
    }

    /**
     * @return the verDetalle
     */
    public boolean isVerDetalle() {
        return verDetalle;
    }

    /**
     * @param verDetalle the verDetalle to set
     */
    public void setVerDetalle(boolean verDetalle) {
        this.verDetalle = verDetalle;
    }

    /**
     * @return the usuarioSeleccionado
     */
    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    /**
     * @param usuarioSeleccionado the usuarioSeleccionado to set
     */
    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
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
     * @return the usuarioActivo
     */
    public boolean isUsuarioActivo() {
        return usuarioActivo;
    }

    /**
     * @param usuarioActivo the usuarioActivo to set
     */
    public void setUsuarioActivo(boolean usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

}
