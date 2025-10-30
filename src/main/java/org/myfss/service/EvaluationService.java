package org.myfss.service;

import org.myfss.model.Evaluation;

public interface EvaluationService {

    Evaluation getEvaluationById(Long id);

    Evaluation updateEvaluation(Long id, Evaluation updatedEvaluation);
}