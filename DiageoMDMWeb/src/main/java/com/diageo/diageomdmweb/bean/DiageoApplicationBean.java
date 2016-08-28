/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean;

import com.diageo.admincontrollerweb.entities.DwProfiles;
import com.diageo.admincontrollerweb.entities.DwDocumentTypes;
import com.diageo.diageonegocio.beans.DepartamentBeanLocal;
import com.diageo.diageonegocio.entidades.DbDepartaments;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import com.diageo.admincontrollerweb.beans.DocumentTypeBeanLocal;
import com.diageo.admincontrollerweb.beans.ProfileBeanLocal;

/**
 *
 * @author yovanoty126
 */
@ApplicationScoped
@Named
public class DiageoApplicationBean extends DiageoRootBean implements Serializable {

    /**
     * Ejb DocumentTypeBeanLocal
     */
    @EJB
    private DocumentTypeBeanLocal tipoDocBean;
    /**
     * Ejb ProfileBeanLocal
     */
    @EJB
    private ProfileBeanLocal perfilBean;
    /**
     * Ejb departamentoBeanLocal
     */
    @EJB
    private DepartamentBeanLocal departamentoBeanLocal;
    /**
     * Lista con todos los tipos de documento
     */
    private List<DwDocumentTypes> listaTipoDocumento;
    /**
     * Lista con todos los perfiles del sistema
     */
    private List<DwProfiles> listaPerfiles;
    /**
     * Lista departamentos
     */
    private List<DbDepartaments> listaDepartamento;

    /**
     * Creates a new instance of DiageoApplicationBean
     */
    public DiageoApplicationBean() {
    }

    @PostConstruct
    public void init() {
        setListaTipoDocumento((List<DwDocumentTypes>) tipoDocBean.findAll());
        setListaPerfiles((List<DwProfiles>) perfilBean.findBySystem());     
        setListaDepartamento(departamentoBeanLocal.findAllDepartament());
    }

    /**
     * @return the listaTipoDocumento
     */
    public List<DwDocumentTypes> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    /**
     * @param listaTipoDocumento the listaTipoDocumento to set
     */
    public void setListaTipoDocumento(List<DwDocumentTypes> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    /**
     * @return the listaPerfiles
     */
    public List<DwProfiles> getListaPerfiles() {
        return listaPerfiles;
    }

    /**
     * @param listaPerfiles the listaPerfiles to set
     */
    public void setListaPerfiles(List<DwProfiles> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    /**
     * @return the listaDepartamento
     */
    public List<DbDepartaments> getListaDepartamento() {
        return listaDepartamento;
    }

    /**
     * @param listaDepartamento the listaDepartamento to set
     */
    public void setListaDepartamento(List<DbDepartaments> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

}
