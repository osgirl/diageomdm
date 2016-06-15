/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.beans.ParameterBeanLocal;
import com.diageo.admincontrollerweb.entities.DwParameters;
import com.diageo.admincontrollerweb.enums.ParameterKeyEnum;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.SegmentBeanLocal;
import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.beans.SubSegmentoBeanLocal;
import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author EDUARDO
 */
@Named(value = "segmentParametersBean")
@ViewScoped
public class SegmentParametersBean extends DiageoRootBean implements Serializable {

    @EJB
    private ChannelBeanLocal channelBeanLocal;
    @EJB
    private SubSegmentoBeanLocal subSegmentoBeanLocal;
    @EJB
    private ParameterBeanLocal parameterBeanLocal;
    private List<DbChannels> listChannel;
    private List<DbSubChannels> listSubChannel;
    private List<DbSegments> listSegment;
    private List<DbSubSegments> listSubSegment;
    private DbChannels channelSelected;
    private DbSubChannels subChannelSelected;
    private DbSegments segmentSelected;
    private DbSubSegments subSegmentSelected;
    private List<DwParameters> listSubSegmentFiler;
    private List<DwParameters> listSubSegmentDelete;

    /**
     * Creates a new instance of SegmentParametersBean
     */
    public SegmentParametersBean() {
    }

    @PostConstruct
    public void init() {
        setListChannel(channelBeanLocal.findAllChannel());
        setListSubSegmentFiler(parameterBeanLocal.findByKey(ParameterKeyEnum.QUERY_SUB_SEGMENT.name()));
        setListSubSegmentDelete(new ArrayList<DwParameters>());
        initList();
    }

    private void initList() {
        setChannelSelected(getListChannel().get(0));
        setSubChannelSelected(getChannelSelected().getDbSubChannelsList().get(0));
        setSegmentSelected(getSubChannelSelected().getDbSegmentsList().get(0));
        setSubSegmentSelected(getSegmentSelected().getDbSubSegmentsList().get(0));
        setListSubChannel(getChannelSelected().getDbSubChannelsList());
        setListSegment(getSubChannelSelected().getDbSegmentsList());
        setListSubSegment(getSegmentSelected().getDbSubSegmentsList());
        listenerSegment();
    }

    public void listenerChannel() {
        setListSubChannel(getChannelSelected().getDbSubChannelsList());
        setSubChannelSelected(getListSubChannel().get(0));
        this.listenerSubChannel();
    }

    public void listenerSubChannel() {
        setSegmentSelected(getSubChannelSelected().getDbSegmentsList().get(0));
        setListSegment(getSubChannelSelected().getDbSegmentsList());
        this.listenerSegment();
    }

    public void listenerSegment() {
        setSubSegmentSelected(getSegmentSelected().getDbSubSegmentsList().get(0));
        setListSubSegment(getSegmentSelected().getDbSubSegmentsList());
    }

    public void createParameter() {
        for (DwParameters param : listSubSegmentFiler) {
            if (param.getParameterId() == null) {
                parameterBeanLocal.createParameter(param);
            }
        }
        for (DwParameters param : listSubSegmentDelete) {
            if (param.getParameterId() != null) {
                parameterBeanLocal.deleteParameter(param);
            }
        }
        showInfoMessage(capturarValor("sis_datos_guardados_exito"));
    }

    public void deleteParameterTable(DwParameters param) {
        getListSubSegmentDelete().add(param);
        getListSubSegmentFiler().remove(param);
    }

    public DbSubSegments nameSegment(String val) {
        if (val != null && !val.isEmpty()) {
            try {
                return subSegmentoBeanLocal.findById(Integer.parseInt(val));
            } catch (DiageoBusinessException ex) {
                Logger.getLogger(SegmentParametersBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        return new DbSubSegments();
    }

    public void addSubSegment() {
        //subSegmentSelected
        DwParameters param = new DwParameters();
        param.setParameterKey(ParameterKeyEnum.QUERY_SUB_SEGMENT.name());
        param.setParameterValue(getSubSegmentSelected().getSubSegmentId() + "");
        for (DwParameters par : getListSubSegmentFiler()) {
            boolean flag = par.getParameterValue().equals(String.valueOf(getSubSegmentSelected().getSubSegmentId()));
            if (flag) {
                showWarningMessage(capturarValor("param_message_duplicate"));
                return;
            }
        }
        getListSubSegmentFiler().add(param);
    }

    public List<DbChannels> getListChannel() {
        return listChannel;
    }

    public void setListChannel(List<DbChannels> listChannel) {
        this.listChannel = listChannel;
    }

    public List<DbSubChannels> getListSubChannel() {
        return listSubChannel;
    }

    public void setListSubChannel(List<DbSubChannels> listSubChannel) {
        this.listSubChannel = listSubChannel;
    }

    public List<DbSegments> getListSegment() {
        return listSegment;
    }

    public void setListSegment(List<DbSegments> listSegment) {
        this.listSegment = listSegment;
    }

    public List<DbSubSegments> getListSubSegment() {
        return listSubSegment;
    }

    public void setListSubSegment(List<DbSubSegments> listSubSegment) {
        this.listSubSegment = listSubSegment;
    }

    public DbChannels getChannelSelected() {
        return channelSelected;
    }

    public void setChannelSelected(DbChannels channelSelected) {
        this.channelSelected = channelSelected;
    }

    public DbSubChannels getSubChannelSelected() {
        return subChannelSelected;
    }

    public void setSubChannelSelected(DbSubChannels subChannelSelected) {
        this.subChannelSelected = subChannelSelected;
    }

    public DbSegments getSegmentSelected() {
        return segmentSelected;
    }

    public void setSegmentSelected(DbSegments segmentSelected) {
        this.segmentSelected = segmentSelected;
    }

    public DbSubSegments getSubSegmentSelected() {
        return subSegmentSelected;
    }

    public void setSubSegmentSelected(DbSubSegments subSegmentSelected) {
        this.subSegmentSelected = subSegmentSelected;
    }

    public List<DwParameters> getListSubSegmentFiler() {
        return listSubSegmentFiler;
    }

    public void setListSubSegmentFiler(List<DwParameters> listSubSegmentFiler) {
        this.listSubSegmentFiler = listSubSegmentFiler;
    }

    public List<DwParameters> getListSubSegmentDelete() {
        return listSubSegmentDelete;
    }

    public void setListSubSegmentDelete(List<DwParameters> listSubSegmentDelete) {
        this.listSubSegmentDelete = listSubSegmentDelete;
    }

}
