package com.lms.service;

import javax.servlet.http.HttpServletRequest;

import com.lms.model.entity.Element;
import com.lms.model.entity.ElementTest;

public interface ElementService {

    Element get(Long id);

    ElementTest getWithQuestions(Long id);

    void save(Element element, String elementType, HttpServletRequest request);

    void delete(Element element);
}
