package org.spirit.model.dao.impl;

import org.spirit.model.dao.TestQuestionDao;
import org.spirit.model.dao.generic.LmsGenericDaoImpl;
import org.spirit.model.entity.TestQuestion;
import org.springframework.stereotype.Repository;

@Repository
public class TestQuestionDaoImpl extends LmsGenericDaoImpl<TestQuestion, Long> implements TestQuestionDao{
}
