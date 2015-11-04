package org.spirit.service;

import org.spirit.model.entity.ElementTest;
import org.spirit.model.entity.TestQuestion;

import java.util.List;
import java.util.Map;

public interface TestService {
    void addQuestion(Map<String, Object> questionData);

    List<TestQuestion> getTestQuestions(ElementTest elementTest, String userLogin);

    Integer checkQuestion(Map<String, Object> responseData, String userLogin);
}
