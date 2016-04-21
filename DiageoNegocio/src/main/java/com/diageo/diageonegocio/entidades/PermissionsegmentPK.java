/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author EDUARDO
 */
@Embeddable
public class PermissionsegmentPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdUsuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdDistributor")
    private int idDistributor;

    public PermissionsegmentPK() {
    }

    public PermissionsegmentPK(int idUsuario, int idDistributor) {
        this.idUsuario = idUsuario;
        this.idDistributor = idDistributor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdDistributor() {
        return idDistributor;
    }

    public void setIdDistributor(int idDistributor) {
        this.idDistributor = idDistributor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idDistributor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermissionsegmentPK)) {
            return false;
        }
        PermissionsegmentPK other = (PermissionsegmentPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idDistributor != other.idDistributor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.PermissionsegmentPK[ idUsuario=" + idUsuario + ", idDistributor=" + idDistributor + " ]";
    }
    
}
