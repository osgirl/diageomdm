/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.athenaout.bean;

import com.diageo.athenaout.dto.T041Dto;
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
public class FileT41 {

    @PersistenceContext(unitName = "AthenaPU")
    private EntityManager em;

    public List<T041Dto> findOutlets() {
        List<T041Dto> list = new ArrayList<>();
        String sql = "SELECT  "
                + " OUTLET_ID,  "
                + " NVL(TMC,KAM) AS TMC,  "
                + " CPA,  "
                + " ADMIN  "
                + "FROM TMP_OUTLET_USER";
//        String sql = "SELECT OU.OUTLET_ID,TMC.NAME_USER AS TMC, "
//                + "CPA.NAME_USER AS CPA,OU.ADMIN_3PARTY "
//                + "FROM ( "
//                + "  SELECT O.OUTLET_ID, OPAR.DB_3PARTY_ID, SCH.CHANNEL_ID,P3.ADMIN_3PARTY "
//                + "  FROM DIAGEO_BUSINESS.DB_OUTLETS O "
//                + "  INNER JOIN DIAGEO_BUSINESS.DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID=O.SUB_SEGMENT_ID "
//                + "  INNER JOIN DIAGEO_BUSINESS.DB_SEGMENTS S ON S.SEGMENT_ID=SS.SEGMENT_ID "
//                + "  INNER JOIN DIAGEO_BUSINESS.DB_SUB_CHANNELS SCH ON SCH.SUB_CHANNEL_ID=S.SUB_CHANNEL_ID "
//                + "  LEFT OUTER JOIN DIAGEO_BUSINESS.DB_OUTLETS_3PARTY OPAR ON OPAR.OUTLET_ID=O.OUTLET_ID "
//                + "  LEFT JOIN DIAGEO_BUSINESS.DB_3PARTY P3 ON P3.DB_3PARTY_ID = OPAR.DB_3PARTY_ID "
//                + "  WHERE O.IS_FATHER='1'  "
//                + "  ) OU "
//                + "LEFT OUTER JOIN "
//                + "  (Select PS.USER_ID, PS.DB_3PARTY_ID, PS.CHANNEL_ID,DU.NAME_USER||'.'||DU.LAST_NAME AS NAME_USER "
//                + "  From DIAGEO_WEB.DW_USERS DU "
//                + "    Inner Join DIAGEO_BUSINESS.DB_PERMISSION_SEGMENTS PS "
//                + "      On DU.USER_ID = PS.USER_ID "
//                + "  Where PROFILE_ID = 3) TMC "
//                + "ON TMC.DB_3PARTY_ID = OU.DB_3PARTY_ID "
//                + "AND TMC.CHANNEL_ID = OU.CHANNEL_ID "
//                + "LEFT OUTER JOIN "
//                + "  (Select PS.USER_ID, PS.DB_3PARTY_ID, PS.CHANNEL_ID,DU.NAME_USER||'.'||DU.LAST_NAME AS NAME_USER "
//                + "  From DIAGEO_WEB.DW_USERS DU "
//                + "    Inner Join DIAGEO_BUSINESS.DB_PERMISSION_SEGMENTS PS "
//                + "      On DU.USER_ID = PS.USER_ID "
//                + "  Where PROFILE_ID = 7) CPA "
//                + "ON CPA.DB_3PARTY_ID = OU.DB_3PARTY_ID "
//                + "AND CPA.CHANNEL_ID = OU.CHANNEL_ID ";
//        String sql = "SELECT "
//                + "  OU.OUTLET_ID, "
//                + "  TMC.NAME_USER AS TMC, "
//                + "  CPA.NAME_USER AS CPA, "
//                + "  OU.ADMIN_3PARTY "
//                + "FROM ( "
//                + "SELECT O.OUTLET_ID, OPAR.DB_3PARTY_ID, SCH.CHANNEL_ID,P3.ADMIN_3PARTY "
//                + "FROM DIAGEO_BUSINESS.DB_OUTLETS O "
//                + "INNER JOIN DIAGEO_BUSINESS.DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID=O.SUB_SEGMENT_ID "
//                + "INNER JOIN DIAGEO_BUSINESS.DB_SEGMENTS S ON S.SEGMENT_ID=SS.SEGMENT_ID "
//                + "INNER JOIN DIAGEO_BUSINESS.DB_SUB_CHANNELS SCH ON SCH.SUB_CHANNEL_ID=S.SUB_CHANNEL_ID "
//                + "LEFT OUTER JOIN DIAGEO_BUSINESS.DB_OUTLETS_3PARTY OPAR ON OPAR.OUTLET_ID=O.OUTLET_ID "
//                + "LEFT JOIN DIAGEO_BUSINESS.DB_3PARTY P3 ON P3.DB_3PARTY_ID = OPAR.DB_3PARTY_ID "
//                + "WHERE O.IS_FATHER='1' "
//                + "AND O.OUTLET_ID BETWEEN 1352 AND 1453 "
//                + ") OU "
//                + "LEFT OUTER JOIN "
//                + "(Select PS.USER_ID, PS.DB_3PARTY_ID, PS.CHANNEL_ID,DU.NAME_USER||'.'||DU.LAST_NAME AS NAME_USER "
//                + "From DIAGEO_WEB.DW_USERS DU "
//                + "Inner Join DIAGEO_BUSINESS.DB_PERMISSION_SEGMENTS PS "
//                + "On DU.USER_ID = PS.USER_ID "
//                + "Where PROFILE_ID = 3) TMC "
//                + "ON TMC.DB_3PARTY_ID = OU.DB_3PARTY_ID "
//                + "AND TMC.CHANNEL_ID = OU.CHANNEL_ID "
//                + "LEFT OUTER JOIN "
//                + "(Select PS.USER_ID, PS.DB_3PARTY_ID, PS.CHANNEL_ID,DU.NAME_USER||'.'||DU.LAST_NAME AS NAME_USER "
//                + "From DIAGEO_WEB.DW_USERS DU "
//                + "Inner Join DIAGEO_BUSINESS.DB_PERMISSION_SEGMENTS PS "
//                + "On DU.USER_ID = PS.USER_ID "
//                + "Where PROFILE_ID = 7) CPA "
//                + "ON CPA.DB_3PARTY_ID = OU.DB_3PARTY_ID "
//                + "AND CPA.CHANNEL_ID = OU.CHANNEL_ID";
//        String sql = "SELECT OU.OUTLET_ID,TMC.NAME_USER AS TMC, "
//                + "       CPA.NAME_USER AS CPA,OU.ADMIN_3PARTY "
//                + "       FROM ( "
//                + "         SELECT O.OUTLET_ID, OPAR.DB_3PARTY_ID, SCH.CHANNEL_ID,P3.ADMIN_3PARTY "
//                + "         FROM DIAGEO_BUSINESS.DB_OUTLETS O "
//                + "         INNER JOIN DIAGEO_BUSINESS.DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID=O.SUB_SEGMENT_ID "
//                + "         INNER JOIN DIAGEO_BUSINESS.DB_SEGMENTS S ON S.SEGMENT_ID=SS.SEGMENT_ID "
//                + "         INNER JOIN DIAGEO_BUSINESS.DB_SUB_CHANNELS SCH ON SCH.SUB_CHANNEL_ID=S.SUB_CHANNEL_ID "
//                + "         LEFT OUTER JOIN DIAGEO_BUSINESS.DB_OUTLETS_3PARTY OPAR ON OPAR.OUTLET_ID=O.OUTLET_ID "
//                + "         LEFT JOIN DIAGEO_BUSINESS.DB_3PARTY P3 ON P3.DB_3PARTY_ID = OPAR.DB_3PARTY_ID "
//                + "         WHERE O.IS_FATHER='1'  "
//                + "         ) OU "
//                + "       LEFT OUTER JOIN "
//                + "         (Select PS.USER_ID, PS.DB_3PARTY_ID, PS.CHANNEL_ID,DU.NAME_USER||'.'||DU.LAST_NAME AS NAME_USER "
//                + "         From DIAGEO_WEB.DW_USERS DU "
//                + " Inner Join DIAGEO_BUSINESS.DB_PERMISSION_SEGMENTS PS "
//                + "   On DU.USER_ID = PS.USER_ID "
//                + "         Where PROFILE_ID = 3) TMC "
//                + "       ON TMC.DB_3PARTY_ID = OU.DB_3PARTY_ID "
//                + "       AND TMC.CHANNEL_ID = OU.CHANNEL_ID "
//                + "       LEFT OUTER JOIN "
//                + "         (Select PS.USER_ID, PS.DB_3PARTY_ID, PS.CHANNEL_ID,DU.NAME_USER||'.'||DU.LAST_NAME AS NAME_USER "
//                + "         From DIAGEO_WEB.DW_USERS DU "
//                + " Inner Join DIAGEO_BUSINESS.DB_PERMISSION_SEGMENTS PS "
//                + "   On DU.USER_ID = PS.USER_ID "
//                + "         Where PROFILE_ID = 7) CPA "
//                + "       ON CPA.DB_3PARTY_ID = OU.DB_3PARTY_ID "
//                + "       AND CPA.CHANNEL_ID = OU.CHANNEL_ID"
//                + "       UNION ALL"
//                + "       SELECT OU.CHAIN_ID,KAM.NAME_USER AS KAM, "
//                + "       CPA.NAME_USER AS CPA,OU.ADMIN_3PARTY "
//                + "       FROM ( "
//                + "         SELECT O.CHAIN_ID, O.DB_3PARTY_ID, SCH.CHANNEL_ID,P3.ADMIN_3PARTY "
//                + "         FROM DIAGEO_BUSINESS.DB_CHAINS O "
//                + "         INNER JOIN DIAGEO_BUSINESS.DB_SUB_SEGMENTS SS ON SS.SUB_SEGMENT_ID=O.SUB_SEGMENT_ID "
//                + "         INNER JOIN DIAGEO_BUSINESS.DB_SEGMENTS S ON S.SEGMENT_ID=SS.SEGMENT_ID "
//                + "         INNER JOIN DIAGEO_BUSINESS.DB_SUB_CHANNELS SCH ON SCH.SUB_CHANNEL_ID=S.SUB_CHANNEL_ID          "
//                + "         LEFT JOIN DIAGEO_BUSINESS.DB_3PARTY P3 ON P3.DB_3PARTY_ID = O.DB_3PARTY_ID          "
//                + "         ) OU "
//                + "       LEFT OUTER JOIN "
//                + "         (Select PS.USER_ID, PS.DB_3PARTY_ID, PS.CHANNEL_ID,DU.NAME_USER||'.'||DU.LAST_NAME AS NAME_USER "
//                + "         From DIAGEO_WEB.DW_USERS DU "
//                + " Inner Join DIAGEO_BUSINESS.DB_PERMISSION_SEGMENTS PS "
//                + "   On DU.USER_ID = PS.USER_ID "
//                + "         Where PROFILE_ID = 5) KAM "
//                + "       ON KAM.DB_3PARTY_ID = OU.DB_3PARTY_ID "
//                + "       AND KAM.CHANNEL_ID = OU.CHANNEL_ID "
//                + "       LEFT OUTER JOIN "
//                + "         (Select PS.USER_ID, PS.DB_3PARTY_ID, PS.CHANNEL_ID,DU.NAME_USER||'.'||DU.LAST_NAME AS NAME_USER "
//                + "         From DIAGEO_WEB.DW_USERS DU "
//                + " Inner Join DIAGEO_BUSINESS.DB_PERMISSION_SEGMENTS PS "
//                + "   On DU.USER_ID = PS.USER_ID "
//                + "         Where PROFILE_ID = 7) CPA "
//                + "       ON CPA.DB_3PARTY_ID = OU.DB_3PARTY_ID "
//                + "       AND CPA.CHANNEL_ID = OU.CHANNEL_ID";
        Query query = em.createNativeQuery(sql);
        List listOutlet = query.getResultList();
        for (Object out : listOutlet) {
            T041Dto dto = new T041Dto();
            Object[] ob = (Object[]) out;
            dto.setCodParty(ob[0] + "");
            dto.setCodDiv("C001");
            dto.setCodUsr1(ob[1] + "");
            dto.setCodStatus("0");
            dto.setCodUser2(ob[2] + "");
            dto.setCodUser3(ob[3] + "");
            dto.setCodCustDeliv(ob[0] + "");
            list.add(dto);
        }
        return list;
    }
}
