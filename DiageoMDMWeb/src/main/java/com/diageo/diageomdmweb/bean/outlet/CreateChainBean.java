/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.outlet;

import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.admincontrollerweb.enums.StatusSystemMDM;
import com.diageo.diageomdmweb.bean.DiageoApplicationBean;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageomdmweb.constant.PatternConstant;
import com.diageo.diageonegocio.beans.ChainBeanLocal;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.ClusterBeanLocal;
import com.diageo.diageonegocio.beans.CustomerBeanLocal;
import com.diageo.diageonegocio.beans.Db3PartyBeanLocal;
import com.diageo.diageonegocio.beans.PhonesBeanLocal;
import com.diageo.diageonegocio.beans.SegmentBeanLocal;
import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.beans.SubSegmentoBeanLocal;
import com.diageo.diageonegocio.beans.TypePhoneBeanLocal;
import com.diageo.diageonegocio.entidades.Audit;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.DbChains;
import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.entidades.DbClusters;
import com.diageo.diageonegocio.entidades.DbCustomers;
import com.diageo.diageonegocio.entidades.DbDepartaments;
import com.diageo.diageonegocio.entidades.DbPhones;
import com.diageo.diageonegocio.entidades.DbPotentials;
import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.entidades.DbTowns;
import com.diageo.diageonegocio.entidades.DbTypePhones;
import com.diageo.diageonegocio.enums.StateOutletChain;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author EDUARDO
 */
@Named(value = "createChainBean")
@ViewScoped
public class CreateChainBean extends DiageoRootBean implements Serializable {

    @Inject
    private LoginBean loginBean;
    @Inject
    protected DiageoApplicationBean diageoApplicationBean;
    @EJB
    protected ChannelBeanLocal channelBeanLocal;
    @EJB
    protected SubChannelBeanLocal subChannelBeanLocal;
    @EJB
    protected SegmentBeanLocal segmentoBeanLocal;
    @EJB
    protected SubSegmentoBeanLocal subSegmentoBeanLocal;
    @EJB
    protected ClusterBeanLocal clusterBeanLocal;
    @EJB
    protected TypePhoneBeanLocal typePhoneBeanLocal;
    @EJB
    protected ChainBeanLocal chainBeanLocal;
    @EJB
    protected Db3PartyBeanLocal db3PartyBeanLocal;
    @EJB
    protected PhonesBeanLocal phonesBeanLocal;
    @EJB
    protected CustomerBeanLocal customerBeanLocal;
    private String kiernan;
    private String chainName;
    private String businessName;
    private String eanCode;
    private DbChannels channelSelected;
    private DbSubChannels subChannelSelected;
    private DbSegments segmentSelected;
    private DbSubSegments subSegmentSelected;
    private DbPotentials potentialSelected;
    private DbClusters clusterSelected;
    private DbDepartaments departamentSelected;
    private DbTowns townSelected;
    private Db3party db3PartySelected;
    private String address;
    private String neighborhood;
    private Double latitude;
    private Double longitude;
    private String phoneAdd;
    private String status;
    private boolean activeChain;
    private DbPhones newPhone;
    private DbTypePhones typePhone;
    private List<DbChannels> listChannel;
    private List<DbSubChannels> listSubChannel;
    private List<DbSegments> listSegment;
    private List<DbSubSegments> listSubSegment;
    private List<DbPotentials> listPotential;
    private List<DbClusters> listCluster;
    private List<DbDepartaments> listDepartament;
    private List<DbTowns> listTowns;
    private List<DbPhones> listPhones;
    private List<DbTypePhones> listTypePhone;
    private List<Db3party> list3Party;
    private List<DbCustomers> listCustomers;
    private DbCustomers customer;

    /**
     * Creates a new instance of CreateChainBean
     */
    public CreateChainBean() {
    }

    @PostConstruct
    public void init() {
        setListChannel(channelBeanLocal.findAllChannel());
        setListTypePhone(typePhoneBeanLocal.findAll());
        setList3Party(db3PartyBeanLocal.searchAllDistributor());
        initFields();
    }

    public void initFields() {
        setNewPhone(new DbPhones());
        setListPhones(new ArrayList<DbPhones>());
        setDepartamentSelected(getDiageoApplicationBean().getListaDepartamento().get(0));
        setTownSelected(getDepartamentSelected().getDbTownsList().get(0));
        setChannelSelected(getListChannel().get(0));
        setListSubChannel(getChannelSelected().getDbSubChannelsList());
        setSubChannelSelected(getListSubChannel().get(0));
        setListSegment(getSubChannelSelected().getDbSegmentsList());
        setSegmentSelected(getListSegment().get(0));
        setListSubSegment(getSegmentSelected().getDbSubSegmentsList());
        setSubSegmentSelected(getListSubSegment().get(0));
        setListPotential(getSubSegmentSelected().getDbPotentialsList());
        setPotentialSelected(getListPotential().get(0));
        setListCluster(clusterBeanLocal.findAll());
        setClusterSelected(getListCluster().get(0));
        setDb3PartySelected(getList3Party().get(0));
        setCustomer(new DbCustomers());
        setListCustomers(new ArrayList<DbCustomers>());
        setKiernan(EMPTY_FIELD);
        setBusinessName(EMPTY_FIELD);
        setChainName(EMPTY_FIELD);
        setEanCode(EMPTY_FIELD);
        setActiveChain(Boolean.FALSE);
        setNeighborhood(EMPTY_FIELD);
        setAddress(EMPTY_FIELD);
        setLatitude(null);
        setLongitude(null);
    }

    public void saveChain() {
        try {
            DbChains chain = new DbChains();
            chain.setAddress(getAddress() != null ? getAddress().toUpperCase() : "");
            chain.setBusinessName(getBusinessName() != null ? getBusinessName().toUpperCase() : "");
            chain.setCodeEan(getEanCode() != null ? getEanCode().toUpperCase() : "");
            chain.setDbClusterId(getClusterSelected());
            chain.setDbPartyId(getDb3PartySelected());
            cleanIdPhones();
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
            chain.setStatusMDM(StatusSystemMDM.PENDING_KAM.name());
            DbCustomers custo = saveCustomer();
            if (custo != null) {
                getListCustomers().add(custo);
            }
            chain.setDbCustomerList(getListCustomers());
            Audit audit = new Audit();
            audit.setCreationDate(super.getCurrentDate());
            audit.setCreationUser(getLoginBean().getUsuario().getEmailUser());
            chain.setAudit(audit);
            chainBeanLocal.createChain(chain);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
            initFields();
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(CreateChainBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public DbCustomers saveCustomer() {
        try {
            DbCustomers customer = new DbCustomers();
            customer.setAddress(getAddress() != null ? getAddress().toUpperCase() : null);
            customer.setCustomerName(getBusinessName() != null ? getBusinessName().toUpperCase() : null);
            customer.setKiernanId(getKiernan());
            customer.setNumberPdv(getEanCode());
            customer.setStatusCustomer(getStatus());
            customer.setTownId(getTownSelected());
            return customerBeanLocal.createCustomer(customer);
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(CreateChainBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void addPhone() {
        if (getNewPhone().getNumberPhone() != null && !getNewPhone().getNumberPhone().isEmpty()) {
            if (Pattern.matches(PatternConstant.NUMBER, getNewPhone().getNumberPhone())) {
                getNewPhone().setTypePhoneId(getTypePhone());
                if (getNewPhone().getPhoneId() == null) {
                    getNewPhone().setPhoneId(getListPhones().size() + 1);
                    getNewPhone().setDeleteId(Boolean.TRUE);
                }
                getListPhones().add(getNewPhone());
                setNewPhone(new DbPhones());
            } else {
                showWarningMessage(capturarValor("chain_msg_phone_pattern"));
            }
        } else {
            showWarningMessage(capturarValor("chain_msg_phone_empty"));
        }
    }

    public void addCustomer() {
        if (getCustomer().getCustomerName() != null && !getCustomer().getCustomerName().isEmpty()) {
            for (DbCustomers cus : getListCustomers()) {
                if (cus.equals(getCustomer())) {
                    showWarningMessage(capturarValor("customer_msg_equal"));
                    return;
                }
            }
            getListCustomers().add(getCustomer());
            setCustomer(new DbCustomers());
        } else {
            showWarningMessage(capturarValor("chain_msg_customer_empty"));
        }
    }

    public void deletePhone(DbPhones phone) {
        getListPhones().remove(phone);
    }

    public void deleteCustomer(DbCustomers custo) {
        getListCustomers().remove(custo);
    }

    public void listenerChannel() {
        setListSubChannel(getChannelSelected().getDbSubChannelsList());
        setSubChannelSelected(getListSubChannel().get(0));
        this.listenerSubChannel();
    }

    public void listenerSubChannel() {
        setSegmentSelected(getSubChannelSelected().getDbSegmentsList().get(0));
        setListSegment(getSubChannelSelected().getDbSegmentsList());
        this.listenerSegment();
    }

    public void listenerSegment() {
        setSubSegmentSelected(getSegmentSelected().getDbSubSegmentsList().get(0));
        setListSubSegment(getSegmentSelected().getDbSubSegmentsList());
        listenerSubSegment();
    }

    public void listenerSubSegment() {
        if (getSubSegmentSelected().getDbPotentialsList() == null || getSubSegmentSelected().getDbPotentialsList().isEmpty()) {
            setListPotential(new ArrayList<DbPotentials>());
        } else {
            setPotentialSelected(getSubSegmentSelected().getDbPotentialsList().get(0));
            setListPotential(getSubSegmentSelected().getDbPotentialsList());
        }
    }

    public List<DbChains> completeChainFather(String query) {
        return chainBeanLocal.findByNameChain(query);
    }

    public List<DbCustomers> completeCustomer(String query) {
        return customerBeanLocal.findByNameCustomer(query);
    }

    /**
     * A cada telefono se le asigna un id temporal, para que pueda ser eliminado
     * mientras se crea
     */
    protected void cleanIdPhones() {
        for (DbPhones phone : getListPhones()) {
            if (phone.isDeleteId()) {
                phone.setPhoneId(null);
            }
        }
    }

    /**
     * @return the kiernan
     */
    public String getKiernan() {
        return kiernan;
    }

    /**
     * @param kiernan the kiernan to set
     */
    public void setKiernan(String kiernan) {
        this.kiernan = kiernan;
    }

    /**
     * @return the chainName
     */
    public String getChainName() {
        return chainName;
    }

    /**
     * @param chainName the chainName to set
     */
    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    /**
     * @return the businessName
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * @param businessName the businessName to set
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * @return the eanCode
     */
    public String getEanCode() {
        return eanCode;
    }

    /**
     * @param eanCode the eanCode to set
     */
    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
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
     * @return the potentialSelected
     */
    public DbPotentials getPotentialSelected() {
        return potentialSelected;
    }

    /**
     * @param potentialSelected the potentialSelected to set
     */
    public void setPotentialSelected(DbPotentials potentialSelected) {
        this.potentialSelected = potentialSelected;
    }

    /**
     * @return the clusterSelected
     */
    public DbClusters getClusterSelected() {
        return clusterSelected;
    }

    /**
     * @param clusterSelected the clusterSelected to set
     */
    public void setClusterSelected(DbClusters clusterSelected) {
        this.clusterSelected = clusterSelected;
    }

    /**
     * @return the departamentSelected
     */
    public DbDepartaments getDepartamentSelected() {
        return departamentSelected;
    }

    /**
     * @param departamentSelected the departamentSelected to set
     */
    public void setDepartamentSelected(DbDepartaments departamentSelected) {
        this.departamentSelected = departamentSelected;
    }

    /**
     * @return the townSelected
     */
    public DbTowns getTownSelected() {
        return townSelected;
    }

    /**
     * @param townSelected the townSelected to set
     */
    public void setTownSelected(DbTowns townSelected) {
        this.townSelected = townSelected;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the neighborhood
     */
    public String getNeighborhood() {
        return neighborhood;
    }

    /**
     * @param neighborhood the neighborhood to set
     */
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the phoneAdd
     */
    public String getPhoneAdd() {
        return phoneAdd;
    }

    /**
     * @param phoneAdd the phoneAdd to set
     */
    public void setPhoneAdd(String phoneAdd) {
        this.phoneAdd = phoneAdd;
    }

    /**
     * @return the newPhone
     */
    public DbPhones getNewPhone() {
        return newPhone;
    }

    /**
     * @param newPhone the newPhone to set
     */
    public void setNewPhone(DbPhones newPhone) {
        this.newPhone = newPhone;
    }

    /**
     * @return the typePhone
     */
    public DbTypePhones getTypePhone() {
        return typePhone;
    }

    /**
     * @param typePhone the typePhone to set
     */
    public void setTypePhone(DbTypePhones typePhone) {
        this.typePhone = typePhone;
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
     * @return the listSubChannel
     */
    public List<DbSubChannels> getListSubChannel() {
        return listSubChannel;
    }

    /**
     * @param listSubChannel the listSubChannel to set
     */
    public void setListSubChannel(List<DbSubChannels> listSubChannel) {
        this.listSubChannel = listSubChannel;
    }

    /**
     * @return the listSegment
     */
    public List<DbSegments> getListSegment() {
        return listSegment;
    }

    /**
     * @param listSegment the listSegment to set
     */
    public void setListSegment(List<DbSegments> listSegment) {
        this.listSegment = listSegment;
    }

    /**
     * @return the listSubSegment
     */
    public List<DbSubSegments> getListSubSegment() {
        return listSubSegment;
    }

    /**
     * @param listSubSegment the listSubSegment to set
     */
    public void setListSubSegment(List<DbSubSegments> listSubSegment) {
        this.listSubSegment = listSubSegment;
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

    /**
     * @return the listCluster
     */
    public List<DbClusters> getListCluster() {
        return listCluster;
    }

    /**
     * @param listCluster the listCluster to set
     */
    public void setListCluster(List<DbClusters> listCluster) {
        this.listCluster = listCluster;
    }

    /**
     * @return the listDepartament
     */
    public List<DbDepartaments> getListDepartament() {
        return listDepartament;
    }

    /**
     * @param listDepartament the listDepartament to set
     */
    public void setListDepartament(List<DbDepartaments> listDepartament) {
        this.listDepartament = listDepartament;
    }

    /**
     * @return the listTowns
     */
    public List<DbTowns> getListTowns() {
        return listTowns;
    }

    /**
     * @param listTowns the listTowns to set
     */
    public void setListTowns(List<DbTowns> listTowns) {
        this.listTowns = listTowns;
    }

    /**
     * @return the listPhones
     */
    public List<DbPhones> getListPhones() {
        return listPhones;
    }

    /**
     * @param listPhones the listPhones to set
     */
    public void setListPhones(List<DbPhones> listPhones) {
        this.listPhones = listPhones;
    }

    /**
     * @return the diageoApplicationBean
     */
    public DiageoApplicationBean getDiageoApplicationBean() {
        return diageoApplicationBean;
    }

    /**
     * @return the listTypePhone
     */
    public List<DbTypePhones> getListTypePhone() {
        return listTypePhone;
    }

    /**
     * @param listTypePhone the listTypePhone to set
     */
    public void setListTypePhone(List<DbTypePhones> listTypePhone) {
        this.listTypePhone = listTypePhone;
    }

    /**
     * @return the db3PartySelected
     */
    public Db3party getDb3PartySelected() {
        return db3PartySelected;
    }

    /**
     * @param db3PartySelected the db3PartySelected to set
     */
    public void setDb3PartySelected(Db3party db3PartySelected) {
        this.db3PartySelected = db3PartySelected;
    }

    /**
     * @return the list3Party
     */
    public List<Db3party> getList3Party() {
        return list3Party;
    }

    /**
     * @param list3Party the list3Party to set
     */
    public void setList3Party(List<Db3party> list3Party) {
        this.list3Party = list3Party;
    }

    /**
     * @return the activeChain
     */
    public boolean isActiveChain() {
        return activeChain;
    }

    /**
     * @param activeChain the activeChain to set
     */
    public void setActiveChain(boolean activeChain) {
        this.activeChain = activeChain;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public StateOutletChain[] getEnumStateOutletChain() {
        return StateOutletChain.values();
    }

    /**
     * @return the customer
     */
    public DbCustomers getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(DbCustomers customer) {
        this.customer = customer;
    }

    /**
     * @return the listCustomers
     */
    public List<DbCustomers> getListCustomers() {
        return listCustomers;
    }

    /**
     * @param listCustomers the listCustomers to set
     */
    public void setListCustomers(List<DbCustomers> listCustomers) {
        this.listCustomers = listCustomers;
    }

    /**
     * @return the loginBean
     */
    public LoginBean getLoginBean() {
        return loginBean;
    }

}
