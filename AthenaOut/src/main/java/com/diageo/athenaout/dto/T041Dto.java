/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.athenaout.dto;

import java.io.Serializable;
import static com.diageo.athenaout.enums.SeparatorEnum.SEPARATOR;
import static com.diageo.athenaout.enums.SeparatorEnum.ENTER;
import static com.diageo.athenaout.util.UtilAthenaOut.castName;

/**
 *
 * @author EDUARDO
 */
public class T041Dto implements Serializable {

    private String codParty;
    private String codDiv;
    private String codUsr1;
    private String codStatus;
    private String codUser2;
    private String codUser3;
    private String codCustDeliv;

    public String getCodParty() {
        return codParty;
    }

    public void setCodParty(String codParty) {
        this.codParty = codParty;
    }

    public String getCodDiv() {
        return codDiv;
    }

    public void setCodDiv(String codDiv) {
        this.codDiv = codDiv;
    }

    public String getCodUsr1() {
        return codUsr1;
    }

    public void setCodUsr1(String codUsr1) {
        this.codUsr1 = codUsr1;
    }

    public String getCodStatus() {
        return codStatus;
    }

    public void setCodStatus(String codStatus) {
        this.codStatus = codStatus;
    }

    public String getCodUser2() {
        return codUser2;
    }

    public void setCodUser2(String codUser2) {
        this.codUser2 = codUser2;
    }

    public String getCodUser3() {
        return codUser3;
    }

    public void setCodUser3(String codUser3) {
        this.codUser3 = codUser3;
    }

    public String getCodCustDeliv() {
        return codCustDeliv;
    }

    public void setCodCustDeliv(String codCustDeliv) {
        this.codCustDeliv = codCustDeliv;
    }

    @Override
    public String toString() {
        return codParty + SEPARATOR.getSeparator() + codDiv + SEPARATOR.getSeparator()
                + castName(codUsr1)
                + SEPARATOR.getSeparator() + codStatus + SEPARATOR.getSeparator()
                + castName(codUser2) + SEPARATOR.getSeparator() + castName(codUser3) + SEPARATOR.getSeparator()
                + codCustDeliv + ENTER.getSeparator();
    }
}
