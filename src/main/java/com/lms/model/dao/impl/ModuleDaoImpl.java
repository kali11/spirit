package com.lms.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.lms.model.dao.ModuleDao;
import com.lms.model.dao.generic.LmsGenericDaoImpl;
import com.lms.model.entity.Module;

@Repository
public class ModuleDaoImpl extends LmsGenericDaoImpl<Module, Long> implements ModuleDao {

}
