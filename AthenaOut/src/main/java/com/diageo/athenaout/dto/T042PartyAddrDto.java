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
public class T042PartyAddrDto implements Serializable {

    private String codParty;
    private String desAddre_1;
    private String desLoc_1;
    private String numPhone_1;
    private String codArea;
    private String codZone;
    private String codNation;
    private String email_1;
    private String website_1;
    private Double vLatitude;
    private Double vLongitude;

    public T042PartyAddrDto() {
    }

    public String getCodParty() {
        return codParty;
    }

    public void setCodParty(String codParty) {
        this.codParty = codParty;
    }

    public String getDesAddre_1() {
        return desAddre_1;
    }

    public void setDesAddre_1(String desAddre_1) {
        this.desAddre_1 = desAddre_1;
    }

    public String getDesLoc_1() {
        return desLoc_1;
    }

    public void setDesLoc_1(String desLoc_1) {
        this.desLoc_1 = desLoc_1;
    }

    public String getNumPhone_1() {
        return numPhone_1;
    }

    public void setNumPhone_1(String numPhone_1) {
        this.numPhone_1 = numPhone_1;
    }

    public String getCodArea() {
        return codArea;
    }

    public void setCodArea(String codArea) {
        this.codArea = codArea;
    }

    public String getCodZone() {
        return codZone;
    }

    public void setCodZone(String codZone) {
        this.codZone = codZone;
    }

    public String getCodNation() {
        return codNation;
    }

    public void setCodNation(String codNation) {
        this.codNation = codNation;
    }

    public String getEmail_1() {
        return email_1;
    }

    public void setEmail_1(String email_1) {
        this.email_1 = email_1;
    }

    public String getWebsite_1() {
        return website_1;
    }

    public void setWebsite_1(String website_1) {
        this.website_1 = website_1;
    }

    /**
     * @return the vLatitude
     */
    public Double getvLatitude() {
        return vLatitude;
    }

    /**
     * @param vLatitude the vLatitude to set
     */
    public void setvLatitude(Double vLatitude) {
        this.vLatitude = vLatitude;
    }

    /**
     * @return the vLongitude
     */
    public Double getvLongitude() {
        return vLongitude;
    }

    /**
     * @param vLongitude the vLongitude to set
     */
    public void setvLongitude(Double vLongitude) {
        this.vLongitude = vLongitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof T042PartyAddrDto) {
            T042PartyAddrDto t = (T042PartyAddrDto) obj;
            return t.getCodParty().equals(this.codParty);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 90;
        hash += codParty.hashCode() + 30;
        return hash;
    }

    @Override
    public String toString() {
        return codParty + SEPARATOR.getSeparator() + desAddre_1 + SEPARATOR.getSeparator()
                + desLoc_1 + SEPARATOR.getSeparator() + numPhone_1 + SEPARATOR.getSeparator()
                + codArea + SEPARATOR.getSeparator() + codZone
                + SEPARATOR.getSeparator() + codNation + ENTER.getSeparator();
    }

}
