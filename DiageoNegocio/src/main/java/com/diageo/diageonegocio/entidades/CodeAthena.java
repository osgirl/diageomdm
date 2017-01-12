/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author EDUARDO
 */
@Embeddable
public class CodeAthena implements Serializable {

    @Column(name = "DISTRI_2")
    private String distri_2;
    @Column(name = "DISTRI_COM")
    private String distri_com;
    @Column(name = "DISTRI_COA")
    private String distri_coa;
    @Column(name = "DISTRI_COD")
    private String distri_cod;
    @Column(name = "DISTRI_CO")
    private String distri_co;

    public CodeAthena() {
    }

    public CodeAthena(String distri_2, String distri_com, String distri_coa, String distri_cod, String distri_co) {
        this.distri_2 = distri_2;
        this.distri_com = distri_com;
        this.distri_coa = distri_coa;
        this.distri_cod = distri_cod;
        this.distri_co = distri_co;
    }

    public String getDistri_2() {
        return distri_2;
    }

    public void setDistri_2(String distri_2) {
        this.distri_2 = distri_2;
    }

    public String getDistri_com() {
        return distri_com;
    }

    public void setDistri_com(String distri_com) {
        this.distri_com = distri_com;
    }

    public String getDistri_coa() {
        return distri_coa;
    }

    public void setDistri_coa(String distri_coa) {
        this.distri_coa = distri_coa;
    }

    public String getDistri_cod() {
        return distri_cod;
    }

    public void setDistri_cod(String distri_cod) {
        this.distri_cod = distri_cod;
    }

    public String getDistri_co() {
        return distri_co;
    }

    public void setDistri_co(String distri_co) {
        this.distri_co = distri_co;
    }

}
