package org.spirit.model.dao.impl;

import org.spirit.model.dao.CourseDao;
import org.spirit.model.dao.generic.LmsGenericDaoImpl;
import org.springframework.stereotype.Repository;

import org.spirit.model.entity.Course;

@Repository
public class CourseDaoImpl extends LmsGenericDaoImpl<Course, Long> implements CourseDao {

}
