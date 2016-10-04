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
import com.diageo.admincontrollerweb.beans.ParameterBeanLocal;
import com.diageo.admincontrollerweb.beans.ProfileBeanLocal;
import com.diageo.admincontrollerweb.enums.ParameterKeyEnum;

/**
 *
 * @author yovanoty126
 */
@ApplicationScoped
@Named
public class DiageoApplicationBean extends DiageoRootBean implements Serializable {

    @EJB
    private ParameterBeanLocal parameterBeanLocal;
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
     * Path mail template
     */
    private String pathMailTemplate;

    /**
     * Creates a new instance of DiageoApplicationBean
     */
    public DiageoApplicationBean() {
    }

    @PostConstruct
    public void init() {
        setListaTipoDocumento((List<DwDocumentTypes>) tipoDocBean.findAll());
        //setListaPerfiles((List<DwProfiles>) perfilBean.findBySystem());
        setListaDepartamento(departamentoBeanLocal.findAllDepartament());
        setPathMailTemplate(parameterBeanLocal.findByKey(ParameterKeyEnum.PATH_MAIL_TEMPLATE.name()).get(0).getParameterValue());
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

    /**
     * @return the pathMailTemplate
     */
    public String getPathMailTemplate() {
        return pathMailTemplate;
    }

    /**
     * @param pathMailTemplate the pathMailTemplate to set
     */
    public void setPathMailTemplate(String pathMailTemplate) {
        this.pathMailTemplate = pathMailTemplate;
    }

}
