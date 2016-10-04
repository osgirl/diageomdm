/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.athenaout.bean;

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
public class FileT042 {

    @PersistenceContext(unitName = "AthenaPU")
    private EntityManager em;

    public List<T042PartyAddrDto> findOutlets() {
        List<T042PartyAddrDto> listDto = new ArrayList<>();
        String sql = "SELECT  "
                + "      O.OUTLET_ID AS codParty, "
                + "      O.ADDRESS AS desAddre_1, "
                + "      CI.NAME_TOWN AS desLoc_1,  "
                + "      NVL(PH.NUMBER_PHONE,CEL.NUMBER_PHONE) AS numPhone_1, "
                + "      PR.DISTRI_1 AS codArea, "
                + "      DE.DISTRI_1 AS codZone, "
                + "      CO.DISTRI_1 AS codNation,  "
                + "      O.EMAIL AS email_1, "
                + "      O.WEBSITE AS website_1, "
                + "      O.LATITUDE, "
                + "      O.LONGITUDE "
                + "FROM DIAGEO_BUSINESS.DB_OUTLETS O  "
                + "LEFT JOIN DIAGEO_BUSINESS.DB_TOWNS CI ON CI.TOWN_ID = O.TOWN_ID  "
                + "LEFT JOIN ( "
                + "           SELECT DOP.OUTLET_ID, DOP.PHONE_ID, DP.NUMBER_PHONE  "
                + "           FROM DIAGEO_BUSINESS.DB_OUTLETS_PHONES DOP  "
                + "           INNER JOIN DIAGEO_BUSINESS.DB_PHONES DP ON DOP.PHONE_ID = DP.PHONE_ID  "
                + "           WHERE DP.TYPE_PHONE_ID = 1 "
                + "           ) PH "
                + "          ON PH.OUTLET_ID = O.OUTLET_ID  "
                + "LEFT JOIN ( "
                + "           SELECT DOP.OUTLET_ID, DOP.PHONE_ID, DP.NUMBER_PHONE  "
                + "           FROM DIAGEO_BUSINESS.DB_OUTLETS_PHONES DOP  "
                + "           INNER JOIN DIAGEO_BUSINESS.DB_PHONES DP ON DOP.PHONE_ID = DP.PHONE_ID  "
                + "           WHERE DP.TYPE_PHONE_ID = 4 "
                + "           ) CEL "
                + "          ON CEL.OUTLET_ID = O.OUTLET_ID  "
                + "LEFT JOIN DIAGEO_BUSINESS.DB_OUTLETS_3PARTY OP ON OP.OUTLET_ID = O.OUTLET_ID  "
                + "LEFT JOIN DIAGEO_BUSINESS.DB_3PARTY PA ON PA.DB_3PARTY_ID = OP.DB_3PARTY_ID  "
                + "LEFT JOIN DIAGEO_BUSINESS.DB_3PARTY_REGIONAL PR ON PR.DB_3PARTY_REGIONAL_ID = PA.DB_3PARTY_REGIONAL_ID  "
                + "LEFT JOIN DIAGEO_BUSINESS.DB_DEPARTAMENTS DE ON DE.DEPARTAMENT_ID = CI.DEPARTAMENT_ID  "
                + "LEFT JOIN DIAGEO_BUSINESS.DB_COUNTRIES CO ON CO.COUNTRY_ID = DE.COUNTRY_ID  "
                + "WHERE O.IS_FATHER = '1' "
                + "AND O.STATUS_OUTLET = 'A' "
                + "AND O.OUTLET_ID > 78180 "
                + "UNION ALL  "
                + "SELECT "
                + "  CHAN.CHAIN_ID, "
                + "  CHAN.ADDRESS, "
                + "  TOWN.DISTRI_1,  "
                + "  NVL(PH.NUMBER_PHONE,CEL.NUMBER_PHONE) NUMBER_PHONE, "
                + "  RE.DISTRI_1, "
                + "  DE.DISTRI_1, "
                + "  'CO' AS NATION, "
                + "  NULL AS EMAIL, "
                + "  NULL AS WEBSITE, "
                + "  CHAN.LATITUDE, "
                + "  CHAN.LONGITUDE "
                + "FROM DB_CHAINS CHAN  "
                + "LEFT JOIN DB_TOWNS TOWN ON TOWN.TOWN_ID=CHAN.TOWN_ID "
                + "LEFT JOIN ( "
                + "           SELECT DOP.CHAIN_ID, DP.PHONE_ID, DP.NUMBER_PHONE  "
                + "           FROM DB_CHAINS_PHONES DOP  "
                + "           INNER JOIN DIAGEO_BUSINESS.DB_PHONES DP ON DOP.PHONE_ID = DP.PHONE_ID  "
                + "           WHERE DP.TYPE_PHONE_ID = 1 "
                + "           ) PH "
                + "           ON PH.CHAIN_ID = CHAN.CHAIN_ID "
                + "LEFT JOIN ( "
                + "           SELECT DOP.OUTLET_ID, DOP.PHONE_ID, DP.NUMBER_PHONE  "
                + "           FROM DIAGEO_BUSINESS.DB_OUTLETS_PHONES DOP  "
                + "           INNER JOIN DIAGEO_BUSINESS.DB_PHONES DP ON DOP.PHONE_ID = DP.PHONE_ID  "
                + "           WHERE DP.TYPE_PHONE_ID = 4 "
                + "           ) CEL "
                + "          ON CEL.OUTLET_ID = CHAN.CHAIN_ID  "
                + " LEFT JOIN DB_DEPARTAMENTS DE ON DE.DEPARTAMENT_ID = TOWN.DEPARTAMENT_ID  "
                + " LEFT JOIN DB_3PARTY PA ON PA.DB_3PARTY_ID = CHAN.DB_3PARTY_ID  "
                + " LEFT JOIN DB_3PARTY_REGIONAL RE ON RE.DB_3PARTY_REGIONAL_ID = PA.DB_3PARTY_REGIONAL_ID "
                + " WHERE CHAN.IS_ACTIVE = 1 "
                + " AND CHAN.CHAIN_ID > 67914 ";
//        String sql = "SELECT  "
//                + "      O.OUTLET_ID AS codParty, "
//                + "      O.ADDRESS AS desAddre_1, "
//                + "      CI.NAME_TOWN AS desLoc_1,  "
//                + "      NVL(PH.NUMBER_PHONE,CEL.NUMBER_PHONE) AS numPhone_1, "
//                + "      PR.DISTRI_1 AS codArea, "
//                + "      DE.DISTRI_1 AS codZone, "
//                + "      CO.DISTRI_1 AS codNation,  "
//                + "      O.EMAIL AS email_1, "
//                + "      O.WEBSITE AS website_1,"
//                + "      O.LATITUDE,"
//                + "      O.LONGITUDE "
//                + "FROM DIAGEO_BUSINESS.DB_OUTLETS O  "
//                + "LEFT JOIN DIAGEO_BUSINESS.DB_TOWNS CI ON CI.TOWN_ID = O.TOWN_ID  "
//                + "LEFT JOIN ( "
//                + "           SELECT DOP.OUTLET_ID, DOP.PHONE_ID, DP.NUMBER_PHONE  "
//                + "           FROM DIAGEO_BUSINESS.DB_OUTLETS_PHONES DOP  "
//                + "           INNER JOIN DIAGEO_BUSINESS.DB_PHONES DP ON DOP.PHONE_ID = DP.PHONE_ID  "
//                + "           WHERE DP.TYPE_PHONE_ID = 1"
//                + "           ) PH"
//                + "          ON PH.OUTLET_ID = O.OUTLET_ID  "
//                + "LEFT JOIN ( "
//                + "           SELECT DOP.OUTLET_ID, DOP.PHONE_ID, DP.NUMBER_PHONE  "
//                + "           FROM DIAGEO_BUSINESS.DB_OUTLETS_PHONES DOP  "
//                + "           INNER JOIN DIAGEO_BUSINESS.DB_PHONES DP ON DOP.PHONE_ID = DP.PHONE_ID  "
//                + "           WHERE DP.TYPE_PHONE_ID = 4"
//                + "           ) CEL"
//                + "          ON CEL.OUTLET_ID = O.OUTLET_ID  "
//                + "LEFT JOIN DIAGEO_BUSINESS.DB_OUTLETS_3PARTY OP ON OP.OUTLET_ID = O.OUTLET_ID  "
//                + "LEFT JOIN DIAGEO_BUSINESS.DB_3PARTY PA ON PA.DB_3PARTY_ID = OP.DB_3PARTY_ID  "
//                + "LEFT JOIN DIAGEO_BUSINESS.DB_3PARTY_REGIONAL PR ON PR.DB_3PARTY_REGIONAL_ID = PA.DB_3PARTY_REGIONAL_ID  "
//                + "LEFT JOIN DIAGEO_BUSINESS.DB_DEPARTAMENTS DE ON DE.DEPARTAMENT_ID = CI.DEPARTAMENT_ID  "
//                + "LEFT JOIN DIAGEO_BUSINESS.DB_COUNTRIES CO ON CO.COUNTRY_ID = DE.COUNTRY_ID  "
//                + "WHERE O.IS_FATHER = '1' "
//                + "AND O.STATUS_OUTLET = 'A' "
//                + "                     "
//                + "UNION ALL  "
//                + "                   "
//                + "SELECT "
//                + "  CHAN.CHAIN_ID, "
//                + "  CHAN.ADDRESS, "
//                + "  TOWN.DISTRI_1,  "
//                + "  NVL(PH.NUMBER_PHONE,CEL.NUMBER_PHONE) NUMBER_PHONE, "
//                + "  RE.DISTRI_1,"
//                + "  DE.DISTRI_1,"
//                + "  'CO' AS NATION, "
//                + "  NULL AS EMAIL,"
//                + "  NULL AS WEBSITE,"
//                + "  CHAN.LATITUDE,"
//                + "  CHAN.LONGITUDE "
//                + "FROM DB_CHAINS CHAN  "
//                + "LEFT JOIN DB_TOWNS TOWN ON TOWN.TOWN_ID=CHAN.TOWN_ID "
//                + "LEFT JOIN ( "
//                + "           SELECT DOP.CHAIN_ID, DP.PHONE_ID, DP.NUMBER_PHONE  "
//                + "           FROM DB_CHAINS_PHONES DOP  "
//                + "           INNER JOIN DIAGEO_BUSINESS.DB_PHONES DP ON DOP.PHONE_ID = DP.PHONE_ID  "
//                + "           WHERE DP.TYPE_PHONE_ID = 1"
//                + "           ) PH"
//                + "           ON PH.CHAIN_ID = CHAN.CHAIN_ID "
//                + "LEFT JOIN ( "
//                + "           SELECT DOP.OUTLET_ID, DOP.PHONE_ID, DP.NUMBER_PHONE  "
//                + "           FROM DIAGEO_BUSINESS.DB_OUTLETS_PHONES DOP  "
//                + "           INNER JOIN DIAGEO_BUSINESS.DB_PHONES DP ON DOP.PHONE_ID = DP.PHONE_ID  "
//                + "           WHERE DP.TYPE_PHONE_ID = 4"
//                + "           ) CEL"
//                + "          ON CEL.OUTLET_ID = CHAN.CHAIN_ID  "
//                + " LEFT JOIN DB_DEPARTAMENTS DE ON DE.DEPARTAMENT_ID = TOWN.DEPARTAMENT_ID  "
//                + " LEFT JOIN DB_3PARTY PA ON PA.DB_3PARTY_ID = CHAN.DB_3PARTY_ID  "
//                + " LEFT JOIN DB_3PARTY_REGIONAL RE ON RE.DB_3PARTY_REGIONAL_ID = PA.DB_3PARTY_REGIONAL_ID                          "
//                + " WHERE CHAN.IS_ACTIVE = 1";
        System.out.println(sql);
        Query query = em.createNativeQuery(sql);
        List list = query.getResultList();
        for (Object obj : list) {
            T042PartyAddrDto dto = new T042PartyAddrDto();
            Object[] arrayObj = (Object[]) obj;
            dto.setCodParty(arrayObj[0] == null ? "" : arrayObj[0] + "");
            dto.setDesAddre_1(arrayObj[1] == null ? "" : arrayObj[1] + "");
            dto.setDesLoc_1(arrayObj[2] == null ? "" : arrayObj[2] + "");
            dto.setNumPhone_1(arrayObj[3] == null ? "" : arrayObj[3] + "");
            dto.setCodArea(arrayObj[4] == null ? "" : arrayObj[4] + "");
            dto.setCodZone(arrayObj[5] == null ? "" : arrayObj[5] + "");
            dto.setCodNation("CO");
            dto.setEmail_1(arrayObj[7] == null ? "" : arrayObj[7] + "");
            dto.setWebsite_1(arrayObj[8] == null ? "" : arrayObj[8] + "");
            dto.setvLatitude(arrayObj[9] == null ? null : Double.parseDouble(arrayObj[9] + ""));
            dto.setvLongitude(arrayObj[10] == null ? null : Double.parseDouble(arrayObj[10] + ""));
            listDto.add(dto);
        }
        return listDto;
    }

}
