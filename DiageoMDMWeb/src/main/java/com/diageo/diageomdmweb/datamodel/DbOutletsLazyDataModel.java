/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.datamodel;

import com.diageo.admincontrollerweb.entities.DwParameters;
import com.diageo.admincontrollerweb.enums.ProfileEnum;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.beans.OutletsUserBeanLocal;
import com.diageo.diageonegocio.beans.SegmentBeanLocal;
import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author EDUARDO
 */
public class DbOutletsLazyDataModel extends LazyDataModel<DbOutlets> {

    private OutletBeanLocal outletBeanLocal;
    private OutletsUserBeanLocal outletsUserBeanLocal;
    private Integer profile;
    private Integer userId;
    /**
     * si la bandera es true, significa que la bandera debe hacer la consulta
     * por commercial manager por false, hace la constulta por tmc
     */
    private boolean flagCommercial;
    /**
     * lista de usuarios que se usa para la consulta de los outlets que verá el
     * commercial manager
     */
    private List<Integer> listIdUser;

    /**
     * Constructor llamado por el perfil administrador
     *
     * @param outletBeanLocal
     * @param profile
     */
    public DbOutletsLazyDataModel(OutletBeanLocal outletBeanLocal, Integer profile) {
        this.outletBeanLocal = outletBeanLocal;
        this.profile = profile;
    }

    /**
     * constructor llamado por TMC
     *
     * @param outletBeanLocal
     * @param profile
     * @param userId
     * @param outletsUserBeanLocal
     */
    public DbOutletsLazyDataModel(OutletBeanLocal outletBeanLocal, Integer profile, Integer userId, OutletsUserBeanLocal outletsUserBeanLocal) {
        this(outletBeanLocal, profile);
        this.userId = userId;
        this.outletsUserBeanLocal = outletsUserBeanLocal;
    }

    public DbOutletsLazyDataModel(OutletBeanLocal outletBeanLocal, Integer profile, List<Integer> listIdUser, OutletsUserBeanLocal outletsUserBeanLocal) {
        this(outletBeanLocal, profile);
        this.listIdUser = listIdUser;
        this.outletsUserBeanLocal = outletsUserBeanLocal;
        flagCommercial = true;
    }

    @Override
    public List<DbOutlets> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        if (ProfileEnum.ADMINISTRATOR.getId().equals(profile) || ProfileEnum.DATA_STEWARD.getId().equals(profile)) {
            setRowCount((int) outletBeanLocal.findAllOutletsCount(first, pageSize, filters));
            List<DbOutlets> findAllOutlets = outletBeanLocal.findAllOutlets(first, pageSize, filters);
            return findAllOutlets;
        } else {
            if (!flagCommercial) {
                setRowCount((int) outletsUserBeanLocal.findOutletByUserCount(first, pageSize, filters, userId));
                List<DbOutlets> findAllOutlets = outletsUserBeanLocal.findOutletByUser(first, pageSize, filters, userId);
                return findAllOutlets;
            }
            setRowCount((int) outletsUserBeanLocal.findOutletByUserCountIn(first, pageSize, filters, listIdUser));
            List<DbOutlets> findAllOutlets = outletsUserBeanLocal.findOutletByUserIn(first, pageSize, filters, listIdUser);
            return findAllOutlets;
        }
    }

    @Override
    public void setRowCount(int rowCount) {
        super.setRowCount(rowCount); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DbOutlets getRowData(String id) {
        try {
            DbOutlets findById = outletBeanLocal.findById(Integer.parseInt(id));
            return findById;
        } catch (DiageoBusinessException ex) {
            Logger.getLogger(DbOutletsLazyDataModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Object getRowKey(DbOutlets object) {
        if (object != null) {
            return object.getOutletId();
        }
        return null;
    }

}
