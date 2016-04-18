/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "PassContainer")
@NamedQueries({
    @NamedQuery(name = "PassContainer.findAll", query = "SELECT p FROM PassContainer p"),
    @NamedQuery(name = PassContainer.FIND_BY_USER, query = "SELECT p FROM PassContainer p WHERE p.passContainerPK.idUser = ?1"),
    @NamedQuery(name = PassContainer.FIND_BY_USER_PASS, query = "SELECT p FROM PassContainer p WHERE p.passContainerPK.idUser = ?1 AND p.passContainerPK.password=?2"),
    @NamedQuery(name = "PassContainer.findByPassword", query = "SELECT p FROM PassContainer p WHERE p.passContainerPK.password = :password"),
    @NamedQuery(name = "PassContainer.findByModificationDate", query = "SELECT p FROM PassContainer p WHERE p.modificationDate = :modificationDate")})
public class PassContainer implements Serializable {

    public static final String FIND_BY_USER = "PassContainer.findByIdUser";
    public static final String FIND_BY_USER_PASS = "PassContainer.findByIdUserPass";
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PassContainerPK passContainerPK;
    @Column(name = "ModificationDate")
    @Temporal(TemporalType.DATE)
    private Date modificationDate;
    @JoinColumn(name = "IdUser", referencedColumnName = "idusuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public PassContainer() {
    }

    public PassContainer(PassContainerPK passContainerPK) {
        this.passContainerPK = passContainerPK;
    }

    public PassContainer(int idUser, String password) {
        this.passContainerPK = new PassContainerPK(idUser, password);
    }

    public PassContainerPK getPassContainerPK() {
        return passContainerPK;
    }

    public void setPassContainerPK(PassContainerPK passContainerPK) {
        this.passContainerPK = passContainerPK;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (passContainerPK != null ? passContainerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PassContainer)) {
            return false;
        }
        PassContainer other = (PassContainer) object;
        if ((this.passContainerPK == null && other.passContainerPK != null) || (this.passContainerPK != null && !this.passContainerPK.equals(other.passContainerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.admincontrollerweb.entities.PassContainer[ passContainerPK=" + passContainerPK + " ]";
    }

}
