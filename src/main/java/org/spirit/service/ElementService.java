package org.spirit.service;

import javax.servlet.http.HttpServletRequest;

import org.spirit.model.entity.Element;
import org.spirit.model.entity.ElementTest;

public interface ElementService {

    Element get(Long id);

    ElementTest getWithQuestions(Long id);

    void save(Element element, String elementType, HttpServletRequest request);

    void delete(Element element);
}
