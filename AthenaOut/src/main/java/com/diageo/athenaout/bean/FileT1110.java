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
        String sql = "SELECT KIERNAN_ID FROM DB_OUTLETS";
        Query query = em.createNativeQuery(sql);
        List listOutlet = query.getResultList();
        for (Object out : listOutlet) {
            T1110Dto dto = new T1110Dto();
            dto.setCodParty(out + "");
            dto.setCodCustDeliv(out + "");
            dto.setCodDiv("C001");
            dto.setDateFrom("1/01/2000");
            dto.setDateTo("1/01/2050");
            dto.setFlagPrimary("-1");
            dto.setFlagAnn("0");
            list.add(dto);
        }
        return list;
    }
}
