package org.spirit.service.impl;

import org.hibernate.Hibernate;
import org.spirit.model.dao.ModuleDao;
import org.spirit.model.entity.Module;
import org.spirit.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Field;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleDao moduleDao;

    @Override
    public void save(Module module) {
        Search search = new Search(Module.class);
        search.addFilter(Filter.equal("course", module.getCourse()));
        search.addField("orderSeq", Field.OP_MAX);
        Long higherOrder = (Long) moduleDao.searchUnique(search);
        module.setOrderSeq(higherOrder != null ? ++higherOrder : 1);
        moduleDao.save(module);
    }

    @Override
    public Module get(Long id) {
        return moduleDao.find(id);
    }

    @Override
    public Module getWithLessons(Long id) {
        Module module = moduleDao.find(id);
        Hibernate.initialize(module.getLessons());
        return module;
    }

}
