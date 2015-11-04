package org.spirit.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.spirit.model.dict.ElementType;
import org.spirit.model.entity.ElementFile;
import org.spirit.model.entity.ElementTest;
import org.hibernate.Hibernate;
import org.spirit.model.dao.ElementDao;
import org.spirit.model.dao.LessonDao;
import org.spirit.model.entity.Element;
import org.spirit.model.entity.ElementText;
import org.spirit.model.entity.ElementVideo;
import org.spirit.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Field;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import org.spirit.model.dao.FileDao;
import org.spirit.model.entity.ElementAudio;
import org.spirit.model.entity.File;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ElementServiceImpl implements ElementService {

    @Autowired
    private ElementDao elementDao;

    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private Environment env;

    @Override
    public Element get(Long id) {
        return elementDao.find(id);
    }

    @Override
    public ElementTest getWithQuestions(Long id){
        Element element = elementDao.find(id);
        if (element instanceof ElementTest) {
            ElementTest elementTest = (ElementTest) element;
            Hibernate.initialize(elementTest.getTestQuestions());
            return elementTest;
        }
        return null;
    }

    @Override
    public void save(Element element, String elementType, HttpServletRequest request) {
        switch (ElementType.valueOf(elementType.toUpperCase())) {
        case TEXT:
            saveText(element, request);
            break;
        case VIDEO:
            saveVideo(element, request);
            break;
        case AUDIO:
            saveAudio(element, request);
            break;
        case TEST:
            saveTest(element, request);
            break;
        case FILE:
            saveFile(element, request);
            break;
        }
    }

    @Override
    public void delete(Element element) {
        element.getLesson().getElements().remove(element);
        lessonDao.save(element.getLesson());
        elementDao.remove(element);
    }

    private void saveText(Element element, HttpServletRequest request) {
        ElementText elementText = new ElementText(element);
        elementText.setText(request.getParameter("text"));
        saveElement(elementText);
    }

    private void saveVideo(Element element, HttpServletRequest request) {
        ElementVideo elementVideo = new ElementVideo(element);
        elementVideo.setDescription(request.getParameter("description"));
        String src = request.getParameter("src");
        src = src.replace("watch?v=", "embed/");
        elementVideo.setSrc(src);
        saveElement(elementVideo);
    }

    private void saveAudio(Element element, HttpServletRequest request) {
        ElementAudio elementAudio = new ElementAudio(element);
        File file = fileDao.find(Long.valueOf(request.getParameter("fileId")));
        elementAudio.setFiles(Arrays.asList(file));
        elementAudio.setDescription(request.getParameter("description"));
        saveElement(elementAudio);
    }

    private void saveFile(Element element, HttpServletRequest request) {
        ElementFile elementFile = new ElementFile(element);
        elementFile.setText(request.getParameter("text"));
        saveElement(elementFile);
    }

    private void saveTest(Element element, HttpServletRequest request) {
        ElementTest elementTest = new ElementTest(element);
        elementTest.setDescription(request.getParameter("description"));
        saveElement(elementTest);
    }

    private Long saveElement(Element element) {
        Search search = new Search(Element.class);
        search.addFilter(Filter.equal("lesson", element.getLesson()));
        search.addField("orderSeq", Field.OP_MAX);
        Long higherOrder = (Long) elementDao.searchUnique(search);
        element.setOrderSeq(higherOrder != null ? ++higherOrder : 1);
        List<File> elementFiles = element.getFiles();
        if (elementFiles != null) {
            for (File f : element.getFiles()) {
                f.setElement(element);
            }
        }
        elementDao.save(element);
        return element.getId();
    }

}
