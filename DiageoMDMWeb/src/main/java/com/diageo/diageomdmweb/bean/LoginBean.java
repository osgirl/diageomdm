/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean;

import com.diageo.admincontrollerweb.beans.UsuarioBeanLocal;
import com.diageo.admincontrollerweb.entities.Modulo;
import com.diageo.admincontrollerweb.entities.Usuario;
import com.diageo.admincontrollerweb.enums.EstadoUsuarioEnum;
import com.diageo.admincontrollerweb.enums.PerfilEnum;
import com.diageo.admincontrollerweb.enums.UsuarioIngresoEnum;
import com.diageo.diageomdmweb.constantes.PatternConstantes;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
    private UsuarioBeanLocal usuarioLocal;
    @Pattern(regexp = PatternConstantes.EMAIL_VALIDADOR, message = "{correo.pattern}")
    private List<Modulo> listModulos;
    private String user;
    private String password;
    private Usuario usuario;
    private boolean recordarme;
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
        setUsuario(getUsuarioLocal().validarUsuarioContrasena(getUser(), pass));
        if (getUsuario() != null) {
            if (getUsuario().getEstado().equals(EstadoUsuarioEnum.ACTIVO.getEstado())) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                setListModulos(getUsuario().getModuloList());
                session.setAttribute(USUARIO, getUsuario());
                setPassword(null);
                if (getUsuario().getPrimerIngreso().equals(UsuarioIngresoEnum.PRIMER_INGRESO.getEstado())) {
                    return "/perfil/cambiarContrasenia/cambiarContrasenia?faces-redirect=true";
                }
                if (getUsuario().getIdPerfil().getIdperfil().equals(PerfilEnum.ADMINISTRADOR.getId())) {
                    return "/admin/usuario/consultarUsuario?faces-redirect=true";
                } else {
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
        FacesContext.getCurrentInstance().getViewRoot().setLocale(getLocaleApp());
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
                if (cookie.getName().equals(COOKIE_MAIL)) {
                    setUser(cookie.getValue());
                } else if (cookie.getName().equals(COOKIE_REMEMBER)) {
                    setRecordarme(Boolean.parseBoolean(cookie.getValue()));
                }
            }
        }
    }

    @PreDestroy
    public void destroy() {
        eliminarObjetos();
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
    public UsuarioBeanLocal getUsuarioLocal() {
        return usuarioLocal;
    }

    /**
     * @param usuarioLocal the usuarioLocal to set
     */
    public void setUsuarioLocal(UsuarioBeanLocal usuarioLocal) {
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

}
