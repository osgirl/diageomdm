/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.DwDocumentTypes;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class DocumentTypeBeanLocal extends WebTransaction<DwDocumentTypes>  {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<DwDocumentTypes> findAll() {
        return findAll(DwDocumentTypes.class);
    }

    public DwDocumentTypes findById(Integer id) {
        return (DwDocumentTypes) findById(DwDocumentTypes.class, id);
    }

}
