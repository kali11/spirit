package com.lms.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.lms.model.dao.LessonDao;
import com.lms.model.dao.generic.LmsGenericDaoImpl;
import com.lms.model.entity.Lesson;

@Repository
public class LessonDaoImpl extends LmsGenericDaoImpl<Lesson, Long> implements LessonDao {

}
