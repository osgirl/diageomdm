/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.outlet;

import com.diageo.admincontrollerweb.beans.ParameterBeanLocal;
import com.diageo.admincontrollerweb.beans.RelationUserBeanLocal;
import com.diageo.admincontrollerweb.entities.DwRelationUsers;
import com.diageo.admincontrollerweb.enums.ProfileEnum;
import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.admincontrollerweb.enums.StatusSystemMDM;
import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageomdmweb.bean.dto.DbChainsDto;
import com.diageo.diageomdmweb.enums.TableOutletFields;
import com.diageo.diageomdmweb.enums.TablesEnum;
import com.diageo.diageomdmweb.enums.WaitingEnum;
import com.diageo.diageomdmweb.jdbc.ConecctionJDBC;
import com.diageo.diageonegocio.beans.ChainUserBeanLocal;
import com.diageo.diageonegocio.beans.DbPartySalesBeanLocal;
import com.diageo.diageonegocio.beans.DiageoLogBeanLocal;
import com.diageo.diageonegocio.beans.LogTerritoryBean;
import com.diageo.diageonegocio.beans.PotentialBeanLocal;
import com.diageo.diageonegocio.entidades.Audit;
import com.diageo.diageonegocio.entidades.Db3partySales;
import com.diageo.diageonegocio.entidades.DbChains;
import com.diageo.diageonegocio.entidades.DbChainsUsers;
import com.diageo.diageonegocio.entidades.DbCustomers;
import com.diageo.diageonegocio.entidades.DbLogTerritory;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.entidades.DbPhones;
import com.diageo.diageonegocio.entidades.DbPotentials;
import com.diageo.diageonegocio.entidades.DiageoLog;
import com.diageo.diageonegocio.enums.StateDiageo;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.data.FilterEvent;

/**
 *
 * @author EDUARDO
 */
@Named(value = "chainSearchBean")
@ViewScoped
public class ChainSearchBean extends CreateChainBean implements Serializable {

    @EJB
    private ChainUserBeanLocal chainUserBeanLocal;
    @EJB
    private ParameterBeanLocal parameterBeanLocal;
    @EJB
    private RelationUserBeanLocal relationUserBeanLocal;
    @EJB
    private DiageoLogBeanLocal diageoLogBeanLocal;
    @EJB
    private PotentialBeanLocal potentialBeanLocal;
    @EJB
    private LogTerritoryBean logTerritoryBean;
    @Inject
    private LoginBean loginBean;
    private List<DbChains> chainsList;
    private List<DbChains> chainsListFiltered;
    private List<DbChains> chainsListSelected;
    private List<DbPhones> phonesDelete;
    private List<DbPermissionSegments> listPermi;
    private List<DbCustomers> listCustomerDelete;
    private boolean seeDetail;
    private Integer idChain;
    private boolean renderMassiveApproval;
    private boolean disabledFields;
    private boolean disabledSegmentation;
    private String buttonNameCommit;
    private Map<String, Object> filtersTable;
    private List<Integer> listId;
    private DbChains chainSelected;
    private List<SelectItem> listFilterStatusMDM;
    private DbChainsDto chainClonable;
    private String codeEanTemp;

    /**
     * Es el potencial que tiene asignado el outlet al ver el detalle del outlet
     */
    private DbPotentials currentPotential;

    /**
     * Creates a new instance of ChainSearchBean
     */
    public ChainSearchBean() {
    }

    @PostConstruct
    @Override
    public void init() {
        setFlagOutletInactive(true);
        setSeeDetail(Boolean.TRUE);
        super.init();
        setDisabledSegmentation(Boolean.FALSE);
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())) {
            setChainsList(chainBeanLocal.findAllChains());
            setRenderMassiveApproval(Boolean.FALSE);
            setButtonNameCommit(capturarValor("btn_send"));
        } else {
            if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId())) {
                setButtonNameCommit(capturarValor("btn_send_approved"));
            } else {
                setButtonNameCommit(capturarValor("btn_approved"));
            }

            setDisabledFields(Boolean.TRUE);
            boolean bol = !(getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())
                    || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_CADENAS.getId()));
            setRenderMassiveApproval(bol);
            if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())
                    || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())) {
                listId = relationUserBeanLocal.findByParentId(getLoginBean().getUsuario().getUserId());
                setChainsList(chainUserBeanLocal.findByUserIdJoinIn(listId));
            } else {
                setChainsList(chainUserBeanLocal.findByUserIdJoin(getLoginBean().getUsuario().getUserId()));
            }
        }
        loadListStatusMdm();
        setChainsListFiltered(getChainsList());
        setListCustomerDelete(new ArrayList<DbCustomers>());
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Object obj = session.getAttribute(CHAIN);
        if (obj != null) {
            setSeeDetail(Boolean.FALSE);
            Integer id = Integer.parseInt(obj + "");
            DbChains chain = chainBeanLocal.findById(id);
            detailChain(chain);
        }
        chainsListSelected = new ArrayList<>();
    }

    private void loadListStatusMdm() {
        listFilterStatusMDM = new ArrayList<>();
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_CADENAS.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())) {
            listFilterStatusMDM.add(new SelectItem("APPROVED", capturarValor("sta_approval")));
            listFilterStatusMDM.add(new SelectItem("PENDING_APPROVAL", capturarValor("sta_pending_approval")));
            listFilterStatusMDM.add(new SelectItem("PENDING_KAM_TMC_CHAINS", capturarValor("sta_pending_kam")));
            listFilterStatusMDM.add(new SelectItem("REJECT", capturarValor("sta_reject")));
            listFilterStatusMDM.add(new SelectItem("PENDING_KIERNAN", capturarValor("code_pending_kiernan")));
        } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM_CADENAS.getId())) {
            listFilterStatusMDM.add(new SelectItem("PENDING_KAM_TMC_CHAINS", capturarValor("sta_pending_kam")));
            listFilterStatusMDM.add(new SelectItem("PENDING_KIERNAN", capturarValor("code_pending_kiernan")));
            listFilterStatusMDM.add(new SelectItem("PENDING_APPROVAL", capturarValor("sta_pending_approval")));
            listFilterStatusMDM.add(new SelectItem("APPROVED", capturarValor("sta_approval")));
            listFilterStatusMDM.add(new SelectItem("REJECT", capturarValor("sta_reject")));
        } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())) {
            listFilterStatusMDM.add(new SelectItem("PENDING_APPROVAL", capturarValor("sta_pending_approval")));
            listFilterStatusMDM.add(new SelectItem("PENDING_KIERNAN", capturarValor("code_pending_kiernan")));
            listFilterStatusMDM.add(new SelectItem("PENDING_KAM_TMC_CHAINS", capturarValor("sta_pending_kam")));
            listFilterStatusMDM.add(new SelectItem("APPROVED", capturarValor("sta_approval")));
            listFilterStatusMDM.add(new SelectItem("REJECT", capturarValor("sta_reject")));
        }
    }

    public void detailChain(DbChains chain) {
        try {
            chainClonable = new DbChainsDto(chain.clone(), getCurrentDate(), getLoginBean().getUsuario().getUserId());
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ChainSearchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChainSelected(chain);
        setIdChain(chain.getChainId());
        setSite(chain.getSite());
        setAddress(chain.getAddress());
        setBusinessName(chain.getBusinessName());
        setEanCode(chain.getCodeEan());
        codeEanTemp = chain.getCodeEan();
        setClusterSelected(chain.getDbClusterId());
        setDb3PartySelected(chain.getDbPartyId());
        setListPhones(chain.getDbPhonesList());
        setDepartamentSelected(chain.getDbTownId().getDepartamentId());
        setTownSelected(chain.getDbTownId());
        setKiernan(chain.getKiernanId());
        setLatitude(chain.getLatitude());
        setLongitude(chain.getLongitude());
        setChainName(chain.getNameChain());
        setNeighborhood(chain.getNeighborhood());
        setStatus(chain.getStatusChain());
        setPotentialSelected(chain.getPotentialId());
        currentPotential = chain.getPotentialId();
        this.setSegmentation(chain);

//        setSubSegmentSelected(chain.getSubSegmentId());
//        setSegmentSelected(getSubSegmentSelected().getSegmentId());
//        setSubChannelSelected(getSegmentSelected().getSubChannelId());
//        setChannelSelected(getSubChannelSelected().getChannelId());
        setListSubChannel(getChannelSelected().getDbSubChannelsList());
        setListSegment(getSubChannelSelected().getDbSegmentsList());
        setListSubSegment(getSegmentSelected().getDbSubSegmentsList());
        setListPotential(getSubSegmentSelected().getDbPotentialsList());
        setPhonesDelete(new ArrayList<DbPhones>());
        setListCustomers(chain.getDbCustomerList());
        setSeeDetail(Boolean.FALSE);
        setLayerSelected(chain.getLayerId());
        setFasciaSelected(chain.getFascia());
        setOwnerSelected(chain.getOwnerId());
        if (chain.getDb3partySaleId() != null) {
            setSellerSelected(chain.getDb3partySaleId());
        } else {
            setSellerSelected(new Db3partySales());
        }
    }

    private void setSegmentation(DbChains cha) {
        try {
            boolean bol = cha.getSubSegmentId().getSegmentId().getSubChannelId().getChannelId().getStateChannel().equals(StateDiageo.ACTIVO.getId());
            if (bol) {
                setChannelSelected(cha.getSubSegmentId().getSegmentId().getSubChannelId().getChannelId());
                if (cha.getSubSegmentId().getSegmentId().getSubChannelId().getStateSubChannel().equals(StateDiageo.ACTIVO.getId())) {
                    setSubChannelSelected(cha.getSubSegmentId().getSegmentId().getSubChannelId());
                    if (cha.getSubSegmentId().getSegmentId().getStateSegment().equals(StateDiageo.ACTIVO.getId())) {
                        setSegmentSelected(cha.getSubSegmentId().getSegmentId());
                        if (cha.getSubSegmentId().getStateSubSegment().equals(StateDiageo.ACTIVO.getId())) {
                            setSubSegmentSelected(cha.getSubSegmentId());
                        } else {
                            setSubSegmentSelected(subSegmentoBeanLocal.findById(0));
                        }
                    } else {
                        setSubSegmentSelected(subSegmentoBeanLocal.findById(0));
                        setSegmentSelected(segmentoBeanLocal.findById(0));
                    }
                } else {
                    setSubChannelSelected(subChannelBeanLocal.findById(0));
                    setSubSegmentSelected(subSegmentoBeanLocal.findById(0));
                    setSegmentSelected(segmentoBeanLocal.findById(0));
                }
            } else {
                setSubSegmentSelected(subSegmentoBeanLocal.findById(0));
                setSegmentSelected(segmentoBeanLocal.findById(0));
                setSubChannelSelected(subChannelBeanLocal.findById(0));
                setChannelSelected(channelBeanLocal.findById(0));
            }
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, ex.getMessage());

        }
    }

    @Override
    public void listenerSubSegment() {
        if (getSubSegmentSelected() == null || getSubSegmentSelected().getDbPotentialsList() == null || getSubSegmentSelected().getDbPotentialsList().isEmpty()) {
            setListPotential(new ArrayList<DbPotentials>());
        } else {
            String potentialName;
            if (currentPotential != null) {
                potentialName = currentPotential.getNamePotential();
                DbPotentials potTemp = potentialBeanLocal.findByNamePotentialAndSubSegmentId(getSubSegmentSelected().getSubSegmentId(), potentialName);
                if (potTemp != null) {
                    setPotentialSelected(potTemp);
                } else {
                    for (DbPotentials po : getSubSegmentSelected().getDbPotentialsList()) {
                        if (po.getLowPotential().equals(StateEnum.ACTIVE.getState())) {
                            setPotentialSelected(po);
                            break;
                        }
                    }
                }
                setListPotential(getSubSegmentSelected().getDbPotentialsList());
            }
        }
    }

    private void deletCustomerChain() {
        if (!getListCustomerDelete().isEmpty()) {
            for (DbCustomers dbCusto : getListCustomerDelete()) {
                chainBeanLocal.deleteCustomerChain(dbCusto.getCustomerId(), getIdChain());
            }
        }
    }

    @Override
    public void saveChain() {
        if (isFlagOutletInactive()) {
            if (!validateCodeEan()) {
                try {
                    DbChains chain = getChainSelected();
                    chain.setAddress(getAddress() != null ? getAddress().toUpperCase() : "");
                    chain.setBusinessName(getBusinessName() != null ? getBusinessName().toUpperCase() : "");
                    chain.setCodeEan(getEanCode() != null ? getEanCode().toUpperCase() : "");
                    chain.setDbClusterId(getClusterSelected());
                    chain.setDbPartyId(getDb3PartySelected());
                    super.cleanIdPhones();
                    chain.setDbPhonesList(getListPhones());
                    chain.setDbTownId(getTownSelected());
                    chain.setKiernanId(getKiernan() != null ? getKiernan().toUpperCase() : "");
                    chain.setLatitude(getLatitude());
                    chain.setLongitude(getLongitude());
                    chain.setNameChain(getChainName() != null ? getChainName().toUpperCase() : "");
                    chain.setNeighborhood(getNeighborhood() != null ? getNeighborhood().toUpperCase() : "");
                    chain.setPotentialId(getPotentialSelected());
                    chain.setSubSegmentId(getSubSegmentSelected());
                    chain.setStatusChain(getStatus());
                    chain.setDbCustomerList(getListCustomers());
                    chain.setFascia(getFasciaSelected());
                    chain.setOwnerId(getOwnerSelected());
                    chain.setDb3partySaleId(getSellerSelected());
                    Audit audit = new Audit();
                    audit.setCreationDate(chain.getAudit() != null ? chain.getAudit().getCreationDate() : null);
                    audit.setCreationUser(chain.getAudit() != null ? chain.getAudit().getCreationUser() : null);
                    audit.setModificationDate(super.getCurrentDate());
                    audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
                    chain.setAudit(audit);
                    chain.setLayerId(getLayerSelected());
                    chain.setSite(getSite());
                    createLogTerritory(chain);
                    this.deletePhone();
                    if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())
                            || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())) {
                        if (chain.getStatusMDM().equals(StatusSystemMDM.PENDING_APPROVAL.name())) {
                            if (chain.getKiernanId() == null || chain.getKiernanId().isEmpty()) {
                                chain.setStatusMDM(StatusSystemMDM.PENDING_KIERNAN.name());
                            } else {
                                chain.setStatusMDM(StatusSystemMDM.APPROVED.name());
                            }
                        }
                    } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId())) {
                        if (chain.getStatusMDM().equals(StatusSystemMDM.PENDING_KAM_TMC_CHAINS.name())
                                || chain.getStatusMDM().equals(StatusSystemMDM.REJECT.name())
                                || chain.getStatusMDM().equals(StatusSystemMDM.APPROVED.name())) {
                            if (!chainClonable.isNotificationChangedSegmentation(chain)) {
                                chain.setStatusMDM(StatusSystemMDM.PENDING_APPROVAL.name());
                            }
                        }
                    } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM_CADENAS.getId())) {
                        if (chain.getKiernanId() == null || chain.getKiernanId().isEmpty()) {
                            chain.setStatusMDM(StatusSystemMDM.PENDING_KIERNAN.name());
                        } else {
                            chain.setStatusMDM(StatusSystemMDM.APPROVED.name());
                        }
                    } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())) {
                        if (chain.getKiernanId() == null || chain.getKiernanId().isEmpty()) {
                            chain.setStatusMDM(StatusSystemMDM.PENDING_KIERNAN.name());
                        } else {
                            chain.setStatusMDM(StatusSystemMDM.APPROVED.name());
                        }
                    }
                    if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_CADENAS.getId())) {
                        if (chain.getStatusMDM().equals(StatusSystemMDM.APPROVED.name())) {
                            deletCustomerChain();
                            chainBeanLocal.updateChain(chain);
                            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
                        } else {
                            showInfoMessage(capturarValor("msg_cp_a_warning"));
                        }
                    } else {
                        deletCustomerChain();
                        chainBeanLocal.updateChain(chain);
                        showInfoMessage(capturarValor("sis_datos_guardados_exito"));
                    }
                    createLog(chain);
                    Connection con = ConecctionJDBC.conexionSQLServer(ipDatabase.get(0).getParameterValue(),
                            userDatabase.get(0).getParameterValue(), passDatabase.get(0).getParameterValue());
                    ConecctionJDBC.callStoreProcedureDBChains(con, chain.getChainId());
                    setSeeDetail(Boolean.TRUE);
                } catch (DiageoBusinessException ex) {
                    Logger.getLogger(CreateChainBean.class.getName()).log(Level.SEVERE, null, ex);
                    showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
                }
            } else {
                showWarningMessage(capturarValor("chain_meg_code_ean"));
            }
        }
        setFlagOutletInactive(true);
    }

    @Override
    public boolean validateCodeEan() {
        DbChains findByCodeEAN = chainBeanLocal.findByCodeEAN(getEanCode());
        if (findByCodeEAN != null) {
            return !findByCodeEAN.getCodeEan().equals(codeEanTemp);
        }
        return false;
    }

    public void createLog(DbChains cha) {
        chainClonable.changes(cha);
        List<DiageoLog> listDiageoLog = chainClonable.getListDiageoLog();
        if (!listDiageoLog.isEmpty()) {
            for (DiageoLog diageoLog : listDiageoLog) {
                diageoLogBeanLocal.createLog(diageoLog);
            }
        }
        chainClonable = new DbChainsDto(cha, getCurrentDate(), getLoginBean().getUsuario().getUserId());
    }
    
     public void createLogTerritory(DbChains cha) {
        if (cha.getDb3partySaleId() != null && chainClonable.getChain().getDb3partySaleId() != null) {
            if (cha.getDb3partySaleId().getDb3partyTerritory() != null && chainClonable.getChain().getDb3partySaleId().getDb3partyTerritory() != null) {
                if (!cha.getDb3partySaleId().getDb3partyTerritory().getNameTerritory().equals(chainClonable.getChain().getDb3partySaleId().getDb3partyTerritory().getNameTerritory())) {
                    DbLogTerritory logTerritory = new DbLogTerritory();
                    logTerritory.setCreationDate(getCurrentDate());
                    logTerritory.setCreationUser(getLoginBean().getUsuario().getEmailUser());
                    logTerritory.setDbOutletId(cha.getChainId());
                    logTerritory.setFieldLog(TableOutletFields.TERRITORY.name());
                    logTerritory.setOutletType(TablesEnum.DB_CHAINS.name());
                    logTerritory.setWaitingStatus(WaitingEnum.REMOVAL.getState());
                    logTerritory.setValueLog(chainClonable.getChain().getDb3partySaleId().getDb3partyTerritory().getNameTerritory());
                    logTerritoryBean.create(logTerritory);
                    logTerritory.setWaitingStatus(WaitingEnum.ACTIVATION.getState());
                    logTerritory.setValueLog(cha.getDb3partySaleId().getDb3partyTerritory().getNameTerritory());
                    logTerritoryBean.create(logTerritory);
                }
            }
        }
    }


    public void buttonBack() {
        setSeeDetail(Boolean.TRUE);
    }

    public void rejectChain() {
        try {
            DbChains chain = getChainSelected();
            if (chain.getStatusMDM().equals(StatusSystemMDM.PENDING_APPROVAL.name())) {
                chain.setStatusMDM(StatusSystemMDM.REJECT.name());
                Audit audit = new Audit();
                audit.setCreationDate(chain.getAudit() != null ? chain.getAudit().getCreationDate() : null);
                audit.setCreationUser(chain.getAudit() != null ? chain.getAudit().getCreationUser() : null);
                audit.setModificationDate(super.getCurrentDate());
                audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
                chain.setAudit(audit);
                chainBeanLocal.updateChain(chain);
                showInfoMessage(capturarValor("sis_datos_guardados_exito"));
                setSeeDetail(Boolean.TRUE);
            } else {
                showWarningMessage(capturarValor("chain_meg_no_reject_outlet"));
            }
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(ChainSearchBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void approbatrionMasive() {
        for (DbChains ch : getChainsListFiltered()) {
            try {
                boolean update = true;
                if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())
                        || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())) {
                    DbChainsUsers chainIdTemp = chainUserBeanLocal.findByChainId(ch.getChainId());
                    if (chainIdTemp != null) {
                        if (ch.getStatusMDM().equals(StatusSystemMDM.PENDING_APPROVAL.name())) {
                            List<DwRelationUsers> listTemp = relationUserBeanLocal.findByUserIdAndParent(chainIdTemp.getDbChainsUsersPK().getUserId(), getLoginBean().getUsuario().getUserId());
                            if (!listTemp.isEmpty()) {
                                update = listTemp.get(0).getStateApproved();
                            }
                            if (update) {
                                if (ch.getKiernanId() == null || ch.getKiernanId().isEmpty()) {
                                    ch.setStatusMDM(StatusSystemMDM.PENDING_KIERNAN.name());
                                } else {
                                    ch.setStatusMDM(StatusSystemMDM.APPROVED.name());
                                }
                            }
                        }
                    }

                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId())) {
                    if (ch.getStatusMDM().equals(StatusSystemMDM.PENDING_KAM_TMC_CHAINS.name())
                            || ch.getStatusMDM().equals(StatusSystemMDM.REJECT.name())
                            || ch.getStatusMDM().equals(StatusSystemMDM.APPROVED.name())) {
                        ch.setStatusMDM(StatusSystemMDM.PENDING_APPROVAL.name());
                    }
                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM_CADENAS.getId())) {
                    ch.setStatusMDM(StatusSystemMDM.APPROVED.name());
                }
                if (update) {
                    Audit audit = new Audit();
                    audit.setCreationDate(ch.getAudit() != null ? ch.getAudit().getCreationDate() : null);
                    audit.setCreationUser(ch.getAudit() != null ? ch.getAudit().getCreationUser() : null);
                    audit.setModificationDate(super.getCurrentDate());
                    audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
                    ch.setAudit(audit);
                    chainBeanLocal.updateChain(ch);
                }
            } catch (DiageoBusinessException ex) {
                Logger.getLogger(ChainSearchBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        showInfoMessage(capturarValor("sis_msg_record_outlet_change"));
    }

    public void approvedSelected() {
        for (DbChains chain : chainsListSelected) {
            try {
                boolean update = true;
                if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())
                        || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())) {
                    DbChainsUsers chainIdTemp = chainUserBeanLocal.findByChainId(chain.getChainId());
                    if (chainIdTemp != null && chain.getStatusMDM().equals(StatusSystemMDM.PENDING_APPROVAL.name())) {
                        List<DwRelationUsers> listTemp = relationUserBeanLocal.findByUserIdAndParent(chainIdTemp.getDbChainsUsersPK().getUserId(), getLoginBean().getUsuario().getUserId());
                        if (!listTemp.isEmpty()) {
                            update = listTemp.get(0).getStateApproved();
                        }
                        if (update) {
                            if (chain.getKiernanId() == null || chain.getKiernanId().isEmpty()) {
                                chain.setStatusMDM(StatusSystemMDM.PENDING_KIERNAN.name());
                            } else {
                                chain.setStatusMDM(StatusSystemMDM.APPROVED.name());
                            }
                        }
                    }
                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId())) {
                    if (chain.getStatusMDM().equals(StatusSystemMDM.PENDING_KAM_TMC_CHAINS.name())
                            || chain.getStatusMDM().equals(StatusSystemMDM.REJECT.name())) {
                        chain.setStatusMDM(StatusSystemMDM.PENDING_APPROVAL.name());
                    }
                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM_CADENAS.getId())) {
                    chain.setStatusMDM(StatusSystemMDM.APPROVED.name());
                }
                if (update) {
                    Audit audit = new Audit();
                    audit.setCreationDate(chain.getAudit() != null ? chain.getAudit().getCreationDate() : null);
                    audit.setCreationUser(chain.getAudit() != null ? chain.getAudit().getCreationUser() : null);
                    audit.setModificationDate(super.getCurrentDate());
                    audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
                    chain.setAudit(audit);
                    chainBeanLocal.updateChain(chain);
                }
            } catch (DiageoBusinessException ex) {
                Logger.getLogger(ChainSearchBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        showInfoMessage(capturarValor("sis_msg_record_outlet_change"));
    }

    public void rejectAllChain() {
        for (DbChains ch : getChainsListFiltered()) {
            try {
                if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())
                        || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())) {
                    boolean update = true;
                    DbChainsUsers chainIdTemp = chainUserBeanLocal.findByChainId(ch.getChainId());
                    if (chainIdTemp != null && ch.getStatusMDM().equals(StatusSystemMDM.PENDING_APPROVAL.name())) {
                        List<DwRelationUsers> listTemp = relationUserBeanLocal.findByUserIdAndParent(chainIdTemp.getDbChainsUsersPK().getUserId(), getLoginBean().getUsuario().getUserId());
                        if (!listTemp.isEmpty()) {
                            update = listTemp.get(0).getStateApproved();
                        }
                        if (update) {
                            ch.setStatusMDM(StatusSystemMDM.REJECT.name());
                            Audit audit = new Audit();
                            audit.setCreationDate(ch.getAudit() != null ? ch.getAudit().getCreationDate() : null);
                            audit.setCreationUser(ch.getAudit() != null ? ch.getAudit().getCreationUser() : null);
                            audit.setModificationDate(super.getCurrentDate());
                            audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
                            ch.setAudit(audit);
                            chainBeanLocal.updateChain(ch);
                        }
                    }

                }
            } catch (DiageoBusinessException ex) {
                Logger.getLogger(ChainSearchBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())
//                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())) {
//            chainUserBeanLocal.updateAllChainsIn(filtersTable, listId, StatusSystemMDM.REJECT.name());
//        }
        showInfoMessage(capturarValor("sis_msg_record_outlet_change"));
    }

    public void rejectAllChainSelected() {
        for (DbChains chain : chainsListSelected) {
            try {
                if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())
                        || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())) {
                    if (chain.getStatusMDM().equals(StatusSystemMDM.PENDING_APPROVAL.name())) {
                        boolean update = true;
                        DbChainsUsers chainIdTemp = chainUserBeanLocal.findByChainId(chain.getChainId());
                        if (chainIdTemp != null) {
                            List<DwRelationUsers> listTemp = relationUserBeanLocal.findByUserIdAndParent(chainIdTemp.getDbChainsUsersPK().getUserId(), getLoginBean().getUsuario().getUserId());
                            if (!listTemp.isEmpty()) {
                                update = listTemp.get(0).getStateApproved();
                            }
                        }
                        if (update) {
                            chain.setStatusMDM(StatusSystemMDM.REJECT.name());
                            Audit audit = new Audit();
                            audit.setCreationDate(chain.getAudit() != null ? chain.getAudit().getCreationDate() : null);
                            audit.setCreationUser(chain.getAudit() != null ? chain.getAudit().getCreationUser() : null);
                            audit.setModificationDate(super.getCurrentDate());
                            audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
                            chain.setAudit(audit);
                            chainBeanLocal.updateChain(chain);
                        }
                    }
                }
            } catch (DiageoBusinessException ex) {
                Logger.getLogger(ChainSearchBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        showInfoMessage(capturarValor("sis_msg_record_outlet_change"));
    }

    public boolean disabledKiernan() {
        return !(getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId()));
    }

    public boolean isDisabledGeography() {
        return !(getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM_CADENAS.getId()));
    }

    @Override
    public void deletePhone(DbPhones phone) {
        getListPhones().remove(phone);
        getPhonesDelete().add(phone);
    }

    @Override
    public void deleteCustomer(DbCustomers custo) {
        getListCustomers().remove(custo);
        getListCustomerDelete().add(custo);

    }

    public void listenerFilterList(FilterEvent fe) {
        if (!fe.getFilters().isEmpty()) {
            filtersTable = fe.getFilters();
        }
    }

    public boolean isRenderButtonRejectTable() {
        return getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId());
    }

    public boolean isRenderButtonReject() {
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())) {
            DbChainsUsers chainIdTemp = chainUserBeanLocal.findByChainId(getIdChain());
            if (chainIdTemp != null) {
                List<DwRelationUsers> listTemp = relationUserBeanLocal.findByUserIdAndParent(chainIdTemp.getDbChainsUsersPK().getUserId(), getLoginBean().getUsuario().getUserId());
                if (!listTemp.isEmpty()) {
                    return listTemp.get(0).getStateApproved();
                }
            }
        }
        return false;
    }

    public boolean isRenderButtonApproved() {
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())) {
            DbChainsUsers chainIdTemp = chainUserBeanLocal.findByChainId(getIdChain());
            Logger.getLogger(ChainSearchBean.class.getName()).log(Level.INFO, "chainIdTemp={0}", chainIdTemp);
            if (chainIdTemp != null) {
                List<DwRelationUsers> listTemp = relationUserBeanLocal.findByUserIdAndParent(chainIdTemp.getDbChainsUsersPK().getUserId(), getLoginBean().getUsuario().getUserId());
                for (DwRelationUsers dwRelationUsers : listTemp) {
                    System.out.println("APROBADO" + dwRelationUsers.getStateApproved());
                    Logger.getLogger(ChainSearchBean.class.getName()).log(Level.INFO, "APROBADO={0}", dwRelationUsers.getStateApproved());
                }
                if (!listTemp.isEmpty()) {
                    Logger.getLogger(ChainSearchBean.class.getName()).log(Level.INFO, "flag={0}", listTemp.get(0).getStateApproved());
                    return listTemp.get(0).getStateApproved();
                }
            }
            return false;
        } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_CADENAS.getId())) {
            return (getChainSelected().getStatusMDM().equals(StatusSystemMDM.APPROVED.name()));
        }
        return true;
    }

    public boolean isDisabledFieldsTmc() {
        return !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId());
    }

    public boolean isRenderApprovedStatus() {
        return getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId());
    }

    private void deletePhone() {
        phonesBeanLocal.deletePhoneList(getPhonesDelete());
    }

    @Override
    public void commandRemoteOutletInactive() {
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM_CADENAS.getId())) {
            if (getStatus().equals("I")) {
                setFlagOutletInactive(false);
                RequestContext rc = RequestContext.getCurrentInstance();
                rc.execute("PF('chainInactive').show()");
            } else if (getSubSegmentSelected().getSubSegmentId().equals(0)) {
                setFlagOutletInactive(false);
                RequestContext rc = RequestContext.getCurrentInstance();
                rc.execute("PF('chainWithoutSubSegment').show()");
            }
        } else if (getSubSegmentSelected().getSubSegmentId().equals(0)) {
            setFlagOutletInactive(false);
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('chainWithoutSubSegment').show()");
        }
    }

    public boolean isDisabledPotentialSegmentation() {
        return !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_CADENAS.getId())
                && !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())
                && !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId());
    }

    public void acceptOutletInactive() {
        setFlagOutletInactive(true);
        saveChain();
    }

    public boolean isRenderExportExcel() {
        return !(getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId()));
    }

    /**
     * @return the chainsList
     */
    public List<DbChains> getChainsList() {
        return chainsList;
    }

    /**
     * @param chainsList the chainsList to set
     */
    public void setChainsList(List<DbChains> chainsList) {
        this.chainsList = chainsList;
    }

    /**
     * @return the seeDetail
     */
    public boolean isSeeDetail() {
        return seeDetail;
    }

    /**
     * @param seeDetail the seeDetail to set
     */
    public void setSeeDetail(boolean seeDetail) {
        this.seeDetail = seeDetail;
    }

    /**
     * @return the phonesDelete
     */
    public List<DbPhones> getPhonesDelete() {
        return phonesDelete;
    }

    /**
     * @param phonesDelete the phonesDelete to set
     */
    public void setPhonesDelete(List<DbPhones> phonesDelete) {
        this.phonesDelete = phonesDelete;
    }

    /**
     * @return the idChain
     */
    public Integer getIdChain() {
        return idChain;
    }

    /**
     * @param idChain the idChain to set
     */
    public void setIdChain(Integer idChain) {
        this.idChain = idChain;
    }

    /**
     * @return the loginBean
     */
    @Override
    public LoginBean getLoginBean() {
        return loginBean;
    }

    /**
     * @return the renderMassiveApproval
     */
    public boolean isRenderMassiveApproval() {
        return renderMassiveApproval;
    }

    /**
     * @param renderMassiveApproval the renderMassiveApproval to set
     */
    public void setRenderMassiveApproval(boolean renderMassiveApproval) {
        this.renderMassiveApproval = renderMassiveApproval;
    }

    /**
     * @return the listPermi
     */
    public List<DbPermissionSegments> getListPermi() {
        return listPermi;
    }

    /**
     * @param listPermi the listPermi to set
     */
    public void setListPermi(List<DbPermissionSegments> listPermi) {
        this.listPermi = listPermi;
    }

    /**
     * @return the disabledFields
     */
    public boolean isDisabledFields() {
        return disabledFields;
    }

    /**
     * @param disabledFields the disabledFields to set
     */
    public void setDisabledFields(boolean disabledFields) {
        this.disabledFields = disabledFields;
    }

    /**
     * @return the listCustomerDelete
     */
    public List<DbCustomers> getListCustomerDelete() {
        return listCustomerDelete;
    }

    /**
     * @param listCustomerDelete the listCustomerDelete to set
     */
    public void setListCustomerDelete(List<DbCustomers> listCustomerDelete) {
        this.listCustomerDelete = listCustomerDelete;
    }

    /**
     * @return the disabledSegmentation
     */
    public boolean isDisabledSegmentation() {
        return disabledSegmentation;
    }

    /**
     * @param disabledSegmentation the disabledSegmentation to set
     */
    public void setDisabledSegmentation(boolean disabledSegmentation) {
        this.disabledSegmentation = disabledSegmentation;
    }

    /**
     * @return the buttonNameCommit
     */
    public String getButtonNameCommit() {
        return buttonNameCommit;
    }

    /**
     * @param buttonNameCommit the buttonNameCommit to set
     */
    public void setButtonNameCommit(String buttonNameCommit) {
        this.buttonNameCommit = buttonNameCommit;
    }

    public List<DbChains> getChainsListFiltered() {
        return chainsListFiltered;
    }

    public void setChainsListFiltered(List<DbChains> chainsListFiltered) {
        this.chainsListFiltered = chainsListFiltered;
    }

    public List<DbChains> getChainsListSelected() {
        return chainsListSelected;
    }

    public void setChainsListSelected(List<DbChains> chainsListSelected) {
        this.chainsListSelected = chainsListSelected;
    }

    public DbChains getChainSelected() {
        return chainSelected;
    }

    public void setChainSelected(DbChains chainSelected) {
        this.chainSelected = chainSelected;
    }

    public List<SelectItem> getListFilterStatusMDM() {
        return listFilterStatusMDM;
    }

    public void setListFilterStatusMDM(List<SelectItem> listFilterStatusMDM) {
        this.listFilterStatusMDM = listFilterStatusMDM;
    }

}
