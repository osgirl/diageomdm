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
        String sql = "SELECT o.OUTLET_ID AS codParty,o.ADDRESS AS desAddre_1,ci.NAME_TOWN AS desLoc_1, "
                + "                pho.NUMBER_PHONE AS numPhone_1,de.DISTRI_1 AS codArea,pr.NAME_REGIONAL AS codZone,co.DISTRI_1 AS codNation, "
                + "                o.EMAIL AS email_1,o.WEBSITE AS website_1 "
                + "                FROM DIAGEO_BUSINESS.DB_OUTLETS o "
                + "                LEFT JOIN DIAGEO_BUSINESS.DB_TOWNS ci ON ci.TOWN_ID=o.TOWN_ID "
                + "                LEFT JOIN DIAGEO_BUSINESS.DB_OUTLETS_PHONES ph ON ph.OUTLET_ID=o.OUTLET_ID "
                + "                LEFT JOIN DIAGEO_BUSINESS.DB_PHONES pho ON pho.PHONE_ID=ph.PHONE_ID "
                + "                LEFT JOIN DIAGEO_BUSINESS.DB_OUTLETS_3PARTY op ON op.OUTLET_ID=o.OUTLET_ID "
                + "                LEFT JOIN DIAGEO_BUSINESS.DB_3PARTY pa ON pa.DB_3PARTY_ID=op.DB_3PARTY_ID "
                + "                LEFT JOIN DIAGEO_BUSINESS.DB_3PARTY_REGIONAL pr ON pr.DB_3PARTY_REGIONAL_ID=pa.DB_3PARTY_REGIONAL_ID "
                + "                LEFT JOIN DIAGEO_BUSINESS.DB_DEPARTAMENTS de ON de.DEPARTAMENT_ID=ci.DEPARTAMENT_ID "
                + "                LEFT JOIN DIAGEO_BUSINESS.DB_COUNTRIES co ON co.COUNTRY_ID=de.COUNTRY_ID";
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
            listDto.add(dto);
        }
        return listDto;
    }

}
