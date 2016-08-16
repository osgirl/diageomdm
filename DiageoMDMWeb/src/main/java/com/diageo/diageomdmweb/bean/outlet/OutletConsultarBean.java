/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.outlet;

import com.diageo.admincontrollerweb.entities.DwDocumentTypes;
import com.diageo.admincontrollerweb.enums.ProfileEnum;
import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageomdmweb.bean.dto.StateOutletDto;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.beans.PotentialBeanLocal;
import com.diageo.diageonegocio.beans.SegmentBeanLocal;
import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.beans.SubSegmentoBeanLocal;
import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.entidades.DbPotentials;
import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.enums.StateOutletChain;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author yovanoty126
 */
@Named(value = "outletConsultarBean")
@ViewScoped
public class OutletConsultarBean extends OutletCrearBean implements Serializable {

    @EJB
    private SegmentBeanLocal segmentoBeanLocal;
    @EJB
    private SubSegmentoBeanLocal subSegmentoBean;
    @EJB
    protected OutletBeanLocal outletBeanLocal;
    @EJB
    private SubChannelBeanLocal subChannelBeanLocal;
    @EJB
    private ChannelBeanLocal channelBeanLocal;
    @Inject
    private LoginBean loginBean;
    private List<DbOutlets> listaOutlets;
    private List<DbOutlets> listaOutletsOld;
    private List<DbPermissionSegments> listPermi;
    private List<DbSubSegments> listSubSegment;
    private boolean verDetalle;
    private boolean disabledSegmentChannel;
    private boolean disabledSubChannel;
    private boolean disabledSegment;
    private boolean disabledSubSegment;
    private boolean desabledPotential;
    private DbOutlets outletSelect;
    private String state;
    private String stateMasive;
    private List<StateOutletDto> listStateOutlet;
    private String messageReject;

    /**
     * Creates a new instance of OutletConsultarBean
     */
    public OutletConsultarBean() {
    }

    @PostConstruct
    @Override
    public void init() {
        listStateOutlet = new ArrayList<>();
        listStateOutlet.add(new StateOutletDto(3, "Aprobado"));
        listStateOutlet.add(new StateOutletDto(4, "Rechazado"));
        setPotentialAutomatic(new DbPotentials());
        setPotentialManula(new DbPotentials());
        if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())) {
            listaOutlets = outletBeanLocal.listOutletNew("1");
            listaOutletsOld = outletBeanLocal.listOutletNew("0");
            //setListaOutlets(outletBeanLocal.listOutletNew("1"));
            //setListaOutletsOld(outletBeanLocal.listOutletNew("0"));
            if (listaOutlets == null) {
                listaOutlets = new ArrayList<>();
            }
            if (listaOutletsOld == null) {
                listaOutletsOld = new ArrayList<>();
            }
        } else {
            setListaOutlets(new ArrayList<DbOutlets>());
            setListaOutletsOld(new ArrayList<DbOutlets>());
            listPermi = getLoginBean().getListPermissionSegment();
            Set<Integer> listDistributor = new HashSet<>();
            Set<Integer> listSubSegment = new HashSet<>();
            for (DbPermissionSegments permi : listPermi) {
                listDistributor.add(permi.getDb3partyId().getDb3partyId());
            }
            System.out.println(listDistributor);
            for (DbPermissionSegments permi : listPermi) {
                if (permi.getSubSegmentCheck().equals(StateEnum.ACTIVE.getState())) {
                    listSubSegment.add(permi.getSubSegmentId());
                } else if (permi.getSegmentCheck().equals(StateEnum.ACTIVE.getState())) {
                    try {
                        DbSegments listSegment = segmentoBeanLocal.findById(permi.getSegmentId());
                        for (DbSubSegments subSegtem : listSegment.getDbSubSegmentsList()) {
                            listSubSegment.add(subSegtem.getSubSegmentId());
                        }
                    } catch (DiageoBusinessException ex) {
                        Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (permi.getSubChannelCheck().equals(StateEnum.ACTIVE.getState())) {
                    try {
                        DbSubChannels subChaTemp = subChannelBeanLocal.consultarId(permi.getSubChannelId());
                        for (DbSegments seg : subChaTemp.getDbSegmentsList()) {
                            for (DbSubSegments subSeg : seg.getDbSubSegmentsList()) {
                                listSubSegment.add(subSeg.getSubSegmentId());
                            }
                        }
                    } catch (DiageoBusinessException ex) {
                        Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (permi.getChannelCheck().equals(StateEnum.ACTIVE.getState())) {
                    try {
                        DbChannels channelTemp = channelBeanLocal.findById(permi.getChannelId());
                        for (DbSubChannels subCha : channelTemp.getDbSubChannelsList()) {
                            for (DbSegments seg : subCha.getDbSegmentsList()) {
                                for (DbSubSegments subSeg : seg.getDbSubSegmentsList()) {
                                    listSubSegment.add(subSeg.getSubSegmentId());
                                }
                            }
                        }
                    } catch (DiageoBusinessException ex) {
                        Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            System.out.println(listSubSegment);
            if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
                List<Integer> stateOutlet = new ArrayList<>();
                stateOutlet.add(2);
                stateOutlet.add(3);
                stateOutlet.add(4);
                setListaOutlets(outletBeanLocal.findByDistributor(listDistributor, listSubSegment, stateOutlet, "1"));
                setListaOutletsOld(outletBeanLocal.findByDistributor(listDistributor, listSubSegment, stateOutlet, "0"));
            } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC.getId())) {
                List<Integer> stateOutlet = new ArrayList<>();
                stateOutlet.add(1);
                stateOutlet.add(4);
                setListaOutlets(outletBeanLocal.findByDistributor(listDistributor, listSubSegment, stateOutlet, "1"));
                setListaOutletsOld(outletBeanLocal.findByDistributor(listDistributor, listSubSegment, stateOutlet, "0"));
            }
        }
        setVerDetalle(Boolean.TRUE);
        super.init();
    }

    public void regresar() {
        setVerDetalle(Boolean.TRUE);
    }

    public void detalle(DbOutlets out) {
        if (!getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                && !getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())) {
            for (DbPermissionSegments ps : listPermi) {
                //arreglar, los distribuidores ahora son una lista
//                if (ps.getDb3partyId().equals(out.getIdDistribuidor().getIdDistribuidor())) {
//                    if (ps.getChannelCheck().equals(StateEnum.ACTIVE.getState())) {
//                        setDisabledSegmentChannel(Boolean.TRUE);
//                    }
//                    if (ps.getPotentialCheck().equals(StateEnum.ACTIVE.getState())) {
//                        setDesabledPotential(Boolean.TRUE);
//                    }
//                }
            }
        }
        setVerDetalle(Boolean.FALSE);
        setRazonSocial(out.getBusinessName());
        setTipoOutlet(out.getTypeOutlet());
        setNombreOutlet(out.getOutletName());
        setNit(out.getNit());
//        setNombresPropietarios(out.getPropietario().getNombres());
//        setApellidosPropietario(out.getPropietario().getApellidos());
//        setNumeroDocumento(out.getPropietario().getNumDoc());
//        setTipoDocumento(new DwDocumentTypes(out.getPropietario().getTipodoc().getIdtipoDocumento()));
        setChannelLabel(out.getSubSegmentId().getSegmentId().getSubChannelId().getChannelId().getNameChannel());
        setSubChannelLabel(out.getSubSegmentId().getSegmentId().getSubChannelId().getNameSubChannel());
        setSegmentLabel(out.getSubSegmentId().getSegmentId().getNameSegment());
        setSubSegmentoSeleccionado(out.getSubSegmentId());

        setDireccion(out.getAddress());
        setBarrio(out.getNeighborhood());
        setDepartamentoOutlet(out.getTownId().getDepartamentId());
        setMunicipioOutlet(out.getTownId());
        setCorreoElectronico(out.getEmail());
        setPuntoVenta(out.getNumberPdv());
//        if (out.getIdPotentialManual() == null) {
//            setPotentialManula(out.getIdPotentialManual());
//        } else {
//            setPotentialManula(getListaPotentialManual().get(0));
//        }
        listenerSubSegment();
        outletSelect = out;
    }

    public void updateOutlet() {

        try {
            if (outletSelect.getStatusOutlet().equals(StateOutletChain.OUTLET_TMC.getId())) {
                outletSelect.setStatusOutlet(StateOutletChain.PENDING_APPROVAL.getId());
            } else {
                if (state.equals("4")) {
                    outletSelect.setRejectMessage(messageReject);
                }
                outletSelect.setStatusOutlet(state);
            }
            outletBeanLocal.updateOutlet(outletSelect);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void approbatrionMasive() {
        for (DbOutlets listaOutlet : listaOutlets) {
            if (listaOutlet.isApprobationMassive()) {
                if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.TMC.getId())) {
                    listaOutlet.setStatusOutlet(StateOutletChain.OUTLET_TMC.getId());
                } else if (getLoginBean().getUsuario().getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
                    if (stateMasive.equals("3")) {
                        listaOutlet.setStatusOutlet(StateOutletChain.PENDING_APPROVAL.getId());
                    } else {
                        listaOutlet.setRejectMessage(capturarValor("out_massive_message_reject"));
                        listaOutlet.setStatusOutlet(StateOutletChain.REJECTED.getId());
                    }
                }
                try {
                    outletBeanLocal.updateOutlet(listaOutlet);
                } catch (DiageoBusinessException ex) {
                    Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        init();
        showInfoMessage(capturarValor("sis_approbation_masive"));
    }

    public String labelState(String id) {
        if (id == null) {
            return "";
        }
        return StateOutletChain.value(id).name();
    }

    public void selectAll() {
        for (DbOutlets listaOutlet : listaOutlets) {
            listaOutlet.setApprobationMassive(Boolean.TRUE);
        }
    }

    public void listenerReject() {
        if (state.equals("4")) {
            RequestContext.getCurrentInstance().execute("PF('xvReject').show();");
        }
    }

    public List<DbOutlets> getListaOutlets() {
        return listaOutlets;
    }

    public void setListaOutlets(List<DbOutlets> listaOutlets) {
        this.listaOutlets = listaOutlets;
    }

    public boolean isVerDetalle() {
        return verDetalle;
    }

    public void setVerDetalle(boolean verDetalle) {
        this.verDetalle = verDetalle;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public boolean isDisabledSegmentChannel() {
        return disabledSegmentChannel;
    }

    public void setDisabledSegmentChannel(boolean disabledSegmentChannel) {
        this.disabledSegmentChannel = disabledSegmentChannel;
    }

    public boolean isDisabledSubChannel() {
        return disabledSubChannel;
    }

    public void setDisabledSubChannel(boolean disabledSubChannel) {
        this.disabledSubChannel = disabledSubChannel;
    }

    public boolean isDisabledSegment() {
        return disabledSegment;
    }

    public void setDisabledSegment(boolean disabledSegment) {
        this.disabledSegment = disabledSegment;
    }

    public boolean isDisabledSubSegment() {
        return disabledSubSegment;
    }

    public void setDisabledSubSegment(boolean disabledSubSegment) {
        this.disabledSubSegment = disabledSubSegment;
    }

    public boolean isDesabledPotential() {
        return desabledPotential;
    }

    public void setDesabledPotential(boolean desabledPotential) {
        this.desabledPotential = desabledPotential;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<StateOutletDto> getListStateOutlet() {
        return listStateOutlet;
    }

    public void setListStateOutlet(List<StateOutletDto> listStateOutlet) {
        this.listStateOutlet = listStateOutlet;
    }

    public String getMessageReject() {
        return messageReject;
    }

    public void setMessageReject(String messageReject) {
        this.messageReject = messageReject;
    }

    public String getStateMasive() {
        return stateMasive;
    }

    public void setStateMasive(String stateMasive) {
        this.stateMasive = stateMasive;
    }

    public List<DbOutlets> getListaOutletsOld() {
        return listaOutletsOld;
    }

    public void setListaOutletsOld(List<DbOutlets> listaOutletsOld) {
        this.listaOutletsOld = listaOutletsOld;
    }
}
