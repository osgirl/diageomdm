/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author EDUARDO
 */
@Embeddable
public class RelationUserPK implements Serializable {

    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "USER_PARENT_ID")
    private Integer userParentId;

    public RelationUserPK() {
    }

    public RelationUserPK(Integer userId, Integer userParentId) {
        this.userId = userId;
        this.userParentId = userParentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserParentId() {
        return userParentId;
    }

    public void setUserParentId(Integer userParentId) {
        this.userParentId = userParentId;
    }

}
