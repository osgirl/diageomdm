/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.entities.Modulo;
import com.diageo.admincontrollerweb.entities.Perfil;
import com.diageo.admincontrollerweb.entities.TipoDoc;
import com.diageo.admincontrollerweb.entities.Usuario;
import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.admincontrollerweb.enums.UserEntryEnum;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.constant.PatternConstant;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.validation.constraints.Pattern;
import org.apache.commons.codec.digest.DigestUtils;
import com.diageo.admincontrollerweb.beans.UserBeanLocal;
import com.diageo.admincontrollerweb.beans.ModuleBeanLocal;
import com.diageo.admincontrollerweb.entities.PermissionSegment;
import com.diageo.admincontrollerweb.enums.ProfileEnum;
import com.diageo.diageonegocio.beans.DistributorBeanLocal;
import com.diageo.diageonegocio.entidades.Distribuidor;
import java.util.List;
import org.primefaces.context.RequestContext;

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
     * Ejb UserBeanLocal
     */
    @EJB
    private UserBeanLocal usuarioBean;
    /**
     * Ejb ModuleBeanLocal
     */
    @EJB
    private ModuleBeanLocal moduloBean;
    /**
     * Ejb distributor
     */
    @EJB
    private DistributorBeanLocal distributorBeanLocal;
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
    @Pattern(regexp = PatternConstant.EMAIL_VALIDADOR, message = "{correo.pattern}")
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
     * Detail edition
     */
    private boolean detailEdition;
    /**
     * Check distributor
     */
    private boolean distributorCheck;
    /**
     * Check chain
     */
    private boolean chainCheck;
    /**
     * Check channel
     */
    private boolean channelCheck;
    /**
     * Check chain
     */
    private boolean subChannelCheck;
    /**
     * Check segment
     */
    private boolean segmentCheck;
    /**
     * Check subsegment
     */
    private boolean subSegmentCheck;
    /**
     * Check potential
     */
    private boolean potentialCheck;
    /**
     * Chain Permissions
     */
    private PermissionSegment permissionSegment;
    /**
     * List distributor
     */
    private List<Distribuidor> listDistributor;
    /**
     * Distributor selected
     */
    private Distribuidor distributorSelected;

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
        permissionSegment = new PermissionSegment();
        setListDistributor(distributorBeanLocal.searchADistributorPadre("0"));
    }

    /**
     * Crea un usuario del sistema
     */
    public void crearUsuario() {
        if (!validarExisteciaCorreo()) {
            try {
                Usuario usu = new Usuario();
                usu.setNombres(getNombres().toUpperCase());
                usu.setApellidos(getApellidos().toUpperCase());
                usu.setCorreo(getCorreo().toUpperCase());
                usu.setContraseina(DigestUtils.md5Hex(getTipoDocumento().getNombre().toLowerCase() + getNumDoc()));
                usu.setNumDoc(getNumDoc().toUpperCase());
                usu.setFechaCreacion(getFechaActual());
                usu.setEstado(isActivo() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
                usu.setTipoDoc(getTipoDocumento().getIdtipoDoc());
                usu.setIdPerfil(getPerfil());
                usu.setIntentosFallidos(0);
                usu.setPrimerIngreso(UserEntryEnum.FIRST_ENTRY.getState());
                usu.setModuloList(perfil.getModuloList());
                usu.setPermissionSegment(permissionSegment);
                usuarioBean.createUser(usu);
                for (Modulo mod : getPerfil().getModuloList()) {
                    mod.getUsuarioList().add(usu);
                    moduloBean.createUserModule(mod);
                }
                showInfoMessage(capturarValor("usu_creado_exito"));
            } catch (ControllerWebException ex) {
                showErrorMessage(capturarValor("usu_creado_fallo"));
                LOG.log(Level.SEVERE, ex.getMessage());
            }
        }
    }

    public void listenerDetailEdition() {
        detailEdition = !((getPerfil().getIdperfil().equals(ProfileEnum.ADMINISTRATOR.getId())) || (getPerfil().getIdperfil().equals(ProfileEnum.DATA_STEWARD.getId())));
    }

    public void selectAllChain() {
        setChannelCheck(Boolean.TRUE);
        setSubChannelCheck(Boolean.TRUE);
        setSegmentCheck(Boolean.TRUE);
        setSubSegmentCheck(Boolean.TRUE);
        setPotentialCheck(Boolean.TRUE);
    }

    public void unSelectAllChain() {
        setChannelCheck(Boolean.FALSE);
        setSubChannelCheck(Boolean.FALSE);
        setSegmentCheck(Boolean.FALSE);
        setSubSegmentCheck(Boolean.FALSE);
        setPotentialCheck(Boolean.FALSE);
    }

    public void aceptChangesChain() {
        permissionSegment = addPermissionUser();
        RequestContext.getCurrentInstance().execute("PF('wvChain').hide();");
    }

    public void cancelChangesChain() {
        unSelectAllChain();
        permissionSegment = addPermissionUser();
        RequestContext.getCurrentInstance().execute("PF('wvChain').hide();");
    }

    private PermissionSegment addPermissionUser() {
        PermissionSegment ps = new PermissionSegment();
        ps.setChannel(isChannelCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        System.out.println(isChannelCheck());
        ps.setSubChannel(isSubChannelCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        ps.setSegmento(isSegmentCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        ps.setSubSegmento(isSubSegmentCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        ps.setPotencial(isPotentialCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        return ps;
    }

    /**
     * Valida por correo si existe o no el usuario
     *
     * @return true si el usuario ya existe con ese correo, false por lo
     * contrario
     */
    private boolean validarExisteciaCorreo() {
        try {
            usuarioBean.findEmail(correo);
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

    /**
     * @return the detailEdition
     */
    public boolean isDetailEdition() {
        return detailEdition;
    }

    /**
     * @param detailEdition the detailEdition to set
     */
    public void setDetailEdition(boolean detailEdition) {
        this.detailEdition = detailEdition;
    }

    /**
     * @return the distributorCheck
     */
    public boolean isDistributorCheck() {
        return distributorCheck;
    }

    /**
     * @param distributorCheck the distributorCheck to set
     */
    public void setDistributorCheck(boolean distributorCheck) {
        this.distributorCheck = distributorCheck;
    }

    /**
     * @return the chainCheck
     */
    public boolean isChainCheck() {
        return chainCheck;
    }

    /**
     * @param chainCheck the chainCheck to set
     */
    public void setChainCheck(boolean chainCheck) {
        this.chainCheck = chainCheck;
    }

    /**
     * @return the channelCheck
     */
    public boolean isChannelCheck() {
        return channelCheck;
    }

    /**
     * @param channelCheck the channelCheck to set
     */
    public void setChannelCheck(boolean channelCheck) {
        this.channelCheck = channelCheck;
    }

    /**
     * @return the subChannelCheck
     */
    public boolean isSubChannelCheck() {
        return subChannelCheck;
    }

    /**
     * @param subChannelCheck the subChannelCheck to set
     */
    public void setSubChannelCheck(boolean subChannelCheck) {
        this.subChannelCheck = subChannelCheck;
    }

    /**
     * @return the segmentCheck
     */
    public boolean isSegmentCheck() {
        return segmentCheck;
    }

    /**
     * @param segmentCheck the segmentCheck to set
     */
    public void setSegmentCheck(boolean segmentCheck) {
        this.segmentCheck = segmentCheck;
    }

    /**
     * @return the subSegmentCheck
     */
    public boolean isSubSegmentCheck() {
        return subSegmentCheck;
    }

    /**
     * @param subSegmentCheck the subSegmentCheck to set
     */
    public void setSubSegmentCheck(boolean subSegmentCheck) {
        this.subSegmentCheck = subSegmentCheck;
    }

    /**
     * @return the potentialCheck
     */
    public boolean isPotentialCheck() {
        return potentialCheck;
    }

    /**
     * @param potentialCheck the potentialCheck to set
     */
    public void setPotentialCheck(boolean potentialCheck) {
        this.potentialCheck = potentialCheck;
    }

    /**
     * @return the listDistributor
     */
    public List<Distribuidor> getListDistributor() {
        return listDistributor;
    }

    /**
     * @param listDistributor the listDistributor to set
     */
    public void setListDistributor(List<Distribuidor> listDistributor) {
        this.listDistributor = listDistributor;
    }

    /**
     * @return the distributorSelected
     */
    public Distribuidor getDistributorSelected() {
        return distributorSelected;
    }

    /**
     * @param distributorSelected the distributorSelected to set
     */
    public void setDistributorSelected(Distribuidor distributorSelected) {
        this.distributorSelected = distributorSelected;
    }

}
