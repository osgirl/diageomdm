/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.Outlet;
import com.diageo.diageonegocio.entidades.Persona;
import com.diageo.diageonegocio.exceptions.DiageoNegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author yovanoty126
 */
@Stateless
public class OutletBean extends TransaccionesNegocio<Outlet> implements OutletBeanLocal {
    
    @EJB
    private PersonaBeanLocal personaBeanLocal;
    @EJB
    private UbicacionBeanLocal ubicacionBeanLocal;
    @EJB
    private TelefonosBeanLocal telefonosBeanLocal;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Outlet crearOutlet(Outlet out) throws DiageoNegocioException {
        try {
            out.setPropietario(personaBeanLocal.crearPersona(out.getPropietario()));
            out.setIdubicacion(ubicacionBeanLocal.crearUbicacion(out.getIdubicacion()));
            out.setTelefonosList(telefonosBeanLocal.crearTelefonos(out.getTelefonosList()));
            out = super.crear(out);
            return out;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

    @Override
    public Outlet modificarOutlet(Outlet outlet) throws DiageoNegocioException {
        try {
            outlet = (Outlet) super.modificar(outlet);
            return outlet;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

    @Override
    public List<Outlet> consultarTodosOutlets() {
        List<Outlet> lista = super.consultarTodo(Outlet.class);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    @Override
    public Outlet consultarId(Integer id) throws DiageoNegocioException {
        try {
            Outlet outlet = (Outlet) super.consultarPorId(Outlet.class, id);
            return outlet;
        } catch (Exception e) {
            throw new DiageoNegocioException(e, e.getMessage());
        }
    }

}
