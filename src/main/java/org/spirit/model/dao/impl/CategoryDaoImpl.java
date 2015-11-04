package org.spirit.model.dao.impl;

import org.spirit.model.dao.generic.LmsGenericDaoImpl;
import org.springframework.stereotype.Repository;

import org.spirit.model.dao.CategoryDao;
import org.spirit.model.entity.Category;

@Repository
public class CategoryDaoImpl extends LmsGenericDaoImpl<Category, Long> implements CategoryDao {

}
