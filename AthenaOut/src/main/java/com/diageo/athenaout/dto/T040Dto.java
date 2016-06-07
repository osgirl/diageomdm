/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.athenaout.dto;

import java.io.Serializable;
import static com.diageo.athenaout.enums.SeparatorEnum.SEPARATOR;
import static com.diageo.athenaout.enums.SeparatorEnum.ENTER;

/**
 *
 * @author EDUARDO
 */
public class T040Dto implements Serializable {

    private String idMdm;
    private String disParty_1;
    private String disParty_2;
    private String codVat;
    private String codChannel;
    private String codSubChannel;
    private String codSegment;
    private String codSubSegment;
    private String owner;
    private String fascia;
    private String flagCustInv;
    private String flagCustDeliv;
    private String flagCustSale;
    private String codCustConc;
    private String flagAnn;
    private String zStoreNumber;
    private String ziln;
    private String zDistributor;
    private String primaryOcs;
    private String secondaryOcs;
    private String regionalSales;
    private String managerSales;
    private String territoryMmanager;
    private String salesRepDis;
    private String salesRepRole;
    private String lastDateSale;
    private String rtcStatus;
    private String battleground;
    private String flagDistributor;
    private String flagChain;

    public T040Dto() {
    }

    public String getIdMdm() {
        return idMdm;
    }

    public void setIdMdm(String idMdm) {
        this.idMdm = idMdm;
    }

    public String getDisParty_1() {
        return disParty_1;
    }

    public void setDisParty_1(String disParty_1) {
        this.disParty_1 = disParty_1;
    }

    public String getDisParty_2() {
        return disParty_2;
    }

    public void setDisParty_2(String disParty_2) {
        this.disParty_2 = disParty_2;
    }

    public String getCodVat() {
        return codVat;
    }

    public void setCodVat(String codVat) {
        this.codVat = codVat;
    }

    public String getCodChannel() {
        return codChannel;
    }

    public void setCodChannel(String codChannel) {
        this.codChannel = codChannel;
    }

    public String getCodSubChannel() {
        return codSubChannel;
    }

    public void setCodSubChannel(String codSubChannel) {
        this.codSubChannel = codSubChannel;
    }

    public String getCodSegment() {
        return codSegment;
    }

    public void setCodSegment(String codSegment) {
        this.codSegment = codSegment;
    }

    public String getCodSubSegment() {
        return codSubSegment;
    }

    public void setCodSubSegment(String codSubSegment) {
        this.codSubSegment = codSubSegment;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getFascia() {
        return fascia;
    }

    public void setFascia(String fascia) {
        this.fascia = fascia;
    }

    public String getFlagCustInv() {
        return flagCustInv;
    }

    public void setFlagCustInv(String flagCustInv) {
        this.flagCustInv = flagCustInv;
    }

    public String getFlagCustDeliv() {
        return flagCustDeliv;
    }

    public void setFlagCustDeliv(String flagCustDeliv) {
        this.flagCustDeliv = flagCustDeliv;
    }

    public String getFlagCustSale() {
        return flagCustSale;
    }

    public void setFlagCustSale(String flagCustSale) {
        this.flagCustSale = flagCustSale;
    }

    public String getCodCustConc() {
        return codCustConc;
    }

    public void setCodCustConc(String codCustConc) {
        this.codCustConc = codCustConc;
    }

    public String getFlagAnn() {
        return flagAnn;
    }

    public void setFlagAnn(String flagAnn) {
        this.flagAnn = flagAnn;
    }

    public String getzStoreNumber() {
        return zStoreNumber;
    }

    public void setzStoreNumber(String zStoreNumber) {
        this.zStoreNumber = zStoreNumber;
    }

    public String getZiln() {
        return ziln;
    }

    public void setZiln(String ziln) {
        this.ziln = ziln;
    }

    public String getzDistributor() {
        return zDistributor;
    }

    public void setzDistributor(String zDistributor) {
        this.zDistributor = zDistributor;
    }

    public String getPrimaryOcs() {
        return primaryOcs;
    }

    public void setPrimaryOcs(String primaryOcs) {
        this.primaryOcs = primaryOcs;
    }

    public String getSecondaryOcs() {
        return secondaryOcs;
    }

    public void setSecondaryOcs(String secondaryOcs) {
        this.secondaryOcs = secondaryOcs;
    }

    public String getRegionalSales() {
        return regionalSales;
    }

    public void setRegionalSales(String regionalSales) {
        this.regionalSales = regionalSales;
    }

    public String getManagerSales() {
        return managerSales;
    }

    public void setManagerSales(String managerSales) {
        this.managerSales = managerSales;
    }

    public String getTerritoryMmanager() {
        return territoryMmanager;
    }

    public void setTerritoryMmanager(String territoryMmanager) {
        this.territoryMmanager = territoryMmanager;
    }

    public String getSalesRepDis() {
        return salesRepDis;
    }

    public void setSalesRepDis(String salesRepDis) {
        this.salesRepDis = salesRepDis;
    }

    public String getSalesRepRole() {
        return salesRepRole;
    }

    public void setSalesRepRole(String salesRepRole) {
        this.salesRepRole = salesRepRole;
    }

    public String getLastDateSale() {
        return lastDateSale;
    }

    public void setLastDateSale(String lastDateSale) {
        this.lastDateSale = lastDateSale;
    }

    public String getRtcStatus() {
        return rtcStatus;
    }

    public void setRtcStatus(String rtcStatus) {
        this.rtcStatus = rtcStatus;
    }

    public String getBattleground() {
        return battleground;
    }

    public void setBattleground(String battleground) {
        this.battleground = battleground;
    }

    public String getFlagDistributor() {
        return flagDistributor;
    }

    public void setFlagDistributor(String flagDistributor) {
        this.flagDistributor = flagDistributor;
    }

    public String getFlagChain() {
        return flagChain;
    }

    public void setFlagChain(String flagChain) {
        this.flagChain = flagChain;
    }

    @Override
    public String toString() {
        return idMdm + SEPARATOR.getSeparator() + disParty_1 + SEPARATOR.getSeparator() + disParty_2 + SEPARATOR.getSeparator()
                + codVat + SEPARATOR.getSeparator() + codChannel + SEPARATOR.getSeparator()
                + codSubChannel + SEPARATOR.getSeparator() + codSegment + SEPARATOR.getSeparator()
                + codSubSegment + SEPARATOR.getSeparator() + owner + SEPARATOR.getSeparator() + fascia + SEPARATOR.getSeparator()
                + flagCustInv + SEPARATOR.getSeparator() + flagCustDeliv + SEPARATOR.getSeparator()
                + flagCustSale + SEPARATOR.getSeparator() + codCustConc + SEPARATOR.getSeparator() + flagAnn
                + SEPARATOR.getSeparator() + zStoreNumber + SEPARATOR.getSeparator() + ziln + SEPARATOR.getSeparator()
                + zDistributor + SEPARATOR.getSeparator() + primaryOcs + SEPARATOR.getSeparator() + secondaryOcs + SEPARATOR.getSeparator()
                + regionalSales + SEPARATOR.getSeparator() + managerSales + SEPARATOR.getSeparator()
                + territoryMmanager + SEPARATOR.getSeparator() + salesRepDis + SEPARATOR.getSeparator()
                + salesRepRole + SEPARATOR.getSeparator() + lastDateSale + SEPARATOR.getSeparator() + rtcStatus + SEPARATOR.getSeparator()
                + battleground + SEPARATOR.getSeparator() + flagDistributor + SEPARATOR.getSeparator() + flagChain + ENTER.getSeparator();
    }

}
