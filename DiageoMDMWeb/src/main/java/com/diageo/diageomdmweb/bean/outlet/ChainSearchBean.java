/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.outlet;

import com.diageo.admincontrollerweb.beans.ParameterBeanLocal;
import com.diageo.admincontrollerweb.entities.DwParameters;
import com.diageo.admincontrollerweb.enums.ParameterKeyEnum;
import com.diageo.admincontrollerweb.enums.ProfileEnum;
import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.admincontrollerweb.enums.StatusSystemMDM;
import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageonegocio.beans.ChainUserBeanLocal;
import com.diageo.diageonegocio.entidades.Audit;
import com.diageo.diageonegocio.entidades.DbChains;
import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.entidades.DbCustomers;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.entidades.DbPhones;
import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

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
    @Inject
    private LoginBean loginBean;
    private List<DbChains> chainsList;
    private List<DbChains> chainsListFiltered;
    private List<DbPhones> phonesDelete;
    private List<DbPermissionSegments> listPermi;
    private List<DbCustomers> listCustomerDelete;
    private boolean seeDetail;
    private Integer idChain;
    private boolean renderMassiveApproval;
    private boolean disabledFields;
    private boolean disabledSegmentation;
    private String buttonNameCommit;

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
            List<Integer> listChainByUser = chainUserBeanLocal.findByUserId(getLoginBean().getUsuario().getUserId());
            setChainsList(chainBeanLocal.findAllChainsProfiles(listChainByUser));
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
    }

    public void detailChain(DbChains chain) {
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
            DbChains chain = chainBeanLocal.findById(getIdChain());
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
            if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())
                    || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())) {
                chain.setStatusMDM(StatusSystemMDM.statusEngine(StatusSystemMDM.APPROVED, getLoginBean().getUsuario().getProfileId().getProfileId()).name());
            }
            deletCustomerChain();
            chainBeanLocal.updateChain(chain);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
            init();
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dtChainSearch').clearFilters()");
            setSeeDetail(Boolean.TRUE);
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(CreateChainBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void rejectChain() {
        try {
            DbChains chain = chainBeanLocal.findById(getIdChain());
            chain.setStatusMDM(StatusSystemMDM.statusEngine(StatusSystemMDM.REJECT, getLoginBean().getUsuario().getProfileId().getProfileId()).name());
            chainBeanLocal.updateChain(chain);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
            init();
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dtChainSearch').clearFilters()");
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(ChainSearchBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void approbatrionMasive() {
        boolean flagChainSelected = false;
        for (DbChains chain : getChainsList()) {
            if (chain.isApprobationMassive()) {
                if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId())
                        || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())) {
                    chain.setStatusMDM(StatusSystemMDM.PENDING_COMMERCIAL_MANAGER.name());
                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())
                        || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())) {
                    chain.setStatusMDM(StatusSystemMDM.APPROVED.name());
                }
                try {
                    chainBeanLocal.updateChain(chain);
                    flagChainSelected = true;
                } catch (DiageoBusinessException ex) {
                    Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (flagChainSelected) {
            init();
            showInfoMessage(capturarValor("sis_approbation_masive"));
        } else {
            showWarningMessage(capturarValor("msg_not_outlets_select"));
        }
    }

    public void rejectAllChain() {
        boolean flagChainSelected = false;
        for (DbChains chain : getChainsList()) {
            if (chain.isApprobationMassive()) {
                chain.setStatusMDM(StatusSystemMDM.REJECT.name());
                try {
                    chainBeanLocal.updateChain(chain);
                    flagChainSelected = true;
                } catch (DiageoBusinessException ex) {
                    Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (flagChainSelected) {
            init();
            showInfoMessage(capturarValor("sis_approbation_masive"));
        } else {
            showWarningMessage(capturarValor("msg_not_outlets_select"));
        }
    }

    public void selectAll() {
        for (DbChains chain : getChainsList()) {
            chain.setApprobationMassive(Boolean.TRUE);
        }
    }

    public void unSelectAll() {
        for (DbChains chain : getChainsList()) {
            chain.setApprobationMassive(Boolean.FALSE);
        }
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

    public boolean isRenderButtonReject() {
        return getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId());
    }

    public boolean isRenderPendingTMCRejectStatus() {
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_CADENAS.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId())) {
            return true;
        } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
            return false;
        }
        return false;
    }

    public boolean isRenderPendingCMStatus() {
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CP_A_CADENAS.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
            return true;
        } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())) {
            return false;
        }
        return false;
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

}
