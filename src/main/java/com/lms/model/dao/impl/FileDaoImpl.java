package com.lms.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.lms.model.dao.FileDao;
import com.lms.model.dao.generic.LmsGenericDaoImpl;
import com.lms.model.entity.File;

@Repository
public class FileDaoImpl extends LmsGenericDaoImpl<File, Long> implements FileDao {

}
