/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.datamodel;

import com.diageo.admincontrollerweb.enums.ProfileEnum;
import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.beans.OutletsUserBeanLocal;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbOutletsUsers;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
            setRowCount((int) outletBeanLocal.findAllOutletsDinamicCount(filters));
            List<DbOutlets> findAllOutlets = outletBeanLocal.findAllOutletsDinamic(first, pageSize, filters);
            return findAllOutlets;
        } else {
            if (!flagCommercial) {
                setRowCount((int) outletsUserBeanLocal.findOutletByUserCount(first, pageSize, filters, userId));
                //setRowCount((int) outletsUserBeanLocal.findAllOutletsDinamicCount(filters, userId, false, null));
                //List<DbOutlets> findAllOutlets = outletsUserBeanLocal.findOutletByUser(first, pageSize, filters, userId);
                List<DbOutlets> findAllOutlets = new ArrayList<>();
                List<DbOutletsUsers> dinamic = outletsUserBeanLocal.findAllOutletsDinamic(first, pageSize, filters, userId, false, null);
                for (DbOutletsUsers outUsu : dinamic) {
                    findAllOutlets.add(outUsu.getDbOutlets());
                }
                return findAllOutlets;
            }
            setRowCount((int) outletsUserBeanLocal.findOutletByUserCountIn(first, pageSize, filters, listIdUser));
            //setRowCount((int) outletsUserBeanLocal.findAllOutletsDinamicCount(filters, userId, true, listIdUser));
            //List<DbOutlets> findAllOutlets = outletsUserBeanLocal.findOutletByUserIn(first, pageSize, filters, listIdUser);
            List<DbOutlets> findAllOutlets = new ArrayList<>();
            List<DbOutletsUsers> dinamic = outletsUserBeanLocal.findAllOutletsDinamic(first, pageSize, filters, userId, true, listIdUser);
            if (dinamic != null) {
                for (DbOutletsUsers dbOutletsUsers : dinamic) {
                    findAllOutlets.add(dbOutletsUsers.getDbOutlets());
                }
            }
            return findAllOutlets;
        }
    }

    @Override
    public void setRowCount(int rowCount) {
        super.setRowCount(rowCount); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Método que se implementa para seleccionar uno o varios registros
     *
     * @param id
     * @return
     */
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

    /**
     * Método que se implementa para seleccionar uno o varios registros
     *
     * @param object
     * @return
     */
    @Override
    public Object getRowKey(DbOutlets object) {
        if (object != null) {
            return object.getOutletId();
        }
        return null;
    }

}
