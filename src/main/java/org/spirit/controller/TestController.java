package org.spirit.controller;

import org.spirit.model.entity.ElementTest;
import org.spirit.service.ElementService;
import org.spirit.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private ElementService elementService;

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/add-question", method = RequestMethod.POST)
    public String ajaxAddQuestion(@RequestBody Map<String, Object> questionData, Model model) {
        testService.addQuestion(questionData);
        ElementTest elementTest = elementService.getWithQuestions(((Integer) questionData.get("element")).longValue());
        model.addAttribute("testQuestions", elementTest.getTestQuestions());
        return "tests/questions-list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        ElementTest element = elementService.getWithQuestions(id);
        //        ElementType elementType = null;
        //        if (element instanceof ElementTest) {
        //            elementType = ElementType.TEST;
        //        }
        //        model.addAttribute("elementType", elementType);
        model.addAttribute("testQuestions", element.getTestQuestions());
        model.addAttribute("element", element);
        model.addAttribute("lessonId", element.getLesson().getId());
        return "tests/edit-element-test";
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> ajaxCheckQuestion(@RequestBody Map<String, Object> responseData,
            HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String userLogin = (String) request.getSession().getAttribute("userlogin");
        result.put("score", testService.checkQuestion(responseData, userLogin));
        return result;
    }
}
