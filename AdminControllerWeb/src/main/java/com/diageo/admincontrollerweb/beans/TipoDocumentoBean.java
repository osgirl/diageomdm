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
public class TipoDocumentoBean extends WebTransaction<TipoDoc> implements TipoDocumentoBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<TipoDoc> capturarListaDocumento() {
        return consultarTodo(TipoDoc.class);
    }

    @Override
    public TipoDoc consultarId(Integer id) {
        return (TipoDoc) consultarPorId(TipoDoc.class, id);
    }

}
