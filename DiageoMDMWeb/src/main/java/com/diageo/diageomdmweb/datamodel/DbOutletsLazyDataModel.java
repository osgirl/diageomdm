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

    private SegmentBeanLocal segmentoBeanLocal;
    private SubChannelBeanLocal subChannelBeanLocal;
    private ChannelBeanLocal channelBeanLocal;
    private Integer profile;
    private List<String> statusMDM;
    private List<DwParameters> parametersQuerySegment;
    private Set<Integer> listDistributor;
    private List<DbOutlets> dbOutlets;
    private List<DbPermissionSegments> listPermi;
    private List<Integer> outletId;

    public DbOutletsLazyDataModel(OutletBeanLocal outletBeanLocal, Integer profile) {
        this.outletBeanLocal = outletBeanLocal;
        this.profile = profile;
    }

    public DbOutletsLazyDataModel(OutletBeanLocal outletBeanLocal, Integer profile, List<Integer> outletId) {
        this(outletBeanLocal, profile);
        this.outletId = outletId;
    }

    @Override
    public List<DbOutlets> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        if (ProfileEnum.ADMINISTRATOR.getId().equals(profile) || ProfileEnum.DATA_STEWARD.getId().equals(profile)) {
            setRowCount((int) outletBeanLocal.findAllOutletsCount(first, pageSize, filters));
            List<DbOutlets> findAllOutlets = outletBeanLocal.findAllOutlets(first, pageSize, filters);
//            Set<String> keySet = filters.keySet();
//            Collection<Object> valueColl = filters.values();
//            String keyString = "";
//            String valueString = "";
//            for (String string : keySet) {
//                keyString += string + ",";
//            }
//            for (Object o : valueColl) {
//                valueString += o + ",";
//            }
//            String javaScript = "scritpListenerFilter([{name:'filter',value:'" + keyString + "'},{name:'filerValue',value:'" + valueString + "'}])";
//            System.out.println(javaScript);
//            RequestContext.getCurrentInstance().execute(javaScript);
            return findAllOutlets;
        } else {
            setRowCount((int) outletBeanLocal.findAllOutletsCountProfiles(first, pageSize, filters, outletId));
            List<DbOutlets> findAllOutlets = outletBeanLocal.findAllOutletsProfiles(first, pageSize, filters, outletId);            
            if(!filters.isEmpty() && findAllOutlets.isEmpty()){
                setRowCount((int) outletBeanLocal.findAllOutletsCount(first, pageSize, filters));
                System.out.println("entró al if outlet tmc");
                findAllOutlets=outletBeanLocal.findAllOutlets(first, pageSize, filters);
                return new ArrayList<DbOutlets>(findAllOutlets);
            }
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
        if(object!=null){
            return object.getOutletId();
        }
        return null;
    }
    
    

    
}
