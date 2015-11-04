package org.spirit.model.dao.impl;

import org.springframework.stereotype.Repository;

import org.spirit.model.dao.RoleDao;
import org.spirit.model.dao.generic.LmsGenericDaoImpl;
import org.spirit.model.entity.Role;

@Repository
public class RoleDaoImpl extends LmsGenericDaoImpl<Role, Long> implements RoleDao {

}
