/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.reports;

import com.diageo.admincontrollerweb.beans.ProfileBeanLocal;
import com.diageo.admincontrollerweb.entities.DwProfiles;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "reportUserBean")
@ViewScoped
public class ReportUserBean extends DiageoRootBean implements Serializable {

    @EJB
    private ProfileBeanLocal profileBeanLocal;
    private DwProfiles profile;
    private List<DwProfiles> listProfile;

    public ReportUserBean() {
    }

    @PostConstruct
    public void init() {
        setListProfile(profileBeanLocal.findAll());
        setProfile(getListProfile().get(0));
    }

    public DwProfiles getProfile() {
        return profile;
    }

    public void setProfile(DwProfiles profile) {
        this.profile = profile;
    }

    public List<DwProfiles> getListProfile() {
        return listProfile;
    }

    public void setListProfile(List<DwProfiles> listProfile) {
        this.listProfile = listProfile;
    }

}
