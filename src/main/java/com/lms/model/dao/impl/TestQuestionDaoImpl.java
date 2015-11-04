package com.lms.model.dao.impl;

import com.lms.model.dao.TestQuestionDao;
import com.lms.model.dao.generic.LmsGenericDaoImpl;
import com.lms.model.entity.TestQuestion;
import org.springframework.stereotype.Repository;

@Repository
public class TestQuestionDaoImpl extends LmsGenericDaoImpl<TestQuestion, Long> implements TestQuestionDao{
}
