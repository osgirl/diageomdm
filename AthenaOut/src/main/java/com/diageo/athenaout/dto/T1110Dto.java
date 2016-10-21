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
public class T1110Dto implements Serializable {

    private String codParty;
    private String codCustDeliv;
    private String codDiv;
    private String dateFrom;
    private String dateTo;
    private String flagPrimary;
    private String flagAnn;

    public T1110Dto() {
    }

    public String getCodParty() {
        return codParty;
    }

    public void setCodParty(String codParty) {
        this.codParty = codParty;
    }

    public String getCodCustDeliv() {
        return codCustDeliv;
    }

    public void setCodCustDeliv(String codCustDeliv) {
        this.codCustDeliv = codCustDeliv;
    }

    public String getCodDiv() {
        return codDiv;
    }

    public void setCodDiv(String codDiv) {
        this.codDiv = codDiv;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getFlagPrimary() {
        return flagPrimary;
    }

    public void setFlagPrimary(String flagPrimary) {
        this.flagPrimary = flagPrimary;
    }

    public String getFlagAnn() {
        return flagAnn;
    }

    public void setFlagAnn(String flagAnn) {
        this.flagAnn = flagAnn;
    }

    @Override
    public String toString() {
        return codParty + SEPARATOR.getSeparator() + codCustDeliv + SEPARATOR.getSeparator()
                + codDiv + SEPARATOR.getSeparator() + dateFrom + SEPARATOR.getSeparator()
                + dateTo + SEPARATOR.getSeparator() + flagPrimary + SEPARATOR.getSeparator()
                +flagAnn + ENTER.getSeparator() ;
    }

}
