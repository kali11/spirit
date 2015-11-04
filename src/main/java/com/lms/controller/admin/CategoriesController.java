package com.lms.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lms.model.entity.Category;
import com.lms.service.CategoryService;

/**
 * Controller responsible for managing course categories
 *
 * @author Piotr Kalinowski
 *
 */
@Controller
@RequestMapping("/admin/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "admin/categories/categories";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@ModelAttribute Category category, BindingResult result, Model model) {
        return "admin/categories/edit_category";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Category category, Model model) {
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.get(id));
        return "admin/categories/edit_category";
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable Long id, Model model) {
        categoryService.remove(id);
        return "redirect:/admin/categories";
    }
}
