package com.lms.controller;

import com.lms.model.entity.Lesson;
import com.lms.service.LessonService;
import com.lms.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private LessonService lessonService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@ModelAttribute Lesson lesson, @RequestParam("moduleId") Long moduleId, Model model) {
        model.addAttribute("moduleId", moduleId);
        return "lessons/edit-lesson";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Lesson lesson, @RequestParam("moduleId") Long moduleId,
            @RequestParam(value = "preds", required = false) String pred) {
        lesson.setModule(moduleService.get(moduleId));
        lesson.setPreds(pred);
        lessonService.save(lesson);
        return "redirect:/modules/" + moduleId.toString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Lesson lesson = lessonService.get(id);
        model.addAttribute("lesson", lesson);
        model.addAttribute("moduleId", lesson.getModule().getId());
        model.addAttribute("lessons", lessonService.getAll());
        return "lessons/edit-lesson";
    }
}
