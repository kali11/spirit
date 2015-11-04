package org.spirit.service;

import org.spirit.model.entity.Module;

public interface ModuleService {
    public void save(Module module);

    public Module get(Long id);

    public Module getWithLessons(Long id);
}
