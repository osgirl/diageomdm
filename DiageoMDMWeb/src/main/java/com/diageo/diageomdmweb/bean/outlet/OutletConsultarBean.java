/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.outlet;

import com.diageo.admincontrollerweb.entities.TipoDoc;
import com.diageo.admincontrollerweb.enums.ProfileEnum;
import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.diageomdmweb.bean.LoginBean;
import com.diageo.diageomdmweb.bean.dto.StateOutletDto;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.entidades.Channel;
import com.diageo.diageonegocio.entidades.Outlet;
import com.diageo.diageonegocio.entidades.Permissionsegment;
import com.diageo.diageonegocio.entidades.Sateoutlet;
import com.diageo.diageonegocio.entidades.Segmento;
import com.diageo.diageonegocio.entidades.SubChannel;
import com.diageo.diageonegocio.entidades.SubSegmento;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    private OutletBeanLocal outletBeanLocal;
    @EJB
    private ChannelBeanLocal channelBeanLocal;
    @Inject
    private LoginBean loginBean;
    private List<Outlet> listaOutlets;
    private List<Permissionsegment> listPermi;
    private List<SubSegmento> listSubSegment;
    private boolean verDetalle;
    private boolean disabledSegmentChannel;
    private boolean disabledSubChannel;
    private boolean disabledSegment;
    private boolean disabledSubSegment;
    private boolean desabledPotential;
    private Outlet outletSelect;
    private String state;
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
        if (getLoginBean().getUsuario().getIdPerfil().getIdperfil().equals(ProfileEnum.ADMINISTRATOR.getId())
                || getLoginBean().getUsuario().getIdPerfil().getIdperfil().equals(ProfileEnum.DATA_STEWARD.getId())) {
            setListaOutlets(outletBeanLocal.consultarTodosOutlets());
        } else {
            setListaOutlets(new ArrayList<Outlet>());
            listPermi = getLoginBean().getListPermissionSegment();
            for (Permissionsegment permi : listPermi) {
                List<Outlet> listTemp = outletBeanLocal.findByDistributor(permi.getDistribuidor().getIdDistribuidor());
                if (permi.getChannelCheck().equals(StateEnum.ACTIVE.getState())) {
                    try {
                        Channel channel = channelBeanLocal.consultarId(permi.getChannel());
                        List<SubChannel> listSubCha = channel.getSubChannelList();
                        for (SubChannel listSubCha1 : listSubCha) {
                            List<Segmento> listSegment = listSubCha1.getSegmentoList();
                            for (Segmento listSegment1 : listSegment) {
                                listSubSegment = listSegment1.getSubSegmentoList();
                                for (SubSegmento listSubSegment1 : listSubSegment) {
                                    for (Outlet listTemp1 : listTemp) {
                                        if (listTemp1.getIdsubsegmento().getIdsubSegmento().equals(listSubSegment1.getIdsubSegmento())) {
                                            if (getLoginBean().getUsuario().getIdPerfil().getIdperfil().equals(ProfileEnum.TMC.getId())) {
                                                listTemp1.setDisabledButtonEdit(Boolean.TRUE);
                                                boolean disabled = !(listTemp1.getIdStateOutlet().getName().equals("OUTLET_TMC"));
                                                if (listTemp1.getIdStateOutlet().getName().equals("REJECTED")) {
                                                    disabled = Boolean.FALSE;
                                                }
                                                listTemp1.setDisabledButtonEdit(disabled);
                                            } else if (getLoginBean().getUsuario().getIdPerfil().getIdperfil().equals(ProfileEnum.CP_A.getId())) {
                                                boolean disabled = !(listTemp1.getIdStateOutlet().getName().equals("PENDING_APPROVAL"));
                                                if (listTemp1.getIdStateOutlet().getName().equals("PENDING_APPROVAL")) {
                                                    RequestContext.getCurrentInstance().execute("PF('dlgPendiente').show();");
                                                }
                                                listTemp1.setDisabledButtonEdit(disabled);
                                            }
                                            getListaOutlets().add(listTemp1);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (DiageoNegocioException ex) {
                        Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        setVerDetalle(Boolean.TRUE);
        super.init();
    }

    public void regresar() {
        setVerDetalle(Boolean.TRUE);
    }

    public void detalle(Outlet out) {
        if (!getLoginBean().getUsuario().getIdPerfil().getIdperfil().equals(ProfileEnum.ADMINISTRATOR.getId())
                && !getLoginBean().getUsuario().getIdPerfil().getIdperfil().equals(ProfileEnum.DATA_STEWARD.getId())) {
            for (Permissionsegment ps : listPermi) {
                if (ps.getDistribuidor().getIdDistribuidor().equals(out.getIdDistribuidor().getIdDistribuidor())) {
                    if (ps.getChannelCheck().equals(StateEnum.ACTIVE.getState())) {
                        setDisabledSegmentChannel(Boolean.TRUE);
                    }
                    if (ps.getPotentialCheck().equals(StateEnum.ACTIVE.getState())) {
                        setDesabledPotential(Boolean.TRUE);
                    }
                }
            }
        }
        setVerDetalle(Boolean.FALSE);
        setRazonSocial(out.getRazonsocial());
        setTipoOutlet(out.getTipoPersona());
        setNombreOutlet(out.getOutletname());
        setNit(out.getNit());
        setNombresPropietarios(out.getPropietario().getNombres());
        setApellidosPropietario(out.getPropietario().getApellidos());
        setNumeroDocumento(out.getPropietario().getNumDoc());
        setTipoDocumento(new TipoDoc(out.getPropietario().getTipodoc().getIdtipoDocumento()));
        setCanalSeleccionado(out.getIdsubsegmento().getIdsegmento().getIdsubchannel().getChannelIdchannel());
        setSubCanalSeleccionado(out.getIdsubsegmento().getIdsegmento().getIdsubchannel());
        setSegmentoSeleccionado(out.getIdsubsegmento().getIdsegmento());
        setSubSegmentoSeleccionado(out.getIdsubsegmento());
        setPotencialSeleccionado(out.getIdPotencial());
        setDistribuidorSeleccionado(out.getIdDistribuidor());
        setBattlegroundSeleccionado(out.getIdbattledground());
        setDireccion(out.getIdubicacion().getDireccion());
        setBarrio(out.getIdubicacion().getBarrio());
        setDepartamentoOutlet(out.getIdubicacion().getIdMunicipio().getIddepartamento());
        setMunicipioOutlet(out.getIdubicacion().getIdMunicipio());
        setCorreoElectronico(out.getCorreoelectronico());
        setLineaNegocio(out.getLineanegocio());
        setCodigoEan(out.getCodigoEAN());
        setPuntoVenta(out.getNumPDV());
        outletSelect = out;
    }

    public void updateOutlet() {
        try {
            if (outletSelect.getIdStateOutlet().getName().equals("OUTLET_TMC")) {
                outletSelect.setIdStateOutlet(new Sateoutlet(2));
            } else {
                if (state.equals("4")) {
                    outletSelect.setMessageReject(messageReject);
                }
                outletSelect.setIdStateOutlet(new Sateoutlet(Integer.parseInt(state)));
            }
            outletBeanLocal.modificarOutlet(outletSelect);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoNegocioException ex) {
            Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    public void listenerReject() {
        if (state.equals("4")) {
            RequestContext.getCurrentInstance().execute("PF('xvReject').show();");
        }
    }

    public List<Outlet> getListaOutlets() {
        return listaOutlets;
    }

    public void setListaOutlets(List<Outlet> listaOutlets) {
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

}
