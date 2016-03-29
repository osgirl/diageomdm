/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "modulo")
@NamedQueries({
    @NamedQuery(name = "Modulo.findAll", query = "SELECT m FROM Modulo m"),
    @NamedQuery(name = "Modulo.findByIdmodulo", query = "SELECT m FROM Modulo m WHERE m.idmodulo = :idmodulo"),
    @NamedQuery(name = "Modulo.findByNombreEs", query = "SELECT m FROM Modulo m WHERE m.nombreEs = :nombreEs"),
    @NamedQuery(name = "Modulo.findByNombreEn", query = "SELECT m FROM Modulo m WHERE m.nombreEn = :nombreEn"),
    @NamedQuery(name = "Modulo.findByIsPadre", query = "SELECT m FROM Modulo m WHERE m.isPadre = :isPadre"),
    @NamedQuery(name = "Modulo.findByUrl", query = "SELECT m FROM Modulo m WHERE m.url = :url")})
public class Modulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmodulo")
    private Integer idmodulo;
    @Column(name = "llave")
    private String llave;
    @Size(max = 45)
    @Column(name = "nombre_es")
    private String nombreEs;
    @Size(max = 45)
    @Column(name = "nombre_en")
    private String nombreEn;
    @Size(max = 1)
    @Column(name = "is_padre")
    private String isPadre;
    @Size(max = 150)
    @Column(name = "url")
    private String url;
    @JoinTable(name = "usuario_has_modulo", joinColumns = {
        @JoinColumn(name = "modulo_idmodulo", referencedColumnName = "idmodulo")}, inverseJoinColumns = {
        @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @JoinTable(name = "modPerfilPermi", joinColumns = {
        @JoinColumn(name = "idMod", referencedColumnName = "idmodulo")}, inverseJoinColumns = {
        @JoinColumn(name = "idPerfil", referencedColumnName = "idperfil")})
    @ManyToMany
    private List<Perfil> perfilList;
    @OneToMany(mappedBy = "padre")
    private List<Modulo> moduloList;
    @JoinColumn(name = "padre", referencedColumnName = "idmodulo")
    @ManyToOne
    private Modulo padre;

    public Modulo() {
    }

    public Modulo(Integer idmodulo) {
        this.idmodulo = idmodulo;
    }

    public Integer getIdmodulo() {
        return idmodulo;
    }

    public void setIdmodulo(Integer idmodulo) {
        this.idmodulo = idmodulo;
    }

    public String getNombreEs() {
        return nombreEs;
    }

    public void setNombreEs(String nombreEs) {
        this.nombreEs = nombreEs;
    }

    public String getNombreEn() {
        return nombreEn;
    }

    public void setNombreEn(String nombreEn) {
        this.nombreEn = nombreEn;
    }

    public String getIsPadre() {
        return isPadre;
    }

    public void setIsPadre(String isPadre) {
        this.isPadre = isPadre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public List<Perfil> getPerfilList() {
        return perfilList;
    }

    public void setPerfilList(List<Perfil> perfilList) {
        this.perfilList = perfilList;
    }

    public List<Modulo> getModuloList() {
        return moduloList;
    }

    public void setModuloList(List<Modulo> moduloList) {
        this.moduloList = moduloList;
    }

    public Modulo getPadre() {
        return padre;
    }

    public void setPadre(Modulo padre) {
        this.padre = padre;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmodulo != null ? idmodulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modulo)) {
            return false;
        }
        Modulo other = (Modulo) object;
        if ((this.idmodulo == null && other.idmodulo != null) || (this.idmodulo != null && !this.idmodulo.equals(other.idmodulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.admincontrollerweb.entities.Modulo[ idmodulo=" + idmodulo + " ]";
    }
    
}
