/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageonegocio.entidades.Audit;
import com.diageo.diageonegocio.entidades.DbPotentials;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@ManagedBean
@ViewScoped
public class PotentialSearchBean extends PotentialCreateBean implements Serializable {

    private List<DbPotentials> listPotential;
    private Integer idPotential;
    private boolean seeDetail;

    /**
     * Creates a new instance of PotentialSearchBean
     */
    public PotentialSearchBean() {
    }

    @PostConstruct
    @Override
    public void init() {
        setSeeDetail(Boolean.TRUE);
        setListPotential(potentialBeanLocal.findAll());
        super.init();
    }

    public void detailPotential(DbPotentials pot) {
        setIdPotential(pot.getPotentialId());
        setName(pot.getNamePotential());
        setSubSegmento(pot.getSubSegmentId());
        setSegment(getSubSegmento().getSegmentId());
        setSubChannel(getSegment().getSubChannelId());
        setChannel(getSubChannel().getChannelId());
        setListSubSegment(getSegment().getDbSubSegmentsList());
        setListSegment(getSubChannel().getDbSegmentsList());
        setListSubChannel(getChannel().getDbSubChannelsList());
        setAthenaCode(pot.getDistri_1());
        setSeeDetail(Boolean.FALSE);
    }

    @Override
    public void createPotential() {
        try {
            DbPotentials pot = potentialBeanLocal.findById(getIdPotential());
            pot.setNamePotential(getName().toUpperCase());
            pot.setSubSegmentId(getSubSegmento());
            pot.setDistri_1(getAthenaCode().toUpperCase());
            Audit audit = new Audit();
            audit.setCreationDate(pot.getAudit() != null ? pot.getAudit().getCreationDate() : null);
            audit.setCreationUser(pot.getAudit() != null ? pot.getAudit().getCreationUser() : null);
            audit.setModificationDate(super.getCurrentDate());
            audit.setModificationUser(getLoginBean().getUsuario().getEmailUser());
            pot.setAudit(audit);
            potentialBeanLocal.updatePotential(pot);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(PotentialSearchBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void back() {
        setSeeDetail(Boolean.TRUE);
    }

    /**
     * @return the listPotential
     */
    public List<DbPotentials> getListPotential() {
        return listPotential;
    }

    /**
     * @param listPotential the listPotential to set
     */
    public void setListPotential(List<DbPotentials> listPotential) {
        this.listPotential = listPotential;
    }

    /**
     * @return the idPotential
     */
    public Integer getIdPotential() {
        return idPotential;
    }

    /**
     * @param idPotential the idPotential to set
     */
    public void setIdPotential(Integer idPotential) {
        this.idPotential = idPotential;
    }

    /**
     * @return the seeDetail
     */
    public boolean isSeeDetail() {
        return seeDetail;
    }

    /**
     * @param seeDetail the seeDetail to set
     */
    public void setSeeDetail(boolean seeDetail) {
        this.seeDetail = seeDetail;
    }

}
