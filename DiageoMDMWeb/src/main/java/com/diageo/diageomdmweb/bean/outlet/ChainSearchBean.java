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
import com.diageo.diageonegocio.entidades.DbChains;
import com.diageo.diageonegocio.entidades.DbChannels;
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

/**
 *
 * @author EDUARDO
 */
@Named(value = "chainSearchBean")
@ViewScoped
public class ChainSearchBean extends CreateChainBean implements Serializable {

    @EJB
    private ParameterBeanLocal parameterBeanLocal;
    @Inject
    private LoginBean loginBean;
    private List<DbChains> chainsList;
    private List<DbPhones> phonesDelete;
    private List<DbPermissionSegments> listPermi;
    private boolean seeDetail;
    private Integer idChain;
    private boolean renderMassiveApproval;
    private boolean disabledFields;

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
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CATDEV.getId())) {
            setChainsList(chainBeanLocal.findAllChains());
            setRenderMassiveApproval(Boolean.FALSE);
            setDisabledFields(getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CATDEV.getId()));
        } else {
            setDisabledFields(Boolean.TRUE);
            setRenderMassiveApproval(Boolean.TRUE);
            List<String> statusMDM = new ArrayList<>();
            if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())
                    || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC.getId())) {
                statusMDM.add(StatusSystemMDM.PENDING_KAM.name());
                statusMDM.add(StatusSystemMDM.PENDING_TMC_POTENTIAL.name());
                statusMDM.add(StatusSystemMDM.REJECT.name());
            } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
                statusMDM.add(StatusSystemMDM.PENDING_COMMERCIAL_MANAGER.name());
            }
            setChainsList(new ArrayList<DbChains>());
            //Segmentos que puede ver el sistema
            List<DwParameters> parametersQuerySegment = parameterBeanLocal.findByKey(ParameterKeyEnum.QUERY_SUB_SEGMENT.name());
            //Carga los permisos del usuario logueado
            setListPermi(getLoginBean().getListPermissionSegment());
            //Carga los distribuidores '3party' que tiene asignado el usuario
            Set<Integer> listDistributor = new HashSet<>();
            for (DbPermissionSegments permi : getListPermi()) {
                listDistributor.add(permi.getDb3partyId().getDb3partyId());
            }
            for (Integer id3party : listDistributor) {
                Set<Integer> listSubSegment = new HashSet<>();
                for (DbPermissionSegments permi : getListPermi()) {
                    if (permi.getDb3partyId().getDb3partyId().equals(id3party)) {
                        if (permi.getSubSegmentCheck().equals(StateEnum.ACTIVE.getState())) {
                            listSubSegment.add(permi.getSubSegmentId());
                        } else if (permi.getSegmentCheck().equals(StateEnum.ACTIVE.getState())) {
                            try {
                                DbSegments listSegment = segmentoBeanLocal.findById(permi.getSegmentId());
                                for (DbSubSegments subSegtem : listSegment.getDbSubSegmentsList()) {
                                    listSubSegment.add(subSegtem.getSubSegmentId());
                                }
                            } catch (DiageoBusinessException ex) {
                                Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (permi.getSubChannelCheck().equals(StateEnum.ACTIVE.getState())) {
                            try {
                                DbSubChannels subChaTemp = subChannelBeanLocal.consultarId(permi.getSubChannelId());
                                for (DbSegments seg : subChaTemp.getDbSegmentsList()) {
                                    for (DbSubSegments subSeg : seg.getDbSubSegmentsList()) {
                                        listSubSegment.add(subSeg.getSubSegmentId());
                                    }
                                }
                            } catch (DiageoBusinessException ex) {
                                Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (permi.getChannelCheck().equals(StateEnum.ACTIVE.getState())) {
                            try {
                                DbChannels channelTemp = channelBeanLocal.findById(permi.getChannelId());
                                for (DbSubChannels subCha : channelTemp.getDbSubChannelsList()) {
                                    for (DbSegments seg : subCha.getDbSegmentsList()) {
                                        for (DbSubSegments subSeg : seg.getDbSubSegmentsList()) {
                                            listSubSegment.add(subSeg.getSubSegmentId());
                                        }
                                    }
                                }
                            } catch (DiageoBusinessException ex) {
                                Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                //Mirar cuales segmentos tiene permiso el sistema para ver, y esos se dejan para la consulta
                List<Integer> listSegmentQuery = new ArrayList<>();
                for (DwParameters param : parametersQuerySegment) {
                    Integer id = Integer.parseInt(param.getParameterValue());
                    for (Integer seg : listSubSegment) {
                        if (id.equals(seg)) {
                            listSegmentQuery.add(id);
                            break;
                        }
                    }
                }
                //Consultar cadenas
                List<DbChains> listChainTem = chainBeanLocal.findBy3PartyPermissionSegment(id3party, listSegmentQuery, statusMDM);
                getChainsList().addAll(listChainTem);
            }

        }

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
        setActiveChain(chain.getIsActive().equals(StateEnum.ACTIVE.getState()));
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
        setSeeDetail(Boolean.FALSE);
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
            chain.setIsActive(isActiveChain() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
            chain.setKiernanId(getKiernan() != null ? getKiernan().toUpperCase() : "");
            chain.setLatitude(getLatitude());
            chain.setLongitude(getLongitude());
            chain.setNameChain(getChainName() != null ? getChainName().toUpperCase() : "");
            chain.setNeighborhood(getNeighborhood() != null ? getNeighborhood().toUpperCase() : "");
            chain.setPotentialId(getPotentialSelected());
            chain.setSubSegmentId(getSubSegmentSelected());
            chain.setStatusChain(getStatus());
            this.deletePhone();
            if (!getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                    && !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())) {
                chain.setStatusMDM(StatusSystemMDM.statusEngine(StatusSystemMDM.APPROVED, getLoginBean().getUsuario().getProfileId().getProfileId()).name());
            }
            chainBeanLocal.updateChain(chain);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(CreateChainBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }
    
    public void rejectChain(){
        try {
            DbChains chain = chainBeanLocal.findById(getIdChain());
            chain.setStatusMDM(StatusSystemMDM.statusEngine(StatusSystemMDM.REJECT, getLoginBean().getUsuario().getProfileId().getProfileId()).name());
            chainBeanLocal.updateChain(chain);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(ChainSearchBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void approbatrionMasive() {
        for (DbChains chain : getChainsList()) {
            if (chain.isApprobationMassive()) {
                if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC.getId())) {
                    chain.setStatusMDM(StatusSystemMDM.PENDING_COMMERCIAL_MANAGER.name());
                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
                    chain.setStatusMDM(StatusSystemMDM.APPROVED.name());
                }
                try {
                    chainBeanLocal.updateChain(chain);
                } catch (DiageoBusinessException ex) {
                    Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        init();
        showInfoMessage(capturarValor("sis_approbation_masive"));
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
    
    public boolean isRenderButtonReject() {
        return getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId());
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

}
