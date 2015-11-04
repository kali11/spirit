package com.lms.model.dao.impl;

import com.lms.model.dao.TestResultDao;
import com.lms.model.dao.generic.LmsGenericDaoImpl;
import com.lms.model.entity.TestResult;
import org.springframework.stereotype.Repository;

@Repository
public class TestResultDaoImpl extends LmsGenericDaoImpl<TestResult, Long> implements TestResultDao{
}
