/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.athenaout.bean;

import com.diageo.athenaout.dto.T040Dto;
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
//        String sql = "SELECT  "
//                + "    O.OUTLET_ID, "
//                + "    O.OUTLET_NAME, "
//                + "    O.BUSINESS_NAME, "
//                + "    O.NIT, "
//                + "    CH.DISTRI_1 CHANNEL_ID, "
//                + "    SC.DISTRI_1 SUB_CHANNEL_ID, "
//                + "    S.DISTRI_1 SEGMENT_ID, "
//                + "    SS.DISTRI_1 SUB_SEGMENT_ID,  "
//                + "    OW.DISTRI_1 NAME_OWNER, "
//                + "    FS.NAME_FASCIA, "
//                + "    O.NUMBER_PDV, "
//                + "    OCP.DISTRI_1 OCS_1, "
//                + "    OCS.DISTRI_1 OCS_2, "
//                + "    PS.DB_3PARTY_PDV, "
//                + "    O.STATUS_OUTLET,"
//                + "    D3P.DISTRI_1 Z_DISTRIBUIDOR,"
//                + "    PT.DISTRI_1 BATTLEGROUND"
//                + "   FROM DIAGEO_BUSINESS.DB_OUTLETS O  "
//                + "   INNER JOIN DB_POTENTIALS PT ON PT.POTENTIAL_ID = O.POTENTIAL_ID"
//                + "   INNER JOIN DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID = O.SUB_SEGMENT_ID  "
//                + "   INNER JOIN DB_SEGMENTS S ON S.SEGMENT_ID = SS.SEGMENT_ID  "
//                + "   INNER JOIN DB_SUB_CHANNELS SC ON SC.SUB_CHANNEL_ID = S.SUB_CHANNEL_ID  "
//                + "   INNER JOIN DB_CHANNELS CH ON CH.CHANNEL_ID = SC.CHANNEL_ID  "
//                + "   INNER JOIN DB_OUTLETS_3PARTY O3P ON O3P.OUTLET_ID = O.OUTLET_ID"
//                + "   INNER JOIN DB_3PARTY D3P ON D3P.DB_3PARTY_ID = O3P.DB_3PARTY_ID"
//                + "   LEFT OUTER JOIN DB_OWNERS OW ON OW.OWNER_ID = O.OWNER_ID  "
//                + "   LEFT OUTER JOIN DB_FASCIAS FS ON FS.FASCIA_ID = OW.FASCIA_ID  "
//                + "   LEFT OUTER JOIN DB_OCS OCP ON OCP.OCS_ID = O.OCS_PRIMARY  "
//                + "   LEFT OUTER JOIN DB_OCS OCS ON OCS.OCS_ID = O.OCS_SECONDARY "
//                + "   LEFT OUTER JOIN DB_3PARTY_SALES PS ON PS.DB_3PARTY_SALE_ID = O.DB_3PARTY_SALE_ID  "
//                + "   LEFT OUTER JOIN DB_3PARTY_MANAGERS PM ON PM.DB_3PARTY_MANAGER_ID = PS.DB_3PARTY_MANAGER_ID  "
//                + "   WHERE O.IS_FATHER = '1'  "
//                + "   AND O.STATUS_OUTLET = 'A'                      "
//                + "   UNION ALL                       "
//                + " SELECT   "
//                + "  CHA.CHAIN_ID, "
//                + "  CHA.NAME_CHAIN, "
//                + "  CHA.BUSINESS_NAME, "
//                + "  NULL AS NIT, "
//                + "  CHAN.DISTRI_1, "
//                + "  SUBCHA.DISTRI_1, "
//                + "  SE.DISTRI_1, "
//                + "  SS.DISTRI_1, "
//                + "  NULL AS OWNERS, "
//                + "  NULL AS FASCIA,  "
//                + "  CHA.KIERNAN_ID, "
//                + "  NULL AS OCS1, "
//                + "  NULL AS OCS2, "
//                + "  NULL AS SALES,  "
//                + "  'A' AS STATE_CHAIN,"
//                + "  C3P.DISTRI_1 ZDISTRIBUIDOR,"
//                + "  DP.DISTRI_1 BATTLEGROUND "
//                + "FROM DB_CHAINS CHA  "
//                + " INNER JOIN DB_POTENTIALS DP ON CHA.POTENTIAL_ID = DP.POTENTIAL_ID "
//                + " INNER JOIN DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID = DP.SUB_SEGMENT_ID  "
//                + " INNER JOIN DB_SEGMENTS SE ON SE.SEGMENT_ID = SS.SEGMENT_ID  "
//                + " INNER JOIN DB_SUB_CHANNELS SUBCHA ON SUBCHA.SUB_CHANNEL_ID = SE.SUB_CHANNEL_ID  "
//                + " INNER JOIN DB_CHANNELS CHAN ON CHAN.CHANNEL_ID = SUBCHA.CHANNEL_ID "
//                + " INNER JOIN DB_3PARTY C3P ON C3P.DB_3PARTY_ID = CHA.DB_3PARTY_ID "
//                + "WHERE CHA.IS_ACTIVE = 1";
        String sql = "SELECT "
                + "  O.OUTLET_ID, "
                + "  O.OUTLET_NAME, "
                + "  O.BUSINESS_NAME, "
                + "  O.NIT, "
                + "  CH.DISTRI_1 CHANNEL_ID, "
                + "  SC.DISTRI_1 SUB_CHANNEL_ID, "
                + "  S.DISTRI_1 SEGMENT_ID, "
                + "  SS.DISTRI_1 SUB_SEGMENT_ID,  "
                + "  OW.DISTRI_1 NAME_OWNER, "
                + "  FS.NAME_FASCIA, "
                + "  O.NUMBER_PDV, "
                + "  OCP.DISTRI_1 OCS_1, "
                + "  OCS.DISTRI_1 OCS_2, "
                + "  PS.DB_3PARTY_PDV, "
                + "  O.STATUS_OUTLET, "
                + "  D3P.DISTRI_1 Z_DISTRIBUIDOR, "
                + "  PT.DISTRI_1 BATTLEGROUND, "
                + "  NULL AS COD_EAN, "
                + "  '1' AS IS_OUTLET "
                + "FROM DIAGEO_BUSINESS.DB_OUTLETS O  "
                + "INNER JOIN DB_POTENTIALS PT ON PT.POTENTIAL_ID = O.POTENTIAL_ID "
                + "INNER JOIN DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID = O.SUB_SEGMENT_ID  "
                + "INNER JOIN DB_SEGMENTS S ON S.SEGMENT_ID = SS.SEGMENT_ID  "
                + "INNER JOIN DB_SUB_CHANNELS SC ON SC.SUB_CHANNEL_ID = S.SUB_CHANNEL_ID  "
                + "INNER JOIN DB_CHANNELS CH ON CH.CHANNEL_ID = SC.CHANNEL_ID  "
                + "INNER JOIN DB_OUTLETS_3PARTY O3P ON O3P.OUTLET_ID = O.OUTLET_ID "
                + "INNER JOIN DB_3PARTY D3P ON D3P.DB_3PARTY_ID = O3P.DB_3PARTY_ID "
                + "LEFT OUTER JOIN DB_OWNERS OW ON OW.OWNER_ID = O.OWNER_ID  "
                + "LEFT OUTER JOIN DB_FASCIAS FS ON FS.FASCIA_ID = OW.FASCIA_ID  "
                + "LEFT OUTER JOIN DB_OCS OCP ON OCP.OCS_ID = O.OCS_PRIMARY  "
                + "LEFT OUTER JOIN DB_OCS OCS ON OCS.OCS_ID = O.OCS_SECONDARY "
                + "LEFT OUTER JOIN DB_3PARTY_SALES PS ON PS.DB_3PARTY_SALE_ID = O.DB_3PARTY_SALE_ID  "
                + "LEFT OUTER JOIN DB_3PARTY_MANAGERS PM ON PM.DB_3PARTY_MANAGER_ID = PS.DB_3PARTY_MANAGER_ID  "
                + "WHERE O.IS_FATHER = '1'   "
                + "AND O.STATUS_OUTLET = 'A' "
                + "AND O.OUTLET_ID > 78180 "
                + "UNION ALL "
                + "SELECT   "
                + "  CHA.CHAIN_ID, "
                + "  CHA.NAME_CHAIN, "
                + "  CHA.BUSINESS_NAME, "
                + "  NULL AS NIT, "
                + "  CHAN.DISTRI_1, "
                + "  SUBCHA.DISTRI_1, "
                + "  SE.DISTRI_1, "
                + "  SS.DISTRI_1, "
                + "  NULL AS OWNERS, "
                + "  NULL AS FASCIA,  "
                + "  CHA.KIERNAN_ID, "
                + "  NULL AS OCS1, "
                + "  NULL AS OCS2, "
                + "  NULL AS SALES,  "
                + "  'A' AS STATE_CHAIN, "
                + "  C3P.DISTRI_1 ZDISTRIBUIDOR, "
                + "  DP.DISTRI_1 BATTLEGROUND, "
                + "  CHA.CODE_EAN, "
                + "  '0' AS IS_OUTLET "
                + "FROM DB_CHAINS CHA  "
                + "INNER JOIN DB_POTENTIALS DP ON CHA.POTENTIAL_ID = DP.POTENTIAL_ID "
                + "INNER JOIN DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID = DP.SUB_SEGMENT_ID  "
                + "INNER JOIN DB_SEGMENTS SE ON SE.SEGMENT_ID = SS.SEGMENT_ID  "
                + "INNER JOIN DB_SUB_CHANNELS SUBCHA ON SUBCHA.SUB_CHANNEL_ID = SE.SUB_CHANNEL_ID  "
                + "INNER JOIN DB_CHANNELS CHAN ON CHAN.CHANNEL_ID = SUBCHA.CHANNEL_ID "
                + "INNER JOIN DB_3PARTY C3P ON C3P.DB_3PARTY_ID = CHA.DB_3PARTY_ID "
                + "WHERE CHA.IS_ACTIVE = 1 "
                + "AND CHA.CHAIN_ID > 67914 ";

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
            dto.setFlagCustInv(ob[18] != null ? ob[18].toString() : "");
            dto.setFlagCustDeliv(ob[18] != null ? (ob[18].toString().equals("1") ? "-1" : "") : "");
            dto.setFlagCustSale(ob[18] != null ? (ob[18].toString().equals("1") ? "-1" : "") : "");
            dto.setCodCustConc(ob[10] == null ? "" : ob[10] + "");
            dto.setFlagAnn(ob[14] == null ? "" : (ob[14].toString().equals("A") ? "1" : "0"));
            dto.setzStoreNumber("");
            dto.setZiln(ob[17] != null ? ob[17].toString() : "");
            dto.setzDistributor(ob[15] == null ? "" : ob[15] + "");
            dto.setPrimaryOcs(ob[11] == null ? "" : ob[11] + "");
            dto.setSecondaryOcs(ob[12] == null ? "" : ob[12] + "");
            dto.setRegionalSales("");
            dto.setManagerSales("");
            dto.setTerritoryMmanager("");
            dto.setSalesRepDis("");//debe ir en blanco
            dto.setSalesRepRole("");//debe ir en blanco
            dto.setLastDateSale("");//debe ir en blanco
            dto.setRtcStatus(ob[14] == null ? "" : (ob[14].toString().equals("A") ? "RTC01" : "RTC02"));
            dto.setBattleground(ob[16] == null ? "" : ob[16] + "");
            dto.setFlagDistributor(ob[18] != null ? ob[18].toString() : "");
            dto.setFlagChain(ob[18] != null ? (ob[18].toString().equals("0") ? "1" : "0") : "");
            listDto.add(dto);
        }
        return listDto;
    }

}
