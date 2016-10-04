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
import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageomdmweb.constant.PatternConstant;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.CustomerBeanLocal;
import com.diageo.diageonegocio.beans.Db3PartyBeanLocal;
import com.diageo.diageonegocio.beans.OcsBeanLocal;
import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.beans.PhonesBeanLocal;
import com.diageo.diageonegocio.beans.SegmentBeanLocal;
import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.beans.SubSegmentoBeanLocal;
import com.diageo.diageonegocio.beans.TypePhoneBeanLocal;
import com.diageo.diageonegocio.entidades.Audit;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.entidades.DbCustomers;
import com.diageo.diageonegocio.entidades.DbDepartaments;
import com.diageo.diageonegocio.entidades.DbOcs;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbPhones;
import com.diageo.diageonegocio.entidades.DbPotentials;
import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.entidades.DbTowns;
import com.diageo.diageonegocio.entidades.DbTypePhones;
import com.diageo.diageonegocio.enums.StateDiageo;
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
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author yovanoty126
 */
@Named(value = "outletCrearBean")
@ViewScoped
public class OutletCrearBean extends DiageoRootBean implements Serializable {

    @Inject
    private LoginBean loginBean;
    @EJB
    protected OutletBeanLocal outletBeanLocal;
    @Inject
    private DiageoApplicationBean diageoApplicationBean;
    @EJB
    protected ChannelBeanLocal channelBeanLocal;
    @EJB
    protected SubChannelBeanLocal subChannelBeanLocal;
    @EJB
    protected SegmentBeanLocal segmentoBeanLocal;
    @EJB
    protected SubSegmentoBeanLocal subSegmentoBeanLocal;
    @EJB
    protected TypePhoneBeanLocal typePhoneBeanLocal;
    @EJB
    protected Db3PartyBeanLocal db3PartyBeanLocal;
    @EJB
    protected PhonesBeanLocal phonesBeanLocal;
    @EJB
    protected OcsBeanLocal ocsBeanLocal;
    @EJB
    protected CustomerBeanLocal customerBeanLocal;
    private DbChannels channelSelected;
    private DbSubChannels subChannelSelected;
    private DbSegments segmentSelected;
    private DbSubSegments subSegmentSelected;
    private DbPotentials potentialSelected;
    private DbDepartaments departamentSelected;
    private DbTowns townSelected;
    private Db3party db3PartySelected;
    private DbPhones newPhone;
    private DbTypePhones typePhone;
    private List<DbChannels> listChannel;
    private List<DbSubChannels> listSubChannel;
    private List<DbSegments> listSegment;
    private List<DbSubSegments> listSubSegment;
    private List<DbPotentials> listPotential;
    private List<DbDepartaments> listDepartament;
    private List<DbTowns> listTowns;
    private List<DbPhones> listPhones;
    private List<DbTypePhones> listTypePhone;
    private List<Db3party> list3Party;
    private List<Db3party> list3PartyToDeploy;
    private List<DbOcs> listOcs;
    private String email;
    private String nit;
    private String verificationNumber;
    private String businessName;
    private String outletName;
    private String pointSale;
    private String typeOutlet;
    private String kiernanId;
    private DbOcs ocsPrimary;
    private DbOcs ocsSecondary;
    private String website;
    private String address;
    private String neighborhood;
    private Double latitude;
    private Double longitude;
    private boolean wine;
    private boolean beer;
    private boolean spirtis;
    private boolean isFather;
    private DbOutlets father;
    private List<DbCustomers> listCustomers;
    private DbCustomers customer;

    /**
     * Creates a new instance of OutletVistaBean
     */
    public OutletCrearBean() {
    }

    @PostConstruct
    public void init() {
        setListChannel(channelBeanLocal.findAllChannel());
        setListTypePhone(typePhoneBeanLocal.findAll());
        setList3PartyToDeploy(db3PartyBeanLocal.searchDistributorByIsChain(StateEnum.INACTIVE.getState(), StateEnum.ACTIVE.getState()));
        setListOcs(ocsBeanLocal.findAll());
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
        setDb3PartySelected(getList3PartyToDeploy().get(0));
        setFather(new DbOutlets());
        setList3Party(new ArrayList<Db3party>());
        setCustomer(new DbCustomers());
        setListCustomers(new ArrayList<DbCustomers>());
        setAddress(EMPTY_FIELD);
        setBeer(Boolean.FALSE);
        setBusinessName(EMPTY_FIELD);
        setEmail(EMPTY_FIELD);
        setIsFather(Boolean.FALSE);
        setKiernanId(EMPTY_FIELD);
        setLatitude(null);
        setLongitude(null);
        setNeighborhood(EMPTY_FIELD);
        setNit(EMPTY_FIELD);
        setOutletName(EMPTY_FIELD);
        setPointSale(EMPTY_FIELD);
        setSpirtis(Boolean.FALSE);
        setVerificationNumber(EMPTY_FIELD);
        setWebsite(EMPTY_FIELD);
        setWine(Boolean.FALSE);
    }

    public void saveOutlet() {
        try {
            DbOutlets outlet = new DbOutlets();
            outlet.setAddress(getAddress() != null ? getAddress().toUpperCase() : "");
            outlet.setBusinessName(getBusinessName() != null ? getBusinessName().toUpperCase() : "");
            outlet.setDb3partyList(getList3Party());
            cleanIdPhones();
            outlet.setDbPhonesList(getListPhones());
            outlet.setEmail(getEmail() != null ? getEmail().toUpperCase() : "");
            outlet.setIsFather(isIsFather() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
            outlet.setIsNewOutlet(StateDiageo.ACTIVO.getId());
            outlet.setKiernanId(getKiernanId() != null ? getKiernanId().toUpperCase() : "");
            outlet.setLatitude(getLatitude());
            outlet.setLongitude(getLongitude());
            outlet.setNeighborhood(getNeighborhood() != null ? getNeighborhood().toUpperCase() : "");
            outlet.setNit(getNit() != null ? getNit().toUpperCase() : "");
            outlet.setNumberPdv(getPointSale() != null ? getPointSale().toUpperCase() : "");
            if (getOcsPrimary().getOcsId() != null) {
                outlet.setOcsPrimary(getOcsPrimary());
            }
            if (getOcsSecondary().getOcsId() != null) {
            outlet.setOcsSecondary(getOcsSecondary());
            }
            if (getFather() != null && getFather().getOutletId() != null) {
                outlet.setOutletIdFather(getFather());
            }
            outlet.setOutletName(getOutletName() != null ? getOutletName().toUpperCase() : "");
            outlet.setPotentialId(getPotentialSelected());
            outlet.setSubSegmentId(getSubSegmentSelected());
            outlet.setTownId(getTownSelected());
            outlet.setTypeOutlet(getTypeOutlet() != null ? getTypeOutlet().toUpperCase() : "");
            outlet.setVerificationNumber(getVerificationNumber());
            outlet.setWebsite(getWebsite() != null ? getWebsite().toUpperCase() : "");
//            outlet.setWine(isWine() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
//            outlet.setBeer(isBeer() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
//            outlet.setSpirtis(isSpirtis() ? StateDiageo.ACTIVO.getId() : StateDiageo.INACTIVO.getId());
            outlet.setStatusOutlet(StateOutletChain.ACTIVE.getId());
            outlet.setStatusMDM(StatusSystemMDM.PENDING_TMC.name());
            DbCustomers custo = saveCustomer();
            if (custo != null) {
                getListCustomers().add(custo);
            }
            outlet.setDbCustomersList(getListCustomers());
            Audit audit = new Audit();
            audit.setCreationDate(super.getCurrentDate());
            audit.setCreationUser(getLoginBean().getUsuario().getEmailUser());
            outlet.setAudit(audit);
            outletBeanLocal.createOutlet(outlet);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
            initFields();
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(OutletCrearBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }

    }

    public DbCustomers saveCustomer() {
        try {
            DbCustomers customer = new DbCustomers();
            customer.setAddress(getAddress() != null ? getAddress().toUpperCase() : null);
            customer.setCustomerName(getBusinessName() != null ? getBusinessName().toUpperCase() : null);
            customer.setKiernanId(getKiernanId());
            customer.setNumberPdv(getPointSale());
            customer.setStatusCustomer(StateOutletChain.ACTIVE.getId());
            customer.setTownId(getTownSelected());
            return customerBeanLocal.createCustomer(customer);
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(CreateChainBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<DbOutlets> completeChainFather(String query) {
        return outletBeanLocal.findByBusinessName(query);
    }

    public void addPhone() {
        if (getNewPhone().getNumberPhone() != null && !getNewPhone().getNumberPhone().isEmpty()) {
            if (Pattern.matches(PatternConstant.NUMBER, getNewPhone().getNumberPhone())) {
                getNewPhone().setTypePhoneId(getTypePhone());
                if (getNewPhone().getPhoneId() == null) {
                    getNewPhone().setPhoneId(getListPhones().size() + 1);
                    getNewPhone().setDeleteId(Boolean.TRUE);
                }
                Audit audit = new Audit();
                audit.setCreationDate(super.getCurrentDate());
                audit.setCreationUser(getLoginBean().getUsuario().getEmailUser());
                getNewPhone().setAudit(audit);
                getListPhones().add(getNewPhone());
                setNewPhone(new DbPhones());
            } else {
                showWarningMessage(capturarValor("chain_msg_phone_pattern"));
            }
        } else {
            showWarningMessage(capturarValor("chain_msg_phone_empty"));
        }
    }

    public void add3Party() {
        for (Db3party par : getList3Party()) {
            if (par.equals(getDb3PartySelected())) {
                showWarningMessage(capturarValor("out_msg_3party_exist"));
                return;
            }
        }
        getList3Party().add(getDb3PartySelected());
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

    public void deleteCustomer(DbCustomers custo) {
        getListCustomers().remove(custo);
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

    public void deletePhone(DbPhones phone) {
        getListPhones().remove(phone);
    }

    public void delete3Pary(Db3party party) {
        getList3Party().remove(party);
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
            for (DbPotentials po : getSubSegmentSelected().getDbPotentialsList()) {
                if (po.getLowPotential().equals(StateEnum.ACTIVE.getState())) {
                    setPotentialSelected(po);
                    break;
                }
            }
            setListPotential(getSubSegmentSelected().getDbPotentialsList());
        }
    }

    public List<DbCustomers> completeCustomer(String query) {
        return customerBeanLocal.findByNameCustomer(query);
    }

    public boolean isDisabledOcs() {
        if (!getChannelSelected().getNameChannel().startsWith("ON")) {
            setOcsPrimary(new DbOcs());
            setOcsSecondary(new DbOcs());
            return true;
        }
        return false;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return the verificationNumber
     */
    public String getVerificationNumber() {
        return verificationNumber;
    }

    /**
     * @param verificationNumber the verificationNumber to set
     */
    public void setVerificationNumber(String verificationNumber) {
        this.verificationNumber = verificationNumber;
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
     * @return the outletName
     */
    public String getOutletName() {
        return outletName;
    }

    /**
     * @param outletName the outletName to set
     */
    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    /**
     * @return the pointSale
     */
    public String getPointSale() {
        return pointSale;
    }

    /**
     * @param pointSale the pointSale to set
     */
    public void setPointSale(String pointSale) {
        this.pointSale = pointSale;
    }

    /**
     * @return the typeOutlet
     */
    public String getTypeOutlet() {
        return typeOutlet;
    }

    /**
     * @param typeOutlet the typeOutlet to set
     */
    public void setTypeOutlet(String typeOutlet) {
        this.typeOutlet = typeOutlet;
    }

    /**
     * @return the kiernanId
     */
    public String getKiernanId() {
        return kiernanId;
    }

    /**
     * @param kiernanId the kiernanId to set
     */
    public void setKiernanId(String kiernanId) {
        this.kiernanId = kiernanId;
    }

    /**
     * @return the ocsPrimary
     */
    public DbOcs getOcsPrimary() {
        return ocsPrimary;
    }

    /**
     * @param ocsPrimary the ocsPrimary to set
     */
    public void setOcsPrimary(DbOcs ocsPrimary) {
        this.ocsPrimary = ocsPrimary;
    }

    /**
     * @return the ocsSecondary
     */
    public DbOcs getOcsSecondary() {
        return ocsSecondary;
    }

    /**
     * @param ocsSecondary the ocsSecondary to set
     */
    public void setOcsSecondary(DbOcs ocsSecondary) {
        this.ocsSecondary = ocsSecondary;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
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
     * @return the wine
     */
    public boolean isWine() {
        return wine;
    }

    /**
     * @param wine the wine to set
     */
    public void setWine(boolean wine) {
        this.wine = wine;
    }

    /**
     * @return the beer
     */
    public boolean isBeer() {
        return beer;
    }

    /**
     * @param beer the beer to set
     */
    public void setBeer(boolean beer) {
        this.beer = beer;
    }

    /**
     * @return the spirtis
     */
    public boolean isSpirtis() {
        return spirtis;
    }

    /**
     * @param spirtis the spirtis to set
     */
    public void setSpirtis(boolean spirtis) {
        this.spirtis = spirtis;
    }

    /**
     * @return the listOcs
     */
    public List<DbOcs> getListOcs() {
        return listOcs;
    }

    /**
     * @param listOcs the listOcs to set
     */
    public void setListOcs(List<DbOcs> listOcs) {
        this.listOcs = listOcs;
    }

    /**
     * @return the diageoApplicationBean
     */
    public DiageoApplicationBean getDiageoApplicationBean() {
        return diageoApplicationBean;
    }

    /**
     * @return the isFather
     */
    public boolean isIsFather() {
        return isFather;
    }

    /**
     * @param isFather the isFather to set
     */
    public void setIsFather(boolean isFather) {
        this.isFather = isFather;
    }

    /**
     * @return the father
     */
    public DbOutlets getFather() {
        return father;
    }

    /**
     * @param father the father to set
     */
    public void setFather(DbOutlets father) {
        this.father = father;
    }

    /**
     * @return the list3PartyToDeploy
     */
    public List<Db3party> getList3PartyToDeploy() {
        return list3PartyToDeploy;
    }

    /**
     * @param list3PartyToDeploy the list3PartyToDeploy to set
     */
    public void setList3PartyToDeploy(List<Db3party> list3PartyToDeploy) {
        this.list3PartyToDeploy = list3PartyToDeploy;
    }

    /**
     * @return the loginBean
     */
    public LoginBean getLoginBean() {
        return loginBean;
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

}
