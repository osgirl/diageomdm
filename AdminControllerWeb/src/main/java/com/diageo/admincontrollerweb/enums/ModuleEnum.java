/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.enums;

/**
 *
 * @author yovanoty126
 */
public enum ModuleEnum {

    OUT("outlet"),
    C_OUT("create outlet"),
    S_OUT("search outlet"),
    LOAD_OUT("load outlet"),
    MAGANER("administrator"),
    USU("user"),
    C_USU("create user"),
    S_USU("search user"),
    CHAN("channel"),
    C_CHAN("create channle"),
    S_CHAN("search channel"),
    SEG("segment"),
    C_SEG("create segment"),
    S_SEG("search segment"),
    SUB_CHAN("subchannel"),
    C_SUB_CHAN("create subchannel"),
    S_SUB_CHAN("search subchannel"),
    SUB_SEG("subsegment"),
    C_SUB_SEG("create subsegment"),
    S_SUB_SEG("search subsegment"),
    BATT("battleground"),
    C_BATT("create battleground"),
    S_BATT("search battleground"),
    MIS_DATOS("My data"),
    CAM_CONTRA("change password"),
    DISTRI("Distributor"),
    C_DISTRI("create distributor"),
    S_DISTRI("search distributor"),
    DISTRI_LOCATION("location distributor"),
    S_DISTRI_LOCATION("search location distributor"),
    C_DISTRI_LOCATION("search location distributor"),
    POTENTIAL("potential"),
    C_POTENTIAL("create potential"),
    S_POTENTIAL("search potential"),
    REPORT("report"),
    REPORT_OUTLET("report outlet"),
    REPORT_DISTRI("report distributor"),
    REPORT_PENDING("report pending"),
    C_CHAIN("create chain");

    private final String description;

    private ModuleEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
