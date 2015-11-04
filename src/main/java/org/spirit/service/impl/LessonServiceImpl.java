package org.spirit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Field;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import org.spirit.model.dao.LessonDao;
import org.spirit.model.entity.Lesson;
import org.spirit.service.LessonService;

import java.util.List;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private Environment env;

    @Override
    public Long save(Lesson lesson) {
        if (lesson.getOrderSeq() == null) {
            Search search = new Search(Lesson.class);
            search.addFilter(Filter.equal("module", lesson.getModule()));
            search.addField("orderSeq", Field.OP_MAX);
            Long higherOrder = (Long) lessonDao.searchUnique(search);
            lesson.setOrderSeq(higherOrder != null ? ++higherOrder : 1);
        }
        lessonDao.save(lesson);
        Long lessonId = lesson.getId();
        return lessonId;
    }

    @Override
    public Lesson get(Long id) {
        return lessonDao.find(id);
    }

    @Override public List<Lesson> getAll() {
        return lessonDao.findAll();
    }

    @Override
    public Lesson getParent(Long id) {
        Lesson lesson = lessonDao.find(id);
        String pred = lesson.getPreds();
        if (pred != null) {
            return lessonDao.find(Long.valueOf(pred));
        }
        return null;
    }
}
