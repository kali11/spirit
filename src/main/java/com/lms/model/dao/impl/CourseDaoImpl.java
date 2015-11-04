package com.lms.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.lms.model.dao.CourseDao;
import com.lms.model.dao.generic.LmsGenericDaoImpl;
import com.lms.model.entity.Course;

@Repository
public class CourseDaoImpl extends LmsGenericDaoImpl<Course, Long> implements CourseDao {

}
