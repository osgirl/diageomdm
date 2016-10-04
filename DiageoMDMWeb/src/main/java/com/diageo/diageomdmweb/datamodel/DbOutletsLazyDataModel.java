/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.datamodel;

import com.diageo.admincontrollerweb.entities.DwParameters;
import com.diageo.admincontrollerweb.enums.ProfileEnum;
import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.diageomdmweb.bean.outlet.OutletConsultarBean;
import com.diageo.diageonegocio.beans.ChannelBeanLocal;
import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.beans.SegmentBeanLocal;
import com.diageo.diageonegocio.beans.SubChannelBeanLocal;
import com.diageo.diageonegocio.entidades.DbChannels;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.entidades.DbSegments;
import com.diageo.diageonegocio.entidades.DbSubChannels;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.util.ArrayList;
import java.util.HashSet;
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

    public DbOutletsLazyDataModel(OutletBeanLocal outletBeanLocal, Integer profile) {
        this.outletBeanLocal = outletBeanLocal;
        this.profile = profile;
    }

    public DbOutletsLazyDataModel(OutletBeanLocal outletBeanLocal, Integer profile, List<DwParameters> parametersQuerySegment, List<String> statusMDM,
            Set<Integer> listDistributor, SegmentBeanLocal segmentoBeanLocal, SubChannelBeanLocal subChannelBeanLocal, ChannelBeanLocal channelBeanLocal,
            List<DbPermissionSegments> listPermi) {
        this.outletBeanLocal = outletBeanLocal;
        this.segmentoBeanLocal = segmentoBeanLocal;
        this.subChannelBeanLocal = subChannelBeanLocal;
        this.channelBeanLocal = channelBeanLocal;
        this.profile = profile;
        this.parametersQuerySegment = parametersQuerySegment;
        this.statusMDM = statusMDM;
        this.listDistributor = listDistributor;
        this.listPermi = listPermi;
    }

    public DbOutletsLazyDataModel(List<DbOutlets> dbOutlets, Integer profile) {
        this.dbOutlets = dbOutlets;
        this.profile = profile;
    }

    @Override
    public List<DbOutlets> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        if (ProfileEnum.ADMINISTRATOR.getId().equals(profile) || ProfileEnum.DATA_STEWARD.getId().equals(profile)) {
            setRowCount((int) outletBeanLocal.findAllOutletsCount(first, pageSize, filters));
            return outletBeanLocal.findAllOutlets(first, pageSize, filters);
        } else {
            dbOutlets = new ArrayList<>();
            for (Integer id3party : listDistributor) {
                Set<Integer> listSubSegment = new HashSet<>();
                for (DbPermissionSegments permi : listPermi) {
                    if (permi.getDb3partyId().getDb3partyId().equals(id3party)) {
                        if (permi.getSubSegmentCheck().equals(StateEnum.ACTIVE.getState())) {
                            listSubSegment.add(permi.getSubSegmentId());
                        } else if (permi.getSegmentCheck().equals(StateEnum.ACTIVE.getState())) {
                            try {
                                DbSegments listSegment = segmentoBeanLocal.findById(permi.getSegmentId());
                                for (DbSubSegments subSegtem : listSegment.getDbSubSegmentsList()) {
                                    listSubSegment.add(subSegtem.getSubSegmentId());
                                }
                            } catch (DiageoBusinessException ex) {
                                Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (permi.getSubChannelCheck().equals(StateEnum.ACTIVE.getState())) {
                            try {
                                DbSubChannels subChaTemp = subChannelBeanLocal.consultarId(permi.getSubChannelId());
                                for (DbSegments seg : subChaTemp.getDbSegmentsList()) {
                                    for (DbSubSegments subSeg : seg.getDbSubSegmentsList()) {
                                        listSubSegment.add(subSeg.getSubSegmentId());
                                    }
                                }
                            } catch (DiageoBusinessException ex) {
                                Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (permi.getChannelCheck().equals(StateEnum.ACTIVE.getState())) {
                            try {
                                DbChannels channelTemp = channelBeanLocal.findById(permi.getChannelId());
                                for (DbSubChannels subCha : channelTemp.getDbSubChannelsList()) {
                                    for (DbSegments seg : subCha.getDbSegmentsList()) {
                                        for (DbSubSegments subSeg : seg.getDbSubSegmentsList()) {
                                            listSubSegment.add(subSeg.getSubSegmentId());
                                        }
                                    }
                                }
                            } catch (DiageoBusinessException ex) {
                                Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                //Mirar cuales segmentos tiene permiso el sistema para ver, y esos se dejan para la consulta
                List<Integer> listSegmentQuery = new ArrayList<>();
                for (DwParameters param : parametersQuerySegment) {
                    Integer id = Integer.parseInt(param.getParameterValue());
                    for (Integer seg : listSubSegment) {
                        if (id.equals(seg)) {
                            listSegmentQuery.add(id);
                            break;
                        }
                    }
                }
                //Consultar los outlets
                //List<DbOutlets> listTemp = new ArrayList<>();
                if (!listSegmentQuery.isEmpty()) {
                    List<DbOutlets> listOutTem = outletBeanLocal.findBy3PartyPermissionSegment(id3party, listSegmentQuery, statusMDM, filters, first, pageSize);
                    dbOutlets.addAll(listOutTem);
                }
            }
            int size = 0;
            for (Integer id3party : listDistributor) {
                Set<Integer> listSubSegment = new HashSet<>();
                for (DbPermissionSegments permi : listPermi) {
                    if (permi.getDb3partyId().getDb3partyId().equals(id3party)) {
                        if (permi.getSubSegmentCheck().equals(StateEnum.ACTIVE.getState())) {
                            listSubSegment.add(permi.getSubSegmentId());
                        } else if (permi.getSegmentCheck().equals(StateEnum.ACTIVE.getState())) {
                            try {
                                DbSegments listSegment = segmentoBeanLocal.findById(permi.getSegmentId());
                                for (DbSubSegments subSegtem : listSegment.getDbSubSegmentsList()) {
                                    listSubSegment.add(subSegtem.getSubSegmentId());
                                }
                            } catch (DiageoBusinessException ex) {
                                Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (permi.getSubChannelCheck().equals(StateEnum.ACTIVE.getState())) {
                            try {
                                DbSubChannels subChaTemp = subChannelBeanLocal.consultarId(permi.getSubChannelId());
                                for (DbSegments seg : subChaTemp.getDbSegmentsList()) {
                                    for (DbSubSegments subSeg : seg.getDbSubSegmentsList()) {
                                        listSubSegment.add(subSeg.getSubSegmentId());
                                    }
                                }
                            } catch (DiageoBusinessException ex) {
                                Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (permi.getChannelCheck().equals(StateEnum.ACTIVE.getState())) {
                            try {
                                DbChannels channelTemp = channelBeanLocal.findById(permi.getChannelId());
                                for (DbSubChannels subCha : channelTemp.getDbSubChannelsList()) {
                                    for (DbSegments seg : subCha.getDbSegmentsList()) {
                                        for (DbSubSegments subSeg : seg.getDbSubSegmentsList()) {
                                            listSubSegment.add(subSeg.getSubSegmentId());
                                        }
                                    }
                                }
                            } catch (DiageoBusinessException ex) {
                                Logger.getLogger(OutletConsultarBean.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                //Mirar cuales segmentos tiene permiso el sistema para ver, y esos se dejan para la consulta
                List<Integer> listSegmentQuery = new ArrayList<>();
                for (DwParameters param : parametersQuerySegment) {
                    Integer id = Integer.parseInt(param.getParameterValue());
                    for (Integer seg : listSubSegment) {
                        if (id.equals(seg)) {
                            listSegmentQuery.add(id);
                            break;
                        }
                    }
                }
                //Consultar los outlets
                //List<DbOutlets> listTemp = new ArrayList<>();
                if (!listSegmentQuery.isEmpty()) {
                    long listOutTem = outletBeanLocal.findBy3PartyPermissionSegmentCount(id3party, listSegmentQuery, statusMDM, filters);
                    size += (int) listOutTem;
                }                
            }
            setRowCount(size);
            return dbOutlets;
        }
    }

    @Override
    public void setRowCount(int rowCount) {
        super.setRowCount(rowCount); //To change body of generated methods, choose Tools | Templates.
    }

}
