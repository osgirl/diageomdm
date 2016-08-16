/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.diageo.diageonegocio.beans.reports;

import com.diageo.diageonegocio.dto.ReportDto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface CasuistryBeanLocal {

    public List<ReportDto> casuistry_1();
    
}
