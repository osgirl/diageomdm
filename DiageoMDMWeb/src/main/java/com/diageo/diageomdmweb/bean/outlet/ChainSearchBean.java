/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.outlet;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.ChainBeanLocal;
import com.diageo.diageonegocio.beans.SubSegmentoBeanLocal;
import com.diageo.diageonegocio.entidades.DbChains;
import com.diageo.diageonegocio.entidades.DbPotentials;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "chainSearchBean")
@ViewScoped
public class ChainSearchBean extends DiageoRootBean implements Serializable {

    @EJB
    protected ChainBeanLocal chainBeanLocal;
    @EJB
    protected SubSegmentoBeanLocal subSegmentoBeanLocal;
    private List<DbChains> chainsList;
    private boolean seeDetail;
    private String channelLabel;
    private String subChannelLabel;
    private String segmentLabel;
    private DbSubSegments subSegmentoSeleccionado;
    private DbPotentials potentialAutomatic;
    private DbPotentials potentialManula;
    private List<DbSubSegments> listSubSegment;
    private List<DbPotentials> listaPotentialAutomatic;
    private List<DbPotentials> listaPotentialManual;
    //Atriubutes
    private Integer chainCode;
    private String eanCode;
    private String chainName;
    private String businessName;
    private String kiernanId;
    private String address;
    private String city;

    /**
     * Creates a new instance of ChainSearchBean
     */
    public ChainSearchBean() {
    }

    @PostConstruct
    public void init() {
        setChainsList(chainBeanLocal.findAllChains());
        setSeeDetail(Boolean.TRUE);
        setListSubSegment(subSegmentoBeanLocal.findAllSubSegment());
    }

    public void detailChain(DbChains chain) {
        setChainCode(chain.getChainId());
        setEanCode(chain.getCodeEan());
        setChainName(chain.getNameChain());
        setBusinessName(chain.getDbPartyId() != null ? chain.getDbPartyId().getName3party() : "");
        setKiernanId(chain.getKiernanId());
        setAddress(chain.getAddress());
        setCity(chain.getDbTownId() != null ? chain.getDbTownId().getNameTown() : "");
        setSubSegmentoSeleccionado(chain.getSubSegmentId());
        listenerSubSegment();
        setSeeDetail(Boolean.FALSE);
    }

    public void listenerSubSegment() {
        if (getSubSegmentoSeleccionado().getDbPotentialsList() == null || getSubSegmentoSeleccionado().getDbPotentialsList().isEmpty()) {
            setListaPotentialAutomatic(new ArrayList<DbPotentials>());
        } else {
            setPotentialAutomatic(getSubSegmentoSeleccionado().getDbPotentialsList().get(0));
            setListaPotentialAutomatic(getSubSegmentoSeleccionado().getDbPotentialsList());
            setSegmentLabel(getSubSegmentoSeleccionado().getSegmentId().getNameSegment());
            setSubChannelLabel(getSubSegmentoSeleccionado().getSegmentId().getSubChannelId().getNameSubChannel());
            setChannelLabel(getSubSegmentoSeleccionado().getSegmentId().getSubChannelId().getChannelId().getNameChannel());
        }

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
     * @return the channelLabel
     */
    public String getChannelLabel() {
        return channelLabel;
    }

    /**
     * @param channelLabel the channelLabel to set
     */
    public void setChannelLabel(String channelLabel) {
        this.channelLabel = channelLabel;
    }

    /**
     * @return the subChannelLabel
     */
    public String getSubChannelLabel() {
        return subChannelLabel;
    }

    /**
     * @param subChannelLabel the subChannelLabel to set
     */
    public void setSubChannelLabel(String subChannelLabel) {
        this.subChannelLabel = subChannelLabel;
    }

    /**
     * @return the segmentLabel
     */
    public String getSegmentLabel() {
        return segmentLabel;
    }

    /**
     * @param segmentLabel the segmentLabel to set
     */
    public void setSegmentLabel(String segmentLabel) {
        this.segmentLabel = segmentLabel;
    }

    /**
     * @return the subSegmentoSeleccionado
     */
    public DbSubSegments getSubSegmentoSeleccionado() {
        return subSegmentoSeleccionado;
    }

    /**
     * @param subSegmentoSeleccionado the subSegmentoSeleccionado to set
     */
    public void setSubSegmentoSeleccionado(DbSubSegments subSegmentoSeleccionado) {
        this.subSegmentoSeleccionado = subSegmentoSeleccionado;
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

    /**
     * @return the listaPotentialManual
     */
    public List<DbPotentials> getListaPotentialManual() {
        return listaPotentialManual;
    }

    /**
     * @param listaPotentialManual the listaPotentialManual to set
     */
    public void setListaPotentialManual(List<DbPotentials> listaPotentialManual) {
        this.listaPotentialManual = listaPotentialManual;
    }

    /**
     * @return the potentialAutomatic
     */
    public DbPotentials getPotentialAutomatic() {
        return potentialAutomatic;
    }

    /**
     * @param potentialAutomatic the potentialAutomatic to set
     */
    public void setPotentialAutomatic(DbPotentials potentialAutomatic) {
        this.potentialAutomatic = potentialAutomatic;
    }

    /**
     * @return the potentialManula
     */
    public DbPotentials getPotentialManula() {
        return potentialManula;
    }

    /**
     * @param potentialManula the potentialManula to set
     */
    public void setPotentialManula(DbPotentials potentialManula) {
        this.potentialManula = potentialManula;
    }

    /**
     * @return the chainCode
     */
    public Integer getChainCode() {
        return chainCode;
    }

    /**
     * @param chainCode the chainCode to set
     */
    public void setChainCode(Integer chainCode) {
        this.chainCode = chainCode;
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
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

}
