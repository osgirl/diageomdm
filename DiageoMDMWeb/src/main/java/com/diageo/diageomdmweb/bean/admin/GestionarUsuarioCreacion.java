/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.entities.DwProfiles;
import com.diageo.admincontrollerweb.enums.StateEnum;
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
import com.diageo.admincontrollerweb.beans.UserBeanLocal;
import com.diageo.admincontrollerweb.beans.ModuleBeanLocal;
import com.diageo.admincontrollerweb.beans.ParameterBeanLocal;
import com.diageo.admincontrollerweb.beans.ProfileBeanLocal;
import com.diageo.admincontrollerweb.entities.Audit;
import com.diageo.admincontrollerweb.entities.DwParameters;
import com.diageo.admincontrollerweb.entities.DwUsers;
import com.diageo.admincontrollerweb.enums.ParameterKeyEnum;
import com.diageo.admincontrollerweb.enums.ProfileEnum;
import com.diageo.admincontrollerweb.enums.UserEntryEnum;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageomdmweb.bean.dto.DistributorPermissionDto;
import com.diageo.diageomdmweb.jdbc.ConecctionJDBC;
import com.diageo.diageomdmweb.mail.EMail;
import com.diageo.diageomdmweb.mail.templates.VelocityTemplate;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.Db3PartyBeanLocal;
import com.diageo.diageonegocio.beans.PotentialBeanLocal;
import com.diageo.diageonegocio.beans.SegmentBeanLocal;
import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.beans.SubSegmentoBeanLocal;
import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.entidades.DbPotentials;
import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.enums.FatherDistributorEnum;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
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
    protected UserBeanLocal usuarioBean;
    /**
     * Ejb ModuleBeanLocal
     */
    @EJB
    protected ModuleBeanLocal moduloBean;
    /**
     * Ejb distributor
     */
    @EJB
    protected Db3PartyBeanLocal distributorBeanLocal;
    @EJB
    protected PotentialBeanLocal potentialBeanLocal;
    @EJB
    protected ChannelBeanLocal channelBeanLocal;
    @EJB
    protected SubChannelBeanLocal subChannelBeanLocal;
    @EJB
    protected SegmentBeanLocal segmentoBeanLocal;
    @EJB
    protected SubSegmentoBeanLocal subSegmentoBeanLocal;
    @EJB
    protected ProfileBeanLocal perfilBean;
    @EJB
    protected ParameterBeanLocal parameterBeanLocal;

    @Inject
    protected LoginBean loginBean;
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
    private List<Db3party> listDistributor;
    private List<Db3party> listDistributorSon;
    private List<DbChannels> listChannel;
    private List<DbSubChannels> listSubChannel;
    private List<DbSegments> listSegment;
    private List<DbSubSegments> listSubSegment;
    private List<DbPotentials> listPotential;
    private List<DbPotentials> listaPotentialAutomatic;
    private Set<DistributorPermissionDto> listDistributorPermission;
    private DistributorPermissionDto distributorPermissionDtoSelected;
    private List<DbPermissionSegments> listPermissionSegmentToPersist;
    /**
     * Distributor selected
     */
    private Db3party distributorSelected;
    private DbChannels channelSelected;
    private DbSubChannels subChannelSelected;
    private DbSegments segmentSelected;
    private DbSubSegments subSegmentSelected;
    private DbPermissionSegments permissionsegmentSelected;
    private DbPotentials potentialAutomatic;
    private DbPotentials potentialManual;
    private Set<DbPermissionSegments> listDistributorPermissionRemove;
    private String athenaCode;
    private List<DwProfiles> listaPerfiles;

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
        setListDistributor(distributorBeanLocal.searchDistributorFather(FatherDistributorEnum.FATHER.getIsFather()));
        setListDistributorSon(distributorBeanLocal.searchDistributorByFather(getListDistributor().get(0).getDb3partyId()));
        setListPotential(potentialBeanLocal.findAll());
        setListDistributorPermission(new HashSet<DistributorPermissionDto>());
        setListPermissionSegmentToPersist(new ArrayList<DbPermissionSegments>());
        setListDistributorPermissionRemove(new HashSet<DbPermissionSegments>());
        //segmentation
        setListChannel(channelBeanLocal.findAllChannel());
        initList();
        setChannelCheck(Boolean.TRUE);
        setDetailEdition(Boolean.FALSE);
        initFields();
        setListaPerfiles((List<DwProfiles>) perfilBean.findBySystem());
    }

    private void initFields() {
        setNombres("");
        setApellidos("");
        setCorreo("");
        setAthenaCode("");
        setActivo(Boolean.FALSE);
    }

    protected void initList() {
        setChannelSelected(getListChannel().get(0));
        setSubChannelSelected(getChannelSelected().getDbSubChannelsList().get(0));
        setSegmentSelected(getSubChannelSelected().getDbSegmentsList().get(0));
        setSubSegmentSelected(getSegmentSelected().getDbSubSegmentsList().get(0));
        setListSubChannel(getChannelSelected().getDbSubChannelsList());
        setListSegment(getSubChannelSelected().getDbSegmentsList());
        setListSubSegment(getSegmentSelected().getDbSubSegmentsList());
        listenerSubSegment();
        setDistributorPermissionDtoSelected(new DistributorPermissionDto(null, new HashSet<DbPermissionSegments>()));
    }

    /**
     * Crea un usuario del sistema
     */
    public void crearUsuario() {
        if (!validateListDistributorPermission()) {
            if (!validarExisteciaCorreo()) {
                try {
                    DwUsers usu = new DwUsers();
                    usu.setNameUser(getNombres().toUpperCase());
                    usu.setLastName(getApellidos().toUpperCase());
                    usu.setEmailUser(getCorreo().toUpperCase());
                    usu.setPasswordUser(DigestUtils.md5Hex("123"));
                    Audit audit = new Audit();
                    audit.setCreationDate(super.getCurrentDate());
                    audit.setCreationUser(getLoginBean().getUsuario().getEmailUser());
                    usu.setAudit(audit);
                    usu.setStateUser(isActivo() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
                    usu.setProfileId(getPerfil());
                    usu.setDistri1(getAthenaCode().toUpperCase());
                    usu.setFailedAttempt(0);
                    usu.setFirstEntry(UserEntryEnum.FIRST_ENTRY.getState());
                    if (isDetailEdition()) {
                        usu.setDistributorId(getDistributorSelected().getDb3partyId());
                        List<DbPermissionSegments> createUser_Test = usuarioBean.createUser_Test(usu, getListPermissionSegmentToPersist());
                        
                        
                        List<DwParameters> ipDatabase = parameterBeanLocal.findByKey(ParameterKeyEnum.DATABASE_IP.name());
                        List<DwParameters> userDatabase = parameterBeanLocal.findByKey(ParameterKeyEnum.USER_DATABASE.name());
                        List<DwParameters> passDatabase = parameterBeanLocal.findByKey(ParameterKeyEnum.PASS_DATABASE.name());                        
                        Connection con = ConecctionJDBC.conexionSQLServer(ipDatabase.get(0).getParameterValue(),
                                userDatabase.get(0).getParameterValue(),passDatabase.get(0).getParameterValue());
                        for (DbPermissionSegments dbPermissionSegments : createUser_Test) {
                            ConecctionJDBC.callStoreOutletsUser(con, dbPermissionSegments.getPermissionSegment(),"Insert");
                        }
                        try {
                            con.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                    } else {
                        usuarioBean.createUser(usu, null);
                    }
                    EMail email = new EMail();
                    HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    String url = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + req.getServerPort() + req.getContextPath();
                    String msg = VelocityTemplate.userCreation(url, usu.getEmailUser(), "123", getLoginBean().getDiageoApplicationBean().getPathMailTemplate());
                    email.send(new String[]{usu.getEmailUser()}, capturarValor("mail_user_creation"), msg);
                    showInfoMessage(capturarValor("usu_creado_exito"));
                    init();
                } catch (ControllerWebException ex) {
                    showErrorMessage(capturarValor("usu_creado_fallo"));
                    LOG.log(Level.SEVERE, ex.getMessage());
                } catch (UnknownHostException ex) {
                    Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void listenerDetailEdition() {
        detailEdition = !((getPerfil().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())) 
                || (getPerfil().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId()))
                || (getPerfil().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId()))
                || (getPerfil().getProfileId().equals(ProfileEnum.KAM.getId()))
                || (getPerfil().getProfileId().equals(ProfileEnum.NAM.getId()))
                );
    }

    public void listenerFindDistributorSonByFather() {
        setListDistributorSon(distributorBeanLocal.searchDistributorByFather(distributorSelected.getDb3partyId()));
    }

    public void addDistributorToUser(Db3party distriSon) {
        for (DistributorPermissionDto temp : getListDistributorPermission()) {
            if (temp.getDistributor().getDb3partyId().equals(distriSon.getDb3partyId())) {
                showWarningMessage(capturarValor("usu_msg_distri_exists"));
                return;
            }
        }
        DistributorPermissionDto dtoTemp = new DistributorPermissionDto();
        dtoTemp.setDistributor(distriSon);
        dtoTemp.setListPermissionSegment(new HashSet<DbPermissionSegments>());
        getListDistributorPermission().add(dtoTemp);
    }

    public void removeDistributorToUser(DistributorPermissionDto distriSon) {
        getListDistributorPermission().remove(distriSon);

    }

    public void seeDetailDistributorAddedUser(DistributorPermissionDto permission) {
        setDistributorPermissionDtoSelected(permission);
        RequestContext.getCurrentInstance().execute("PF('wvChain').show();");
    }

    public void selectAllChain() {
        setSubChannelCheck(Boolean.TRUE);
        setSegmentCheck(Boolean.TRUE);
        setSubSegmentCheck(Boolean.TRUE);
        setPotentialCheck(Boolean.TRUE);
    }

    public void unSelectAllChain() {
        setSubChannelCheck(Boolean.FALSE);
        setSegmentCheck(Boolean.FALSE);
        setSubSegmentCheck(Boolean.FALSE);
        setPotentialCheck(Boolean.FALSE);
    }

    public void aceptChangesChain() {
        for (DbPermissionSegments ps : getDistributorPermissionDtoSelected().getListPermissionSegment()) {
            com.diageo.diageonegocio.entidades.Audit audit = new com.diageo.diageonegocio.entidades.Audit();
            audit.setCreationDate(super.getCurrentDate());
            audit.setCreationUser(getLoginBean().getUsuario().getEmailUser());
            ps.setAudit(audit);
            getListPermissionSegmentToPersist().add(ps);
        }
        initList();
        unSelectAllChain();
        RequestContext.getCurrentInstance().execute("PF('wvChain').hide();");
    }

    /**
     * Add to the table of the popup, a record about the permissions that have
     * the distributor
     */
    public void addPermissionDistributorList() {
        DbPermissionSegments ps = new DbPermissionSegments();
        ps.setChannelCheck(isChannelCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        ps.setSubChannelCheck(isSubChannelCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        ps.setSegmentCheck(isSegmentCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        ps.setSubSegmentCheck(isSubSegmentCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
        ps.setPotentialCheck(isPotentialCheck() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());

        ps.setChannelId(getChannelSelected().getChannelId());
        if (ps.getSubChannelCheck().equals(StateEnum.ACTIVE.getState())) {
            ps.setSubChannelId(getSubChannelSelected().getSubChannelId());
        }
        if (ps.getSegmentCheck().equals(StateEnum.ACTIVE.getState())) {
            ps.setSegmentId(getSegmentSelected().getSegmentId());
        }
        if (ps.getSubSegmentCheck().equals(StateEnum.ACTIVE.getState())) {
            ps.setSubSegmentId(getSubSegmentSelected().getSubSegmentId());
        }
        if (ps.getPotentialCheck().equals(StateEnum.ACTIVE.getState())) {
            ps.setPotentialId(getPotentialAutomatic().getPotentialId());
        }
        if (!getDistributorPermissionDtoSelected().getListPermissionSegment().isEmpty()) {
            for (DbPermissionSegments perSeg : getDistributorPermissionDtoSelected().getListPermissionSegment()) {
                if (perSeg.equals(ps)) {
                    showWarningMessage(capturarValor("usu_msg_segment_exists"));
                    return;
                }
            }
        }
        ps.setDb3partyId(getDistributorPermissionDtoSelected().getDistributor());
        getDistributorPermissionDtoSelected().getListPermissionSegment().add(ps);
    }

    public void removePermissionFromPopup(DbPermissionSegments dto) {
        getListDistributorPermissionRemove().add(dto);
        getDistributorPermissionDtoSelected().getListPermissionSegment().remove(dto);
    }

    public void listenerChannel() {
        if (getChannelSelected().getDbSubChannelsList() != null && !getChannelSelected().getDbSubChannelsList().isEmpty()) {
            setListSubChannel(getChannelSelected().getDbSubChannelsList());
            setSubChannelSelected(getListSubChannel().get(0));
            this.listenerSubChannel();
        } else {
            setListSubChannel(new ArrayList<DbSubChannels>());
            setListSegment(new ArrayList<DbSegments>());
            setListSubSegment(new ArrayList<DbSubSegments>());
            setListPotential(new ArrayList<DbPotentials>());
        }
    }

    public void listenerSubChannel() {
        if (getSubChannelSelected().getDbSegmentsList() != null && !getSubChannelSelected().getDbSegmentsList().isEmpty()) {
            setSegmentSelected(getSubChannelSelected().getDbSegmentsList().get(0));
            setListSegment(getSubChannelSelected().getDbSegmentsList());
            this.listenerSegment();
        } else {
            setListSegment(new ArrayList<DbSegments>());
            setListSubSegment(new ArrayList<DbSubSegments>());
            setListPotential(new ArrayList<DbPotentials>());
        }
    }

    public void listenerSegment() {
        if (getSegmentSelected().getDbSubSegmentsList() != null && !getSegmentSelected().getDbSubSegmentsList().isEmpty()) {
            setSubSegmentSelected(getSegmentSelected().getDbSubSegmentsList().get(0));
            setListSubSegment(getSegmentSelected().getDbSubSegmentsList());
            listenerSubSegment();
        } else {
            setListSubSegment(new ArrayList<DbSubSegments>());
            setListPotential(new ArrayList<DbPotentials>());
        }
    }

    public void listenerSubSegment() {
        if (getSubSegmentSelected().getDbPotentialsList() == null || getSubSegmentSelected().getDbPotentialsList().isEmpty()) {
            setListaPotentialAutomatic(new ArrayList<DbPotentials>());
        } else {
            setPotentialAutomatic(getSubSegmentSelected().getDbPotentialsList().get(0));
            setListaPotentialAutomatic(getSubSegmentSelected().getDbPotentialsList());
        }

    }

    public String channelName(Integer id) {
        try {
            return channelBeanLocal.findById(id).getNameChannel();
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String subChannelName(Integer id) {
        if (id == null) {
            return "";
        }
        if (id == -1) {
            return capturarValor("usu_msg_all");
        }
        try {
            return subChannelBeanLocal.findById(id).getNameSubChannel();
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String segmentName(Integer id) {
        if (id == null) {
            return "";
        }
        if (id == -1) {
            return capturarValor("usu_msg_all");
        }
        try {
            return segmentoBeanLocal.findById(id).getNameSegment();
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String subSegmentName(Integer id) {
        if (id == null) {
            return "";
        }
        if (id == -1) {
            return capturarValor("usu_msg_all");
        }
        try {
            return subSegmentoBeanLocal.findById(id).getNameSubsegment();
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String potentialName(Integer id) {
        if (id == null) {
            return "";
        }
        if (id == -1) {
            return capturarValor("usu_msg_all");
        }
        try {
            return potentialBeanLocal.findById(id).getNamePotential();
        } catch (Exception ex) {
            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void listenerCheckSubChannel() {
        if (!isSubChannelCheck()) {
            setSegmentCheck(Boolean.FALSE);
            setSubSegmentCheck(Boolean.FALSE);
            setPotentialCheck(Boolean.FALSE);
        }
    }

    public void listenerCheckSegment() {
        if (!isSegmentCheck()) {
            setSubSegmentCheck(Boolean.FALSE);
            setPotentialCheck(Boolean.FALSE);
        } else {
            setSubChannelCheck(Boolean.TRUE);
        }
    }

    public void listenerCheckSubSegment() {
        if (!isSubSegmentCheck()) {
            setPotentialCheck(Boolean.FALSE);
        } else {
            setSegmentCheck(Boolean.TRUE);
            setSubChannelCheck(Boolean.TRUE);
        }
    }

    public void listenerPotentialCheck() {
        setSubSegmentCheck(Boolean.TRUE);
        setSegmentCheck(Boolean.TRUE);
        setSubChannelCheck(Boolean.TRUE);
    }

    /**
     * Valida por correo si existe o no el usuario
     *
     * @return true si el usuario ya existe con ese correo, false por lo
     * contrario
     */
    protected boolean validarExisteciaCorreo() {
        try {
            usuarioBean.findEmail(getCorreo().toUpperCase());
            showWarningMessage(capturarValor("usu_correo_existe"));
            return true;
        } catch (ControllerWebException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            return false;
        }
    }

    /**
     * validates the permissions list
     *
     * @return true if some permissions list is empty false when all is fine
     */
    protected boolean validateListDistributorPermission() {
        if (isDetailEdition()) {
            if (getListDistributorPermission().isEmpty()) {
                showWarningMessage(capturarValor("usu_msg_validate_list_permission_distributor"));
                return true;
            }
            for (DistributorPermissionDto dto : getListDistributorPermission()) {
                if (dto.getListPermissionSegment().isEmpty()) {
                    showWarningMessage(capturarValor("usu_msg_validate_list_permission"));
                    return true;
                }
            }
        }
        return false;
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
    public List<Db3party> getListDistributor() {
        return listDistributor;
    }

    /**
     * @param listDistributor the listDistributor to set
     */
    public void setListDistributor(List<Db3party> listDistributor) {
        this.listDistributor = listDistributor;
    }

    /**
     * @return the distributorSelected
     */
    public Db3party getDistributorSelected() {
        return distributorSelected;
    }

    /**
     * @param distributorSelected the distributorSelected to set
     */
    public void setDistributorSelected(Db3party distributorSelected) {
        this.distributorSelected = distributorSelected;
    }

    /**
     * @return the listDistributorSon
     */
    public List<Db3party> getListDistributorSon() {
        return listDistributorSon;
    }

    /**
     * @param listDistributorSon the listDistributorSon to set
     */
    public void setListDistributorSon(List<Db3party> listDistributorSon) {
        this.listDistributorSon = listDistributorSon;
    }

    /**
     * @return the listChannel
     */
    public List<DbChannels> getListChannel() {
        return listChannel;
    }

    /**
     * @param listChannel the listChannel to set
     */
    public void setListChannel(List<DbChannels> listChannel) {
        this.listChannel = listChannel;
    }

    /**
     * @return the channelSelected
     */
    public DbChannels getChannelSelected() {
        return channelSelected;
    }

    /**
     * @param channelSelected the channelSelected to set
     */
    public void setChannelSelected(DbChannels channelSelected) {
        this.channelSelected = channelSelected;
    }

    /**
     * @return the subChannelSelected
     */
    public DbSubChannels getSubChannelSelected() {
        return subChannelSelected;
    }

    /**
     * @param subChannelSelected the subChannelSelected to set
     */
    public void setSubChannelSelected(DbSubChannels subChannelSelected) {
        this.subChannelSelected = subChannelSelected;
    }

    /**
     * @return the segmentSelected
     */
    public DbSegments getSegmentSelected() {
        return segmentSelected;
    }

    /**
     * @param segmentSelected the segmentSelected to set
     */
    public void setSegmentSelected(DbSegments segmentSelected) {
        this.segmentSelected = segmentSelected;
    }

    /**
     * @return the subSegmentSelected
     */
    public DbSubSegments getSubSegmentSelected() {
        return subSegmentSelected;
    }

    /**
     * @param subSegmentSelected the subSegmentSelected to set
     */
    public void setSubSegmentSelected(DbSubSegments subSegmentSelected) {
        this.subSegmentSelected = subSegmentSelected;
    }

    /**
     * @return the permissionsegmentSelected
     */
    public DbPermissionSegments getPermissionsegmentSelected() {
        return permissionsegmentSelected;
    }

    /**
     * @param permissionsegmentSelected the permissionsegmentSelected to set
     */
    public void setPermissionsegmentSelected(DbPermissionSegments permissionsegmentSelected) {
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
    public List<DbPotentials> getListPotential() {
        return listPotential;
    }

    /**
     * @param listPotential the listPotential to set
     */
    public void setListPotential(List<DbPotentials> listPotential) {
        this.listPotential = listPotential;
    }

    public List<DbSubChannels> getListSubChannel() {
        return listSubChannel;
    }

    public void setListSubChannel(List<DbSubChannels> listSubChannel) {
        this.listSubChannel = listSubChannel;
    }

    public List<DbSegments> getListSegment() {
        return listSegment;
    }

    public void setListSegment(List<DbSegments> listSegment) {
        this.listSegment = listSegment;
    }

    public List<DbSubSegments> getListSubSegment() {
        return listSubSegment;
    }

    public void setListSubSegment(List<DbSubSegments> listSubSegment) {
        this.listSubSegment = listSubSegment;
    }

    public DbPotentials getPotentialAutomatic() {
        return potentialAutomatic;
    }

    public void setPotentialAutomatic(DbPotentials potentialAutomatic) {
        this.potentialAutomatic = potentialAutomatic;
    }

    /**
     * @return the listaPotentialAutomatic
     */
    public List<DbPotentials> getListaPotentialAutomatic() {
        return listaPotentialAutomatic;
    }

    /**
     * @param listaPotentialAutomatic the listaPotentialAutomatic to set
     */
    public void setListaPotentialAutomatic(List<DbPotentials> listaPotentialAutomatic) {
        this.listaPotentialAutomatic = listaPotentialAutomatic;
    }

    public DbPotentials getPotentialManual() {
        return potentialManual;
    }

    public void setPotentialManual(DbPotentials potentialManual) {
        this.potentialManual = potentialManual;
    }

    public Set<DistributorPermissionDto> getListDistributorPermission() {
        return listDistributorPermission;
    }

    public void setListDistributorPermission(Set<DistributorPermissionDto> listDistributorPermission) {
        this.listDistributorPermission = listDistributorPermission;
    }

    public DistributorPermissionDto getDistributorPermissionDtoSelected() {
        return distributorPermissionDtoSelected;
    }

    public void setDistributorPermissionDtoSelected(DistributorPermissionDto distributorPermissionDtoSelected) {
        this.distributorPermissionDtoSelected = distributorPermissionDtoSelected;
    }

    public List<DbPermissionSegments> getListPermissionSegmentToPersist() {
        return listPermissionSegmentToPersist;
    }

    public void setListPermissionSegmentToPersist(List<DbPermissionSegments> listPermissionSegmentToPersist) {
        this.listPermissionSegmentToPersist = listPermissionSegmentToPersist;
    }

    /**
     * @return the listDistributorPermissionRemove
     */
    public Set<DbPermissionSegments> getListDistributorPermissionRemove() {
        return listDistributorPermissionRemove;
    }

    /**
     * @param listDistributorPermissionRemove the
     * listDistributorPermissionRemove to set
     */
    public void setListDistributorPermissionRemove(Set<DbPermissionSegments> listDistributorPermissionRemove) {
        this.listDistributorPermissionRemove = listDistributorPermissionRemove;
    }

    /**
     * @return the athenaCode
     */
    public String getAthenaCode() {
        return athenaCode;
    }

    /**
     * @param athenaCode the athenaCode to set
     */
    public void setAthenaCode(String athenaCode) {
        this.athenaCode = athenaCode;
    }

    public List<DwProfiles> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<DwProfiles> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

}
