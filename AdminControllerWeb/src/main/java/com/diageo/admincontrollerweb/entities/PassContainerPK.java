/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Embeddable
public class PassContainerPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IdUser")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Password")
    private String password;

    public PassContainerPK() {
    }

    public PassContainerPK(int idUser, String password) {
        this.idUser = idUser;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUser;
        hash += (password != null ? password.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PassContainerPK)) {
            return false;
        }
        PassContainerPK other = (PassContainerPK) object;
        if (this.idUser != other.idUser) {
            return false;
        }
        if ((this.password == null && other.password != null) || (this.password != null && !this.password.equals(other.password))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.admincontrollerweb.entities.PassContainerPK[ idUser=" + idUser + ", password=" + password + " ]";
    }
    
}
