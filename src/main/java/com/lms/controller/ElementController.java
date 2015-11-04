package com.lms.controller;

import javax.servlet.http.HttpServletRequest;

import com.lms.model.dict.ElementType;
import com.lms.model.entity.ElementFile;
import com.lms.model.entity.ElementTest;
import com.lms.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.model.entity.Element;
import com.lms.model.entity.ElementAudio;
import com.lms.model.entity.ElementText;
import com.lms.model.entity.ElementVideo;
import com.lms.model.entity.Lesson;
import com.lms.service.ElementService;
import com.lms.service.LessonService;

@Controller
@RequestMapping("/elements")
public class ElementController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private ElementService elementService;

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String ajaxAdd(@ModelAttribute Element element, @RequestParam("lessonId") Long lessonId, Model model) {
        model.addAttribute("lessonId", lessonId);
        return "elements/edit-element";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Element element, @RequestParam("lessonId") Long lessonId,
            @RequestParam("elementType") String elementType, HttpServletRequest request) {
        Lesson lesson = lessonService.get(lessonId);
        element.setLesson(lesson);
        elementService.save(element, elementType, request);
        return "redirect:/modules/" + lesson.getModule().getId() + "?lessonId=" + lessonId;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String ajaxDisplay(@PathVariable Long id, Model model, HttpServletRequest request) {
        Element element = elementService.get(id);
        ElementType elementType = null;
        if (element instanceof ElementText) {
            elementType = ElementType.TEXT;
        } else if (element instanceof ElementVideo) {
            elementType = ElementType.VIDEO;
        } else if (element instanceof ElementAudio) {
            model.addAttribute("filePath", request.getContextPath() + "/files/" + element.getFiles().get(0).getId());
            elementType = ElementType.AUDIO;
        } else if (element instanceof ElementFile) {
            elementType = ElementType.FILE;
        } else if (element instanceof ElementTest) {
            elementType = ElementType.TEST;
            String userLoing = (String) request.getSession().getAttribute("userlogin");
            model.addAttribute("testQuestions", testService.getTestQuestions((ElementTest) element, userLoing));
        }
        model.addAttribute("elementType", elementType);
        model.addAttribute("element", element);
        return "elements/display-element";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        Element element = elementService.get(id);
        elementService.delete(element);
        Lesson lesson = element.getLesson();
        return "redirect:/modules/" + lesson.getModule().getId() + "?lessonId=" + lesson.getId();
    }
}
