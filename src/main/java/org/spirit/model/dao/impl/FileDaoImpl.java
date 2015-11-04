package org.spirit.model.dao.impl;

import org.spirit.model.dao.generic.LmsGenericDaoImpl;
import org.springframework.stereotype.Repository;

import org.spirit.model.dao.FileDao;
import org.spirit.model.entity.File;

@Repository
public class FileDaoImpl extends LmsGenericDaoImpl<File, Long> implements FileDao {

}
