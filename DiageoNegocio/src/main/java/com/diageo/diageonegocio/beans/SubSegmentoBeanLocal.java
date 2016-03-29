/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.SubSegmento;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface SubSegmentoBeanLocal {

    public SubSegmento crearSubSegmento(SubSegmento subSegmento) throws DiageoNegocioException;

    public SubSegmento modificarSubSegmento(SubSegmento subSegmento) throws DiageoNegocioException;

    public List<SubSegmento> consultarTodosSubSegmentos();

    public SubSegmento consultarId(Integer id) throws DiageoNegocioException;
    
}
