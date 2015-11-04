package com.lms.service;

import com.lms.model.entity.Module;

public interface ModuleService {
    public void save(Module module);

    public Module get(Long id);

    public Module getWithLessons(Long id);
}
