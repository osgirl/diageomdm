/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.athenaout.bean;

import com.diageo.athenaout.dto.T1110Dto;
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
public class FileT1110 {

    @PersistenceContext(unitName = "AthenaPU")
    private EntityManager em;

    public List<T1110Dto> findOutlets() {
        List<T1110Dto> list = new ArrayList<>();
        String sql = "SELECT OUTLET_ID, NVL(O.OUTLET_ID_FATHER, O.OUTLET_ID) OUTLET_ID_PADRE "
                + "FROM DIAGEO_BUSINESS.DB_OUTLETS O  "
                + " WHERE O.IS_FATHER = '1'  "
                + " AND O.STATUS_OUTLET = 'A' "
                + " UNION ALL  "
                + " SELECT CHAIN_ID, CHAIN_ID PADRE "
                + "FROM DIAGEO_BUSINESS.DB_CHAINS "
                + " WHERE IS_ACTIVE = 1";
        Query query = em.createNativeQuery(sql);
        List listOutlet = query.getResultList();
        for (Object out : listOutlet) {
            T1110Dto dto = new T1110Dto();
            Object[] arrayObj = (Object[]) out;
            dto.setCodParty(arrayObj[0] + "");
            dto.setCodCustDeliv(arrayObj[1] + "");
            dto.setCodDiv("C001");
            dto.setDateFrom("07/1/2016");
            dto.setDateTo("06/30/2017");
            dto.setFlagPrimary("-1");
            dto.setFlagAnn("0");
            list.add(dto);
        }
        return list;
    }
}
