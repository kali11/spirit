package org.spirit.model.dao.impl;

import org.spirit.model.dao.TestAnswerDao;
import org.spirit.model.dao.generic.LmsGenericDaoImpl;
import org.spirit.model.entity.TestAnswer;
import org.springframework.stereotype.Repository;

@Repository
public class TestAnswerDaoImpl extends LmsGenericDaoImpl<TestAnswer, Long> implements TestAnswerDao {
}
