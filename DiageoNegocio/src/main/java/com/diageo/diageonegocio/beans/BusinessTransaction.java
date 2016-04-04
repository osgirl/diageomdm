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
public class BusinessTransaction<T> {

    @PersistenceContext(unitName = "DiageoNegocioUP")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return this.em;
    }

    public Object searchById(Class t, Object id) {
        Object o = getEntityManager().find(t, id);
        return o;
    }
    
    public Object update(Object obj){
        obj=getEntityManager().merge(obj);
        getEntityManager().flush();
        return obj;
    }

    public T create(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        return entity;
    }

    public void delete(Object entity) {
        getEntityManager().remove(entity);
    }

    public List<T> searchByNamedQuery(Class type, String namedQuery, Object... parameters) {
        Query query = getEntityManager().createNamedQuery(namedQuery, type);
        for (int i = 0; i < parameters.length; i++) {
            query.setParameter(i + 1, parameters[i]);
        }
        return query.getResultList();
    }

    public List<T> searchAll(Class type) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteria = cb.createQuery(type);
        criteria.from(type);
        Query query = getEntityManager().createQuery(criteria);
        return query.getResultList();
    }

}
