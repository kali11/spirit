package org.spirit.model.dao.impl;

import org.spirit.model.dao.LessonDao;
import org.spirit.model.dao.generic.LmsGenericDaoImpl;
import org.springframework.stereotype.Repository;

import org.spirit.model.entity.Lesson;

@Repository
public class LessonDaoImpl extends LmsGenericDaoImpl<Lesson, Long> implements LessonDao {

}
