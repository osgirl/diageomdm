/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.entities.DwDocumentTypes;
import com.diageo.admincontrollerweb.entities.DwUsers;
import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import com.diageo.diageomdmweb.bean.dto.DistributorPermissionDto;
import com.diageo.diageonegocio.beans.PermissionsegmentBeanLocal;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;

/**
 *
 * @author yovanoty126
 */
@Named(value = "consultarUsuarioBean")
@ViewScoped
public class ConsultarUsuarioBean extends GestionarUsuarioCreacion implements Serializable {

    private static final Logger LOG = Logger.getLogger(ConsultarUsuarioBean.class.getName());
    @EJB
    private PermissionsegmentBeanLocal permissionsegmentBeanLocal;
    private List<DwUsers> listaUsuariosSistema;
    private boolean verDetalle;
    private DwUsers usuarioSeleccionado;
    private boolean usuarioActivo;
    private Set<DbPermissionSegments> listDistributorPermissionRemove;
    private String temporalMail;
    /**
     * Contains the records that will be removed
     */
    private List<DistributorPermissionDto> listDistributorPermissionRemoveUser;

    /**
     * Creates a new instance of ConsultarUsuarioBean
     */
    public ConsultarUsuarioBean() {
    }

    @PostConstruct
    @Override
    public void init() {
        super.init();
        setVerDetalle(Boolean.TRUE);
        consultarUsuariosSistema();
        setListDistributorPermissionRemove(new HashSet<DbPermissionSegments>());
        setListDistributorPermissionRemoveUser(new ArrayList<DistributorPermissionDto>());
    }

    public void consultarUsuariosSistema() {
        try {
            setListaUsuariosSistema(usuarioBean.findAll());
        } catch (ControllerWebException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }

    }

    public void verDetalleUsuario(DwUsers usu) {
        setVerDetalle(Boolean.FALSE);
        setUsuarioSeleccionado(usu);
        setTemporalMail(usu.getEmailUser());
        setPerfil(usu.getProfileId());
        setTipoDocumento(new DwDocumentTypes(usu.getDocumentTypeId().getDocumentTypeId()));
        setUsuarioActivo(usu.getStateUser().equals(StateEnum.ACTIVE.getState()));
        //find permission segment
        List<DbPermissionSegments> list = permissionsegmentBeanLocal.findByUser(usu.getUserId());
        setListDistributorPermission(new HashSet<DistributorPermissionDto>());
        for (DbPermissionSegments list1 : list) {
            DistributorPermissionDto dto = new DistributorPermissionDto();
            dto.setDistributor(list1.getDb3partyId());
            Set<DbPermissionSegments> listPS = permissionsegmentBeanLocal.findByUserDistributor(getUsuarioSeleccionado().getUserId(), list1.getDb3partyId().getDb3partyId());
            dto.setListPermissionSegment(listPS);
            getListDistributorPermission().add(dto);
        }
        super.listenerDetailEdition();
    }

    public void guardarCambiosUsuario() {
        if (!validateListDistributorPermission()) {
            if (!validarExisteciaCorreo()) {
                try {
                    getUsuarioSeleccionado().setProfileId(getPerfil());
                    getUsuarioSeleccionado().setDocumentTypeId(getTipoDocumento());
                    getUsuarioSeleccionado().setStateUser(isUsuarioActivo() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
                    getUsuarioSeleccionado().setUpdateDate(super.getFechaActual());
                    getUsuarioSeleccionado().setNameUser(getUsuarioSeleccionado().getNameUser().toUpperCase());
                    getUsuarioSeleccionado().setLastName(getUsuarioSeleccionado().getLastName().toUpperCase());
                    getUsuarioSeleccionado().setEmailUser(getUsuarioSeleccionado().getEmailUser().toUpperCase());
                    getUsuarioSeleccionado().setDocumentNumber(getUsuarioSeleccionado().getDocumentNumber());
                    deletePermissionSegment();
                    setListPermissionSegmentToPersist(new ArrayList<DbPermissionSegments>());
                    for (DistributorPermissionDto ps : getListDistributorPermission()) {
                        getListPermissionSegmentToPersist().addAll(ps.getListPermissionSegment());
                    }
                    usuarioBean.updateUser(getUsuarioSeleccionado(), getListPermissionSegmentToPersist());
                    showInfoMessage(capturarValor("usu_mis_datos"));
                } catch (ControllerWebException ex) {
                    LOG.log(Level.SEVERE, ex.getMessage());
                    showErrorMessage(capturarValor("usu_erro_mis_datos"));
                }
            }
        }
    }

    @Override
    protected boolean validarExisteciaCorreo() {
        try {
            if (getUsuarioSeleccionado().getEmailUser().toUpperCase().equals(getTemporalMail().toUpperCase())) {
                return false;
            }
            usuarioBean.findEmail(getUsuarioSeleccionado().getEmailUser().toUpperCase());
            showWarningMessage(capturarValor("usu_correo_existe"));
            return true;
        } catch (ControllerWebException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            return false;
        }
    }

    public void regresar() {
        setUsuarioSeleccionado(null);
        setDetailEdition(Boolean.FALSE);
        setPerfil(null);
        setTipoDocumento(null);
        setUsuarioActivo(Boolean.FALSE);
        setVerDetalle(Boolean.TRUE);
    }

    public void removePermissionFromPopup(DbPermissionSegments dto) {
        getListDistributorPermissionRemove().add(dto);
        getDistributorPermissionDtoSelected().getListPermissionSegment().remove(dto);
    }

    public void deletePermissionSegment() {
        for (DbPermissionSegments ps : getListDistributorPermissionRemove()) {
            permissionsegmentBeanLocal.remove(ps);
        }
    }

    @Override
    public void removeDistributorToUser(DistributorPermissionDto dto) {
        getListDistributorPermission().remove(dto);
        getListDistributorPermissionRemoveUser().add(dto);
        for (DbPermissionSegments ps : dto.getListPermissionSegment()) {
            getListDistributorPermissionRemove().add(ps);
        }
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

    public Set<DbPermissionSegments> getListDistributorPermissionRemove() {
        return listDistributorPermissionRemove;
    }

    public void setListDistributorPermissionRemove(Set<DbPermissionSegments> listDistributorPermissionRemove) {
        this.listDistributorPermissionRemove = listDistributorPermissionRemove;
    }

    public List<DistributorPermissionDto> getListDistributorPermissionRemoveUser() {
        return listDistributorPermissionRemoveUser;
    }

    public void setListDistributorPermissionRemoveUser(List<DistributorPermissionDto> listDistributorPermissionRemoveUser) {
        this.listDistributorPermissionRemoveUser = listDistributorPermissionRemoveUser;
    }

    public String getTemporalMail() {
        return temporalMail;
    }

    public void setTemporalMail(String temporalMail) {
        this.temporalMail = temporalMail;
    }
}
