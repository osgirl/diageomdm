/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Segmento;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class SegmentoBean extends BusinessTransaction<Segmento> implements SegmentoBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Segmento crearSegmento(Segmento segmento) throws DiageoNegocioException {
        try {
            segmento = super.create(segmento);
            return segmento;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

    @Override
    public Segmento modificarSegmento(Segmento segmento) throws DiageoNegocioException {
        try {
            segmento = (Segmento) super.update(segmento);
            return segmento;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

    @Override
    public List<Segmento> consultarTodosSegmentos() {
        List<Segmento> lista = super.searchAll(Segmento.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    @Override
    public List<Segmento> consultarPorSubChannel(Integer id) {
        return super.searchByNamedQuery(Segmento.class, Segmento.FIND_BY_SUBCHANNEL, id);
    }

    @Override
    public Segmento consultarId(Integer id) throws DiageoNegocioException {
        try {
            Segmento seg = (Segmento) super.searchById(Segmento.class, id);
            return seg;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }
}
