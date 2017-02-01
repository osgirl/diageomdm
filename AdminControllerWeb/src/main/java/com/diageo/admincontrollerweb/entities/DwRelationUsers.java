/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DW_RELATION_USERS")
@NamedQueries({
    @NamedQuery(name = DwRelationUsers.FIND_BY_PARENT, query = "SELECT r.relationUserPK.userId FROM DwRelationUsers r WHERE r.relationUserPK.userParentId = ?1")
    ,
    @NamedQuery(name = DwRelationUsers.FIND_BY_PARENT_OBJECT, query = "SELECT r FROM DwRelationUsers r WHERE r.relationUserPK.userParentId = ?1")
    ,
    @NamedQuery(name = DwRelationUsers.FIND_BY_USER, query = "SELECT r FROM DwRelationUsers r WHERE r.relationUserPK.userId = ?1")
    ,
    @NamedQuery(name = DwRelationUsers.FIND_BY_USER_AND_PARENT, query = "SELECT r FROM DwRelationUsers r WHERE r.relationUserPK.userId = ?1 AND r.relationUserPK.userParentId = ?2")
})
public class DwRelationUsers implements Serializable {

    public static final String FIND_BY_PARENT = "DwRelationUsers.findParentId";
    public static final String FIND_BY_PARENT_OBJECT = "DwRelationUsers.findParentIdObject";
    public static final String FIND_BY_USER = "DwRelationUsers.findUser";
    public static final String FIND_BY_USER_AND_PARENT = "DwRelationUsers.findUserAndParent";
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private RelationUserPK relationUserPK;    
    @Column(name = "STATE_VIEW")
    private Boolean stateView;
    @Column(name = "STATE_APPROVED")
    private Boolean stateApproved;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    public DwRelationUsers() {
    }
    
    public Boolean getStateView() {
        return stateView;
    }

    public void setStateView(Boolean stateView) {
        this.stateView = stateView;
    }

    public Boolean getStateApproved() {
        return stateApproved;
    }

    public void setStateApproved(Boolean stateApproved) {
        this.stateApproved = stateApproved;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public RelationUserPK getRelationUserPK() {
        return relationUserPK;
    }

    public void setRelationUserPK(RelationUserPK relationUserPK) {
        this.relationUserPK = relationUserPK;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.relationUserPK);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof DwRelationUsers) {
            DwRelationUsers e = (DwRelationUsers) obj;
            if (e.getRelationUserPK() != null) {
                return e.getRelationUserPK().getUserId().equals(this.getRelationUserPK().getUserId())
                        && e.getRelationUserPK().getUserParentId().equals(this.getRelationUserPK().getUserParentId());
            }
            return false;
        }
        return false;
    }

}
