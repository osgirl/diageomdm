/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean;

import com.diageo.admincontrollerweb.entities.Modulo;
import com.diageo.admincontrollerweb.entities.Usuario;
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
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;
import com.diageo.admincontrollerweb.beans.UserBeanLocal;

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
    @EJB
    private UserBeanLocal usuarioLocal;
    @Pattern(regexp = PatternConstant.EMAIL_VALIDADOR, message = "{correo.pattern}")
    private List<Modulo> listModulos;
    private String user;
    private String password;
    private Usuario usuario;
    private boolean recordarme;
    private MenuModel migaPan;
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
        setUsuario(getUsuarioLocal().validateUserPassword(getUser(), pass));
        if (getUsuario() != null) {
            if (getUsuario().getEstado().equals(StateEnum.ACTIVE.getState())) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                setListModulos(getUsuario().getModuloList());
                session.setAttribute(USUARIO, getUsuario());
                setPassword(null);
                if (getUsuario().getPrimerIngreso().equals(UserEntryEnum.FIRST_ENTRY.getState())) {
                    armarMigaPan(capturarValor("m_perfil"), capturarValor("m_cambiar_contrase"));
                    return "/perfil/cambiarContrasenia/cambiarContrasenia?faces-redirect=true";
                }
                if (getUsuario().getIdPerfil().getIdperfil().equals(ProfileEnum.ADMINISTRATOR.getId())) {
                    armarMigaPan(capturarValor("m_administrador"), capturarValor("m_usuario"), capturarValor("m_usuario_consultar"));
                    return "/admin/usuario/consultarUsuario?faces-redirect=true";
                } else {
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

    public boolean renderizarMenu(String idModulo) {
        for (Modulo mod : getListModulos()) {
            if (mod.getLlave().equals(idModulo)) {
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
            listaLlaves.add(capturarLlave(dmi.getValue().toString()));
        }
        FacesContext.getCurrentInstance().getViewRoot().setLocale(getLocaleApp());
        setMigaPan(new BaseMenuModel());
        for (int i = 0; i < elements.size(); i++) {
            DefaultMenuItem dmi = new DefaultMenuItem(capturarValor(listaLlaves.get(i)));
            getMigaPan().addElement(dmi);
        }
    }

    public void changeLanguage(String idioma) {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(idioma));
    }

    public String logout() {
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

    @PreDestroy
    public void destroy() {
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
        cookieMail.setMaxAge(HOUR_SECOND);
        cookieMail.setPath(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath());
        cookieRecordarme.setMaxAge(HOUR_SECOND);
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
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
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

    public List<Modulo> getListModulos() {
        return listModulos;
    }

    public void setListModulos(List<Modulo> listModulos) {
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

}
