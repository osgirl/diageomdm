/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.entities.Audit;
import com.diageo.admincontrollerweb.entities.DwParameters;
import com.diageo.admincontrollerweb.entities.DwUsers;
import com.diageo.admincontrollerweb.enums.ParameterKeyEnum;
import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import com.diageo.diageomdmweb.bean.dto.DistributorPermissionDto;
import com.diageo.diageomdmweb.jdbc.ConecctionJDBC;
import com.diageo.diageonegocio.beans.PermissionsegmentBeanLocal;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import org.primefaces.context.RequestContext;

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

    private String temporalMail;
    /**
     * Contains the records that will be removed
     */
    private List<DistributorPermissionDto> listDistributorPermissionRemoveUser;
    /**
     * Campos para enviar al procedimiento almacenado [dbo].[SP_DB_USERS]
     */
    private String distriSP;
    private String stateSP;

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

    private void findPermissionSegment(DwUsers usu) {
        List<DbPermissionSegments> list = permissionsegmentBeanLocal.findByUser(usu.getUserId());
        setListDistributorPermission(new HashSet<DistributorPermissionDto>());
        for (DbPermissionSegments list1 : list) {
            DistributorPermissionDto dto = new DistributorPermissionDto();
            dto.setDistributor(list1.getDb3partyId());
            Set<DbPermissionSegments> listPS = permissionsegmentBeanLocal.findByUserDistributor(getUsuarioSeleccionado().getUserId(), list1.getDb3partyId().getDb3partyId());
            dto.setListPermissionSegment(listPS);
            getListDistributorPermission().add(dto);
        }
    }

    public void verDetalleUsuario(DwUsers usu) {
        setVerDetalle(Boolean.FALSE);
        setUsuarioSeleccionado(usu);
        setTemporalMail(usu.getEmailUser());
        setPerfil(usu.getProfileId());
        setUsuarioActivo(usu.getStateUser().equals(StateEnum.ACTIVE.getState()));
        stateSP=usu.getStateUser();
        setAthenaCode(usu.getDistri1());
        distriSP=usu.getDistri1();
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
                    getUsuarioSeleccionado().setStateUser(isUsuarioActivo() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
                    Audit audit = new Audit();
                    audit.setCreationDate(getUsuarioSeleccionado().getAudit() != null ? getUsuarioSeleccionado().getAudit().getCreationDate() : null);
                    audit.setCreationUser(getUsuarioSeleccionado().getAudit() != null ? getUsuarioSeleccionado().getAudit().getCreationUser() : null);
                    audit.setModificationDate(super.getCurrentDate());
                    audit.setModificationUser(super.getLoginBean().getUsuario().getEmailUser());
                    getUsuarioSeleccionado().setAudit(audit);
                    getUsuarioSeleccionado().setNameUser(getUsuarioSeleccionado().getNameUser().toUpperCase());
                    getUsuarioSeleccionado().setLastName(getUsuarioSeleccionado().getLastName().toUpperCase());
                    getUsuarioSeleccionado().setEmailUser(getUsuarioSeleccionado().getEmailUser().toUpperCase());
                    getUsuarioSeleccionado().setDistri1(getAthenaCode().toUpperCase());
                    deletePermissionSegment();
                    setListPermissionSegmentToPersist(new ArrayList<DbPermissionSegments>());
                    for (DistributorPermissionDto ps : getListDistributorPermission()) {
                        getListPermissionSegmentToPersist().addAll(ps.getListPermissionSegment());
                    }
                    List<DbPermissionSegments> updateUser_Test = usuarioBean.updateUser_Test(getUsuarioSeleccionado(), getListPermissionSegmentToPersist());

                    List<DwParameters> ipDatabase = parameterBeanLocal.findByKey(ParameterKeyEnum.DATABASE_IP.name());
                    List<DwParameters> userDatabase = parameterBeanLocal.findByKey(ParameterKeyEnum.USER_DATABASE.name());
                    List<DwParameters> passDatabase = parameterBeanLocal.findByKey(ParameterKeyEnum.PASS_DATABASE.name());
                    Connection con = ConecctionJDBC.conexionSQLServer(ipDatabase.get(0).getParameterValue(),
                            userDatabase.get(0).getParameterValue(), passDatabase.get(0).getParameterValue());
                    for (DbPermissionSegments dbPermissionSegments : updateUser_Test) {
                        ConecctionJDBC.callStoreOutletsUser(con, dbPermissionSegments.getPermissionSegment(), "Insert");
                    }
                    ConecctionJDBC.callStoreProcedureDBUsers(con, getUsuarioSeleccionado().getUserId(), distriSP, stateSP);
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    showInfoMessage(capturarValor("usu_mis_datos"));
                    findPermissionSegment(usuarioSeleccionado);
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

    @Override
    public void aceptChangesChain() {
        for (DbPermissionSegments ps : getDistributorPermissionDtoSelected().getListPermissionSegment()) {
            com.diageo.diageonegocio.entidades.Audit audit = new com.diageo.diageonegocio.entidades.Audit();
            if (ps.getAudit() != null && ps.getAudit().getCreationDate() == null) {
                audit.setCreationDate(super.getCurrentDate());
                audit.setCreationUser(getLoginBean().getUsuario().getEmailUser());
            } else {
                audit.setModificationDate(super.getCurrentDate());
                audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
            }
            ps.setAudit(audit);
            getListPermissionSegmentToPersist().add(ps);
        }
        super.initList();
        unSelectAllChain();
        RequestContext.getCurrentInstance().execute("PF('wvChain').hide();");
    }

    public void regresar() {
        setUsuarioSeleccionado(null);
        setDetailEdition(Boolean.FALSE);
        setPerfil(null);
        setUsuarioActivo(Boolean.FALSE);
        setVerDetalle(Boolean.TRUE);
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('wvUsu').clearFilters()");
    }

    public void deletePermissionSegment() {
        List<DwParameters> ipDatabase = parameterBeanLocal.findByKey(ParameterKeyEnum.DATABASE_IP.name());
        List<DwParameters> userDatabase = parameterBeanLocal.findByKey(ParameterKeyEnum.USER_DATABASE.name());
        List<DwParameters> passDatabase = parameterBeanLocal.findByKey(ParameterKeyEnum.PASS_DATABASE.name());
        Connection con = ConecctionJDBC.conexionSQLServer(ipDatabase.get(0).getParameterValue(),
                userDatabase.get(0).getParameterValue(), passDatabase.get(0).getParameterValue());
        for (DbPermissionSegments dbPermissionSegments : getListDistributorPermissionRemove()) {
            ConecctionJDBC.callStoreOutletsUser(con, dbPermissionSegments.getPermissionSegment(), "Delete");
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (DbPermissionSegments ps : getListDistributorPermissionRemove()) {
            permissionsegmentBeanLocal.remove(ps);
        }
        setListDistributorPermissionRemove(new HashSet<DbPermissionSegments>());
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
