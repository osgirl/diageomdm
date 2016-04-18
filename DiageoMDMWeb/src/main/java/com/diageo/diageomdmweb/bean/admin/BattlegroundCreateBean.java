/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.enums.StateEnum;
import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.BattleGroundBeanLocal;
import com.diageo.diageonegocio.entidades.Battleground;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author yovanoty126
 */
@Named(value = "battlegroundCreateBean")
@ViewScoped
public class BattlegroundCreateBean extends DiageoRootBean implements Serializable {

    @EJB
    protected BattleGroundBeanLocal battleGroundBeanLocal;
    private String nameBattle;
    private boolean state;

    /**
     * Creates a new instance of BattlegroundCreateBean
     */
    public BattlegroundCreateBean() {
    }

    public void create() {
        try {
            Battleground bat = new Battleground();
            bat.setEstado(isState() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
            bat.setNombre(getNameBattle());
            battleGroundBeanLocal.createBattleground(bat);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoNegocioException ex) {
            Logger.getLogger(BattlegroundCreateBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }

    /**
     * @return the nameBattle
     */
    public String getNameBattle() {
        return nameBattle;
    }

    /**
     * @param nameBattle the nameBattle to set
     */
    public void setNameBattle(String nameBattle) {
        this.nameBattle = nameBattle;
    }

    /**
     * @return the state
     */
    public boolean isState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(boolean state) {
        this.state = state;
    }

}
