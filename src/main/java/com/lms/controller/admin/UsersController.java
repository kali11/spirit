package com.lms.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.model.entity.User;
import com.lms.service.RoleService;
import com.lms.service.UserService;

/**
 * Controller responsible for users administration
 *
 * @author Piotr Kalinowski
 *
 */
@Controller
@RequestMapping("/admin/users")
public class UsersController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String users(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users/users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(@ModelAttribute User user, BindingResult result, Model model) {
        model.addAttribute("roles", roleService.getAllRolesMap());
        return "admin/users/edit_user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user, @RequestParam("roleId") String roleId, Model model) {
        userService.saveUser(user, roleId);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRolesMap());
        return "admin/users/edit_user";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id, Model model) {
        userService.deleteUser(userService.getUser(id));
        return "redirect:/admin/users";
    }
}
