/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.SubSegmento;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class SubSegmentoBean extends TransaccionesNegocio<SubSegmento>implements SubSegmentoBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public SubSegmento crearSubSegmento(SubSegmento subSegmento) throws DiageoNegocioException{
        try {
            subSegmento = super.crear(subSegmento);
            return subSegmento;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }
    
    @Override
    public SubSegmento modificarSubSegmento(SubSegmento subSegmento) throws DiageoNegocioException{
        try {
            subSegmento = (SubSegmento) super.modificar(subSegmento);
            return subSegmento;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }
    
    @Override
    public List<SubSegmento> consultarTodosSubSegmentos(){
        List<SubSegmento> lista = super.consultarTodo(SubSegmento.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }
    
    @Override
    public SubSegmento consultarId(Integer id) throws DiageoNegocioException{
        try {
            SubSegmento subSegmento = (SubSegmento) super.consultarPorId(SubSegmento.class, id);
            return subSegmento;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }
}
