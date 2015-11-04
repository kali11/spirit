package org.spirit.model.dao.impl;

import org.spirit.model.dao.generic.LmsGenericDaoImpl;
import org.springframework.stereotype.Repository;

import org.spirit.model.dao.ModuleDao;
import org.spirit.model.entity.Module;

@Repository
public class ModuleDaoImpl extends LmsGenericDaoImpl<Module, Long> implements ModuleDao {

}
