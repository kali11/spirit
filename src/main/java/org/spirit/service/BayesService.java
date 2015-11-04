package org.spirit.service;

import org.eclipse.recommenders.jayes.BayesNet;

public interface BayesService {
    BayesNet createNetwork();

    String getNote(Long userId, Long lessonId);
}
