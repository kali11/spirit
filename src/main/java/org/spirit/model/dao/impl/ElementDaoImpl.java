package org.spirit.model.dao.impl;

import org.spirit.model.dao.generic.LmsGenericDaoImpl;
import org.springframework.stereotype.Repository;

import org.spirit.model.dao.ElementDao;
import org.spirit.model.entity.Element;

@Repository
public class ElementDaoImpl extends LmsGenericDaoImpl<Element, Long> implements ElementDao {

}
