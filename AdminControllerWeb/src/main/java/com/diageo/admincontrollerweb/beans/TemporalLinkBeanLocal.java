/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.DwTemporalLink;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface TemporalLinkBeanLocal {

    public void createTemporal(DwTemporalLink entity);

    public List<DwTemporalLink> findbyEmail(String email);

    public DwTemporalLink findByToken(String token);
    
}
