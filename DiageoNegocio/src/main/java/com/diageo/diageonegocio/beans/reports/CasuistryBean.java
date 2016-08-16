/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans.reports;

import com.diageo.diageonegocio.beans.BusinessTransaction;
import com.diageo.diageonegocio.dto.ReportDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class CasuistryBean extends BusinessTransaction implements CasuistryBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<ReportDto> casuistry_1() {
        String sql = "select * from table (DIAGEO_BUSINESS.CASUISTICA1)";
        Query query = getEntityManager().createNativeQuery(sql);
        List list = query.getResultList();
        List<ReportDto> listDto = new ArrayList<>();
        for (Object object : list) {
            Object[] obj = (Object[]) object;
            ReportDto dto = new ReportDto();
            dto.setId((BigDecimal) obj[0]);
            dto.setIdFather(obj[1] == null ? null : (BigDecimal) obj[1]);
            dto.setKiernanId(obj[2] == null ? "" : obj[2].toString());
            dto.setNumberPDV(obj[3] == null ? "" : obj[3].toString());
            dto.setNit(obj[4] == null ? "" : obj[4].toString());
            dto.setOutletName(obj[5] == null ? "" : obj[5].toString());
            dto.setSubSegment(obj[6] == null ? "" : obj[6].toString());
            dto.setPotential(obj[7] == null ? "" : obj[7].toString());
            dto.setSource(obj[8] == null ? "" : obj[8].toString());
            listDto.add(dto);
        }
        return listDto;
    }
}
