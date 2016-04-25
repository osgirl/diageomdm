/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "potential")
@NamedQueries({
    @NamedQuery(name = Potential.FIND_BY_SUBSEGMENT, query = "SELECT p FROM Potential p WHERE p.idSubSegment=?1")})
public class Potential implements Serializable {

    public static final String FIND_BY_SUBSEGMENT = "Potential.findBySubsegment";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPotential")
    private Integer idPotential;
    @Column(name = "IsNewClient")
    private String isNewClient;
    @Column(name = "Inclusive")
    private String inclusive;
    @Column(name = "Exclusive")
    private String exclusive;
    @OneToMany(mappedBy = "idPotential")
    private List<Outlet> outletList;

    @Size(max = 60)
    @Column(name = "NamePot")
    private String namePot;
    @Size(max = 1)
    @Column(name = "RangePot")
    private String rangePot;
    @Column(name = "ValueInitial")
    private BigInteger valueInitial;
    @Column(name = "ValueFinal")
    private BigInteger valueFinal;
    @JoinColumn(name = "IdSubSegment", referencedColumnName = "idsub_segmento")
    @ManyToOne(optional = false)
    private SubSegmento idSubSegment;

    public Potential() {
    }

    public Potential(Integer idPotential) {
        this.idPotential = idPotential;
    }

    public Integer getIdPotential() {
        return idPotential;
    }

    public void setIdPotential(Integer idPotential) {
        this.idPotential = idPotential;
    }


    public String getIsNewClient() {
        return isNewClient;
    }

    public void setIsNewClient(String isNewClient) {
        this.isNewClient = isNewClient;
    }


    public String getInclusive() {
        return inclusive;
    }

    public void setInclusive(String inclusive) {
        this.inclusive = inclusive;
    }

    public String getExclusive() {
        return exclusive;
    }

    public void setExclusive(String exclusive) {
        this.exclusive = exclusive;
    }

    public List<Outlet> getOutletList() {
        return outletList;
    }

    public void setOutletList(List<Outlet> outletList) {
        this.outletList = outletList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPotential != null ? idPotential.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Potential)) {
            return false;
        }
        Potential other = (Potential) object;
        if ((this.idPotential == null && other.idPotential != null) || (this.idPotential != null && !this.idPotential.equals(other.idPotential))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.Potential[ idPotential=" + idPotential + " ]";
    }

   

    public String getNamePot() {
        return namePot;
    }

    public void setNamePot(String namePot) {
        this.namePot = namePot;
    }

    public String getRangePot() {
        return rangePot;
    }

    public void setRangePot(String rangePot) {
        this.rangePot = rangePot;
    }

    public BigInteger getValueInitial() {
        return valueInitial;
    }

    public void setValueInitial(BigInteger valueInitial) {
        this.valueInitial = valueInitial;
    }

    public BigInteger getValueFinal() {
        return valueFinal;
    }

    public void setValueFinal(BigInteger valueFinal) {
        this.valueFinal = valueFinal;
    }

    public SubSegmento getIdSubSegment() {
        return idSubSegment;
    }

    public void setIdSubSegment(SubSegmento idSubSegment) {
        this.idSubSegment = idSubSegment;
    }

}
