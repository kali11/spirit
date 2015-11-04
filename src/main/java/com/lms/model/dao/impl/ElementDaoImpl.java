package com.lms.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.lms.model.dao.ElementDao;
import com.lms.model.dao.generic.LmsGenericDaoImpl;
import com.lms.model.entity.Element;

@Repository
public class ElementDaoImpl extends LmsGenericDaoImpl<Element, Long> implements ElementDao {

}
