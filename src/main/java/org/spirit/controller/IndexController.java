package org.spirit.controller;

import org.spirit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
class IndexController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }
}
