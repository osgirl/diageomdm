/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.admin;

import com.diageo.admincontrollerweb.enums.StateEnum;
import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageonegocio.entidades.Battleground;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author yovanoty126
 */
@Named(value = "battlegroundSearchBean")
@ViewScoped
public class BattlegroundSearchBean extends BattlegroundCreateBean {

    private List<Battleground> listBattle;
    private boolean seeDetail;
    private Battleground battlegroundSelect;

    /**
     * Creates a new instance of BattlegroundSearchBean
     */
    public BattlegroundSearchBean() {
    }

    @PostConstruct
    public void init() {
        setListBattle(battleGroundBeanLocal.consultarTodosBattlegroun());
        setSeeDetail(Boolean.TRUE);
    }

    public void detailBattleground(Battleground bat) {
        setSeeDetail(Boolean.FALSE);
        setBattlegroundSelect(bat);
        setNameBattle(bat.getNombre());
        setState(bat.getEstado().equals(StateEnum.ACTIVE.getState()));
    }

    public void update() {
        try {
            getBattlegroundSelect().setEstado(isState() ? StateEnum.ACTIVE.getState() : StateEnum.INACTIVE.getState());
            getBattlegroundSelect().setNombre(getNameBattle());
            battleGroundBeanLocal.ModificarBattlegroun(battlegroundSelect);
            showInfoMessage(capturarValor("sis_datos_guardados_exito"));
        } catch (DiageoNegocioException ex) {
            Logger.getLogger(BattlegroundCreateBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage(capturarValor("sis_datos_guardados_sin_exito"));
        }
    }
    
    public void back(){
        setSeeDetail(Boolean.TRUE);
    }

    public List<Battleground> getListBattle() {
        return listBattle;
    }

    public void setListBattle(List<Battleground> listBattle) {
        this.listBattle = listBattle;
    }

    /**
     * @return the seeDetail
     */
    public boolean isSeeDetail() {
        return seeDetail;
    }

    /**
     * @param seeDetail the seeDetail to set
     */
    public void setSeeDetail(boolean seeDetail) {
        this.seeDetail = seeDetail;
    }

    /**
     * @return the battlegroundSelect
     */
    public Battleground getBattlegroundSelect() {
        return battlegroundSelect;
    }

    /**
     * @param battlegroundSelect the battlegroundSelect to set
     */
    public void setBattlegroundSelect(Battleground battlegroundSelect) {
        this.battlegroundSelect = battlegroundSelect;
    }

}
