/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.dto;

import com.diageo.diageomdmweb.enums.TablesEnum;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DiageoLog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author EDUARDO
 */
public class DbOutletsDto implements Serializable {

    private DbOutlets outlet;
    private final List<DiageoLog> listDiageoLog;
    private final Date currentDate;
    private final Integer userId;

    public DbOutletsDto(DbOutlets outlet, Date currentDate, Integer userId) {
        listDiageoLog = new ArrayList<>();
        this.currentDate = currentDate;
        this.outlet = outlet;
        this.userId = userId;
    }

    public DbOutlets getOutlet() {
        return outlet;
    }

    public void setOutlet(DbOutlets outlet) {
        this.outlet = outlet;
    }

    public void changes(DbOutlets out) {
        boolean outletFather = (this.outlet.getOutletIdFather() != null && out.getOutletIdFather() != null)
                ? (this.outlet.getOutletIdFather().getOutletId().equals(out.getOutletIdFather().getOutletId())) : true;
        if (!outletFather) {
            listDiageoLog.add(recordLog(returnNameField("getOutletIdFather"), outlet.getOutletIdFather() + "", out.getOutletIdFather() + ""));
        }
        boolean businessName = this.outlet.getBusinessName().equals(out.getBusinessName());
        if (!businessName) {
            listDiageoLog.add(recordLog(returnNameField("getBusinessName"), outlet.getBusinessName() + "", out.getBusinessName() + ""));
        }
        boolean nit = this.outlet.getNit().equals(out.getNit());
        if (!nit) {
            listDiageoLog.add(recordLog(returnNameField("getNit"), outlet.getNit() + "", out.getNit() + ""));
        }
        boolean verificationNumber = this.outlet.getVerificationNumber().equals(out.getVerificationNumber());
        if (!verificationNumber) {
            listDiageoLog.add(recordLog(returnNameField("getVerificationNumber"), outlet.getVerificationNumber() + "", out.getVerificationNumber() + ""));
        }
        boolean outletName = this.outlet.getOutletName().equals(out.getOutletName());
        if (!outletName) {
            listDiageoLog.add(recordLog(returnNameField("getOutletName"), outlet.getOutletName() + "", out.getOutletName() + ""));
        }
        boolean pointOfSale = this.outlet.getNumberPdv().equals(out.getNumberPdv());
        if (!pointOfSale) {
            listDiageoLog.add(recordLog(returnNameField("getNumberPdv"), outlet.getNumberPdv() + "", out.getNumberPdv() + ""));
        }
        boolean typeOutlet = this.outlet.getTypeOutlet().equals(out.getTypeOutlet());
        if (!typeOutlet) {
            listDiageoLog.add(recordLog(returnNameField("getTypeOutlet"), outlet.getTypeOutlet() + "", out.getTypeOutlet() + ""));
        }
        boolean webSite = this.outlet.getWebsite().equals(out.getWebsite());
        if (!webSite) {
            listDiageoLog.add(recordLog(returnNameField("getWebsite"), outlet.getWebsite() + "", out.getWebsite() + ""));
        }
        boolean db3partyOld = this.outlet.getDb3PartyIdOld().getDb3partyId().equals(out.getDb3PartyIdOld().getDb3partyId());
        if (!db3partyOld) {
            listDiageoLog.add(recordLog(returnNameField("getDb3PartyIdOld"), outlet.getDb3PartyIdOld() + "", out.getDb3PartyIdOld() + ""));
        }
        boolean journeyPlan = this.outlet.getJourneyPlan().equals(out.getJourneyPlan());
        if (!journeyPlan) {
            listDiageoLog.add(recordLog(returnNameField("getJourneyPlan"), outlet.getJourneyPlan() + "", out.getJourneyPlan() + ""));
        }
        boolean statusOutlet = this.outlet.getStatusOutlet().equals(out.getStatusOutlet());
        if (!statusOutlet) {
            listDiageoLog.add(recordLog(returnNameField("getStatusOutlet"), outlet.getStatusOutlet() + "", out.getStatusOutlet() + ""));
        }
        boolean potential = this.outlet.getPotentialId().getPotentialId().equals(out.getPotentialId().getPotentialId());
        if (!potential) {
            listDiageoLog.add(recordLog(returnNameField("getPotentialId"), outlet.getPotentialId().getPotentialId() + "", out.getPotentialId().getPotentialId() + ""));
        }
        boolean ocs_1 = this.outlet.getOcsPrimary().getOcsId().equals(out.getOcsPrimary().getOcsId());
        if (!ocs_1) {
            listDiageoLog.add(recordLog(returnNameField("getOcsPrimary"), outlet.getOcsPrimary().getOcsId() + "", out.getOcsPrimary().getOcsId() + ""));
        }
        boolean ocs_2 = this.outlet.getOcsSecondary().getOcsId().equals(out.getOcsSecondary().getOcsId());
        if (!ocs_2) {
            listDiageoLog.add(recordLog(returnNameField("getOcsSecondary"), outlet.getOcsSecondary().getOcsId() + "", out.getOcsSecondary().getOcsId() + ""));
        }
        boolean seller = this.outlet.getDb3partySaleId().getDb3partySaleId().equals(out.getDb3partySaleId().getDb3partySaleId());
        if (!seller) {
            listDiageoLog.add(recordLog(returnNameField("getDb3partySaleId"), outlet.getDb3partySaleId().getDb3partySaleId() + "", out.getDb3partySaleId().getDb3partySaleId() + ""));
        }
        boolean city = this.outlet.getTownId().getTownId().equals(out.getTownId().getTownId());
        if (!city) {
            listDiageoLog.add(recordLog(returnNameField("getTownId"), outlet.getTownId().getTownId() + "", out.getTownId().getTownId() + ""));
        }
        boolean neighborhood = this.outlet.getNeighborhood().equals(out.getNeighborhood());
        if (!neighborhood) {
            listDiageoLog.add(recordLog(returnNameField("getNeighborhood"), outlet.getNeighborhood() + "", out.getNeighborhood() + ""));
        }
        boolean address = this.outlet.getAddress().equals(out.getAddress());
        if (!address) {
            listDiageoLog.add(recordLog(returnNameField("getAddress"), outlet.getAddress() + "", out.getAddress() + ""));
        }
        boolean latitude = this.outlet.getLatitude() != null ? this.outlet.getLatitude().equals(out.getLatitude()) : true;
        if (!latitude) {
            listDiageoLog.add(recordLog(returnNameField("getLatitude"), outlet.getLatitude() + "", out.getLatitude() + ""));
        }
        boolean longitude = this.outlet.getLongitude() != null ? this.outlet.getLongitude().equals(out.getLongitude()) : true;
        if (!longitude) {
            listDiageoLog.add(recordLog(returnNameField("getLongitude"), outlet.getLongitude() + "", out.getLongitude() + ""));
        }
        boolean segmentation = this.outlet.getSubSegmentId().getSubSegmentId().equals(out.getSubSegmentId().getSubSegmentId());
        if (!segmentation) {
            listDiageoLog.add(recordLog(returnNameField("getSubSegmentId"), outlet.getSubSegmentId().getSubSegmentId() + "", out.getSubSegmentId().getSubSegmentId() + ""));
        }
    }

    public DiageoLog recordLog(String field, String oldValue, String newValue) {
        DiageoLog log = new DiageoLog();
        log.setCreationDate(currentDate);
        log.setUserId(userId);
        log.setDiageoField(field);
        log.setDiageoTable(TablesEnum.DB_OUTLETS.name());
        log.setOldValue(oldValue);
        log.setNewValue(newValue);
        log.setTableId(outlet.getOutletId());
        return log;
    }

    public String returnNameField(String field) {
        return field.substring(3);
    }

    public boolean isNotificationChangedSegmentation(DbOutlets out) {
        return this.outlet.getSubSegmentId().getSubSegmentId().equals(out.getSubSegmentId().getSubSegmentId());
    }

    public List<DiageoLog> getListDiageoLog() {
        return listDiageoLog;
    }
}
