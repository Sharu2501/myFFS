package org.myfss.service;

import lombok.RequiredArgsConstructor;
import org.myfss.model.Evaluation;
import org.myfss.repository.EvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }
}
