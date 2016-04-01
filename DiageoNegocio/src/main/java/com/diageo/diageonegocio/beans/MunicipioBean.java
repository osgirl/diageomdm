/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Municipio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class MunicipioBean extends TransaccionesNegocio<Municipio> implements MunicipioBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Municipio consultarId(Integer id) {
        return (Municipio) super.consultarPorId(Municipio.class, id);
    }

    @Override
    public List<Municipio> consultarPorDepartamentoId(Integer id) {
        List<Municipio> lista = super.consultarPorNamedQuery(Municipio.class, Municipio.FIND_BY_DEPTO, id);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }
}
