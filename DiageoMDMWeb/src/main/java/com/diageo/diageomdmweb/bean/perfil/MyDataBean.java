/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.perfil;

import com.diageo.admincontrollerweb.entities.DwUsers;
import com.diageo.admincontrollerweb.enums.DocumentTypeEnum;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageomdmweb.bean.LoginBean;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import com.diageo.admincontrollerweb.beans.UserBeanLocal;
import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageomdmweb.bean.admin.GestionarUsuarioCreacion;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.PermissionsegmentBeanLocal;
import com.diageo.diageonegocio.beans.SegmentBeanLocal;
import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.beans.SubSegmentoBeanLocal;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.List;

/**
 *
 * @author yovanoty126
 */
@Named(value = "myDataBean")
@ViewScoped
public class MyDataBean extends DiageoRootBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(MyDataBean.class.getName());
    @EJB
    protected ChannelBeanLocal channelBeanLocal;
    @EJB
    protected SubChannelBeanLocal subChannelBeanLocal;
    @EJB
    protected SegmentBeanLocal segmentoBeanLocal;
    @EJB
    protected SubSegmentoBeanLocal subSegmentoBeanLocal;
    @Inject
    private LoginBean loginBean;
    @EJB
    private PermissionsegmentBeanLocal permissionsegmentBeanLocal;
    @EJB
    private UserBeanLocal usuarioBeanLocal;
    private String nombres;
    private String apellidos;
    private String correo;
    private String perfil;
    private List<DbPermissionSegments> listPermission;

    /**
     * Creates a new instance of MisDatosBean
     */
    public MyDataBean() {
    }

    @PostConstruct
    public void init() {
        setApellidos(getLoginBean().getUsuario().getLastName());
        setNombres(getLoginBean().getUsuario().getNameUser());
        setCorreo("<b>" + getLoginBean().getUsuario().getEmailUser() + "</b>");
        setPerfil("<b>" + getLoginBean().getUsuario().getProfileId().getNameProfile() + "</b>");
        setListPermission(permissionsegmentBeanLocal.findByUser(getLoginBean().getUsuario().getUserId()));
    }    

    public void updateData() {
        try {
            DwUsers usuario = getLoginBean().getUsuario();
            usuario.setNameUser(getNombres().toUpperCase());
            usuario.setLastName(getApellidos().toUpperCase());
            usuario = usuarioBeanLocal.updateUser(usuario);
            getLoginBean().setUsuario(usuario);
            super.showInfoMessage(capturarValor("usu_mis_datos"));
        } catch (ControllerWebException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            super.showErrorMessage(capturarValor("usu_erro_mis_datos"));
        }
    }
    
     public String channelName(Integer id) {
        try {
            return channelBeanLocal.findById(id).getNameChannel();
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String subChannelName(Integer id) {
        if (id == null) {
            return "";
        }
        if (id == -1) {
            return capturarValor("usu_msg_all");
        }
        try {
            return subChannelBeanLocal.consultarId(id).getNameSubChannel();
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String segmentName(Integer id) {
        if (id == null) {
            return "";
        }
        if (id == -1) {
            return capturarValor("usu_msg_all");
        }
        try {
            return segmentoBeanLocal.findById(id).getNameSegment();
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String subSegmentName(Integer id) {
        if (id == null) {
            return "";
        }
        if (id == -1) {
            return capturarValor("usu_msg_all");
        }
        try {
            return subSegmentoBeanLocal.findById(id).getNameSubsegment();
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(GestionarUsuarioCreacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
    public void findByPermission(){
        
    }

    /**
     * @return the loginBean
     */
    public LoginBean getLoginBean() {
        return loginBean;
    }
    
    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the perfil
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public List<DbPermissionSegments> getListPermission() {
        return listPermission;
    }

    public void setListPermission(List<DbPermissionSegments> listPermission) {
        this.listPermission = listPermission;
    }

}
