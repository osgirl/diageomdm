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
@Table(name = "sub_segmento")
@NamedQueries({
    @NamedQuery(name = "SubSegmento.findAll", query = "SELECT s FROM SubSegmento s"),
    @NamedQuery(name = "SubSegmento.findByIdsubSegmento", query = "SELECT s FROM SubSegmento s WHERE s.idsubSegmento = :idsubSegmento"),
    @NamedQuery(name = "SubSegmento.findByNomnbre", query = "SELECT s FROM SubSegmento s WHERE s.nomnbre = :nomnbre"),
    @NamedQuery(name = "SubSegmento.findByEstado", query = "SELECT s FROM SubSegmento s WHERE s.estado = :estado")})
public class SubSegmento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsub_segmento")
    private Integer idsubSegmento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomnbre")
    private String nomnbre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "idsubsegmento")
    private List<Establecimiento> establecimientoList;
    @JoinColumn(name = "idsegmento", referencedColumnName = "idsegmento")
    @ManyToOne
    private Segmento idsegmento;

    public SubSegmento() {
    }

    public SubSegmento(Integer idsubSegmento) {
        this.idsubSegmento = idsubSegmento;
    }

    public SubSegmento(Integer idsubSegmento, String nomnbre, String estado) {
        this.idsubSegmento = idsubSegmento;
        this.nomnbre = nomnbre;
        this.estado = estado;
    }

    public Integer getIdsubSegmento() {
        return idsubSegmento;
    }

    public void setIdsubSegmento(Integer idsubSegmento) {
        this.idsubSegmento = idsubSegmento;
    }

    public String getNomnbre() {
        return nomnbre;
    }

    public void setNomnbre(String nomnbre) {
        this.nomnbre = nomnbre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }

    public Segmento getIdsegmento() {
        return idsegmento;
    }

    public void setIdsegmento(Segmento idsegmento) {
        this.idsegmento = idsegmento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsubSegmento != null ? idsubSegmento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubSegmento)) {
            return false;
        }
        SubSegmento other = (SubSegmento) object;
        if ((this.idsubSegmento == null && other.idsubSegmento != null) || (this.idsubSegmento != null && !this.idsubSegmento.equals(other.idsubSegmento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.diageo.diageonegocio.entidades.SubSegmento[ idsubSegmento=" + idsubSegmento + " ]";
    }
    
}
