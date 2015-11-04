package com.lms.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.lms.model.dao.RoleDao;
import com.lms.model.dao.generic.LmsGenericDaoImpl;
import com.lms.model.entity.Role;

@Repository
public class RoleDaoImpl extends LmsGenericDaoImpl<Role, Long> implements RoleDao {

}
