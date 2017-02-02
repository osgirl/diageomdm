/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.outlet;

import com.diageo.admincontrollerweb.beans.ParameterBeanLocal;
import com.diageo.admincontrollerweb.beans.RelationUserBeanLocal;
import com.diageo.admincontrollerweb.beans.UserBeanLocal;
import com.diageo.admincontrollerweb.entities.DwRelationUsers;
import com.diageo.admincontrollerweb.entities.DwUsers;
import com.diageo.admincontrollerweb.enums.ProfileEnum;
import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.admincontrollerweb.enums.StatusSystemMDM;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageomdmweb.datamodel.DbOutletsLazyDataModel;
import com.diageo.diageomdmweb.mail.EMail;
import com.diageo.diageomdmweb.mail.templates.VelocityTemplate;
import com.diageo.diageonegocio.beans.OutletsUserBeanLocal;
import com.diageo.diageonegocio.beans.PermissionsegmentBeanLocal;
import com.diageo.diageonegocio.entidades.Audit;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.DbCustomers;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbOutletsUsers;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.entidades.DbPhones;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.enums.StateDiageo;
import com.diageo.diageonegocio.enums.StateOutletChain;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
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
    private ParameterBeanLocal parameterBeanLocal;
    @EJB
    private PermissionsegmentBeanLocal permissionsegmentBeanLocal;
    @EJB
    private UserBeanLocal userBeanLocal;
    @EJB
    private OutletsUserBeanLocal outletsUserBeanLocal;
    @EJB
    private RelationUserBeanLocal relationUserBeanLocal;
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

    /**
     * Creates a new instance of OutletConsultarBean
     */
    public OutletConsultarBean() {
    }

    @PostConstruct
    @Override
    public void init() {
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())) {
            setOutletsLazyDataModel(new DbOutletsLazyDataModel(outletBeanLocal, getLoginBean().getUsuario().getProfileId().getProfileId()));
            setRenderMassiveApproval(Boolean.FALSE);
            setButtonNameCommit(capturarValor("btn_send"));
        } else {
            setButtonNameCommit(capturarValor("btn_send_approved"));
            setDisabledFields(Boolean.TRUE);
            setRenderMassiveApproval(Boolean.TRUE);
            if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
                listId = relationUserBeanLocal.findByParentId(getLoginBean().getUsuario().getUserId());
                setOutletsLazyDataModel(new DbOutletsLazyDataModel(outletBeanLocal,
                        getLoginBean().getUsuario().getProfileId().getProfileId(), listId, outletsUserBeanLocal));
            } else {
                setOutletsLazyDataModel(new DbOutletsLazyDataModel(outletBeanLocal,
                        getLoginBean().getUsuario().getProfileId().getProfileId(), getLoginBean().getUsuario().getUserId(), outletsUserBeanLocal));
            }
        }
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
    }

    public void seeDetail(DbOutlets out) {
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
        setSubSegmentSelected(out.getSubSegmentId());
        setSegmentSelected(getSubSegmentSelected().getSegmentId());
        setSubChannelSelected(getSegmentSelected().getSubChannelId());
        setChannelSelected(getSubChannelSelected().getChannelId());
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
        setDistributorOld(out.getDb3PartyIdOld());
        setSellerSelected(out.getDb3partySaleId());
        setJourneyPlan(out.getJourneyPlan().equals(StateEnum.ACTIVE.getState()));
        setStatusOutlet(out.getStatusOutlet());
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

    @Override
    public void saveOutlet() {
        try {
            DbOutlets outlet = outletBeanLocal.findById(getIdOutlet());
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
            outlet.setOutletIdFather(getFather());
            outlet.setStatusOutlet(getStatusOutlet());
            outlet.setOutletName(getOutletName() != null ? getOutletName().toUpperCase() : "");
            outlet.setPotentialId(getPotentialSelected());
            outlet.setSubSegmentId(getSubSegmentSelected());
            outlet.setTownId(getTownSelected());
            outlet.setTypeOutlet(getTypeOutlet() != null ? getTypeOutlet().toUpperCase() : "");
            outlet.setVerificationNumber(getVerificationNumber());
            outlet.setWebsite(getWebsite() != null ? getWebsite().toUpperCase() : "");
            outlet.setDb3partySaleId(getSellerSelected());
            outlet.setJourneyPlan(isJourneyPlan() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
//            outlet.setWine(isWine() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
//            outlet.setBeer(isBeer() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
//            outlet.setSpirtis(isSpirtis() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
            outlet.setDbCustomersList(getListCustomers());

            if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
                if (outlet.getStatusMDM().equals(StatusSystemMDM.PENDING_APPROVAL.name())) {
                    outlet.setStatusMDM(StatusSystemMDM.APPROVED.name());
                }
            } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_DISTRIBUIDORES.getId())) {
                outlet.setStatusMDM(StatusSystemMDM.PENDING_TMC.name());
            } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())) {
                outlet.setStatusMDM(StatusSystemMDM.PENDING_APPROVAL.name());
            }

            Audit audit = new Audit();
            audit.setCreationDate(outlet.getAudit() != null ? outlet.getAudit().getCreationDate() : null);
            audit.setCreationUser(outlet.getAudit() != null ? outlet.getAudit().getCreationUser() : null);
            audit.setModificationDate(super.getCurrentDate());
            audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
            outlet.setAudit(audit);
            deletCustomerChain();
            outletBeanLocal.updateOutlet(outlet);
            //sendMail();
            setVerDetalle(!getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                    && !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId()));
            //init();
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dtOutletSearch').clearFilters()");
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(OutletCrearBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }

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
            outletsUserBeanLocal.updateOutletCommercialManager(listId, StatusSystemMDM.APPROVED.name(), filtersTable);
        } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())) {
            outletsUserBeanLocal.updateOutlet(getLoginBean().getUsuario().getUserId(), StatusSystemMDM.PENDING_APPROVAL.name(), filtersTable);
        } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_DISTRIBUIDORES.getId())) {
            outletsUserBeanLocal.updateOutlet(getLoginBean().getUsuario().getUserId(), StatusSystemMDM.PENDING_TMC.name(), filtersTable);
        }
        showInfoMessage(capturarValor("sis_msg_record_outlet_change"));
    }

    public void rejectAllOutlet() {
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
            outletsUserBeanLocal.updateOutletCommercialManager(listId, StatusSystemMDM.REJECT.name(), filtersTable);
            showInfoMessage(capturarValor("sis_msg_record_outlet_change"));
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
                        out.setStatusMDM(StatusSystemMDM.APPROVED.name());
                    }
                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())) {
                    out.setStatusMDM(StatusSystemMDM.PENDING_APPROVAL.name());
                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_DISTRIBUIDORES.getId())) {
                    out.setStatusMDM(StatusSystemMDM.PENDING_TMC.name());
                }
                if (update) {
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
                            out.setStatusMDM(StatusSystemMDM.REJECT.name());
                            outletBeanLocal.updateOutlet(out);
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
            DbOutlets outlet = outletBeanLocal.findById(getIdOutlet());
            outlet.setStatusMDM(StatusSystemMDM.REJECT.name());
            outletBeanLocal.updateOutlet(outlet);
            //sendMail();
            init();
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
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
                && !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId());
    }

    public boolean isDisabledFuntionalSegmentation() {
        return !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())
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

}
