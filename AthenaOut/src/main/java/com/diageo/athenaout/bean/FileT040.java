/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.athenaout.bean;

import com.diageo.athenaout.dto.T040Dto;
import com.diageo.athenaout.dto.T042PartyAddrDto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author EDUARDO
 */
@Stateless
@LocalBean
public class FileT040 {

    @PersistenceContext(unitName = "AthenaPU")
    private EntityManager em;

    public List<T040Dto> findOutlets() {
        List<T040Dto> listDto = new ArrayList<>();
        String sql = "SELECT O.OUTLET_ID,O.OUTLET_NAME,O.BUSINESS_NAME,O.NIT,CH.DISTRI_1, "
                + "SC.DISTRI_1,S.DISTRI_1,SS.DISTRI_1,OW.NAME_OWNER,FS.NAME_FASCIA, "
                + "O.NUMBER_PDV,OCP.DISTRI_1,OCS.DISTRI_1,PS.DB_3PARTY_PDV,O.STATUS_OUTLET "
                + "FROM "
                + "DIAGEO_BUSINESS.DB_OUTLETS O "
                + "LEFT JOIN DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID=O.SUB_SEGMENT_ID "
                + "LEFT JOIN DB_SEGMENTS S ON S.SEGMENT_ID=SS.SEGMENT_ID "
                + "LEFT JOIN DB_SUB_CHANNELS SC ON SC.SUB_CHANNEL_ID=S.SUB_CHANNEL_ID "
                + "LEFT JOIN DB_CHANNELS CH ON CH.CHANNEL_ID=SC.CHANNEL_ID "
                + "LEFT JOIN DB_OWNERS OW ON OW.OWNER_ID=O.OWNER_ID "
                + "LEFT JOIN DB_FASCIAS FS ON FS.FASCIA_ID=OW.FASCIA_ID "
                + "LEFT JOIN DB_OCS OCP ON OCP.OCS_ID=O.OCS_PRIMARY "
                + "LEFT JOIN DB_OCS OCS ON OCS.OCS_ID=O.OCS_SECONDARY "
                + "LEFT JOIN DB_3PARTY_SALES PS ON PS.DB_3PARTY_SALE_ID=O.DB_3PARTY_SALE_ID "
                + "LEFT JOIN DB_3PARTY_MANAGERS PM ON PM.DB_3PARTY_MANAGER_ID=PS.DB_3PARTY_MANAGER_ID "
                + "WHERE O.IS_FATHER='1' "
                + "UNION ALL "
                + "SELECT CHA.CHAIN_ID,CHA.NAME_CHAIN, "
                + "CHA.BUSINESS_NAME,NULL AS NIT,CHAN.DISTRI_1,SUBCHA.DISTRI_1, "
                + "SE.DISTRI_1,SS.DISTRI_1,NULL AS OWNERS,NULL AS FASCIA, "
                + "CHA.KIERNAN_ID,NULL AS OCS1,NULL AS OCS2,NULL AS SALES, NULL AS STATE_CHAIN "
                + "FROM DB_CHAINS CHA "
                + "LEFT JOIN DB_SUB_SEGMENTS SS ON SS.SEGMENT_ID=CHA.SUB_SEGMENT_ID "
                + "LEFT JOIN DB_SEGMENTS SE ON SE.SEGMENT_ID=SS.SEGMENT_ID "
                + "LEFT JOIN DB_SUB_CHANNELS SUBCHA ON SUBCHA.SUB_CHANNEL_ID=SE.SUB_CHANNEL_ID "
                + "LEFT JOIN DB_CHANNELS CHAN ON CHAN.CHANNEL_ID=SUBCHA.CHANNEL_ID";
//        String sql = "SELECT "
//                + "  O.OUTLET_ID, "
//                + "  O.OUTLET_NAME, "
//                + "  O.BUSINESS_NAME, "
//                + "  O.NIT,CH.DISTRI_1, "
//                + "  SC.DISTRI_1, "
//                + "  S.DISTRI_1, "
//                + "  SS.DISTRI_1, "
//                + "  OW.NAME_OWNER, "
//                + "  FS.NAME_FASCIA, "
//                + "  O.NUMBER_PDV, "
//                + "  OCP.DISTRI_1, "
//                + "  OCS.DISTRI_1, "
//                + "  PS.DB_3PARTY_PDV "
//                + "FROM "
//                + "DIAGEO_BUSINESS.DB_OUTLETS O "
//                + "LEFT JOIN DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID=O.SUB_SEGMENT_ID "
//                + "LEFT JOIN DB_SEGMENTS S ON S.SEGMENT_ID=SS.SEGMENT_ID "
//                + "LEFT JOIN DB_SUB_CHANNELS SC ON SC.SUB_CHANNEL_ID=S.SUB_CHANNEL_ID "
//                + "LEFT JOIN DB_CHANNELS CH ON CH.CHANNEL_ID=SC.CHANNEL_ID "
//                + "LEFT JOIN DB_OWNERS OW ON OW.OWNER_ID=O.OWNER_ID "
//                + "LEFT JOIN DB_FASCIAS FS ON FS.FASCIA_ID=OW.FASCIA_ID "
//                + "LEFT JOIN DB_OCS OCP ON OCP.OCS_ID=O.OCS_PRIMARY "
//                + "LEFT JOIN DB_OCS OCS ON OCS.OCS_ID=O.OCS_SECONDARY "
//                + "LEFT JOIN DB_3PARTY_SALES PS ON PS.DB_3PARTY_SALE_ID=O.DB_3PARTY_SALE_ID "
//                + "LEFT JOIN DB_3PARTY_MANAGERS PM ON PM.DB_3PARTY_MANAGER_ID=PS.DB_3PARTY_MANAGER_ID "
//                + "WHERE O.IS_FATHER='1' "
//                + "AND O.OUTLET_ID BETWEEN 1352 AND 1453";

        Query query = em.createNativeQuery(sql);
        List list = query.getResultList();
        for (Object obj : list) {
            T040Dto dto = new T040Dto();
            Object[] ob = (Object[]) obj;
            dto.setIdMdm(ob[0] == null ? "" : ob[0] + "");
            dto.setDisParty_1(ob[1] == null ? "" : ob[1] + "");
            dto.setDisParty_2(ob[2] == null ? "" : ob[2] + "");
            dto.setCodVat(ob[3] == null ? "" : ob[3] + "");
            dto.setCodChannel(ob[4] == null ? "" : ob[4] + "");
            dto.setCodSubChannel(ob[5] == null ? "" : ob[5] + "");
            dto.setCodSegment(ob[6] == null ? "" : ob[6] + "");
            dto.setCodSubSegment(ob[7] == null ? "" : ob[7] + "");
            dto.setOwner(ob[8] == null ? "" : ob[8] + "");
            dto.setFascia(ob[9] == null ? "" : ob[9] + "");
            dto.setFlagCustInv("0");
            dto.setFlagCustDeliv("-1");
            dto.setFlagCustSale("-1");
            dto.setCodCustConc(ob[10] == null ? "" : ob[10] + "");
            dto.setFlagAnn(ob[14] == null ? "":(ob[14].toString().equals("A")?"1":"0"));
            dto.setzStoreNumber("");
            dto.setZiln("");
            dto.setzDistributor("");//???
            dto.setPrimaryOcs(ob[11] == null ? "" : ob[11] + "");
            dto.setSecondaryOcs(ob[12] == null ? "" : ob[12] + "");
            dto.setRegionalSales("");
            dto.setManagerSales("");
            dto.setTerritoryMmanager("");
            dto.setSalesRepDis("");//debe ir en blanco
            dto.setSalesRepRole("");//debe ir en blanco
            dto.setLastDateSale("");//debe ir en blanco
            dto.setRtcStatus("");
            dto.setBattleground("");
            dto.setFlagDistributor("1");
            dto.setFlagChain("0");
            listDto.add(dto);
        }
        return listDto;
    }

}
