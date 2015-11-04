package com.lms.service;

import com.lms.model.entity.Lesson;

import java.util.List;

public interface LessonService {

    public Long save(Lesson lesson);

    public Lesson get(Long id);

    public List<Lesson> getAll();

    Lesson getParent(Long id);
}
