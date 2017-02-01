/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.beans;

import com.diageo.admincontrollerweb.entities.DwRelationUsers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EDUARDO
 */
@Local
public interface RelationUserBeanLocal {

    public List<Integer> findByParentId(Integer parentId);

    public List<DwRelationUsers> findByUserIdAndParent(Integer userId, Integer parentId);

    public List<DwRelationUsers> findByParentObject(Integer parentId);

    public void createRelationUser(DwRelationUsers entity);

    public void deleteRelation(DwRelationUsers entity);

}
