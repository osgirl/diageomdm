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
//        String sql = "SELECT   "
//                + "  OUTLET_ID,   "
//                + "  NVL(TMC,KAM) AS TMC,   "
//                + "  CPA,   "
//                + "  ADMIN   "
//                + " FROM TMP_OUTLET_USER";
        String sql = "SELECT  "
                + "  OUTLET_ID,  "
                + "  NVL(TMC,KAM) AS TMC,  "
                + "  CPA,  "
                + "  ADMIN  "
                + " FROM TMP_OUTLET_USER "
                + "WHERE OUTLET_ID IN (SELECT O.OUTLET_ID  "
                + "FROM DIAGEO_BUSINESS.DB_OUTLETS O "
                + "WHERE (TO_DATE(O.MODIFICATION_DATE,'DD-MM-YYYY') > TO_DATE('15-11-16','DD-MM-YYYY') "
                + "OR TO_DATE(O.CREATION_DATE,'DD-MM-YYYY') = TO_DATE('21-11-16','DD-MM-YYYY')) "
                + "AND O.STATUS_OUTLET = 'A' "
                + "AND O.IS_FATHER = '1' "
                + "UNION ALL "
                + "SELECT CHA.CHAIN_ID "
                + "FROM DB_CHAINS CHA  "
                + "WHERE CHA.IS_ACTIVE = 1 "
                + "AND (TO_DATE(CHA.MODIFICATION_DATE,'DD-MM-YYYY') > TO_DATE('15-11-16','DD-MM-YYYY') "
                + "OR TO_DATE(CHA.CREATION_DATE,'DD-MM-YYYY') = TO_DATE('21-11-16','DD-MM-YYYY'))) ";
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
