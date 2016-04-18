/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.TipoDoc;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class DocumentTypeBean extends WebTransaction<TipoDoc> implements DocumentTypeBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<TipoDoc> findAll() {
        return findAll(TipoDoc.class);
    }

    @Override
    public TipoDoc findById(Integer id) {
        return (TipoDoc) findById(TipoDoc.class, id);
    }

}
