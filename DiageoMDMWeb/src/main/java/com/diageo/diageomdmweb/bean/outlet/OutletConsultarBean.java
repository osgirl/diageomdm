/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.outlet;

import com.diageo.admincontrollerweb.entities.TipoDoc;
import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.entidades.Outlet;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author yovanoty126
 */
@Named(value = "outletConsultarBean")
@ViewScoped
public class OutletConsultarBean extends OutletCrearBean implements Serializable {

    @EJB
    private OutletBeanLocal outletBeanLocal;
    private List<Outlet> listaOutlets;
    private boolean verDetalle;

    /**
     * Creates a new instance of OutletConsultarBean
     */
    public OutletConsultarBean() {
    }

    @PostConstruct
    @Override
    public void init() {
        setListaOutlets(outletBeanLocal.consultarTodosOutlets());
        setVerDetalle(Boolean.TRUE);
        super.init();
    }

    public void regresar() {
        setVerDetalle(Boolean.TRUE);
    }

    public void detalle(Outlet out) {
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

}
