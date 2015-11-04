package org.spirit.service.impl;

import com.googlecode.genericdao.search.Field;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.Sort;
import org.spirit.model.dao.ElementDao;
import org.spirit.model.dao.TestAnswerDao;
import org.spirit.model.dao.TestQuestionDao;
import org.spirit.model.dao.TestResultDao;
import org.spirit.model.dao.UserDao;
import org.spirit.model.dict.QuestionType;
import org.spirit.model.entity.ElementTest;
import org.spirit.model.entity.TestAnswer;
import org.spirit.model.entity.TestQuestion;
import org.spirit.model.entity.TestResult;
import org.spirit.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private ElementDao elementDao;

    @Autowired
    private TestQuestionDao testQuestionDao;

    @Autowired
    private TestAnswerDao testAnswerDao;

    @Autowired
    private TestResultDao testResultDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<TestQuestion> getTestQuestions(ElementTest elementTest, String userLogin) {
        Search search = new Search();
        search.addFilter(Filter.equal("elementTest", elementTest));
        search.addSort(Sort.asc("orderSeq"));
        search.addFetch("testResults");
        return testQuestionDao.search(search);
    }

    @Override
    public Integer checkQuestion(Map<String, Object> responseData, String userLogin) {
        Long questionId = Long.parseLong(responseData.get("questionId").toString());
        TestQuestion testQuestion = testQuestionDao.find(questionId);
        Search search = new Search();
        search.addFilter(Filter.equal("testQuestion", testQuestion));
        search.addFilter(Filter.equal("correct", true));
        List<TestAnswer> testAnswers = testAnswerDao.search(search);
        Integer score = 0;
        Integer maxScore = 0;
        if (testQuestion.getQuestionType().equals(QuestionType.MULTI)) {
            List<String> userResponse = (List<String>) responseData.get("userResponse");
            score = checkMulti(testAnswers, userResponse);
            for (TestAnswer a : testAnswers) {
                maxScore += a.getMaxPoints();
            }
        } else if (testQuestion.getQuestionType().equals(QuestionType.SINGLE)) {
            String userResponse = (String) responseData.get("userResponse");
            if (testAnswers.get(0).getId().toString().equals(userResponse)) {
                score = testAnswers.get(0).getMaxPoints();
            }
            maxScore = testAnswers.get(0).getMaxPoints();
        } else if (testQuestion.getQuestionType().equals(QuestionType.OPEN)) {
            String userResponse = (String) responseData.get("userResponse");
            if (testAnswers.get(0).getAnswer().equals(userResponse)) {
                score = testAnswers.get(0).getMaxPoints();
            }
            maxScore = testAnswers.get(0).getMaxPoints();
        }
        Search search1 = new Search();
        search1.addFilter(Filter.equal("user", userDao.getByLogin(userLogin)));
        search1.addFilter(Filter.equal("testQuestion", testQuestion));
        List<TestResult> existingResults = testResultDao.search(search1);
        TestResult testResult;
        if (existingResults.size() > 0) {
            testResult = existingResults.get(0);
        } else {
            testResult = new TestResult();
        }
        testResult.setTestQuestion(testQuestion);
        testResult.setElementTest(testQuestion.getElementTest());
        testResult.setScore(score);
        testResult.setUser(userDao.getByLogin(userLogin));
        testResult.setMaxScore(maxScore);
        testResultDao.save(testResult);
        return score;
    }

    private Integer checkMulti(List<TestAnswer> testAnswers, List<String> responses) {
        return testAnswers.stream().map(a -> {
            if (responses.contains(a.getId().toString())) {
                return a.getMaxPoints();
            }
            return 0;
        }).reduce(0, (a, b) -> a + b);
    }

    @Override
    public void addQuestion(Map<String, Object> questionData) {
        Long elementId = Long.parseLong(questionData.get("element").toString());
        ElementTest elementTest = (ElementTest) elementDao.find(elementId);

        String question = (String) questionData.get("question");
        QuestionType questionType = QuestionType.valueOf(((String) questionData.get("type")).toUpperCase());

        Search search = new Search();
        search.addFilter(Filter.equal("elementTest", elementTest));
        search.addField("orderSeq", Field.OP_MAX);
        Long higherOrder = (Long) testQuestionDao.searchUnique(search);

        TestQuestion testQuestion = new TestQuestion();
        testQuestion.setQuestion(question);
        testQuestion.setOrderSeq(higherOrder != null ? ++higherOrder : 1);
        testQuestion.setQuestionType(questionType);
        testQuestion.setElementTest(elementTest);
        List<TestQuestion> testQuestions = elementTest.getTestQuestions();
        testQuestions.add(testQuestion);
        elementTest.setTestQuestions(testQuestions);

        List<Map<String, Object>> answers = (List<Map<String, Object>>) questionData.get("answers");
        List<TestAnswer> testAnswers = answers.stream().map(a -> getTestAnswer(a, testQuestion)).collect(
                Collectors.toList());
        testQuestion.setTestAnswers(testAnswers);

        testQuestionDao.save(testQuestion);
    }

    private TestAnswer getTestAnswer(Map<String, Object> answer, TestQuestion testQuestion) {
        TestAnswer testAnswer = new TestAnswer();
        testAnswer.setAnswer((String) answer.get("answer"));
        String pointsStr = (String) answer.get("points");
        Boolean correct = (Boolean) answer.get("correct");
        if (correct == null)
            correct = true;
        testAnswer.setMaxPoints(pointsStr == "" ? 0 : Integer.valueOf(pointsStr));
        testAnswer.setCorrect(correct);
        testAnswer.setTestQuestion(testQuestion);
        return testAnswer;
    }
}
