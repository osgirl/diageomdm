/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "DW_PARAMETERS", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = DwParameters.FIND_KEY, query = "SELECT p FROM DwParameters p WHERE p.parameterKey = ?1")
})
public class DwParameters implements Serializable {

    public static final String FIND_KEY = "DwParameters.findByKey";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "SQ_Dw_PARAMETERS", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "SQ_Dw_PARAMETERS", sequenceName = "SQ_Dw_PARAMETERS", allocationSize = 1)
    @Column(name = "PARAMETER_ID")
    private Integer parameterId;
    @Column(name = "PARAMETER_KEY")
    private String parameterKey;
    @Column(name = "PARAMETER_VALUE")
    private String parameterValue;

    public DwParameters() {
    }

    public DwParameters(Integer parameterId) {
        this.parameterId = parameterId;
    }

    public Integer getParameterId() {
        return parameterId;
    }

    public void setParameterId(Integer parameterId) {
        this.parameterId = parameterId;
    }

    public String getParameterKey() {
        return parameterKey;
    }

    public void setParameterKey(String parameterKey) {
        this.parameterKey = parameterKey;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof DwParameters) {
            DwParameters dp = (DwParameters) obj;
            return dp.getParameterId().equals(parameterId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 30;
        hash += parameterId.hashCode() + 40;
        return hash;
    }

}
