/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Segmento;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface SegmentoBeanLocal {

    public Segmento crearSegmento(Segmento segmento) throws DiageoNegocioException;

    public Segmento modificarSegmento(Segmento segmento) throws DiageoNegocioException;

    public List<Segmento> consultarTodosSegmentos();

    public Segmento consultarId(Integer id) throws DiageoNegocioException;

    public List<Segmento> consultarPorSubChannel(Integer id);
    
}
