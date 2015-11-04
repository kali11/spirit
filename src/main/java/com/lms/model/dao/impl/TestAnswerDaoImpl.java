package com.lms.model.dao.impl;

import com.lms.model.dao.TestAnswerDao;
import com.lms.model.dao.generic.LmsGenericDaoImpl;
import com.lms.model.entity.TestAnswer;
import org.springframework.stereotype.Repository;

@Repository
public class TestAnswerDaoImpl extends LmsGenericDaoImpl<TestAnswer, Long> implements TestAnswerDao {
}
