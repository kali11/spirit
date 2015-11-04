package com.lms.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.lms.model.dao.CategoryDao;
import com.lms.model.dao.generic.LmsGenericDaoImpl;
import com.lms.model.entity.Category;

@Repository
public class CategoryDaoImpl extends LmsGenericDaoImpl<Category, Long> implements CategoryDao {

}
