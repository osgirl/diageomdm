/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean;

import com.diageo.admincontrollerweb.beans.PerfilBeanLocal;
import com.diageo.admincontrollerweb.beans.TipoDocumentoBeanLocal;
import com.diageo.admincontrollerweb.entities.Perfil;
import com.diageo.admincontrollerweb.entities.TipoDoc;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author yovanoty126
 */
@ApplicationScoped
@Named
public class DiageoApplicationBean extends DiageoRootBean implements Serializable {
    
    /**
     * Ejb TipoDocumentoBeanLocal
     */
    @EJB
    private TipoDocumentoBeanLocal tipoDocBean;
    /**
     * Ejb PerfilBeanLocal
     */
    @EJB
    private PerfilBeanLocal perfilBean;
    /**
     * Lista con todos los tipos de documento
     */
    private List<TipoDoc> listaTipoDocumento;
    /**
     * Lista con todos los perfiles del sistema
     */
    private List<Perfil> listaPerfiles;    

    /**
     * Creates a new instance of DiageoApplicationBean
     */
    public DiageoApplicationBean() {
    }

    @PostConstruct
    public void init() {
        setListaTipoDocumento((List<TipoDoc>) tipoDocBean.capturarListaDocumento());
        setListaPerfiles((List<Perfil>) perfilBean.capturarTodosPerfiles());
    }    

    /**
     * @return the listaTipoDocumento
     */
    public List<TipoDoc> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    /**
     * @param listaTipoDocumento the listaTipoDocumento to set
     */
    public void setListaTipoDocumento(List<TipoDoc> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    /**
     * @return the listaPerfiles
     */
    public List<Perfil> getListaPerfiles() {
        return listaPerfiles;
    }

    /**
     * @param listaPerfiles the listaPerfiles to set
     */
    public void setListaPerfiles(List<Perfil> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

}
