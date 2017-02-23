/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.dto;

import com.diageo.diageonegocio.entidades.DbOutlets;
import java.io.Serializable;

/**
 *
 * @author EDUARDO
 */
public class DbOutletsDto implements Serializable {

    private DbOutlets outlet;

    public DbOutletsDto() {
    }

    public DbOutletsDto(DbOutlets outlet) {
        this.outlet = outlet;
    }

    public DbOutlets getOutlet() {
        return outlet;
    }

    public void setOutlet(DbOutlets outlet) {
        this.outlet = outlet;
    }

    public boolean isJustChangedSegmentation(DbOutlets out) {
        boolean outletFather = (this.outlet.getOutletIdFather() != null && out.getOutletIdFather() != null)
                ? (this.outlet.getOutletIdFather().getOutletId().equals(out.getOutletIdFather().getOutletId())) : false;
        boolean businessName = this.outlet.getBusinessName().equals(out.getBusinessName());
        boolean nit = this.outlet.getNit().equals(out.getNit());
        boolean verificationNumber = this.outlet.getVerificationNumber().equals(out.getVerificationNumber());
        boolean outletName = this.outlet.getOutletName().equals(out.getOutletName());
        boolean pointOfSale = this.outlet.getNumberPdv().equals(out.getNumberPdv());
        boolean typeOutlet = this.outlet.getTypeOutlet().equals(out.getTypeOutlet());
        boolean webSite = this.outlet.getWebsite().equals(out.getWebsite());
        boolean db3partyOld = this.outlet.getDb3PartyIdOld().getDb3partyId().equals(out.getDb3PartyIdOld().getDb3partyId());
        boolean journeyPlan = this.outlet.getJourneyPlan().equals(out.getJourneyPlan());
        boolean statusOutlet = this.outlet.getStatusOutlet().equals(out.getStatusOutlet());
        boolean potential = this.outlet.getPotentialId().getPotentialId().equals(out.getPotentialId().getPotentialId());
        boolean ocs_1 = this.outlet.getOcsPrimary().getOcsId().equals(out.getOcsPrimary().getOcsId());
        boolean ocs_2 = this.outlet.getOcsSecondary().getOcsId().equals(out.getOcsSecondary().getOcsId());
        boolean seller = this.outlet.getDb3partySaleId().getDb3partySaleId().equals(out.getDb3partySaleId().getDb3partySaleId());
        boolean city = this.outlet.getTownId().getTownId().equals(out.getTownId().getTownId());
        boolean neighborhood = this.outlet.getNeighborhood().equals(out.getNeighborhood());
        boolean address = this.outlet.getAddress().equals(out.getAddress());
        boolean latitude = this.outlet.getLatitude().equals(out.getLatitude());
        boolean longitude = this.outlet.getLongitude().equals(out.getLongitude());
        return false;
    }

    public boolean isNotificationChangedSegmentation(DbOutlets out) {
        return this.outlet.getSubSegmentId().getSubSegmentId().equals(out.getSubSegmentId().getSubSegmentId());
    }

}
