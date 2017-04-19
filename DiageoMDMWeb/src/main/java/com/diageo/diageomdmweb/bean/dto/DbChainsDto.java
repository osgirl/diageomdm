/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.dto;

import com.diageo.diageomdmweb.enums.TablesEnum;
import com.diageo.diageonegocio.entidades.DbChains;
import com.diageo.diageonegocio.entidades.DiageoLog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author EDUARDO
 */
public class DbChainsDto implements Serializable {

    private DbChains chain;
    private final List<DiageoLog> listDiageoLog;
    private final Date currentDate;
    private final Integer userId;

    public DbChainsDto(DbChains chain, Date currentDate, Integer userId) {
        this.chain=chain;
        this.listDiageoLog = new ArrayList<>();
        this.currentDate = currentDate;
        this.userId = userId;
    }

    public DbChains getChain() {
        return chain;
    }

    public void setChain(DbChains chain) {
        this.chain = chain;
    }

    public void changes(DbChains cha) {
        boolean chainName = this.chain.getNameChain().equals(cha.getNameChain());
        if (!chainName) {
            listDiageoLog.add(recordLog(returnNameField("getNameChain"), chain.getNameChain(), cha.getNameChain()));
        }
        boolean businessName = this.chain.getBusinessName().equals(cha.getBusinessName());
        if (!businessName) {
            listDiageoLog.add(recordLog(returnNameField("getBusinessName"), chain.getBusinessName(), cha.getBusinessName()));
        }
        boolean eanCode = this.chain.getCodeEan().equals(cha.getCodeEan());
        if (!eanCode) {
            listDiageoLog.add(recordLog(returnNameField("getCodeEan"), chain.getCodeEan(), cha.getCodeEan()));
        }
        boolean distributor = this.chain.getDbPartyId().equals(cha.getDbPartyId());
        if (!distributor) {
            listDiageoLog.add(recordLog(returnNameField("getDbPartyId"), chain.getDbPartyId().getDb3partyId() + "", cha.getDbPartyId().getDb3partyId() + ""));
        }
        boolean status = this.chain.getStatusChain().equals(cha.getStatusChain());
        if (!distributor) {
            listDiageoLog.add(recordLog(returnNameField("getStatusChain"), chain.getStatusChain(), cha.getStatusChain()));
        }
        boolean layer = this.chain.getLayerId().equals(cha.getLayerId());
        if (!layer) {
            listDiageoLog.add(recordLog(returnNameField("getLayerId"), (chain.getLayerId() != null ? chain.getLayerId().getLayerId() : "") + "", (cha.getLayerId() != null ? cha.getLayerId().getLayerId() : "") + ""));
        }
        boolean format = this.chain.getSite().equals(cha.getSite());
        if (!format) {
            listDiageoLog.add(recordLog(returnNameField("getSite"), chain.getSite(), cha.getSite()));
        }
        boolean segment = this.chain.getSubSegmentId().getSubSegmentId().equals(cha.getSubSegmentId().getSubSegmentId());
        if (!segment) {
            listDiageoLog.add(recordLog(returnNameField("getSubSegmentId"), chain.getSubSegmentId().getSubSegmentId() + "", cha.getSubSegmentId().getSubSegmentId() + ""));
        }
        boolean potential = this.chain.getPotentialId().equals(cha.getPotentialId());
        if (!potential) {
            listDiageoLog.add(recordLog(returnNameField("getPotentialId"), chain.getPotentialId().getPotentialId() + "", cha.getPotentialId().getPotentialId() + ""));
        }
        boolean cluster = this.chain.getDbClusterId().equals(cha.getDbClusterId());
        if (!cluster) {
            listDiageoLog.add(recordLog(returnNameField("getDbClusterId"), chain.getDbClusterId().getDbClusterId() + "", cha.getDbClusterId().getDbClusterId() + ""));
        }
        boolean city = this.chain.getDbTownId().equals(cha.getDbTownId());
        if (!city) {
            listDiageoLog.add(recordLog(returnNameField("getDbTownId"), chain.getDbTownId().getTownId() + "", cha.getDbTownId().getTownId() + ""));
        }
        boolean neighborhood = this.chain.getNeighborhood().equals(cha.getNeighborhood());
        if (!neighborhood) {
            listDiageoLog.add(recordLog(returnNameField("getNeighborhood"), chain.getNeighborhood(), cha.getNeighborhood()));
        }
        boolean address = this.chain.getAddress().equals(cha.getAddress());
        if (!address) {
            listDiageoLog.add(recordLog(returnNameField("getAddress"), chain.getAddress(), cha.getAddress()));
        }
        boolean latitude = this.chain.getLatitude() != null && (this.chain.getLatitude().equals(cha.getLatitude()));
        if (!address) {
            listDiageoLog.add(recordLog(returnNameField("getLatitude"), chain.getLatitude() + "", cha.getLatitude() + ""));
        }
        boolean longitude = this.chain.getLongitude() != null && (this.chain.getLongitude().equals(cha.getLongitude()));
        if (!address) {
            listDiageoLog.add(recordLog(returnNameField("getLongitude"), chain.getLongitude() + "", cha.getLongitude() + ""));
        }

    }

    public boolean isNotificationChangedSegmentation(DbChains cha) {
        return this.chain.getSubSegmentId().getSubSegmentId().equals(cha.getSubSegmentId().getSubSegmentId());
    }

    public DiageoLog recordLog(String field, String oldValue, String newValue) {
        DiageoLog log = new DiageoLog();
        log.setCreationDate(currentDate);
        log.setUserId(userId);
        log.setDiageoField(field);
        log.setDiageoTable(TablesEnum.DB_CHAINS.name());
        log.setOldValue(oldValue);
        log.setNewValue(newValue);
        log.setTableId(chain.getChainId());
        return log;
    }

    public String returnNameField(String field) {
        return field.substring(3);
    }

    public List<DiageoLog> getListDiageoLog() {
        return listDiageoLog;
    }

}
