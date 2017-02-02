/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.beans.ProfileBeanLocal;
import com.diageo.admincontrollerweb.beans.RelationUserBeanLocal;
import com.diageo.admincontrollerweb.beans.UserBeanLocal;
import com.diageo.admincontrollerweb.entities.DwProfiles;
import com.diageo.admincontrollerweb.entities.DwRelationUsers;
import com.diageo.admincontrollerweb.entities.DwUsers;
import com.diageo.admincontrollerweb.entities.RelationUserPK;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.bean.dto.RelationUserDto;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "relationUserBean")
@ViewScoped
public class RelationUserBean extends DiageoRootBean implements Serializable {

    @EJB
    private ProfileBeanLocal profileBeanLocal;
    @EJB
    private UserBeanLocal userBeanLocal;
    @EJB
    private RelationUserBeanLocal relationUserBeanLocal;
    private List<DwUsers> listUserByProfileLevel_1;
    private List<DwUsers> listUserByProfileLevel_2;
    private List<DwProfiles> listProfileLevel_2;
    private DwUsers userSelectedLevel_1;
    private DwUsers userSelectedLevel_2;
    private DwProfiles profileLevel_2;
    private String readModify;
    private boolean detail;
    private Set<RelationUserDto> tableUserRelation;
    private Set<RelationUserDto> tableUserRelationNew;
    private Set<RelationUserDto> tableUserRelationDelete;

    @PostConstruct
    public void init() {
        setListUserByProfileLevel_1(userBeanLocal.usersByProfileLevel(true));
        setListProfileLevel_2(profileBeanLocal.findByLevel(false));
        setProfileLevel_2(new DwProfiles());
        setDetail(Boolean.TRUE);
        setUserSelectedLevel_2(new DwUsers());
        setReadModify("0");
    }

    public void seeDetail(DwUsers usu) {
        setDetail(Boolean.FALSE);
        setUserSelectedLevel_1(usu);
        findUserByUserLevel_1();
        tableUserRelationNew = new HashSet<>();
        tableUserRelationDelete = new HashSet<>();
    }

    public void btnBack() {
        setDetail(Boolean.TRUE);
    }

    public void listenerProfileUser() {
        setListUserByProfileLevel_2(userBeanLocal.usersByProfile(getProfileLevel_2().getProfileId()));
    }

    /**
     * carga la tabla del usuario seleccionado con los usuarios que tiene a
     * cargo
     */
    public void findUserByUserLevel_1() {
        List<DwRelationUsers> list = relationUserBeanLocal.findByParentObject(getUserSelectedLevel_1().getUserId());
        setTableUserRelation(new HashSet<RelationUserDto>());
        for (DwRelationUsers dwRelationUsers : list) {
            RelationUserDto dto = new RelationUserDto();
            dto.setModify(dwRelationUsers.getStateApproved() ? "X" : "");
            dto.setRead(dwRelationUsers.getStateView() ? "X" : "");
            DwUsers usu = userBeanLocal.findById(dwRelationUsers.getRelationUserPK().getUserId());
            dto.setUserSelected(usu);
            getTableUserRelation().add(dto);
        }
    }

    public void addUser() {
        RelationUserDto dto = new RelationUserDto();
        dto.setUserSelected(getUserSelectedLevel_2());
        dto.setModify(getReadModify().equals("1") ? "X" : "");
        dto.setRead(getReadModify().equals("0") ? "X" : "");
        if (!getTableUserRelation().contains(dto)) {
            getTableUserRelation().add(dto);
            tableUserRelationNew.add(dto);
            showInfoMessage(capturarValor("relation_msg_add"));
        } else {
            showWarningMessage(capturarValor("relation_msg_add_exists"));
        }
    }

    public void deleteUserFromTable(RelationUserDto dto) {
        getTableUserRelation().remove(dto);
        tableUserRelationNew.remove(dto);
        tableUserRelationDelete.add(dto);
        showInfoMessage(capturarValor("relation_msg_delete"));
    }

    public void saveRelationUser() {
        for (RelationUserDto relationUserDto : tableUserRelationNew) {
            relationUserBeanLocal.createRelationUser(buildEntity(relationUserDto));
        }
        for (RelationUserDto relationUserDto : tableUserRelationDelete) {
            boolean flag = true;
            for (RelationUserDto relUsu : tableUserRelationNew) {
                if (relationUserDto.equals(relUsu)) {
                    flag=false;
                    break;
                }
            }
            if (flag) {
                relationUserBeanLocal.deleteRelation(buildEntity(relationUserDto));
            }
        }
        tableUserRelationDelete = new HashSet<>();
        tableUserRelationNew = new HashSet<>();
        showInfoMessage(capturarValor("relation_msg_save"));
    }

    private DwRelationUsers buildEntity(RelationUserDto dto) {
        DwRelationUsers e = new DwRelationUsers();
        e.setCreationDate(getCurrentDate());
        e.setStateApproved(dto.getModify().equals("X"));
        e.setStateView(dto.getRead().equals("X"));
        e.setRelationUserPK(new RelationUserPK(dto.getUserSelected().getUserId(), getUserSelectedLevel_1().getUserId()));
        return e;
    }

    public List<DwUsers> getListUserByProfileLevel_1() {
        return listUserByProfileLevel_1;
    }

    public void setListUserByProfileLevel_1(List<DwUsers> listUserByProfileLevel_1) {
        this.listUserByProfileLevel_1 = listUserByProfileLevel_1;
    }

    public DwUsers getUserSelectedLevel_1() {
        return userSelectedLevel_1;
    }

    public void setUserSelectedLevel_1(DwUsers userSelectedLevel_1) {
        this.userSelectedLevel_1 = userSelectedLevel_1;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public List<DwProfiles> getListProfileLevel_2() {
        return listProfileLevel_2;
    }

    public void setListProfileLevel_2(List<DwProfiles> listProfileLevel_2) {
        this.listProfileLevel_2 = listProfileLevel_2;
    }

    public DwProfiles getProfileLevel_2() {
        return profileLevel_2;
    }

    public void setProfileLevel_2(DwProfiles profileLevel_2) {
        this.profileLevel_2 = profileLevel_2;
    }

    public DwUsers getUserSelectedLevel_2() {
        return userSelectedLevel_2;
    }

    public void setUserSelectedLevel_2(DwUsers userSelectedLevel_2) {
        this.userSelectedLevel_2 = userSelectedLevel_2;
    }

    public List<DwUsers> getListUserByProfileLevel_2() {
        return listUserByProfileLevel_2;
    }

    public void setListUserByProfileLevel_2(List<DwUsers> listUserByProfileLevel_2) {
        this.listUserByProfileLevel_2 = listUserByProfileLevel_2;
    }

    public Set<RelationUserDto> getTableUserRelation() {
        return tableUserRelation;
    }

    public void setTableUserRelation(Set<RelationUserDto> tableUserRelation) {
        this.tableUserRelation = tableUserRelation;
    }

    public String getReadModify() {
        return readModify;
    }

    public void setReadModify(String readModify) {
        this.readModify = readModify;
    }

}
