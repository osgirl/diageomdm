/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.dto;

import com.diageo.admincontrollerweb.entities.DwUsers;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author EDUARDO
 */
public class RelationUserDto implements Serializable {

    private DwUsers userSelected;
    private String modify;
    private String read;

    public DwUsers getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(DwUsers userSelected) {
        this.userSelected = userSelected;
    }

    public String getModify() {
        return modify;
    }

    public void setModify(String modify) {
        this.modify = modify;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.userSelected);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof RelationUserDto) {
            RelationUserDto dto = (RelationUserDto) obj;
            return dto.getUserSelected().getUserId().equals(this.getUserSelected().getUserId());
        }
        return false;
    }

}
