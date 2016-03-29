/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author yovanoty126
 * @param <T>
 */
public class TransaccionesNegocio<T> {

    @PersistenceContext(unitName = "DiageoNegocioUP")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return this.em;
    }

    public Object consultarPorId(Class t, Object id) {
        Object o = getEntityManager().find(t, id);
        return o;
    }
    
    public Object modificar(Object obj){
        obj=getEntityManager().merge(obj);
        getEntityManager().flush();
        return obj;
    }

    public T crear(T entidad) {
        getEntityManager().persist(entidad);
        getEntityManager().flush();
        return entidad;
    }

    public void eliminar(Object entidad) {
        getEntityManager().remove(entidad);
    }

    public List<T> consultarPorNamedQuery(Class tipo, String namedQuery, Object... parametros) {
        Query query = getEntityManager().createNamedQuery(namedQuery, tipo);
        for (int i = 0; i < parametros.length; i++) {
            query.setParameter(i + 1, parametros[i]);
        }
        return query.getResultList();
    }

    public List<T> consultarTodo(Class tipo) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteria = cb.createQuery(tipo);
        criteria.from(tipo);
        Query query = getEntityManager().createQuery(criteria);
        return query.getResultList();
    }

}
