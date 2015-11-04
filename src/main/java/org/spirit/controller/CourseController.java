package org.spirit.controller;

import org.spirit.model.entity.Course;
import org.spirit.service.CategoryService;
import org.spirit.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String courses(@ModelAttribute Course course, Model model) {
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("categories", categoryService.getAllCategoriesMap());
        return "courses/courses";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String ajaxGetCourses(@RequestParam("category") String category, Model model) {
        model.addAttribute("courses", courseService.getByCategoryId(Long.parseLong(category)));
        return "courses/courses-list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String courseIndex(@PathVariable Long id, Model model, HttpServletRequest request) {
        Course course = courseService.getWithModules(id);
        model.addAttribute("course", course);
        model.addAttribute("currentUrl", request.getServletPath());
        return "courses/course-index";
    }

    @Deprecated
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@ModelAttribute Course course, Model model) {
        model.addAttribute("categories", categoryService.getAllCategoriesMap());
        return "courses/edit-course";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Course course, @RequestParam(value = "fileId", required = false) Long fileId,
            @RequestParam("categoryIds") List<String> categoryId, Model model) {
        Long courseId = courseService.save(course, categoryId, fileId);
        return "redirect:/courses/" + courseId;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Course course = courseService.get(id);
        model.addAttribute("course", course);
        model.addAttribute("categories", categoryService.getAllCategoriesMap());
        model.addAttribute("course_categories", courseService.getCourseCategoriesList(course));
        return "courses/edit-course";
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable Long id, Model model) {
        courseService.remove(id);
        return "redirect:/courses";
    }

    @RequestMapping(value = "/subscribe/{id}", method = RequestMethod.GET)
    public String subscribe(@PathVariable Long id, Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        courseService.subscribeUser(id, login);
        return "redirect:/courses";
    }
}
