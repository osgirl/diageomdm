/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Battleground;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class BattleGroundBean extends BusinessTransaction<Battleground> implements BattleGroundBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Battleground creaerBattlegroun(Battleground b) throws DiageoNegocioException {
        try {
            b = super.create(b);
            return b;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

    @Override
    public Battleground ModificarBattlegroun(Battleground b) throws DiageoNegocioException {
        try {
            b = (Battleground) super.update(b);
            return b;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

    @Override
    public List<Battleground> consultarTodosBattlegroun() {
        List<Battleground> lista = super.searchAll(Battleground.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    @Override
    public Battleground consultarId(Integer id) throws DiageoNegocioException {
        try {
            Battleground b = (Battleground) super.searchById(Battleground.class, id);
            return b;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }
}
