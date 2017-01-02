/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author EDUARDO
 */
@Entity
@Table(name = "DB_LAYER")
public class DbLayer implements Serializable {

    @Id
    @Column(name = "LAYER_ID")
    private Integer layerId;
    @Column(name = "NAME_LAYER")
    private String nameLayer;
    @OneToMany(mappedBy = "layerId")
    private List<DbChains> dbLayerList;
    

    public Integer getLayerId() {
        return layerId;
    }

    public void setLayerId(Integer layerId) {
        this.layerId = layerId;
    }

    public String getNameLayer() {
        return nameLayer;
    }

    public void setNameLayer(String nameLayer) {
        this.nameLayer = nameLayer;
    }

    public List<DbChains> getDbLayerList() {
        return dbLayerList;
    }

    public void setDbLayerList(List<DbChains> dbLayerList) {
        this.dbLayerList = dbLayerList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.layerId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof DbLayer) {
            DbLayer layer = (DbLayer) obj;
            return layer.getLayerId().equals(this.layerId);
        }
        return false;
    }

}
