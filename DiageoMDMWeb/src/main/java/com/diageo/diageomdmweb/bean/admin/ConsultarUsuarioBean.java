/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.entities.DwProfiles;
import com.diageo.admincontrollerweb.entities.DwDocumentTypes;
import com.diageo.admincontrollerweb.entities.DwUsers;
import com.diageo.admincontrollerweb.enums.StateEnum;
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
import com.diageo.admincontrollerweb.beans.UserBeanLocal;

/**
 *
 * @author yovanoty126
 */
@Named(value = "consultarUsuarioBean")
@ViewScoped
public class ConsultarUsuarioBean extends DiageoRootBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(ConsultarUsuarioBean.class.getName());
    @EJB
    private UserBeanLocal usuarioLocal;
    private List<DwUsers> listaUsuariosSistema;
    private boolean verDetalle;
    private DwUsers usuarioSeleccionado;
    private DwProfiles perfil;
    private DwDocumentTypes tipoDocumento;
    private boolean usuarioActivo;

    /**
     * Creates a new instance of ConsultarUsuarioBean
     */
    public ConsultarUsuarioBean() {
    }

    @PostConstruct
    public void init() {
        setVerDetalle(Boolean.TRUE);
        setPerfil(new DwProfiles());
        setTipoDocumento(new DwDocumentTypes());
        consultarUsuariosSistema();
    }

    public void consultarUsuariosSistema() {
        try {
            setListaUsuariosSistema(usuarioLocal.findAll());
        } catch (ControllerWebException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }

    }

    public void verDetalleUsuario(DwUsers usu) {
        setVerDetalle(Boolean.FALSE);
        setUsuarioSeleccionado(usu);
        setPerfil(usu.getProfileId());
        setTipoDocumento(new DwDocumentTypes(usu.getDocumentTypeId().getDocumentTypeId()));
        setUsuarioActivo(usu.getStateUser().equals(StateEnum.ACTIVE.getState()));
    }

    public void guardarCambiosUsuario() {
        try {
            getUsuarioSeleccionado().setProfileId(getPerfil());
            getUsuarioSeleccionado().setDocumentTypeId(getTipoDocumento());
            getUsuarioSeleccionado().setStateUser(isUsuarioActivo() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
            getUsuarioSeleccionado().setUpdateDate(super.getFechaActual());
            getUsuarioSeleccionado().setNameUser(getUsuarioSeleccionado().getNameUser().toUpperCase());
            getUsuarioSeleccionado().setLastName(getUsuarioSeleccionado().getLastName().toUpperCase());
            getUsuarioSeleccionado().setEmailUser(getUsuarioSeleccionado().getEmailUser().toUpperCase());
            getUsuarioSeleccionado().setDocumentNumber(getUsuarioSeleccionado().getDocumentNumber());
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
    public List<DwUsers> getListaUsuariosSistema() {
        return listaUsuariosSistema;
    }

    /**
     * @param listaUsuariosSistema the listaUsuariosSistema to set
     */
    public void setListaUsuariosSistema(List<DwUsers> listaUsuariosSistema) {
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
    public DwUsers getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    /**
     * @param usuarioSeleccionado the usuarioSeleccionado to set
     */
    public void setUsuarioSeleccionado(DwUsers usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    /**
     * @return the perfil
     */
    public DwProfiles getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(DwProfiles perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the tipoDocumento
     */
    public DwDocumentTypes getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(DwDocumentTypes tipoDocumento) {
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
