/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean;

import com.diageo.admincontrollerweb.enums.ModuleEnum;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author yovanoty126
 */
@Named(value = "menuBean")
@RequestScoped
public class MenuBean extends DiageoRootBean {

    @Inject
    private LoginBean loginBean;

    /**
     * Creates a new instance of MenuBean
     */
    public MenuBean() {
    }

    public String navegar(String llave) {
        ModuleEnum moduloEnum = ModuleEnum.valueOf(llave);
        switch (moduloEnum) {
            case C_OUT:
                getLoginBean().armarMigaPan(capturarValor("m_outlet"), capturarValor("m_outlet_crear"));
                return "/outlet/crearOutlet?faces-redirect=true";
            case S_OUT:
                getLoginBean().armarMigaPan(capturarValor("m_outlet"), capturarValor("m_outlet_consultar"));
                return "/outlet/consultarOutlet?faces-redirect=true";
            case LOAD_OUT:
                getLoginBean().armarMigaPan(capturarValor("m_outlet"), capturarValor("m_outlet_carga"));
                return "/outlet/cargaMasivaOutlet?faces-redirect=true";
            case C_CHAIN:
                getLoginBean().armarMigaPan(capturarValor("m_outlet"), capturarValor("m_chain_create"));
                return "/outlet/createChain?faces-redirect=true";
            case S_USU:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_usuario"), capturarValor("m_usuario_consultar"));
                return "/admin/usuario/consultarUsuario?faces-redirect=true";
            case C_USU:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_usuario"), capturarValor("m_usuario_crear"));
                return "/admin/usuario/crearUsuario?faces-redirect=true";
            case C_CHAN:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_canal"), capturarValor("m_canal_crear"));
                return "/admin/canal/crearCanal?faces-redirect=true";
            case S_CHAN:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_canal"), capturarValor("m_canal_consultar"));
                return "/admin/canal/consultarCanal?faces-redirect=true";
            case C_SEG:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_segmento"), capturarValor("m_segmento_crear"));
                return "/admin/segmento/crearSegmento?faces-redirect=true";
            case S_SEG:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_segmento"), capturarValor("m_segmento_consultar"));
                return "/admin/segmento/consultarSegmento?faces-redirect=true";
            case C_SUB_CHAN:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_canal_sub"), capturarValor("m_canal_sub_crear"));
                return "/admin/subcanal/crearSubCanal?faces-redirect=true";
            case S_SUB_CHAN:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_canal_sub"), capturarValor("m_canal_sub_consultar"));
                return "/admin/subcanal/consultarSubCanal?faces-redirect=true";
            case C_SUB_SEG:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_segmento_sub"), capturarValor("m_segmento_sub_crear"));
                return "/admin/subsegmento/crearSubSegmento?faces-redirect=true";
            case S_SUB_SEG:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_segmento_sub"), capturarValor("m_segmento_sub_consultar"));
                return "/admin/subsegmento/consultarSubSegmento?faces-redirect=true";
            case C_BATT:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_battleground"), capturarValor("m_battleground_crear"));
                return "/admin/battleground/crearBattleground?faces-redirect=true";
            case S_BATT:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_battleground"), capturarValor("m_battleground_consultar"));
                return "/admin/battleground/consultarBattleground?faces-redirect=true";
            case MIS_DATOS:
                getLoginBean().armarMigaPan(capturarValor("m_perfil"), capturarValor("m_mis_datos"));
                return "/perfil/datos/datosUsuario?faces-redirect=true";
            case CAM_CONTRA:
                getLoginBean().armarMigaPan(capturarValor("m_perfil"), capturarValor("m_cambiar_contrase"));
                return "/perfil/cambiarContrasenia/cambiarContrasenia?faces-redirect=true";
            case C_DISTRI:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_distribuidor"), capturarValor("m_distri_crear"));
                return "/admin/distribuidor/crearDistribuidor?faces-redirect=true";
            case S_DISTRI:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_distribuidor"), capturarValor("m_distri_consultar"));
                return "/admin/distribuidor/consultarDistribuidor?faces-redirect=true";
            case C_DISTRI_LOCATION:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("regional"), capturarValor("regional_create"));
                return "/admin/distributorLocation/createDistributorLocation?faces-redirect=true";
            case S_DISTRI_LOCATION:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("regional"), capturarValor("regional_search"));
                return "/admin/distributorLocation/searchDistributorLocation?faces-redirect=true";
            case C_POTENTIAL:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_management_potential"), capturarValor("m_potential_create"));
                return "/admin/potential/createPotential?faces-redirect=true";
            case S_POTENTIAL:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("m_management_potential"), capturarValor("m_potential_search"));
                return "/admin/potential/searchPotential?faces-redirect=true";
            case REPORT_OUTLET:
                getLoginBean().armarMigaPan(capturarValor("m_report"), capturarValor("m_report_create_outlet"));
                return "/reports/outletReport?faces-redirect=true";
            case REPORT_DISTRI:
                getLoginBean().armarMigaPan(capturarValor("m_report"), capturarValor("m_report_create_distributor"));
                return "/reports/distributorReport?faces-redirect=true";
            case REPORT_PENDING:
                getLoginBean().armarMigaPan(capturarValor("m_report"), capturarValor("m_report_outlet_pendding"));
                return "/reports/pendingReport?faces-redirect=true";
            case QUERY_SEGMENT_EDIT:
                getLoginBean().armarMigaPan(capturarValor("m_administrador"), capturarValor("param"),capturarValor("param_segment_query"));
                return "/admin/parameter/filterQuerySegment?faces-redirect=true";
            case S_CHAIN:
                getLoginBean().armarMigaPan(capturarValor("m_outlet"), capturarValor("m_chain_search"));
                return "/outlet/searchChain?faces-redirect=true";
        }
        return null;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

}
