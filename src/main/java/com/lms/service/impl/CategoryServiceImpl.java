package com.lms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.dao.CategoryDao;
import com.lms.model.entity.Category;
import com.lms.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getAll() {
        return categoryDao.findAll();
    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public Category get(Long id) {
        return categoryDao.find(id);
    }

    @Override
    public void remove(Long id) {
        categoryDao.remove(categoryDao.find(id));
    }

    @Override
    public Map<String, String> getAllCategoriesMap() {
        List<Category> categories = categoryDao.findAll();
        Map<String, String> categoriesMap = new HashMap<>();
        for (Category category : categories) {
            categoriesMap.put(category.getId().toString(), category.getName());
        }
        return categoriesMap;
    }

}
