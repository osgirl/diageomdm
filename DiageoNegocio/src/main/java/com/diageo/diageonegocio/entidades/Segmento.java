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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yovanoty126
 */
@Entity
@Table(name = "segmento")
@NamedQueries({
    @NamedQuery(name = Segmento.FIND_BY_SUBCHANNEL, query = "SELECT s FROM Segmento s WHERE s.idsubchannel.idsubchannel = ?1")})
public class Segmento implements Serializable {

    public static final String FIND_BY_SUBCHANNEL = "Segmento.findBySubChannel";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsegmento")
    private Integer idsegmento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "idsegmento")
    private List<SubSegmento> subSegmentoList;
    @JoinColumn(name = "idsubchannel", referencedColumnName = "idsubchannel")
    @ManyToOne
    private SubChannel idsubchannel;

    public Segmento() {
    }

    public Segmento(Integer idsegmento) {
        this.idsegmento = idsegmento;
    }

    public Segmento(Integer idsegmento, String nombre, String estado) {
        this.idsegmento = idsegmento;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getIdsegmento() {
        return idsegmento;
    }

    public void setIdsegmento(Integer idsegmento) {
        this.idsegmento = idsegmento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<SubSegmento> getSubSegmentoList() {
        return subSegmentoList;
    }

    public void setSubSegmentoList(List<SubSegmento> subSegmentoList) {
        this.subSegmentoList = subSegmentoList;
    }

    public SubChannel getIdsubchannel() {
        return idsubchannel;
    }

    public void setIdsubchannel(SubChannel idsubchannel) {
        this.idsubchannel = idsubchannel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsegmento != null ? idsegmento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Segmento)) {
            return false;
        }
        Segmento other = (Segmento) object;
        if ((this.idsegmento == null && other.idsegmento != null) || (this.idsegmento != null && !this.idsegmento.equals(other.idsegmento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.Segmento[ idsegmento=" + idsegmento + " ]";
    }

}
