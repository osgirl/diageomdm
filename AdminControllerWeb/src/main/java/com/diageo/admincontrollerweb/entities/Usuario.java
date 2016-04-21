/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = Usuario.FIND_CORREO, query = "SELECT u FROM Usuario u WHERE u.correo = ?1"),
    @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado"),
    @NamedQuery(name = "Usuario.findByContraseina", query = "SELECT u FROM Usuario u WHERE u.contraseina = :contraseina"),
    @NamedQuery(name = "Usuario.findByFechaCreacion", query = "SELECT u FROM Usuario u WHERE u.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Usuario.findByFechaModificaicon", query = "SELECT u FROM Usuario u WHERE u.fechaModificaicon = :fechaModificaicon"),
    @NamedQuery(name = "Usuario.findByNumDoc", query = "SELECT u FROM Usuario u WHERE u.numDoc = :numDoc"),
    @NamedQuery(name = "Usuario.findByTipoDoc", query = "SELECT u FROM Usuario u WHERE u.tipoDoc = :tipoDoc"),
    @NamedQuery(name = Usuario.FIND_MAIL_PASS, query = "SELECT u FROM Usuario u WHERE u.contraseina = ?1 AND u.correo = ?2")})
public class Usuario implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<PassContainer> passContainerCollection;

    public static final String FIND_MAIL_PASS = "Usuario.findByMailPass";
    public static final String FIND_CORREO = "Usuario.findByCorreo";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idusuario;
    @Size(max = 50)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 50)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 120)
    @Column(name = "correo")
    private String correo;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @Size(max = 32)
    @Column(name = "contraseina")
    private String contraseina;
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "fechaModificaicon")
    @Temporal(TemporalType.DATE)
    private Date fechaModificaicon;
    @Size(max = 45)
    @Column(name = "numDoc")
    private String numDoc;
    @Column(name = "tipoDoc")
    private Integer tipoDoc;
    @Column(name = "primerIngreso")
    private String primerIngreso;
    @Column(name = "ingresoExitoso")
    @Temporal(TemporalType.DATE)
    private Date ingresoExitoso;
    @Column(name = "ingresoFallido")
    @Temporal(TemporalType.DATE)
    private Date ingresoFallido;
    @Column(name = "ultimoIngresoExitoso")
    @Temporal(TemporalType.DATE)
    private Date ultimoIngresoExitoso;
    @Column(name = "intentosFallidos")
    private Integer intentosFallidos;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Modulo> moduloList;
    @JoinColumn(name = "idPerfil", referencedColumnName = "idperfil")
    @ManyToOne
    private Perfil idPerfil;
    @OneToMany(mappedBy = "idUsuario")
    private List<LinkTemporal> linkTemporalList;
    @Column(name = "distributor")
    private Integer distributor;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getContraseina() {
        return contraseina;
    }

    public void setContraseina(String contraseina) {
        this.contraseina = contraseina;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificaicon() {
        return fechaModificaicon;
    }

    public void setFechaModificaicon(Date fechaModificaicon) {
        this.fechaModificaicon = fechaModificaicon;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public Integer getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(Integer tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public List<Modulo> getModuloList() {
        return moduloList;
    }

    public void setModuloList(List<Modulo> moduloList) {
        this.moduloList = moduloList;
    }

    public Perfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    public List<LinkTemporal> getLinkTemporalList() {
        return linkTemporalList;
    }

    public void setLinkTemporalList(List<LinkTemporal> linkTemporalList) {
        this.linkTemporalList = linkTemporalList;
    }

    public String getPrimerIngreso() {
        return primerIngreso;
    }

    public void setPrimerIngreso(String primerIngreso) {
        this.primerIngreso = primerIngreso;
    }

    public Date getIngresoExitoso() {
        return ingresoExitoso;
    }

    public void setIngresoExitoso(Date ingresoExitoso) {
        this.ingresoExitoso = ingresoExitoso;
    }

    public Date getIngresoFallido() {
        return ingresoFallido;
    }

    public void setIngresoFallido(Date ingresoFallido) {
        this.ingresoFallido = ingresoFallido;
    }

    public Integer getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(Integer intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public Date getUltimoIngresoExitoso() {
        return ultimoIngresoExitoso;
    }

    public void setUltimoIngresoExitoso(Date ultimoIngresoExitoso) {
        this.ultimoIngresoExitoso = ultimoIngresoExitoso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.admincontrollerweb.entities.Usuario[ idusuario=" + idusuario + " ]";
    }

    public Collection<PassContainer> getPassContainerCollection() {
        return passContainerCollection;
    }

    public void setPassContainerCollection(Collection<PassContainer> passContainerCollection) {
        this.passContainerCollection = passContainerCollection;
    }

    public Integer getDistributor() {
        return distributor;
    }

    public void setDistributor(Integer distributor) {
        this.distributor = distributor;
    }

}
