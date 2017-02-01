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
import com.diageo.admincontrollerweb.enums.StatusSystemMDM;
import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageonegocio.beans.ChainUserBeanLocal;
import com.diageo.diageonegocio.entidades.Audit;
import com.diageo.diageonegocio.entidades.DbChains;
import com.diageo.diageonegocio.entidades.DbChainsUsers;
import com.diageo.diageonegocio.entidades.DbCustomers;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.entidades.DbPhones;
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
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
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

    /**
     * Creates a new instance of ChainSearchBean
     */
    public ChainSearchBean() {
    }

    @PostConstruct
    @Override
    public void init() {
        setSeeDetail(Boolean.TRUE);
        super.init();
        setDisabledSegmentation(Boolean.FALSE);
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())) {
            setChainsList(chainBeanLocal.findAllChains());
            setRenderMassiveApproval(Boolean.FALSE);
            setButtonNameCommit(capturarValor("btn_send"));
        } else {
            setButtonNameCommit(capturarValor("btn_approved"));
            setDisabledFields(Boolean.TRUE);
            setRenderMassiveApproval(Boolean.TRUE);
            if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())
                    || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())) {
                listId = relationUserBeanLocal.findByParentId(getLoginBean().getUsuario().getUserId());
                setChainsList(chainUserBeanLocal.findByUserIdJoinIn(listId));
            } else {
                setChainsList(chainUserBeanLocal.findByUserIdJoin(getLoginBean().getUsuario().getUserId()));
            }
        }
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

    public void detailChain(DbChains chain) {
        setChainSelected(chain);
        setIdChain(chain.getChainId());
        setAddress(chain.getAddress());
        setBusinessName(chain.getBusinessName());
        setEanCode(chain.getCodeEan());
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
        setSubSegmentSelected(chain.getSubSegmentId());
        setSegmentSelected(getSubSegmentSelected().getSegmentId());
        setSubChannelSelected(getSegmentSelected().getSubChannelId());
        setChannelSelected(getSubChannelSelected().getChannelId());
        setListSubChannel(getChannelSelected().getDbSubChannelsList());
        setListSegment(getSubChannelSelected().getDbSegmentsList());
        setListSubSegment(getSegmentSelected().getDbSubSegmentsList());
        setListPotential(getSubSegmentSelected().getDbPotentialsList());
        setPhonesDelete(new ArrayList<DbPhones>());
        setListCustomers(chain.getDbCustomerList());
        setSeeDetail(Boolean.FALSE);
        setLayerSelected(chain.getLayerId());
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
            Audit audit = new Audit();
            audit.setCreationDate(chain.getAudit() != null ? chain.getAudit().getCreationDate() : null);
            audit.setCreationUser(chain.getAudit() != null ? chain.getAudit().getCreationUser() : null);
            audit.setModificationDate(super.getCurrentDate());
            audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
            chain.setAudit(audit);
            chain.setLayerId(getLayerSelected());
            this.deletePhone();
            if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())
                    || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())) {
                if (chain.getStatusMDM().equals(ProfileEnum.COMMERCIAL_MANAGER.name())) {
                    chain.setStatusMDM(StatusSystemMDM.APPROVED.name());
                }
            } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId())
                    || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM_CADENAS.getId())) {
                chain.setStatusMDM(StatusSystemMDM.PENDING_COMMERCIAL_MANAGER.name());
            } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_CADENAS.getId())) {
                chain.setStatusMDM(StatusSystemMDM.PENDING_KAM.name());
            }
            deletCustomerChain();
            chainBeanLocal.updateChain(chain);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));

            setSeeDetail(Boolean.TRUE);
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(CreateChainBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void buttonBack() {
        setSeeDetail(Boolean.TRUE);
    }

    public void rejectChain() {
        try {
            DbChains chain = getChainSelected();
            chain.setStatusMDM(StatusSystemMDM.REJECT.name());
            chainBeanLocal.updateChain(chain);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
            setSeeDetail(Boolean.TRUE);
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
                        List<DwRelationUsers> listTemp = relationUserBeanLocal.findByUserIdAndParent(chainIdTemp.getDbChainsUsersPK().getUserId(), getLoginBean().getUsuario().getUserId());
                        if (!listTemp.isEmpty()) {
                            update = listTemp.get(0).getStateApproved();
                        }
                    }
                    if (update) {
                        ch.setStatusMDM(StatusSystemMDM.APPROVED.name());
                    }
                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId())
                        || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM_CADENAS.getId())) {
                    ch.setStatusMDM(StatusSystemMDM.PENDING_COMMERCIAL_MANAGER.name());
                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_CADENAS.getId())) {
                    ch.setStatusMDM(StatusSystemMDM.PENDING_KAM.name());
                }
                if (update) {
                    chainBeanLocal.updateChain(ch);
                }
            } catch (DiageoBusinessException ex) {
                Logger.getLogger(ChainSearchBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())
//                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())) {
//            chainUserBeanLocal.updateAllChains(filtersTable, getLoginBean().getUsuario().getUserId(), StatusSystemMDM.APPROVED.name());
//        } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId())
//                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM_CADENAS.getId())) {
//            chainUserBeanLocal.updateAllChains(filtersTable, getLoginBean().getUsuario().getUserId(), StatusSystemMDM.PENDING_COMMERCIAL_MANAGER.name());
//        } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_CADENAS.getId())) {
//            chainUserBeanLocal.updateAllChains(filtersTable, getLoginBean().getUsuario().getUserId(), StatusSystemMDM.PENDING_KAM.name());
//        }
        showInfoMessage(capturarValor("sis_msg_record_outlet_change"));
//        setChainsList(new ArrayList<DbChains>());
//        setChainsList(chainUserBeanLocal.findByUserIdJoin(getLoginBean().getUsuario().getUserId()));
    }

    public void approvedSelected() {
        for (DbChains chain : chainsListSelected) {
            try {
                boolean update = true;
                if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())
                        || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())) {
                    DbChainsUsers chainIdTemp = chainUserBeanLocal.findByChainId(chain.getChainId());
                    if (chainIdTemp != null) {
                        List<DwRelationUsers> listTemp = relationUserBeanLocal.findByUserIdAndParent(chainIdTemp.getDbChainsUsersPK().getUserId(), getLoginBean().getUsuario().getUserId());
                        if (!listTemp.isEmpty()) {
                            update = listTemp.get(0).getStateApproved();
                        }
                    }
                    if (update) {
                        chain.setStatusMDM(StatusSystemMDM.APPROVED.name());
                    }
                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId())
                        || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM_CADENAS.getId())) {
                    chain.setStatusMDM(StatusSystemMDM.PENDING_COMMERCIAL_MANAGER.name());
                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_CADENAS.getId())) {
                    chain.setStatusMDM(StatusSystemMDM.PENDING_KAM.name());
                }
                if (update) {
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
                    if (chainIdTemp != null) {
                        List<DwRelationUsers> listTemp = relationUserBeanLocal.findByUserIdAndParent(chainIdTemp.getDbChainsUsersPK().getUserId(), getLoginBean().getUsuario().getUserId());
                        if (!listTemp.isEmpty()) {
                            update = listTemp.get(0).getStateApproved();
                        }
                    }
                    if (update) {
                        ch.setStatusMDM(StatusSystemMDM.REJECT.name());
                        chainBeanLocal.updateChain(ch);
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
                        chainBeanLocal.updateChain(chain);
                    }
                }
            } catch (DiageoBusinessException ex) {
                Logger.getLogger(ChainSearchBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        showInfoMessage(capturarValor("sis_msg_record_outlet_change"));
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
        return getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId());
    }

    public boolean isRenderButtonReject() {
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())) {
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
            if (chainIdTemp != null) {
                List<DwRelationUsers> listTemp = relationUserBeanLocal.findByUserIdAndParent(chainIdTemp.getDbChainsUsersPK().getUserId(), getLoginBean().getUsuario().getUserId());
                if (!listTemp.isEmpty()) {
                    return listTemp.get(0).getStateApproved();
                }
            }
            return false;
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

}
