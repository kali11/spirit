package org.spirit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.spirit.model.entity.Course;
import org.spirit.model.entity.Module;
import org.spirit.service.CourseService;
import org.spirit.service.ModuleService;

@Controller
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@ModelAttribute Module module, @RequestParam("courseId") Long courseId, Model model) {
        model.addAttribute("courseId", courseId);
        return "modules/edit_module";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Module module, @RequestParam("courseId") Long courseId) {
        module.setCourse(courseService.get(courseId));
        moduleService.save(module);
        return "redirect:/courses/" + courseId.toString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Module module = moduleService.get(id);
        model.addAttribute("module", module);
        model.addAttribute("courseId", module.getCourse().getId());
        return "modules/edit_module";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String index(@PathVariable Long id, @RequestParam(value = "lessonId", required = false) Long lessonId,
            Model model) {
        Module module = moduleService.getWithLessons(id);
        model.addAttribute("module", module);
        Course course = courseService.getWithModules(module.getCourse().getId());
        model.addAttribute("course", course);
        model.addAttribute("activeLessonId", lessonId);
        return "modules/module-index";
    }
}
