package org.spirit.model.dao.impl;

import org.spirit.model.dao.TestResultDao;
import org.spirit.model.dao.generic.LmsGenericDaoImpl;
import org.spirit.model.entity.TestResult;
import org.springframework.stereotype.Repository;

@Repository
public class TestResultDaoImpl extends LmsGenericDaoImpl<TestResult, Long> implements TestResultDao {
}
