/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.outlet;

import com.diageo.diageomdmweb.bean.DiageoApplicationBean;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageomdmweb.constant.PatternConstant;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.Db3PartyBeanLocal;
import com.diageo.diageonegocio.beans.OcsBeanLocal;
import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.beans.PhonesBeanLocal;
import com.diageo.diageonegocio.beans.PotentialBeanLocal;
import com.diageo.diageonegocio.beans.SegmentBeanLocal;
import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.beans.SubSegmentoBeanLocal;
import com.diageo.diageonegocio.beans.TypePhoneBeanLocal;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.entidades.DbClusters;
import com.diageo.diageonegocio.entidades.DbDepartaments;
import com.diageo.diageonegocio.entidades.DbOcs;
import com.diageo.diageonegocio.entidades.DbPhones;
import com.diageo.diageonegocio.entidades.DbPotentials;
import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.entidades.DbTowns;
import com.diageo.diageonegocio.entidades.DbTypePhones;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

    @EJB
    protected OutletBeanLocal outletBeanLocal;
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
    protected TypePhoneBeanLocal typePhoneBeanLocal;
    @EJB
    protected Db3PartyBeanLocal db3PartyBeanLocal;
    @EJB
    protected PhonesBeanLocal phonesBeanLocal;
    @EJB
    protected OcsBeanLocal ocsBeanLocal;
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

    /**
     * Creates a new instance of OutletVistaBean
     */
    public OutletCrearBean() {
    }

    @PostConstruct
    public void init() {
        setListChannel(channelBeanLocal.findAllChannel());
        setListTypePhone(typePhoneBeanLocal.findAll());
        setList3Party(db3PartyBeanLocal.searchAllDistributor());
        initFields();
    }

    public void initFields() {

    }

    public void saveOutlet() {

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

    public void deletePhone(DbPhones phone) {
        getListPhones().remove(phone);
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

}
