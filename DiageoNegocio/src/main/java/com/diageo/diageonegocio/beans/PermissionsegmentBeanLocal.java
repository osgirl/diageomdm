/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.beans;

import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import com.diageo.diageonegocio.jdbc.ConecctionJDBC;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class PermissionsegmentBeanLocal extends BusinessTransaction<DbPermissionSegments> {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<DbPermissionSegments> createPermissionSegmentList(List<DbPermissionSegments> entity) throws DiageoBusinessException {
        List<DbPermissionSegments> list = new ArrayList<>();
        try {
            for (DbPermissionSegments entity1 : entity) {
                DbPermissionSegments obj = (DbPermissionSegments) super.update(entity1);
                list.add(obj);
            }
        } catch (Exception e) {
            throw new DiageoBusinessException(e, e.getMessage());
        }
        //insertOutletUser(list);
        return list;
    }

    public void insertOutletUser(List<DbPermissionSegments> list) {
        try (Connection con = ConecctionJDBC.conexionSQLServer()) {
            for (DbPermissionSegments id : list) {
                ConecctionJDBC.callStoreInsertOutletsUser(con, id.getPermissionSegment());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermissionsegmentBeanLocal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<DbPermissionSegments> findByUser(Integer idUser) {
        List<DbPermissionSegments> list = super.searchByNamedQuery(DbPermissionSegments.class, DbPermissionSegments.FIND_BY_USER, idUser);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public Set<DbPermissionSegments> findByUserDistributor(Integer idUser, Integer distributor) {
        List<DbPermissionSegments> list = super.searchByNamedQuery(DbPermissionSegments.class, DbPermissionSegments.FIND_BY_USER_DISTRIBUTOR, idUser, distributor);
        Set<DbPermissionSegments> listSet = new HashSet<>();
        for (DbPermissionSegments list1 : list) {
            listSet.add(list1);
        }
        if (list == null) {
            return new HashSet<>();
        }
        return listSet;
    }

    public void remove(DbPermissionSegments ps) {
        super.delete(ps);
    }

    public List<Integer> findByUser3Party(List<Integer> users, List<Integer> db3Party) {
        List<Integer> list = super.getEntityManager().createNamedQuery(DbPermissionSegments.FIND_BY_USERS_3PARTY_LIST).
                setParameter(1, users).setParameter(2, db3Party).getResultList();
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }
}
