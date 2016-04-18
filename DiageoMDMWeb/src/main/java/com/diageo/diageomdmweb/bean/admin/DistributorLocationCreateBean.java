/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.diageomdmweb.bean.DiageoApplicationBean;
import com.diageo.diageonegocio.entidades.Departamento;
import com.diageo.diageonegocio.entidades.Distribuidor;
import com.diageo.diageonegocio.entidades.Municipio;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author yovanoty126
 */
@Named(value = "distributorLocationCreateBean")
@ViewScoped
public class DistributorLocationCreateBean extends DistributorCreate {

    private static final String DEPTO = "D";
    private static final String CITY = "C";
    @Inject
    private DiageoApplicationBean diageoApplicationBean;
    private String typeLocation;
    private Departamento depto;
    private Municipio city;

    /**
     * Creates a new instance of DistributorLocationBean
     */
    public DistributorLocationCreateBean() {
    }

    @PostConstruct
    @Override
    public void init() {
        super.init();
        setDepto(diageoApplicationBean.getListaDepartamento().get(0));
        setCity(getDepto().getMunicipioList().get(0));
    }

    public void createLocatorDistributor() {
        Distribuidor distri = new Distribuidor();
        distri.setPadreIdDistribuidor(getSelectedDistributor().getIdDistribuidor());
        distri.setIsPadre("0");
        switch (getTypeLocation()) {
            case DEPTO:                
                distri.setIsDepto("1");
                distri.setIdDepartamento(getDepto());
                distri.setNombre(getSelectedDistributor().getNombre().toUpperCase() + " " + getDepto().getNombredepto().toUpperCase());
                break;
            case CITY:                
                distri.setIsDepto("0");
                distri.setIdMunicipio(getCity());                
                distri.setNombre(getSelectedDistributor().getNombre().toUpperCase() + " " + getCity().getNombremunicipio().toUpperCase());
                break;
        }
        try {
            distributorBeanLocal.createDistributor(distri);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoNegocioException ex) {
            Logger.getLogger(DistributorLocationCreateBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    /**
     * @return the typeLocation
     */
    public String getTypeLocation() {
        return typeLocation;
    }

    /**
     * @param typeLocation the typeLocation to set
     */
    public void setTypeLocation(String typeLocation) {
        this.typeLocation = typeLocation;
    }

    /**
     * @return the depto
     */
    public Departamento getDepto() {
        return depto;
    }

    /**
     * @param depto the depto to set
     */
    public void setDepto(Departamento depto) {
        this.depto = depto;
    }

    /**
     * @return the city
     */
    public Municipio getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(Municipio city) {
        this.city = city;
    }

}
