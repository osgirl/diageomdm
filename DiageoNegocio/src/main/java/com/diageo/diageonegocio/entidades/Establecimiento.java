/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

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
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "establecimiento")
@NamedQueries({
    @NamedQuery(name = "Establecimiento.findAll", query = "SELECT e FROM Establecimiento e"),
    @NamedQuery(name = "Establecimiento.findByIdestablecimiento", query = "SELECT e FROM Establecimiento e WHERE e.idestablecimiento = :idestablecimiento"),
    @NamedQuery(name = "Establecimiento.findByIdmunicipio", query = "SELECT e FROM Establecimiento e WHERE e.idmunicipio = :idmunicipio"),
    @NamedQuery(name = "Establecimiento.findByCorreoelectronico", query = "SELECT e FROM Establecimiento e WHERE e.correoelectronico = :correoelectronico"),
    @NamedQuery(name = "Establecimiento.findByNit", query = "SELECT e FROM Establecimiento e WHERE e.nit = :nit"),
    @NamedQuery(name = "Establecimiento.findByRazonsocial", query = "SELECT e FROM Establecimiento e WHERE e.razonsocial = :razonsocial"),
    @NamedQuery(name = "Establecimiento.findByLineanegocio", query = "SELECT e FROM Establecimiento e WHERE e.lineanegocio = :lineanegocio"),
    @NamedQuery(name = "Establecimiento.findByOutletname", query = "SELECT e FROM Establecimiento e WHERE e.outletname = :outletname"),
    @NamedQuery(name = "Establecimiento.findByMercadoasociado", query = "SELECT e FROM Establecimiento e WHERE e.mercadoasociado = :mercadoasociado"),
    @NamedQuery(name = "Establecimiento.findByCodigoEAN", query = "SELECT e FROM Establecimiento e WHERE e.codigoEAN = :codigoEAN"),
    @NamedQuery(name = "Establecimiento.findByNumPDV", query = "SELECT e FROM Establecimiento e WHERE e.numPDV = :numPDV"),
    @NamedQuery(name = "Establecimiento.findByTipoPersona", query = "SELECT e FROM Establecimiento e WHERE e.tipoPersona = :tipoPersona")})
public class Establecimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestablecimiento")
    private Integer idestablecimiento;
    @Column(name = "idmunicipio")
    private Integer idmunicipio;
    @Size(max = 45)
    @Column(name = "correoelectronico")
    private String correoelectronico;
    @Size(max = 45)
    @Column(name = "nit")
    private String nit;
    @Size(max = 45)
    @Column(name = "razonsocial")
    private String razonsocial;
    @Size(max = 45)
    @Column(name = "lineanegocio")
    private String lineanegocio;
    @Size(max = 45)
    @Column(name = "outletname")
    private String outletname;
    @Size(max = 45)
    @Column(name = "mercadoasociado")
    private String mercadoasociado;
    @Size(max = 45)
    @Column(name = "codigoEAN")
    private String codigoEAN;
    @Size(max = 45)
    @Column(name = "numPDV")
    private String numPDV;
    @Size(max = 1)
    @Column(name = "tipoPersona")
    private String tipoPersona;
    @JoinTable(name = "telefonos_has_establecimiento", joinColumns = {
        @JoinColumn(name = "establecimiento_idestablecimiento", referencedColumnName = "idestablecimiento")}, inverseJoinColumns = {
        @JoinColumn(name = "telefonos_idtelefonos", referencedColumnName = "idtelefonos")})
    @ManyToMany
    private List<Telefonos> telefonosList;
    @JoinColumn(name = "subduenio", referencedColumnName = "idpersona")
    @ManyToOne
    private Persona subduenio;
    @JoinColumn(name = "propietario", referencedColumnName = "idpersona")
    @ManyToOne
    private Persona propietario;
    @JoinColumn(name = "idubicacion", referencedColumnName = "idUbicacion")
    @ManyToOne
    private Ubicacion idubicacion;
    @JoinColumn(name = "idsubsegmento", referencedColumnName = "idsub_segmento")
    @ManyToOne
    private SubSegmento idsubsegmento;
    @JoinColumn(name = "idbattledground", referencedColumnName = "idbattleground")
    @ManyToOne
    private Battleground idbattledground;

    public Establecimiento() {
    }

    public Establecimiento(Integer idestablecimiento) {
        this.idestablecimiento = idestablecimiento;
    }

    public Integer getIdestablecimiento() {
        return idestablecimiento;
    }

    public void setIdestablecimiento(Integer idestablecimiento) {
        this.idestablecimiento = idestablecimiento;
    }

    public Integer getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getLineanegocio() {
        return lineanegocio;
    }

    public void setLineanegocio(String lineanegocio) {
        this.lineanegocio = lineanegocio;
    }

    public String getOutletname() {
        return outletname;
    }

    public void setOutletname(String outletname) {
        this.outletname = outletname;
    }

    public String getMercadoasociado() {
        return mercadoasociado;
    }

    public void setMercadoasociado(String mercadoasociado) {
        this.mercadoasociado = mercadoasociado;
    }

    public String getCodigoEAN() {
        return codigoEAN;
    }

    public void setCodigoEAN(String codigoEAN) {
        this.codigoEAN = codigoEAN;
    }

    public String getNumPDV() {
        return numPDV;
    }

    public void setNumPDV(String numPDV) {
        this.numPDV = numPDV;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public List<Telefonos> getTelefonosList() {
        return telefonosList;
    }

    public void setTelefonosList(List<Telefonos> telefonosList) {
        this.telefonosList = telefonosList;
    }

    public Persona getSubduenio() {
        return subduenio;
    }

    public void setSubduenio(Persona subduenio) {
        this.subduenio = subduenio;
    }

    public Persona getPropietario() {
        return propietario;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    public Ubicacion getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(Ubicacion idubicacion) {
        this.idubicacion = idubicacion;
    }

    public SubSegmento getIdsubsegmento() {
        return idsubsegmento;
    }

    public void setIdsubsegmento(SubSegmento idsubsegmento) {
        this.idsubsegmento = idsubsegmento;
    }

    public Battleground getIdbattledground() {
        return idbattledground;
    }

    public void setIdbattledground(Battleground idbattledground) {
        this.idbattledground = idbattledground;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestablecimiento != null ? idestablecimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Establecimiento)) {
            return false;
        }
        Establecimiento other = (Establecimiento) object;
        if ((this.idestablecimiento == null && other.idestablecimiento != null) || (this.idestablecimiento != null && !this.idestablecimiento.equals(other.idestablecimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.Establecimiento[ idestablecimiento=" + idestablecimiento + " ]";
    }
    
}
