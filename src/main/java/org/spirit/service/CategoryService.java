package org.spirit.service;

import java.util.List;
import java.util.Map;

import org.spirit.model.entity.Category;

public interface CategoryService {
    public List<Category> getAll();

    public void save(Category category);

    public Category get(Long id);

    public void remove(Long id);

    public Map<String, String> getAllCategoriesMap();
}
