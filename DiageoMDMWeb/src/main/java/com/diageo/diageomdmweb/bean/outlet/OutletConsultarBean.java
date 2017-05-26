/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.outlet;

import com.diageo.admincontrollerweb.beans.RelationUserBeanLocal;
import com.diageo.admincontrollerweb.beans.UserBeanLocal;
import com.diageo.admincontrollerweb.entities.DwRelationUsers;
import com.diageo.admincontrollerweb.entities.DwUsers;
import com.diageo.admincontrollerweb.enums.ProfileEnum;
import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.admincontrollerweb.enums.StatusSystemMDM;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageomdmweb.bean.dto.DbOutletsDto;
import com.diageo.diageomdmweb.datamodel.DbOutletsLazyDataModel;
import com.diageo.diageomdmweb.enums.TableOutletFields;
import com.diageo.diageomdmweb.enums.TablesEnum;
import com.diageo.diageomdmweb.enums.WaitingEnum;
import com.diageo.diageomdmweb.jdbc.ConecctionJDBC;
import com.diageo.diageomdmweb.mail.EMail;
import com.diageo.diageomdmweb.mail.templates.VelocityTemplate;
import com.diageo.diageonegocio.beans.DiageoLogBeanLocal;
import com.diageo.diageonegocio.beans.LogTerritoryBean;
import com.diageo.diageonegocio.beans.OutletsUserBeanLocal;
import com.diageo.diageonegocio.beans.PermissionsegmentBeanLocal;
import com.diageo.diageonegocio.beans.PotentialBeanLocal;
import com.diageo.diageonegocio.entidades.Audit;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.Db3partySales;
import com.diageo.diageonegocio.entidades.DbCustomers;
import com.diageo.diageonegocio.entidades.DbLogTerritory;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbOutletsUsers;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.entidades.DbPhones;
import com.diageo.diageonegocio.entidades.DbPotentials;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.entidades.DiageoLog;
import com.diageo.diageonegocio.enums.StateDiageo;
import com.diageo.diageonegocio.enums.StateOutletChain;
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
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.data.FilterEvent;

/**
 *
 * @author yovanoty126
 */
@Named(value = "outletConsultarBean")
@ViewScoped
public class OutletConsultarBean extends OutletCrearBean implements Serializable {

    @EJB
    private PermissionsegmentBeanLocal permissionsegmentBeanLocal;
    @EJB
    private UserBeanLocal userBeanLocal;
    @EJB
    private OutletsUserBeanLocal outletsUserBeanLocal;
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
    private List<DbPermissionSegments> listPermi;
    private List<DbCustomers> listCustomerDelete;
    private boolean verDetalle;
    private DbOutlets outletSelect;
    private String state;
    private String stateMasive;
    private String messageReject;
    private List<DbPhones> phonesDelete;
    private Integer idOutlet;
    private boolean renderMassiveApproval;
    private boolean disabledFields;
    private String buttonNameCommit;
    private DbOutletsLazyDataModel outletsLazyDataModel;
    private DbSubSegments subSegmentDistributor;
    private List<DbOutlets> listOutlets;
    private List<DbOutlets> listOutletsFilter;
    private Integer distributorOld;
    private Map<String, Object> filtersTable;
    /**
     * Si el perfil es un commercial manager, esta variable almacenará todos los
     * id de usuarios que tiene asignados
     */
    private List<Integer> listId;
    private List<SelectItem> listFilterStatusMDM;
    private DbOutletsDto outletClonable;
    private String nameTmc;
    private Db3party db3PartyIdNew;
    private List<Db3party> db3PartyList;
    /**
     * Es el potencial que tiene asignado el outlet al ver el detalle del outlet
     */
    private DbPotentials currentPotential;
    private List<DbOutlets> listOutletsSons;
    private List<DbOutlets> listOutletsSonsDelete;

    /**
     * Creates a new instance of OutletConsultarBean
     */
    public OutletConsultarBean() {
    }

    @PostConstruct
    @Override
    public void init() {
        setFlagOutletInactive(true);
        setDb3PartyList(db3PartyBeanLocal.searchAll());
        setSelectedSon(new DbOutlets());
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())) {
            setOutletsLazyDataModel(new DbOutletsLazyDataModel(outletBeanLocal, getLoginBean().getUsuario().getProfileId().getProfileId()));
            setRenderMassiveApproval(Boolean.FALSE);
            setButtonNameCommit(capturarValor("btn_send"));
        } else {
            setButtonNameCommit(capturarValor("btn_send_approved"));
            setDisabledFields(Boolean.TRUE);
            if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_DISTRIBUIDORES.getId())) {
                setRenderMassiveApproval(Boolean.FALSE);
            } else {
                setRenderMassiveApproval(Boolean.TRUE);
            }
            if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
                listId = relationUserBeanLocal.findByParentId(getLoginBean().getUsuario().getUserId());
                setOutletsLazyDataModel(new DbOutletsLazyDataModel(outletBeanLocal,
                        getLoginBean().getUsuario().getProfileId().getProfileId(), listId, outletsUserBeanLocal));
            } else {
                setOutletsLazyDataModel(new DbOutletsLazyDataModel(outletBeanLocal,
                        getLoginBean().getUsuario().getProfileId().getProfileId(), getLoginBean().getUsuario().getUserId(), outletsUserBeanLocal));
            }
        }
        loadListStatusMdm();
        setVerDetalle(Boolean.TRUE);
        setListCustomerDelete(new ArrayList<DbCustomers>());
        super.init();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Object obj = session.getAttribute(OUTLET);
        if (obj != null) {
            try {
                setVerDetalle(Boolean.FALSE);
                Integer id = Integer.parseInt(obj + "");
                DbOutlets outlets = outletBeanLocal.findById(id);
                seeDetail(outlets);
                session.setAttribute(OUTLET, null);
            } catch (DiageoBusinessException ex) {
                Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        setSellerSelected(new Db3partySales());
    }

    private void loadListStatusMdm() {
        listFilterStatusMDM = new ArrayList<>();
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_DISTRIBUIDORES.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())) {
            listFilterStatusMDM.add(new SelectItem("APPROVED", capturarValor("sta_approval")));
            listFilterStatusMDM.add(new SelectItem("PENDING_APPROVAL", capturarValor("sta_pending_commercial_manager")));
            listFilterStatusMDM.add(new SelectItem("PENDING_TMC", capturarValor("sta_pending_tmc")));
            listFilterStatusMDM.add(new SelectItem("REJECT", capturarValor("sta_reject")));
        } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())) {
            listFilterStatusMDM.add(new SelectItem("PENDING_TMC", capturarValor("sta_pending_tmc")));
            listFilterStatusMDM.add(new SelectItem("PENDING_APPROVAL", capturarValor("sta_pending_commercial_manager")));
            listFilterStatusMDM.add(new SelectItem("APPROVED", capturarValor("sta_approval")));
            listFilterStatusMDM.add(new SelectItem("REJECT", capturarValor("sta_reject")));
        } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
            listFilterStatusMDM.add(new SelectItem("PENDING_APPROVAL", capturarValor("sta_pending_commercial_manager")));
            listFilterStatusMDM.add(new SelectItem("PENDING_TMC", capturarValor("sta_pending_tmc")));
            listFilterStatusMDM.add(new SelectItem("APPROVED", capturarValor("sta_approval")));
            listFilterStatusMDM.add(new SelectItem("REJECT", capturarValor("sta_reject")));
        }
    }
    
    @Override
    public List<Db3partySales> completeSeller(String query) {        
        return dbPartySalesBeanLocal.findByNameSeller(query,getDistributorOld());
    }

    public void seeDetail(DbOutlets out) {
        setListOutletsSonsDelete(new ArrayList<DbOutlets>());
        try {
            outletClonable = new DbOutletsDto(out.clone(), getCurrentDate(), getLoginBean().getUsuario().getUserId());
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        setIdOutlet(out.getOutletId());
        setIsFather(out.getIsFather().equals(StateDiageo.ACTIVO.getId()));
        setFather(out.getOutletIdFather());
        setKiernanId(out.getKiernanId());
        setBusinessName(out.getBusinessName());
        setNit(out.getNit());
        setVerificationNumber(out.getVerificationNumber());
        setOutletName(out.getOutletName());
        setPointSale(out.getNumberPdv());
        setTypeOutlet(out.getTypeOutlet());
        setWebsite(out.getWebsite());
//        setWine(out.getWine() != null ? out.getWine().equals(StateDiageo.ACTIVO.getId()) : false);
//        setBeer(out.getBeer() != null ? out.getBeer().equals(StateDiageo.ACTIVO.getId()) : false);
//        setSpirtis(out.getSpirtis() != null ? out.getSpirtis().equals(StateDiageo.ACTIVO.getId()) : false);
        setOcsPrimary(out.getOcsPrimary());
        setOcsSecondary(out.getOcsSecondary());
        setPotentialSelected(out.getPotentialId());
        currentPotential = out.getPotentialId();
        this.setSegmentation(out);

        setListSubChannel(getChannelSelected().getDbSubChannelsList());
        setListSegment(getSubChannelSelected().getDbSegmentsList());
        setListSubSegment(getSegmentSelected().getDbSubSegmentsList());
        setListPotential(getSubSegmentSelected().getDbPotentialsList());
        setListPhones(out.getDbPhonesList());
        setNeighborhood(out.getNeighborhood());
        setAddress(out.getAddress());
        setLongitude(out.getLongitude());
        setLatitude(out.getLatitude());
        setDepartamentSelected(out.getTownId().getDepartamentId());
        setTownSelected(out.getTownId());
        setPhonesDelete(new ArrayList<DbPhones>());
        setListCustomers(out.getDbCustomersList());
        setSubSegmentDistributor(out.getDistributorSubSegmentId());
        setVerDetalle(Boolean.FALSE);
        if (out.getDb3PartyIdOld() != null) {
            setDistributorOld(out.getDb3PartyIdOld().getDb3partyId());
        }
        setDb3PartyIdNew(out.getDb3PartyIdNew());
        if (out.getDb3partySaleId() != null) {
            setSellerSelected(out.getDb3partySaleId());
        }
        setJourneyPlan(out.getJourneyPlan().equals(StateEnum.ACTIVE.getState()));
        setStatusOutlet(out.getStatusOutlet());
        setAgreement(out.getAgreement() == null ? Boolean.FALSE : (out.getAgreement().equals(StateEnum.ACTIVE.getState())));
        setOwner(out.getOwnerId());
        try {
            DbOutletsUsers findByOutletId = outletsUserBeanLocal.findByOutletIdProfileId(idOutlet);
            if (findByOutletId != null) {
                DwUsers findById = userBeanLocal.findById(findByOutletId.getDbOutletsUsersPK().getUserId());
                setNameTmc(findById.getNameUser().toUpperCase() + " " + findById.getLastName().toUpperCase());
            }
        } catch (Exception e) {
            Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        this.loadOutletsSons(out.getOutletId());
        //super.listenerChannel();
    }

    private void loadOutletsSons(Integer id) {
        setListOutletsSons(outletBeanLocal.findByIdSons(id));
    }

    /**
     * si el check agreement es seleccionado entonces la segmentación se puede
     * cambiar, sino, entonces la segmentación no se podrá cambiar. Los perfiles
     * administrador y datasteward, pueden cambiar la segmentación y el check
     * cuando lo requieran
     *
     * @return true, entonces puede cambiar la segmentación false, no puede
     * cambiar la segmentación
     */
    public boolean validateAgreement() {
        if ((getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId()))) {
            return true;
        }
        return isAgreement();
    }

    /**
     * método para setear la segmentación identificando si tiene activa o no la
     * segmentación
     *
     * @param out
     */
    private void setSegmentation(DbOutlets out) {
        try {
            boolean bol = out.getSubSegmentId().getSegmentId().getSubChannelId().getChannelId().getStateChannel().equals(StateDiageo.ACTIVO.getId());
            if (bol) {
                setChannelSelected(out.getSubSegmentId().getSegmentId().getSubChannelId().getChannelId());
                if (out.getSubSegmentId().getSegmentId().getSubChannelId().getStateSubChannel().equals(StateDiageo.ACTIVO.getId())) {
                    setSubChannelSelected(out.getSubSegmentId().getSegmentId().getSubChannelId());
                    if (out.getSubSegmentId().getSegmentId().getStateSegment().equals(StateDiageo.ACTIVO.getId())) {
                        setSegmentSelected(out.getSubSegmentId().getSegmentId());
                        if (out.getSubSegmentId().getStateSubSegment().equals(StateDiageo.ACTIVO.getId())) {
                            setSubSegmentSelected(out.getSubSegmentId());
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

    private void deletCustomerChain() {
        if (!getListCustomerDelete().isEmpty()) {
            for (DbCustomers dbCusto : getListCustomerDelete()) {
                outletBeanLocal.deleteCustomerOutlet(dbCusto.getCustomerId(), getIdOutlet());
            }
        }
    }

    @Override
    public void deleteCustomer(DbCustomers custo) {
        getListCustomers().remove(custo);
        getListCustomerDelete().add(custo);
    }

    /**
     * si el componente es padre, tiene un check, significa que tiene uno o
     * varios hijos sino tiene el check, entonces es un hijo que debe tener
     * padre
     */
    @Override
    public void deleteOutletFather() {
        if (!isIsFather()) {
            setFather(null);
        } else {
            setFather(outletClonable.getOutlet().getOutletIdFather());
        }
    }

    @Override
    public void saveOutlet() {
        if (isFlagOutletInactive()) {
            try {
                DbOutlets outlet = outletBeanLocal.findById(getIdOutlet());
                if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
                    if (!outlet.getStatusMDM().equals(StatusSystemMDM.PENDING_APPROVAL.name())) {
                        showWarningMessage(capturarValor("msg_not_approved"));
                        return;
                    }
                }
                outlet.setAddress(getAddress() != null ? getAddress().toUpperCase() : "");
                outlet.setBusinessName(getBusinessName() != null ? getBusinessName().toUpperCase() : "");
                cleanIdPhones();
                outlet.setDbPhonesList(getListPhones());
                outlet.setEmail(getEmail() != null ? getEmail().toUpperCase() : "");
                outlet.setIsFather(isIsFather() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
                outlet.setKiernanId(getKiernanId() != null ? getKiernanId().toUpperCase() : "");
                outlet.setLatitude(getLatitude());
                outlet.setLongitude(getLongitude());
                outlet.setNeighborhood(getNeighborhood() != null ? getNeighborhood().toUpperCase() : "");
                outlet.setNit(getNit() != null ? getNit().toUpperCase() : "");
                outlet.setNumberPdv(getPointSale() != null ? getPointSale().toUpperCase() : "");
                outlet.setOcsPrimary(getOcsPrimary());
                outlet.setOcsSecondary(getOcsSecondary());
                if (getFather() != null && getFather().getOutletId() != null) {
                    outlet.setOutletIdFather(getFather());
                } else {
                    outlet.setOutletIdFather(outlet);
                }
                outlet.setStatusOutlet(getStatusOutlet());
                outlet.setOutletName(getOutletName() != null ? getOutletName().toUpperCase() : "");
                outlet.setPotentialId(getPotentialSelected());
                if (!isAgreement()) {
                    outlet.setSubSegmentId(getSubSegmentSelected());
                }
                outlet.setAgreement(isAgreement() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
                outlet.setTownId(getTownSelected());
                outlet.setTypeOutlet(getTypeOutlet() != null ? getTypeOutlet().toUpperCase() : "");
                outlet.setVerificationNumber(getVerificationNumber());
                outlet.setWebsite(getWebsite() != null ? getWebsite().toUpperCase() : "");
                if (getSellerSelected() != null) {
                    outlet.setDb3partySaleId(getSellerSelected());
                }
                outlet.setJourneyPlan(isJourneyPlan() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
                outlet.setDb3PartyIdNew(db3PartyIdNew);
                outlet.setOwnerId(getOwner());
//            outlet.setWine(isWine() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
//            outlet.setBeer(isBeer() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
//            outlet.setSpirtis(isSpirtis() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
                outlet.setDbCustomersList(getListCustomers());
                if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
                    if (outlet.getStatusMDM().equals(StatusSystemMDM.PENDING_APPROVAL.name())) {
                        outlet.setStatusMDM(StatusSystemMDM.APPROVED.name());
                    }
                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())) {
                    if (!outletClonable.isNotificationChangedSegmentation(outlet)) {
                        outlet.setStatusMDM(StatusSystemMDM.PENDING_APPROVAL.name());
                    } else {
                        if (outlet.getStatusMDM().equals(StatusSystemMDM.PENDING_TMC.name())) {
                            outlet.setStatusMDM(StatusSystemMDM.PENDING_APPROVAL.name());
                        }
                    }
                }
                /**
                 * Relacion de hijos y padres
                 */
                if (isIsFather()) {
                    this.toBreakRelationSonFather();
                    this.saveSonOutlets(outlet);
                    outlet.setOutletIdFather(outlet);
                } else {
                    if (listOutletsSons != null) {
                        for (DbOutlets tempOut : listOutletsSons) {
                            tempOut.setOutletIdFather(tempOut);
                            outletBeanLocal.update(tempOut);
                        }
                    }
                }
                createLogTerritory(outlet);
                Audit audit = new Audit();
                audit.setCreationDate(outlet.getAudit() != null ? outlet.getAudit().getCreationDate() : null);
                audit.setCreationUser(outlet.getAudit() != null ? outlet.getAudit().getCreationUser() : null);
                audit.setModificationDate(super.getCurrentDate());
                audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
                outlet.setAudit(audit);
                deletCustomerChain();
                if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_DISTRIBUIDORES.getId())) {
                    if (outlet.getStatusMDM().equals(StatusSystemMDM.APPROVED.name())) {
                        outletBeanLocal.updateOutlet(outlet);
                        createLog(outlet);
                        showInfoMessage(capturarValor("sis_datos_guardados_exito"));
                    } else {
                        showInfoMessage(capturarValor("msg_cp_a_warning"));
                    }
                } else {
                    outletBeanLocal.updateOutlet(outlet);
                    createLog(outlet);
                    showInfoMessage(capturarValor("sis_datos_guardados_exito"));
                }
                
                Connection con = ConecctionJDBC.conexionSQLServer(ipDatabase.get(0).getParameterValue(),
                        userDatabase.get(0).getParameterValue(), passDatabase.get(0).getParameterValue());
                ConecctionJDBC.callStoreProcedureDBOutlets(con, outlet.getOutletId());
                setVerDetalle(!getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                        && !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId()));
                //sendMail();
            } catch (DiageoBusinessException ex) {
                Logger.getLogger(OutletCrearBean.class.getName()).log(Level.SEVERE, null, ex);
                showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
            }
        }
        setFlagOutletInactive(true);
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

    public void createLog(DbOutlets out) {
        outletClonable.changes(out);
        List<DiageoLog> listDiageoLog = outletClonable.getListDiageoLog();
        if (!listDiageoLog.isEmpty()) {
            for (DiageoLog diageoLog : listDiageoLog) {
                diageoLogBeanLocal.createLog(diageoLog);
            }
        }
        outletClonable = new DbOutletsDto(out, getCurrentDate(), getLoginBean().getUsuario().getUserId());
    }

    public void createLogTerritory(DbOutlets out) {
        if (out.getDb3partySaleId() != null && outletClonable.getOutlet().getDb3partySaleId() != null) {
            if (out.getDb3partySaleId().getDb3partyTerritory() != null && outletClonable.getOutlet().getDb3partySaleId().getDb3partyTerritory() != null) {
                if (!out.getDb3partySaleId().getDb3partyTerritory().getNameTerritory().equals(outletClonable.getOutlet().getDb3partySaleId().getDb3partyTerritory().getNameTerritory())) {
                    DbLogTerritory logTerritory = new DbLogTerritory();
                    logTerritory.setCreationDate(getCurrentDate());
                    logTerritory.setCreationUser(getLoginBean().getUsuario().getEmailUser());
                    logTerritory.setDbOutletId(out.getOutletId());
                    logTerritory.setFieldLog(TableOutletFields.TERRITORY.name());
                    logTerritory.setOutletType(TablesEnum.DB_OUTLETS.name());
                    logTerritory.setWaitingStatus(WaitingEnum.REMOVAL.getState());
                    logTerritory.setValueLog(outletClonable.getOutlet().getDb3partySaleId().getDb3partyTerritory().getNameTerritory());
                    logTerritoryBean.create(logTerritory);
                    logTerritory.setWaitingStatus(WaitingEnum.ACTIVATION.getState());
                    logTerritory.setValueLog(out.getDb3partySaleId().getDb3partyTerritory().getNameTerritory());
                    logTerritoryBean.create(logTerritory);
                }
            }
        }
        if (!out.getJourneyPlan().equals(outletClonable.getOutlet().getJourneyPlan())) {
            DbLogTerritory logTerritory = new DbLogTerritory();
            logTerritory.setCreationDate(getCurrentDate());
            logTerritory.setCreationUser(getLoginBean().getUsuario().getEmailUser());
            logTerritory.setDbOutletId(out.getOutletId());
            logTerritory.setFieldLog(TableOutletFields.JOURNEY_PLAN.name());
            logTerritory.setOutletType(TablesEnum.DB_OUTLETS.name());
            if (out.getJourneyPlan().equals(StateEnum.ACTIVE.getState())) {
                logTerritory.setWaitingStatus(WaitingEnum.ACTIVATION.getState());
            } else {
                logTerritory.setWaitingStatus(WaitingEnum.REMOVAL.getState());
            }
            logTerritory.setValueLog(out.getJourneyPlan());
            logTerritoryBean.create(logTerritory);
        }
    }

    public void addSonToOutlet() {
        if (listOutletsSons == null) {
            listOutletsSons = new ArrayList<>();
        }
        if (getSelectedSon() != null) {
            if (!listOutletsSons.contains(getSelectedSon())) {
                listOutletsSons.add(getSelectedSon());
            } else {
                showWarningMessage(capturarValor("msg_son_exist"));
            }
        } else {
            showWarningMessage(capturarValor("msg_son_fild_required"));
        }
        setSelectedSon(new DbOutlets());
    }

    public void removeOutletSon(DbOutlets out) {
        listOutletsSonsDelete.add(out);
        listOutletsSons.remove(out);
    }

    public void saveSonOutlets(DbOutlets father) {
        outletBeanLocal.relateOutlets(listOutletsSons, father);
    }

    public void toBreakRelationSonFather() {
        outletBeanLocal.toBreakRelationSonFather(listOutletsSonsDelete);
    }

    public void buttonBack() {
        setVerDetalle(true);
    }

    private void sendMail() {
        if (!getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())) {
            List<Integer> listIntegerUser = new ArrayList<>();
            List<Integer> listInteger3party = new ArrayList<>();

            if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())) {
                //Consultar los commercial manager
                List<DwUsers> listCommercialManager = userBeanLocal.usersByProfile(ProfileEnum.COMMERCIAL_MANAGER.getId());
                for (DwUsers usu : listCommercialManager) {
                    listIntegerUser.add(usu.getUserId());
                }
                List<Integer> findByUser3Party = permissionsegmentBeanLocal.findByUser3Party(listIntegerUser, listInteger3party);
                for (Integer idUser : findByUser3Party) {
                    DwUsers usuTemp = userBeanLocal.findById(idUser);
                    EMail mail = new EMail();
                    String msg = VelocityTemplate.notificationOutlet(getKiernanId(), getDiageoApplicationBean().getPathMailTemplate());
                    mail.send(new String[]{usuTemp.getEmailUser()}, capturarValor("mail_notificacion"), msg);
                }
            } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
                List<DwUsers> listTMC = userBeanLocal.usersByProfile(ProfileEnum.TMC_DISTRIBUIDORES.getId());
                for (DwUsers usu : listTMC) {
                    listIntegerUser.add(usu.getUserId());
                }
                List<Integer> findByUser3Party = permissionsegmentBeanLocal.findByUser3Party(listIntegerUser, listInteger3party);
                for (Integer idUser : findByUser3Party) {
                    DwUsers usuTemp = userBeanLocal.findById(idUser);
                    EMail mail = new EMail();
                    String msg = VelocityTemplate.notificationOutlet(getKiernanId(), getDiageoApplicationBean().getPathMailTemplate());
                    mail.send(new String[]{usuTemp.getEmailUser()}, capturarValor("mail_notificacion"), msg);
                }
            }
        }
    }

    /**
     * Aprueba todos los outlets que tenga asignados en la tabla DB_OUTLETS_USER
     * verifica primero por perfil, para enviar el estado que le corresponde. Si
     * es TMC_DISTRIBUIDRES todos los outlets quedarán en
     * PENDING_COMMERCIAL_MANAGER. Si es CP_A_DISTRIBUIDORES todos quedarán como
     * PENDING_TMC. Con COMMERCIAL_MANAGER el tema es un poco diferente, ya que
     * él sólo aprobará los que estén en estado PENDING_COMMERCIAL_MANAGER
     *
     */
    public void approvedAllOutlets() {
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
            outletsUserBeanLocal.updateOutletCommercialManager(listId, StatusSystemMDM.APPROVED.name(), StatusSystemMDM.PENDING_APPROVAL.name(), getLoginBean().getUsuario().getEmailUser());
        } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())) {
            outletsUserBeanLocal.updateOutlet(getLoginBean().getUsuario().getUserId(), StatusSystemMDM.PENDING_APPROVAL.name(), StatusSystemMDM.PENDING_TMC.name(), getLoginBean().getUsuario().getEmailUser());
        }
        showInfoMessage(capturarValor("sis_msg_record_outlet_change"));
        init();
    }

    public void rejectAllOutlet() {
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
            outletsUserBeanLocal.updateOutletCommercialManager(listId, StatusSystemMDM.REJECT.name(), StatusSystemMDM.PENDING_APPROVAL.name(), getLoginBean().getUsuario().getEmailUser());
            showInfoMessage(capturarValor("sis_msg_record_outlet_change"));
            init();
        }
    }

    public void approvedAllOutletsSelected() {
        for (DbOutlets out : getListOutletsFilter()) {
            try {
                boolean update = true;
                if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
                    DbOutletsUsers outletIdTemp = outletsUserBeanLocal.findByOutletId(out.getOutletId());
                    if (outletIdTemp != null) {
                        List<DwRelationUsers> listTemp = relationUserBeanLocal.findByUserIdAndParent(outletIdTemp.getDbOutletsUsersPK().getUserId(), getLoginBean().getUsuario().getUserId());
                        if (!listTemp.isEmpty()) {
                            update = listTemp.get(0).getStateApproved();
                        }
                    }
                    if (update) {
                        if (out.getStatusMDM().equals(StatusSystemMDM.PENDING_APPROVAL.name())) {
                            out.setStatusMDM(StatusSystemMDM.APPROVED.name());
                        }
                    }
                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())) {
                    if (out.getStatusMDM().equals(StatusSystemMDM.PENDING_TMC.name())) {
                        out.setStatusMDM(StatusSystemMDM.PENDING_APPROVAL.name());
                    }
                }
                if (update) {
                    Audit audit = new Audit();
                    audit.setCreationDate(out.getAudit() != null ? out.getAudit().getCreationDate() : null);
                    audit.setCreationUser(out.getAudit() != null ? out.getAudit().getCreationUser() : null);
                    audit.setModificationDate(super.getCurrentDate());
                    audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
                    out.setAudit(audit);
                    outletBeanLocal.updateOutlet(out);
                }
            } catch (DiageoBusinessException ex) {
                Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        showInfoMessage(capturarValor("sis_msg_record_outlet_change"));
    }

    public void rejectAllOutletsSelected() {
        for (DbOutlets out : getListOutletsFilter()) {
            try {
                DbOutletsUsers outletIdTemp = outletsUserBeanLocal.findByOutletId(out.getOutletId());
                boolean update = true;
                if (outletIdTemp != null) {
                    List<DwRelationUsers> listTemp = relationUserBeanLocal.findByUserIdAndParent(outletIdTemp.getDbOutletsUsersPK().getUserId(), getLoginBean().getUsuario().getUserId());
                    update = listTemp.get(0).getStateApproved();
                }
                if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
                    if (out.getStatusMDM().equals(StatusSystemMDM.PENDING_APPROVAL.name())) {
                        if (update) {
                            if (out.getStatusMDM().equals(StatusSystemMDM.PENDING_APPROVAL.name())) {
                                out.setStatusMDM(StatusSystemMDM.REJECT.name());
                                Audit audit = new Audit();
                                audit.setCreationDate(out.getAudit() != null ? out.getAudit().getCreationDate() : null);
                                audit.setCreationUser(out.getAudit() != null ? out.getAudit().getCreationUser() : null);
                                audit.setModificationDate(super.getCurrentDate());
                                audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
                                out.setAudit(audit);
                                outletBeanLocal.updateOutlet(out);
                            }
                        }
                    }
                }

            } catch (DiageoBusinessException ex) {
                Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        showInfoMessage(capturarValor("sis_msg_record_outlet_change"));
    }

    public void rejectOutlet() {
        try {
            if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
                DbOutlets outlet = outletBeanLocal.findById(getIdOutlet());
                if (outlet.getStatusMDM().equals(StatusSystemMDM.PENDING_APPROVAL.name())) {
                    outlet.setStatusMDM(StatusSystemMDM.REJECT.name());
                    Audit audit = new Audit();
                    audit.setCreationDate(outlet.getAudit() != null ? outlet.getAudit().getCreationDate() : null);
                    audit.setCreationUser(outlet.getAudit() != null ? outlet.getAudit().getCreationUser() : null);
                    audit.setModificationDate(super.getCurrentDate());
                    audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
                    outlet.setAudit(audit);
                    outletBeanLocal.updateOutlet(outlet);
                    //sendMail();
                    init();
                    showInfoMessage(capturarValor("sis_datos_guardados_exito"));
                } else {
                    showWarningMessage(capturarValor("msg_not_reject"));
                }
            }
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void nextOutlet() {
        if (listOutletsFilter != null && !listOutletsFilter.isEmpty()) {
            for (int i = 0; i < listOutletsFilter.size(); i++) {
                if (idOutlet.equals(listOutletsFilter.get(i).getOutletId())) {
                    seeDetail(listOutletsFilter.get(i + 1));
                    break;
                }
            }
        }
    }

    public void backOutlet() {
        if (listOutletsFilter != null && !listOutletsFilter.isEmpty()) {
            for (int i = 1; i < listOutletsFilter.size(); i++) {
                if (idOutlet.equals(listOutletsFilter.get(i).getOutletId())) {
                    seeDetail(listOutletsFilter.get(i - 1));
                    break;
                }
            }
        }
    }

    public void listenerFilterList(FilterEvent fe) {
        if (!fe.getFilters().isEmpty()) {
            filtersTable = fe.getFilters();
        }
    }

    public String labelState(String id) {
        if (id == null || id.isEmpty()) {
            return "";
        }
        return StateOutletChain.value(id).name();
    }

    public void selectAll() {
        for (DbOutlets listaOutlet : getListOutlets()) {
            listaOutlet.setApprobationMassive(Boolean.TRUE);
        }
    }

    public void unSelectAll() {
        for (DbOutlets listaOutlet : getListOutlets()) {
            listaOutlet.setApprobationMassive(Boolean.FALSE);
        }
    }

    public void listenerReject() {
        if (getState().equals("4")) {
            RequestContext.getCurrentInstance().execute("PF('xvReject').show();");
        }
    }

    public boolean isRenderButtonReject() {
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
            DbOutletsUsers outletIdTemp = outletsUserBeanLocal.findByOutletId(getIdOutlet());
            boolean update = true;
            if (outletIdTemp != null) {
                List<DwRelationUsers> listTemp = relationUserBeanLocal.findByUserIdAndParent(outletIdTemp.getDbOutletsUsersPK().getUserId(), getLoginBean().getUsuario().getUserId());
                update = listTemp.get(0).getStateApproved();
            }
            return getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId()) && update;
        }
        return false;
    }

    public boolean isRenderButtonApproved() {
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
            DbOutletsUsers outletIdTemp = outletsUserBeanLocal.findByOutletId(getIdOutlet());
            if (outletIdTemp != null) {
                List<DwRelationUsers> listTemp = relationUserBeanLocal.findByUserIdAndParent(outletIdTemp.getDbOutletsUsersPK().getUserId(), getLoginBean().getUsuario().getUserId());
                return listTemp.get(0).getStateApproved();
            }
            return false;
        }
        return true;
    }

    public boolean isRenderPendingTMCRejectStatus() {
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_DISTRIBUIDORES.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())) {
            return true;
        } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
            return false;
        }
        return false;
    }

    public boolean isRenderPendingCMStatus() {
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_DISTRIBUIDORES.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
            return true;
        } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())) {
            return false;
        }
        return false;
    }

    public boolean isDisabledPotentialSegmentation() {
        return !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_DISTRIBUIDORES.getId())
                && !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())
                && !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId());
    }

    public boolean isDisabledFuntionalSegmentation() {
        return !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())
                && !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())
                && !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId());
    }

    public boolean isDisabledFieldsTmc() {
        return !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId());
    }

    public boolean isRenderApprovedStatus() {
        return getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId());
    }

    public String showDistributorName(Integer id) {
        Db3party searchId = db3PartyBeanLocal.searchId(id);
        if (searchId == null) {
            return "";
        }
        return searchId.getName3party();
    }

    public boolean renderDataTableLazyDataModel() {
        return isVerDetalle() && (getLoginBean().getUsuario().getProfileId().getNameProfile().equalsIgnoreCase("ADMINISTRATOR")
                || getLoginBean().getUsuario().getProfileId().getNameProfile().equalsIgnoreCase("DATA STEWARD")
                || getLoginBean().getUsuario().getProfileId().getNameProfile().equalsIgnoreCase("CP&A_DISTRIBUIDORES")
                || getLoginBean().getUsuario().getProfileId().getNameProfile().equalsIgnoreCase("TMC_DISTRIBUIDORES")
                || getLoginBean().getUsuario().getProfileId().getNameProfile().equalsIgnoreCase("COMMERCIAL MANAGER"));
    }

    public boolean renderDataTable() {
        return isVerDetalle()
                && (getLoginBean().getUsuario().getProfileId().getNameProfile().equalsIgnoreCase("TMC_DISTRIBUIDORES")
                || getLoginBean().getUsuario().getProfileId().getNameProfile().equalsIgnoreCase("COMMERCIAL MANAGER"));
    }

    public boolean isDisabledFieldStatusOutlet() {
        return !(getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId()));
    }

    @Override
    public void commandRemoteOutletInactive() {
        if (validateAgreement()) {
            if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())) {
                if (getStatusOutlet().equals("I")) {
                    setFlagOutletInactive(false);
                    RequestContext rc = RequestContext.getCurrentInstance();
                    rc.execute("PF('outletInactive').show()");
                } else if (getSubSegmentSelected().getSubSegmentId().equals(0)) {
                    setFlagOutletInactive(false);
                    RequestContext rc = RequestContext.getCurrentInstance();
                    rc.execute("PF('outletWithoutSubSegment').show()");
                }
            } else if (getSubSegmentSelected().getSubSegmentId().equals(0)) {
                setFlagOutletInactive(false);
                RequestContext rc = RequestContext.getCurrentInstance();
                rc.execute("PF('outletWithoutSubSegment').show()");
            }
        }
    }

    public void acceptOutletInactive() {
        setFlagOutletInactive(true);
        saveOutlet();
    }

    public boolean isRenderExportExcel() {
        return !(getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId()));
    }

    /**
     * unicamente habilitado para el admin y datasteward
     *
     * @return
     */
    public boolean isDisabledDb3PartyNew() {
        return !(getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId()));
    }

    /**
     * @return the loginBean
     */
    @Override
    public LoginBean getLoginBean() {
        return loginBean;
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
     * @return the outletSelect
     */
    public DbOutlets getOutletSelect() {
        return outletSelect;
    }

    /**
     * @param outletSelect the outletSelect to set
     */
    public void setOutletSelect(DbOutlets outletSelect) {
        this.outletSelect = outletSelect;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the stateMasive
     */
    public String getStateMasive() {
        return stateMasive;
    }

    /**
     * @param stateMasive the stateMasive to set
     */
    public void setStateMasive(String stateMasive) {
        this.stateMasive = stateMasive;
    }

    /**
     * @return the messageReject
     */
    public String getMessageReject() {
        return messageReject;
    }

    /**
     * @param messageReject the messageReject to set
     */
    public void setMessageReject(String messageReject) {
        this.messageReject = messageReject;
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
     * @return the idOutlet
     */
    public Integer getIdOutlet() {
        return idOutlet;
    }

    /**
     * @param idOutlet the idOutlet to set
     */
    public void setIdOutlet(Integer idOutlet) {
        this.idOutlet = idOutlet;
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
     * @return the outletsLazyDataModel
     */
    public DbOutletsLazyDataModel getOutletsLazyDataModel() {
        return outletsLazyDataModel;
    }

    /**
     * @param outletsLazyDataModel the outletsLazyDataModel to set
     */
    public void setOutletsLazyDataModel(DbOutletsLazyDataModel outletsLazyDataModel) {
        this.outletsLazyDataModel = outletsLazyDataModel;
    }

    /**
     * @return the subSegmentDistributor
     */
    public DbSubSegments getSubSegmentDistributor() {
        return subSegmentDistributor;
    }

    /**
     * @param subSegmentDistributor the subSegmentDistributor to set
     */
    public void setSubSegmentDistributor(DbSubSegments subSegmentDistributor) {
        this.subSegmentDistributor = subSegmentDistributor;
    }

    /**
     * @return the listOutlets
     */
    public List<DbOutlets> getListOutlets() {
        return listOutlets;
    }

    /**
     * @param listOutlets the listOutlets to set
     */
    public void setListOutlets(List<DbOutlets> listOutlets) {
        this.listOutlets = listOutlets;
    }

    /**
     * @return the distributorOld
     */
    public Integer getDistributorOld() {
        return distributorOld;
    }

    /**
     * @param distributorOld the distributorOld to set
     */
    public void setDistributorOld(Integer distributorOld) {
        this.distributorOld = distributorOld;
    }

    public List<DbOutlets> getListOutletsFilter() {
        return listOutletsFilter;
    }

    public void setListOutletsFilter(List<DbOutlets> listOutletsFilter) {
        this.listOutletsFilter = listOutletsFilter;
    }

    public List<SelectItem> getListFilterStatusMDM() {
        return listFilterStatusMDM;
    }

    public void setListFilterStatusMDM(List<SelectItem> listFilterStatusMDM) {
        this.listFilterStatusMDM = listFilterStatusMDM;
    }

    public String getNameTmc() {
        return nameTmc;
    }

    public void setNameTmc(String nameTmc) {
        this.nameTmc = nameTmc;
    }

    /**
     * @return the db3PartyIdNew
     */
    public Db3party getDb3PartyIdNew() {
        return db3PartyIdNew;
    }

    /**
     * @param db3PartyIdNew the db3PartyIdNew to set
     */
    public void setDb3PartyIdNew(Db3party db3PartyIdNew) {
        this.db3PartyIdNew = db3PartyIdNew;
    }

    /**
     * @return the db3PartyList
     */
    public List<Db3party> getDb3PartyList() {
        return db3PartyList;
    }

    /**
     * @param db3PartyList the db3PartyList to set
     */
    public void setDb3PartyList(List<Db3party> db3PartyList) {
        this.db3PartyList = db3PartyList;
    }

    public List<DbOutlets> getListOutletsSons() {
        return listOutletsSons;
    }

    public void setListOutletsSons(List<DbOutlets> listOutletsSons) {
        this.listOutletsSons = listOutletsSons;
    }

    public List<DbOutlets> getListOutletsSonsDelete() {
        return listOutletsSonsDelete;
    }

    public void setListOutletsSonsDelete(List<DbOutlets> listOutletsSonsDelete) {
        this.listOutletsSonsDelete = listOutletsSonsDelete;
    }

}
