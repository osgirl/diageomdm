/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.reports;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.bean.dto.reports.CommercialManagementDto;
import com.diageo.diageonegocio.beans.ChainBeanLocal;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.Db3PartyBeanLocal;
import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.DbChains;
import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author EDUARDO
 */
@Named(value = "reportCommercialManagement")
@ViewScoped
public class ReportCommercialManagement extends DiageoRootBean implements Serializable {

    
    @EJB
    private ChainBeanLocal chainBeanLocal;
    @EJB
    private OutletBeanLocal outletBeanLocal;
    @EJB
    private Db3PartyBeanLocal partyBeanLocal;
    @EJB
    protected ChannelBeanLocal channelBeanLocal;
    private Db3party db3party;
    private List<Db3party> listdb3party;
    private DbChannels channelSelected;
    private DbSubChannels subChannelSelected;
    private DbSegments segmentSelected;
    private DbSubSegments subSegmentSelected;
    private List<DbChannels> listChannel;
    private List<DbSubChannels> listSubChannel;
    private List<DbSegments> listSegment;
    private List<DbSubSegments> listSubSegment;
    private List<CommercialManagementDto> listReportCommercial;

    /**
     * Creates a new instance of ReportCommercialManagement
     */
    public ReportCommercialManagement() {
    }

    @PostConstruct
    public void init() {
        setListdb3party(partyBeanLocal.searchAllDistributor());
        setDb3party(getListdb3party().get(0));
        setListChannel(channelBeanLocal.findAllChannel());
        setListReportCommercial(new ArrayList<CommercialManagementDto>());
        initList();
    }

    private void initList() {
        setChannelSelected(getListChannel().get(0));
        setSubChannelSelected(getChannelSelected().getDbSubChannelsList().get(0));
        setSegmentSelected(getSubChannelSelected().getDbSegmentsList().get(0));
        setSubSegmentSelected(getSegmentSelected().getDbSubSegmentsList().get(0));
        setListSubChannel(getChannelSelected().getDbSubChannelsList());
        setListSegment(getSubChannelSelected().getDbSegmentsList());
        setListSubSegment(getSegmentSelected().getDbSubSegmentsList());
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
    }

    /**
     * Search in the database all outlets and chains by Db3Party and functional
     * segmentation
     */
    public void findOutletChainsBy3PartySegment() {
        setListReportCommercial(new ArrayList<CommercialManagementDto>());
        getListReportCommercial().addAll(this.findOutlets());
        getListReportCommercial().addAll(this.findChains());
        if (getListReportCommercial().isEmpty()) {
            showWarningMessage(capturarValor("report_message_empty_list"));
        }
    }

    private List<CommercialManagementDto> findOutlets() {
        List<DbOutlets> findBySegmentDb3Party = outletBeanLocal.findBySegmentDb3Party(getSubSegmentSelected().getSubSegmentId(), getDb3party().getDb3partyId());
        List<CommercialManagementDto> outletTemp = new ArrayList<>();
        if (!findBySegmentDb3Party.isEmpty()) {
            for (DbOutlets outlet : findBySegmentDb3Party) {
                CommercialManagementDto dto = new CommercialManagementDto();
                dto.setAthenaCode(getDb3party().getDistri_1());
                dto.setBusinessName(outlet.getBusinessName());
                dto.setChannel(outlet.getSubSegmentId().getSegmentId().getSubChannelId().getChannelId().getNameChannel());
                dto.setKiernan(outlet.getKiernanId());
                dto.setNit(outlet.getNit());
                dto.setOutletCode(outlet.getOutletId());
                dto.setSegment(outlet.getSubSegmentId().getSegmentId().getNameSegment());
                dto.setSubChannel(outlet.getSubSegmentId().getSegmentId().getSubChannelId().getNameSubChannel());
                dto.setSubSegment(outlet.getSubSegmentId().getNameSubsegment());
                dto.setSource(OUTLET);
                dto.setPos(outlet.getNumberPdv());
                outletTemp.add(dto);
            }
        }
        return outletTemp;
    }

    private List<CommercialManagementDto> findChains() {
        List<DbChains> findBySegment3Party = chainBeanLocal.findBySegment3Party(getSubSegmentSelected().getSubSegmentId(), getDb3party().getDb3partyId());
        List<CommercialManagementDto> chainTemp = new ArrayList<>();
        if (!findBySegment3Party.isEmpty()) {
            for (DbChains chain : findBySegment3Party) {
                CommercialManagementDto dto = new CommercialManagementDto();
                dto.setAthenaCode(getDb3party().getDistri_1());
                dto.setBusinessName(chain.getNameChain());
                dto.setChannel(chain.getSubSegmentId().getSegmentId().getSubChannelId().getChannelId().getNameChannel());
                dto.setKiernan(chain.getKiernanId());
                dto.setNit(chain.getCodeEan());
                dto.setOutletCode(chain.getChainId());
                dto.setSegment(chain.getSubSegmentId().getSegmentId().getNameSegment());
                dto.setSubChannel(chain.getSubSegmentId().getSegmentId().getSubChannelId().getNameSubChannel());
                dto.setSubSegment(chain.getSubSegmentId().getNameSubsegment());
                dto.setSource(CHAIN);
                dto.setPos(chain.getCodeEan());
                chainTemp.add(dto);
            }
        }
        return chainTemp;
    }

    public String detail(CommercialManagementDto dto) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        if (dto.getSource().equals(OUTLET)) {
            session.setAttribute(OUTLET, dto.getOutletCode());
            return "/outlet/consultarOutlet?faces-redirect=true";
        }
        session.setAttribute(CHAIN, dto.getOutletCode());
        return "/outlet/searchChain?faces-redirect=true";
    }

    /**
     * @return the db3party
     */
    public Db3party getDb3party() {
        return db3party;
    }

    /**
     * @param db3party the db3party to set
     */
    public void setDb3party(Db3party db3party) {
        this.db3party = db3party;
    }

    /**
     * @return the listdb3party
     */
    public List<Db3party> getListdb3party() {
        return listdb3party;
    }

    /**
     * @param listdb3party the listdb3party to set
     */
    public void setListdb3party(List<Db3party> listdb3party) {
        this.listdb3party = listdb3party;
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
     * @return the listReportCommercial
     */
    public List<CommercialManagementDto> getListReportCommercial() {
        return listReportCommercial;
    }

    /**
     * @param listReportCommercial the listReportCommercial to set
     */
    public void setListReportCommercial(List<CommercialManagementDto> listReportCommercial) {
        this.listReportCommercial = listReportCommercial;
    }

}
