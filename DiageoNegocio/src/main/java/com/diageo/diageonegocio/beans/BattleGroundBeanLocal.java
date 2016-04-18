/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Battleground;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yovanoty126
 */
@Local
public interface BattleGroundBeanLocal {

    public Battleground createBattleground(Battleground b) throws DiageoNegocioException;

    public Battleground ModificarBattlegroun(Battleground b) throws DiageoNegocioException;

    public List<Battleground> consultarTodosBattlegroun();

    public Battleground consultarId(Integer id) throws DiageoNegocioException;
    
}
