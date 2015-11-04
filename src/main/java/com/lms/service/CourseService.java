package com.lms.service;

import java.util.List;

import com.lms.model.entity.Course;

public interface CourseService {

    public List<Course> getAll();

    public Long save(Course course);

    public Long save(Course course, List<String> categoryId, Long fileId);

    public Course get(Long id);

    public Course getWithModules(Long id);

    public void remove(Long id);

    public List<String> getCourseCategoriesList(Course course);

    public void subscribeUser(Long id, String login);

    public List<Course> getByCategoryId(Long id);

    public List<Course> getByUserlogin(String login);
}
