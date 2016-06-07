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
        String sql = "SELECT KIERNAN_ID FROM DB_OUTLETS";
        Query query = em.createNativeQuery(sql);
        List listOutlet = query.getResultList();
        for (Object out : listOutlet) {
            T041Dto dto = new T041Dto();
            dto.setCodParty(out + "");
            dto.setCodDiv("C001");
            dto.setCodUsr1("YASMIN.ROMERO");
            dto.setCodStatus("0");
            dto.setCodUser2("MARICELA.FLORES");
            dto.setCodUser3("RICARDO.RIVERA");
            dto.setCodCustDeliv(out + "");
            list.add(dto);
        }
        return list;
    }
}
