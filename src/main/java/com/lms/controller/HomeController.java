package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lms.service.CourseService;
import com.lms.service.UserService;

@Controller
@RequestMapping("/home")
@SessionAttributes("userlogin")
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            model.addAttribute("userlogin", auth.getName());
        }
        model.addAttribute("courses", courseService.getByUserlogin(auth.getName()));
        return "home/index";
    }
}
