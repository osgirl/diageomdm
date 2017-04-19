/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.DwRelationUsers;
import com.diageo.admincontrollerweb.entities.DwUsers;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class RelationUserBeanLocal extends WebTransaction<DwRelationUsers>  {

    @EJB
    private UserBeanLocal userBeanLocal;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Integer> findByParentId(Integer parentId) {
        Query query = getEntityManager().createNamedQuery(DwRelationUsers.FIND_BY_PARENT).setParameter(1, parentId);
        Vector vector = (Vector) query.getResultList();
        List<Integer> listUserId = new ArrayList<>(vector);
        return listUserId;
    }

    public List<DwRelationUsers> findByUserIdAndParent(Integer userId, Integer parentId) {
        List<DwRelationUsers> list = super.findByNamedQuery(DwRelationUsers.class, DwRelationUsers.FIND_BY_USER_AND_PARENT, userId, parentId);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public List<DwRelationUsers> findByParentObject(Integer parentId) {
        List<DwRelationUsers> list = super.findByNamedQuery(DwRelationUsers.class, DwRelationUsers.FIND_BY_PARENT_OBJECT, parentId);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public void createRelationUser(DwRelationUsers entity) {
        try {
            super.create(entity);
        } catch (Exception e) {
            Logger.getLogger(RelationUserBeanLocal.class.getName()).log(Level.SEVERE,
                    "Not saved the relation {0} {1}", new Object[]{entity.getRelationUserPK().getUserId(), entity.getRelationUserPK().getUserParentId()});
        }
    }

    public void deleteRelation(DwRelationUsers entity) {
        try {
            String sql = "DELETE FROM DwRelationUsers r WHERE r.relationUserPK.userId = ?1 AND r.relationUserPK.userParentId = ?2";
            Query q = getEntityManager().createQuery(sql);
            q.setParameter(1, entity.getRelationUserPK().getUserId());
            q.setParameter(2, entity.getRelationUserPK().getUserParentId());
            q.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(RelationUserBeanLocal.class.getName()).log(Level.SEVERE,
                    "Not removed the relation {0} {1}", new Object[]{entity.getRelationUserPK().getUserId(), entity.getRelationUserPK().getUserParentId()});
        }
    }
}
