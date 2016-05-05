/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.entities.DwModules;
import com.diageo.admincontrollerweb.entities.DwProfiles;
import com.diageo.admincontrollerweb.entities.DwDocumentTypes;
import com.diageo.admincontrollerweb.entities.DwUsers;
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
import com.diageo.admincontrollerweb.enums.ProfileEnum;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.DistributorBeanLocal;
import com.diageo.diageonegocio.beans.PotentialBeanLocal;
import com.diageo.diageonegocio.beans.SegmentoBeanLocal;
import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.beans.SubSegmentoBeanLocal;
import com.diageo.diageonegocio.entidades.Channel;
import com.diageo.diageonegocio.entidades.Departamento;
import com.diageo.diageonegocio.entidades.Distribuidor;
import com.diageo.diageonegocio.entidades.Municipio;
import com.diageo.diageonegocio.entidades.Permissionsegment;
import com.diageo.diageonegocio.entidades.PermissionsegmentPK;
import com.diageo.diageonegocio.entidades.Potential;
import com.diageo.diageonegocio.entidades.Segmento;
import com.diageo.diageonegocio.entidades.SubChannel;
import com.diageo.diageonegocio.entidades.SubSegmento;
import com.diageo.diageonegocio.enums.FatherDistributorEnum;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
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
    @EJB
    private PotentialBeanLocal potentialBeanLocal;
    @EJB
    private ChannelBeanLocal channelBeanLocal;
    @EJB
    private SubChannelBeanLocal subChannelBeanLocal;
    @EJB
    private SegmentoBeanLocal segmentoBeanLocal;
    @EJB
    private SubSegmentoBeanLocal subSegmentoBeanLocal;

    @Inject
    private LoginBean loginBean;
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
    private DwDocumentTypes tipoDocumento;
    /**
     * DwProfiles que se le asignará al usuario creado
     */
    private DwProfiles perfil;
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
     * List distributor
     */
    private List<Distribuidor> listDistributor;
    private List<Distribuidor> listDistributorSon;
    private List<Permissionsegment> listDistributorAddToUser;
    private List<Channel> listChannel;
    private List<SubChannel> listSubChannel;
    private List<Segmento> listSegment;
    private List<SubSegmento> listSubSegment;
    private List<Potential> listPotential;
    private List<Potential> listaPotentialAutomatic;
    /**
     * List that indicate the channels the user can see by distributor
     */
    private List<Permissionsegment> listSegmentByDistributor;
    /**
     * Distributor selected
     */
    private Distribuidor distributorSelected;
    private Channel channelSelected;
    private SubChannel subChannelSelected;
    private Segmento segmentSelected;
    private SubSegmento subSegmentSelected;
    private Permissionsegment permissionsegmentSelected;
    private Potential potentialAutomatic;
    private Potential potentialManual;

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
        setPerfil(new DwProfiles());
        setTipoDocumento(new DwDocumentTypes());
        setListDistributor(distributorBeanLocal.searchADistributorPadre(FatherDistributorEnum.FATHER.getIsPadre()));
        setListDistributorSon(distributorBeanLocal.searchDistributorByPadre(getListDistributor().get(0).getIdDistribuidor()));
        setListDistributorAddToUser(new ArrayList<Permissionsegment>());
        setListPotential(potentialBeanLocal.findAll());
        //segmentation
        setListChannel(channelBeanLocal.consultarTodosChannel());
        initList();
    }

    private void initList() {
        setChannelSelected(getListChannel().get(0));
        setSubChannelSelected(getChannelSelected().getSubChannelList().get(0));
        setSegmentSelected(getSubChannelSelected().getSegmentoList().get(0));
        setSubSegmentSelected(getSegmentSelected().getSubSegmentoList().get(0));
        setListSubChannel(getChannelSelected().getSubChannelList());
        setListSegment(getSubChannelSelected().getSegmentoList());
        setListSubSegment(getSegmentSelected().getSubSegmentoList());
        listenerSubSegment();
        setListSegmentByDistributor(new ArrayList<Permissionsegment>());
    }

    /**
     * Crea un usuario del sistema
     */
    public void crearUsuario() {
        if (!validarExisteciaCorreo()) {
            try {
                DwUsers usu = new DwUsers();
                usu.setNameUser(getNombres().toUpperCase());
                usu.setLastName(getApellidos().toUpperCase());
                usu.setEmailUser(getCorreo().toUpperCase());
                usu.setPasswordUser(DigestUtils.md5Hex(getTipoDocumento().getNameDocumentType().toLowerCase() + getNumDoc()));
                usu.setDocumentNumber(getNumDoc().toUpperCase());
                usu.setCreationDate(getFechaActual());
                usu.setStateUser(isActivo() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
                usu.setDocumentTypeId(getTipoDocumento());
                usu.setProfileId(getPerfil());
                usu.setFailedAttempt(0);
                usu.setFirstEntry(UserEntryEnum.FIRST_ENTRY.getState());
                usu.setDwModulesList(perfil.getDwModulesList());
                if (isDetailEdition()) {
                    usu.setDistributorId(getDistributorSelected().getIdDistribuidor());
                    usuarioBean.createUser(usu, getListDistributorAddToUser());
                } else {
                    usuarioBean.createUser(usu, null);
                }
                for (DwModules mod : getPerfil().getDwModulesList()) {
                    mod.getDwUsersList().add(usu);
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
        detailEdition = !((getPerfil().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())) || (getPerfil().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId()))
                || (getPerfil().getProfileId().equals(ProfileEnum.CATDEV.getId())));
    }

    public void listenerFindDistributorSonByFather() {
        setListDistributorSon(distributorBeanLocal.searchDistributorByPadre(distributorSelected.getIdDistribuidor()));
    }

    public void addDistributorToUser(Distribuidor distriSon) {
        for (Permissionsegment d : getListDistributorAddToUser()) {
            if (d.getDistribuidor().getIdDistribuidor().equals(distriSon.getIdDistribuidor())) {
                showWarningMessage(capturarValor("usu_mjs_distri_exists"));
                return;
            }
        }
        Distribuidor di = new Distribuidor();
        di.setIdDistribuidor(distriSon.getIdDistribuidor());
        di.setIsPadre(distriSon.getIsPadre());
        di.setNombre(distriSon.getNombre());
        di.setPadreIdDistribuidor(distriSon.getPadreIdDistribuidor());
        Permissionsegment ps = new Permissionsegment();
        PermissionsegmentPK permissionsegmentPK = new PermissionsegmentPK();
        permissionsegmentPK.setIdDistributor(di.getIdDistribuidor());
        ps.setDistribuidor(di);
        ps.setPermissionsegmentPK(permissionsegmentPK);
        getListDistributorAddToUser().add(ps);
    }

    public void removeDistributorToUser(Permissionsegment distriSon) {
        getListDistributorAddToUser().remove(distriSon);
    }

    public void seeDetailDistributorAddedUser(Permissionsegment permission) {
        setPermissionsegmentSelected(permission);
        getPermissionsegmentSelected().setDistribuidor(permission.getDistribuidor());
        //Assign primary key        
        getPermissionsegmentSelected().setPermissionsegmentPK(permission.getPermissionsegmentPK());
        //BooleanCheck
        setChannelCheck(permission.getChannelCheck() != null ? (permission.getChannelCheck().equals(StateEnum.ACTIVE.getState())) : false);
        setSubChannelCheck(permission.getSubChannelCheck() != null ? (permission.getSubChannelCheck().equals(StateEnum.ACTIVE.getState())) : false);
        setSegmentCheck(permission.getSegmentoCheck() != null ? (permission.getSegmentoCheck().equals(StateEnum.ACTIVE.getState())) : false);
        setSubSegmentCheck(permission.getSubSegmentCheck() != null ? (permission.getSubSegmentCheck().equals(StateEnum.ACTIVE.getState())) : false);
        setPotentialCheck(permission.getPotentialCheck() != null ? (permission.getPotentialCheck().equals(StateEnum.ACTIVE.getState())) : false);
        //Objects segmentation
        if (permission.getChannel() != null) {
            try {
                setChannelSelected(channelBeanLocal.consultarId(permission.getChannel()));
                setSubChannelSelected(subChannelBeanLocal.consultarId(permission.getSubChannel()));
                setSegmentSelected(segmentoBeanLocal.consultarId(permission.getSegmento()));
                setSubSegmentSelected(subSegmentoBeanLocal.consultarId(permission.getSubSegmento()));
            } catch (DiageoNegocioException ex) {
                Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, ex.getMessage());
            }
        } else {
            setChannelSelected(getListChannel().get(0));
            setSubChannelSelected(getChannelSelected().getSubChannelList().get(0));
            setSegmentSelected(getSubChannelSelected().getSegmentoList().get(0));
            setSubSegmentSelected(getSegmentSelected().getSubSegmentoList().get(0));
        }
        RequestContext.getCurrentInstance().execute("PF('wvChain').show();");
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
        LOG.log(Level.INFO, ("isChainCheck()" + isChannelCheck()));
        getPermissionsegmentSelected().setChannelCheck(isChannelCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        getPermissionsegmentSelected().setSubChannelCheck(isSubChannelCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        getPermissionsegmentSelected().setSegmentoCheck(isSegmentCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        getPermissionsegmentSelected().setSubSegmentCheck(isSubSegmentCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        getPermissionsegmentSelected().setPotentialCheck(isPotentialCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        getPermissionsegmentSelected().setChannel(getChannelSelected().getIdchannel());
        getPermissionsegmentSelected().setSubChannel(getSubChannelSelected().getIdsubchannel());
        getPermissionsegmentSelected().setSegmento(getSegmentSelected().getIdsegmento());
        getPermissionsegmentSelected().setSubSegmento(getSubSegmentSelected().getIdsubSegmento());
        //cancelChangesChain();
        RequestContext.getCurrentInstance().execute("PF('wvChain').hide();");
    }

    /**
     * Add to the table of the popup, a record about the permissions that have
     * the distributor
     */
    public void addPermissionDistributorList() {
        Permissionsegment ps = new Permissionsegment();
        ps.setChannelCheck(isChannelCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        ps.setSubChannelCheck(isSubChannelCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        ps.setSegmentoCheck(isSegmentCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        ps.setSubSegmentCheck(isSubSegmentCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        ps.setPotentialCheck(isPotentialCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        ps.setChannel(getChannelSelected().getIdchannel());
        ps.setSubChannel(getSubChannelSelected().getIdsubchannel());
        ps.setSegmento(getSegmentSelected().getIdsegmento());
        ps.setSubSegmento(getSubSegmentSelected().getIdsubSegmento());
        getListSegmentByDistributor().add(ps);
    }

    public void cancelChangesChain() {
        unSelectAllChain();
        setChannelSelected(getListChannel().get(0));
        setSubChannelSelected(getChannelSelected().getSubChannelList().get(0));
        setSegmentSelected(getSubChannelSelected().getSegmentoList().get(0));
        setSubSegmentSelected(getSegmentSelected().getSubSegmentoList().get(0));
        RequestContext.getCurrentInstance().execute("PF('wvChain').hide();");
    }

    public void listenerChannel() {
        setListSubChannel(getChannelSelected().getSubChannelList());
        setSubChannelSelected(getListSubChannel().get(0));
        this.listenerSubChannel();
    }

    public void listenerSubChannel() {
        setSegmentSelected(getSubChannelSelected().getSegmentoList().get(0));
        setListSegment(getSubChannelSelected().getSegmentoList());
        this.listenerSegment();
    }

    public void listenerSegment() {
        setSubSegmentSelected(getSegmentSelected().getSubSegmentoList().get(0));
        setListSubSegment(getSegmentSelected().getSubSegmentoList());
        listenerSubSegment();
    }

    public void listenerSubSegment() {
        if (getSubSegmentSelected().getPotentialList() == null || getSubSegmentSelected().getPotentialList().isEmpty()) {
            setListaPotentialAutomatic(new ArrayList<Potential>());
        } else {
            setPotentialAutomatic(getSubSegmentSelected().getPotentialList().get(0));
            setListaPotentialAutomatic(getSubSegmentSelected().getPotentialList());
        }

    }

    public String channelName(Integer id) {
        try {
            return channelBeanLocal.consultarId(id).getNombre();
        } catch (DiageoNegocioException ex) {
            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String subChannelName(Integer id) {
        try {
            return subChannelBeanLocal.consultarId(id).getNombre();
        } catch (DiageoNegocioException ex) {
            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String segmentName(Integer id) {
        try {
            return segmentoBeanLocal.consultarId(id).getNombre();
        } catch (DiageoNegocioException ex) {
            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String subSegmentName(Integer id) {
        try {
            return subSegmentoBeanLocal.consultarId(id).getNomnbre();
        } catch (DiageoNegocioException ex) {
            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
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

    /**
     * @return the listDistributorSon
     */
    public List<Distribuidor> getListDistributorSon() {
        return listDistributorSon;
    }

    /**
     * @param listDistributorSon the listDistributorSon to set
     */
    public void setListDistributorSon(List<Distribuidor> listDistributorSon) {
        this.listDistributorSon = listDistributorSon;
    }

    /**
     * @return the listDistributorAddToUser
     */
    public List<Permissionsegment> getListDistributorAddToUser() {
        return listDistributorAddToUser;
    }

    /**
     * @param listDistributorAddToUser the listDistributorAddToUser to set
     */
    public void setListDistributorAddToUser(List<Permissionsegment> listDistributorAddToUser) {
        this.listDistributorAddToUser = listDistributorAddToUser;
    }

    /**
     * @return the listChannel
     */
    public List<Channel> getListChannel() {
        return listChannel;
    }

    /**
     * @param listChannel the listChannel to set
     */
    public void setListChannel(List<Channel> listChannel) {
        this.listChannel = listChannel;
    }

    /**
     * @return the channelSelected
     */
    public Channel getChannelSelected() {
        return channelSelected;
    }

    /**
     * @param channelSelected the channelSelected to set
     */
    public void setChannelSelected(Channel channelSelected) {
        this.channelSelected = channelSelected;
    }

    /**
     * @return the subChannelSelected
     */
    public SubChannel getSubChannelSelected() {
        return subChannelSelected;
    }

    /**
     * @param subChannelSelected the subChannelSelected to set
     */
    public void setSubChannelSelected(SubChannel subChannelSelected) {
        this.subChannelSelected = subChannelSelected;
    }

    /**
     * @return the segmentSelected
     */
    public Segmento getSegmentSelected() {
        return segmentSelected;
    }

    /**
     * @param segmentSelected the segmentSelected to set
     */
    public void setSegmentSelected(Segmento segmentSelected) {
        this.segmentSelected = segmentSelected;
    }

    /**
     * @return the subSegmentSelected
     */
    public SubSegmento getSubSegmentSelected() {
        return subSegmentSelected;
    }

    /**
     * @param subSegmentSelected the subSegmentSelected to set
     */
    public void setSubSegmentSelected(SubSegmento subSegmentSelected) {
        this.subSegmentSelected = subSegmentSelected;
    }

    /**
     * @return the permissionsegmentSelected
     */
    public Permissionsegment getPermissionsegmentSelected() {
        return permissionsegmentSelected;
    }

    /**
     * @param permissionsegmentSelected the permissionsegmentSelected to set
     */
    public void setPermissionsegmentSelected(Permissionsegment permissionsegmentSelected) {
        this.permissionsegmentSelected = permissionsegmentSelected;
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
     * @return the listPotential
     */
    public List<Potential> getListPotential() {
        return listPotential;
    }

    /**
     * @param listPotential the listPotential to set
     */
    public void setListPotential(List<Potential> listPotential) {
        this.listPotential = listPotential;
    }

    public List<SubChannel> getListSubChannel() {
        return listSubChannel;
    }

    public void setListSubChannel(List<SubChannel> listSubChannel) {
        this.listSubChannel = listSubChannel;
    }

    public List<Segmento> getListSegment() {
        return listSegment;
    }

    public void setListSegment(List<Segmento> listSegment) {
        this.listSegment = listSegment;
    }

    public List<SubSegmento> getListSubSegment() {
        return listSubSegment;
    }

    public void setListSubSegment(List<SubSegmento> listSubSegment) {
        this.listSubSegment = listSubSegment;
    }

    public Potential getPotentialAutomatic() {
        return potentialAutomatic;
    }

    public void setPotentialAutomatic(Potential potentialAutomatic) {
        this.potentialAutomatic = potentialAutomatic;
    }

    /**
     * @return the listaPotentialAutomatic
     */
    public List<Potential> getListaPotentialAutomatic() {
        return listaPotentialAutomatic;
    }

    /**
     * @param listaPotentialAutomatic the listaPotentialAutomatic to set
     */
    public void setListaPotentialAutomatic(List<Potential> listaPotentialAutomatic) {
        this.listaPotentialAutomatic = listaPotentialAutomatic;
    }

    public Potential getPotentialManual() {
        return potentialManual;
    }

    public void setPotentialManual(Potential potentialManual) {
        this.potentialManual = potentialManual;
    }

    /**
     * @return the listSegmentByDistributor
     */
    public List<Permissionsegment> getListSegmentByDistributor() {
        return listSegmentByDistributor;
    }

    /**
     * @param listSegmentByDistributor the listSegmentByDistributor to set
     */
    public void setListSegmentByDistributor(List<Permissionsegment> listSegmentByDistributor) {
        this.listSegmentByDistributor = listSegmentByDistributor;
    }

}
