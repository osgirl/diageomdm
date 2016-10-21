/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean;

import com.diageo.admincontrollerweb.beans.TemporalLinkBeanLocal;
import com.diageo.admincontrollerweb.entities.DwModules;
import com.diageo.admincontrollerweb.entities.DwUsers;
import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.admincontrollerweb.enums.ProfileEnum;
import com.diageo.admincontrollerweb.enums.UserEntryEnum;
import com.diageo.diageomdmweb.constant.PatternConstant;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.model.menu.BaseMenuModel;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;
import com.diageo.admincontrollerweb.beans.UserBeanLocal;
import com.diageo.admincontrollerweb.entities.DwTemporalLink;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import com.diageo.diageomdmweb.mail.EMail;
import com.diageo.diageomdmweb.mail.templates.VelocityTemplate;
import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.beans.PermissionsegmentBeanLocal;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.enums.StateOutletChain;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author yovanoty126
 */
@SessionScoped
@Named
public class LoginBean extends DiageoRootBean implements Serializable {

    /**
     * Constante que indica la sigla del idioma español
     */
    private static final String ESPANOL = "es";
    @Inject
    private DiageoApplicationBean diageoApplicationBean;
    @EJB
    private TemporalLinkBeanLocal temporalLinkBeanLocal;
    @EJB
    private UserBeanLocal usuarioLocal;
    @EJB
    private PermissionsegmentBeanLocal permissionsegmentBeanLocal;
    @EJB
    private OutletBeanLocal outletBeanLocal;
    private List<DwModules> listModulos;
    @Pattern(regexp = PatternConstant.EMAIL_VALIDADOR, message = "{correo.pattern}")
    private String user;
    private String password;
    @Pattern(regexp = PatternConstant.EMAIL_VALIDADOR, message = "{correo.pattern}")
    private String emailRecover;
    private DwUsers usuario;
    private boolean recordarme;
    private MenuModel migaPan;
    private List<DbPermissionSegments> listPermissionSegment;
    /**
     * Idioma seleccionado
     */
    private String idioma;
    /**
     * Locale de la aplicacion
     */
    private Locale localeApp;

    @PostConstruct
    public void init() {
        establecerCookiesCamposLogin();
        setLocaleApp(new Locale(ESPANOL));
    }

    public String login() {
        administrarCookies();
        String pass = DigestUtils.md5Hex(getPassword());
        setUsuario(getUsuarioLocal().validateUserPassword(getUser().toUpperCase(), pass));
        if (getUsuario() != null) {
            if (getUsuario().getStateUser().equals(StateEnum.ACTIVE.getState())) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                setListModulos(getUsuario().getDwModulesList());
                session.setAttribute(USUARIO, getUsuario());
                //setPassword(null);
                findPermissionSegment(getUsuario().getUserId());
                if (getUsuario().getFirstEntry().equals(UserEntryEnum.FIRST_ENTRY.getState())) {
                    armarMigaPan(capturarValor("m_perfil"), capturarValor("m_cambiar_contrase"));
                    return "/perfil/cambiarContrasenia/cambiarContrasenia?faces-redirect=true";
                }
                if (getUsuario().getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())) {
                    armarMigaPan(capturarValor("m_administrador"), capturarValor("m_usuario"), capturarValor("m_usuario_consultar"));
                    return "/admin/usuario/consultarUsuario?faces-redirect=true";
                } else {
                    if (getUsuario().getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())) {
                        armarMigaPan(capturarValor("m_outlet"), capturarValor("m_chain_search"));
                        return "/outlet/searchChain?faces-redirect=true";
                    }
                    if (getUsuario().getProfileId().getProfileId().equals(ProfileEnum.CATDEV.getId())) {
                        armarMigaPan(capturarValor("m_outlet"), capturarValor("m_outlet_consultar"));
                        return "/outlet/consultarOutlet?faces-redirect=true";
                    }
                    if (getUsuario().getProfileId().getProfileId().equals(ProfileEnum.NAM.getId())) {
                        armarMigaPan(capturarValor("m_outlet"), capturarValor("m_chain_search"));
                        return "/outlet/searchChain?faces-redirect=true";
                    }
                    armarMigaPan(capturarValor("m_outlet"), capturarValor("m_outlet_consultar"));
                    return "/outlet/consultarOutlet?faces-redirect=true";
                }
            }
            showWarningMessage(capturarValor("user_locked"));
            return null;
        }
        showErrorMessage(capturarValor("sis_user_pass"));
        return null;

    }

    private boolean revisarOutletsPendientesRevision() {
        List<DbPermissionSegments> listPermi = getListPermissionSegment();
        for (DbPermissionSegments permi : listPermi) {
            List<DbOutlets> listTemp = outletBeanLocal.findByDistributor(permi.getDb3partyId().getDb3partyId());
            for (DbOutlets out : listTemp) {
                if (permi.getSubSegmentId().equals(out.getSubSegmentId().getSubSegmentId())) {
                    if (out.getStatusOutlet().equals(StateOutletChain.PENDING_APPROVAL.getId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean renderizarMenu(String idModulo) {
        for (DwModules mod : getListModulos()) {
            if (mod.getKekModule().equals(idModulo)) {
                return true;
            }
        }
        return false;
    }

    public void changeLanguage() {
        setLocaleApp(new Locale(getIdioma()));
        List<MenuElement> elements = getMigaPan().getElements();
        List<String> listaLlaves = new ArrayList<>();
        for (MenuElement element : elements) {
            DefaultMenuItem dmi = (DefaultMenuItem) element;
            String valueLabelProperties = capturarLlave(dmi.getValue().toString());
            if (valueLabelProperties.isEmpty()) {
                valueLabelProperties = capturarLlave(dmi.getValue().toString(), ESPANOL);
            }
            if (valueLabelProperties.isEmpty()) {
                valueLabelProperties = capturarLlave(dmi.getValue().toString(), Locale.ENGLISH.getLanguage());
            }
            listaLlaves.add(valueLabelProperties);
        }
        FacesContext.getCurrentInstance().getViewRoot().setLocale(getLocaleApp());
        setMigaPan(new BaseMenuModel());
        for (int i = 0; i < elements.size(); i++) {
            DefaultMenuItem dmi = new DefaultMenuItem(capturarValor(listaLlaves.get(i)));
            getMigaPan().addElement(dmi);
        }
    }

    public void recoverPassword() {
        try {
            usuarioLocal.findEmail(getEmailRecover());
            String token = org.apache.commons.codec.digest.DigestUtils.sha256Hex("jardila@latino-bi.com" + getCurrentDate());
            DwTemporalLink tl = new DwTemporalLink();
            Calendar expiration = Calendar.getInstance();
            expiration.setTime(getCurrentDate());
            expiration.roll(Calendar.DATE, 3);
            tl.setCreattionDate(getCurrentDate());
            tl.setToken(token);
            tl.setEmail(getEmailRecover());
            tl.setExpiration(expiration.getTime());
            temporalLinkBeanLocal.createTemporal(tl);
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String url = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + req.getServerPort() + req.getContextPath() + req.getServletPath() + "/recoverPassword.xhtml?" + TOKEN + "=" + token;
            EMail mail = new EMail();
            String msg = VelocityTemplate.recoverPassword(url, getDiageoApplicationBean().getPathMailTemplate());
            mail.send(new String[]{getEmailRecover()}, capturarValor("mail_recover_pass"), msg);
            showInfoMessage(capturarValor("msg_mail_send_recover_password"));
        } catch (ControllerWebException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            showWarningMessage("El correo ingresado no existe");
        } catch (UnknownHostException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeLanguage(String idioma) {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(idioma));
    }

    public String logout() {
        setLocaleApp(new Locale(ESPANOL));
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getAttribute(USUARIO) != null) {
            request.getSession().invalidate();
        }
        eliminarObjetos();
        establecerCookiesCamposLogin();
        return "/login?faces-redirect=true";
    }

    private void establecerCookiesCamposLogin() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Cookie[] array = request.getCookies();
        if (array != null) {
            for (Cookie cookie : array) {
                switch (cookie.getName()) {
                    case COOKIE_MAIL:
                        setUser(cookie.getValue());
                        break;
                    case COOKIE_REMEMBER:
                        setRecordarme(Boolean.parseBoolean(cookie.getValue()));
                        break;
                }
            }
        }
    }

    private void findPermissionSegment(Integer idUser) {
        setListPermissionSegment(permissionsegmentBeanLocal.findByUser(idUser));
    }

    @PreDestroy
    public void destroy() {
        setLocaleApp(new Locale(ESPANOL));
        eliminarObjetos();
    }

    public void armarMigaPan(String... parametros) {
        setMigaPan(new DefaultMenuModel());
        if (parametros != null) {
            DefaultMenuItem item = new DefaultMenuItem(parametros[0]);
            getMigaPan().addElement(item);
            for (String parametro : parametros) {
                DefaultMenuItem item2 = new DefaultMenuItem(parametro);
                getMigaPan().addElement(item2);
            }
        }
    }

    private void eliminarObjetos() {
        setUser(null);
        setPassword(null);
        setListModulos(null);
        setUsuario(null);
    }

    private void administrarCookies() {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        Cookie cookieMail;
        Cookie cookieRecordarme;
        if (isRecordarme()) {
            cookieMail = new Cookie(COOKIE_MAIL, getUser());
            cookieRecordarme = new Cookie(COOKIE_REMEMBER, isRecordarme() + "");
        } else {
            cookieMail = new Cookie(COOKIE_MAIL, null);
            cookieRecordarme = new Cookie(COOKIE_REMEMBER, isRecordarme() + "");
        }
        int day = HOUR_SECOND * 24;
        cookieMail.setMaxAge(day);
        cookieMail.setPath(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath());
        cookieRecordarme.setMaxAge(day);
        cookieRecordarme.setPath(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath());
        response.addCookie(cookieMail);
        response.addCookie(cookieRecordarme);
    }

    /**
     * @return the usuarioLocal
     */
    public UserBeanLocal getUsuarioLocal() {
        return usuarioLocal;
    }

    /**
     * @param usuarioLocal the usuarioLocal to set
     */
    public void setUsuarioLocal(UserBeanLocal usuarioLocal) {
        this.usuarioLocal = usuarioLocal;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the usuario
     */
    public DwUsers getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(DwUsers usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the recordarme
     */
    public boolean isRecordarme() {
        return recordarme;
    }

    /**
     * @param recordarme the recordarme to set
     */
    public void setRecordarme(boolean recordarme) {
        this.recordarme = recordarme;
    }

    public List<DwModules> getListModulos() {
        return listModulos;
    }

    public void setListModulos(List<DwModules> listModulos) {
        this.listModulos = listModulos;
    }

    /**
     * @return the idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * @param idioma the idioma to set
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * @return the localeApp
     */
    public Locale getLocaleApp() {
        return localeApp;
    }

    /**
     * @param localeApp the localeApp to set
     */
    public void setLocaleApp(Locale localeApp) {
        this.localeApp = localeApp;
    }

    /**
     * @return the migaPan
     */
    public MenuModel getMigaPan() {
        return migaPan;
    }

    /**
     * @param migaPan the migaPan to set
     */
    public void setMigaPan(MenuModel migaPan) {
        this.migaPan = migaPan;
    }

    /**
     * @return the listPermissionSegment
     */
    public List<DbPermissionSegments> getListPermissionSegment() {
        return listPermissionSegment;
    }

    /**
     * @param listPermissionSegment the listPermissionSegment to set
     */
    public void setListPermissionSegment(List<DbPermissionSegments> listPermissionSegment) {
        this.listPermissionSegment = listPermissionSegment;
    }

    /**
     * @return the emailRecover
     */
    public String getEmailRecover() {
        return emailRecover;
    }

    /**
     * @param emailRecover the emailRecover to set
     */
    public void setEmailRecover(String emailRecover) {
        this.emailRecover = emailRecover;
    }

    /**
     * @return the diageoApplicationBean
     */
    public DiageoApplicationBean getDiageoApplicationBean() {
        return diageoApplicationBean;
    }

}
