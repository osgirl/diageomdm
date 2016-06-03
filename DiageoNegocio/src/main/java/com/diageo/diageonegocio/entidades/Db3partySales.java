/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_3PARTY_SALES")
@NamedQueries({
    @NamedQuery(name = "Db3partySales.findAll", query = "SELECT d FROM Db3partySales d"),
    @NamedQuery(name = "Db3partySales.findByDb3partySaleId", query = "SELECT d FROM Db3partySales d WHERE d.db3partySaleId = :db3partySaleId"),
    @NamedQuery(name = "Db3partySales.findByNameSales", query = "SELECT d FROM Db3partySales d WHERE d.nameSales = :nameSales")})
public class Db3partySales implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(generator = "SQ_DB_3PARTY_SALES", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DB_3PARTY_SALES", sequenceName = "SQ_DB_3PARTY_SALES", allocationSize = 1)
    @Column(name = "DB_3PARTY_SALE_ID")
    private Integer db3partySaleId;
    @Size(max = 50)
    @Column(name = "NAME_SALES")
    private String nameSales;
    @JoinColumn(name = "DB_3PARTY_MANAGER_ID", referencedColumnName = "DB_3PARTY_MANAGER_ID")
    @ManyToOne(optional = false)
    private Db3partyManagers db3partyManagerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "db3partySaleId")
    private List<DbOutlets> dbOutletsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "db3partySaleId")
    private List<DbChains> dbChainsList;

    public Db3partySales() {
    }

    public Db3partySales(Integer db3partySaleId) {
        this.db3partySaleId = db3partySaleId;
    }

    public Integer getDb3partySaleId() {
        return db3partySaleId;
    }

    public void setDb3partySaleId(Integer db3partySaleId) {
        this.db3partySaleId = db3partySaleId;
    }

    public String getNameSales() {
        return nameSales;
    }

    public void setNameSales(String nameSales) {
        this.nameSales = nameSales;
    }

    public Db3partyManagers getDb3partyManagerId() {
        return db3partyManagerId;
    }

    public void setDb3partyManagerId(Db3partyManagers db3partyManagerId) {
        this.db3partyManagerId = db3partyManagerId;
    }

    public List<DbOutlets> getDbOutletsList() {
        return dbOutletsList;
    }

    public void setDbOutletsList(List<DbOutlets> dbOutletsList) {
        this.dbOutletsList = dbOutletsList;
    }

    public List<DbChains> getDbChainsList() {
        return dbChainsList;
    }

    public void setDbChainsList(List<DbChains> dbChainsList) {
        this.dbChainsList = dbChainsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (db3partySaleId != null ? db3partySaleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Db3partySales)) {
            return false;
        }
        Db3partySales other = (Db3partySales) object;
        if ((this.db3partySaleId == null && other.db3partySaleId != null) || (this.db3partySaleId != null && !this.db3partySaleId.equals(other.db3partySaleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totalseguros.entidadesdiageobusiness.Db3partySales[ db3partySaleId=" + db3partySaleId + " ]";
    }

}
