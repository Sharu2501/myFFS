package org.myfss.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myfss.exception.EvaluationExceptions.*;
import org.myfss.model.Evaluation;
import org.myfss.repository.EvaluationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final OralService oralService;
    private final ReportService reportService;

    public Evaluation getEvaluationById(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new EvaluationNotFoundException(id));
    }

    @Transactional
    public Evaluation updateEvaluation(Long id, Evaluation updatedEvaluation) {
        Evaluation existing = getEvaluationById(id);

        oralService.updateOral(existing.getOral().getId(), updatedEvaluation.getOral());
        reportService.updateReport(existing.getReport().getId(), updatedEvaluation.getReport());

        return evaluationRepository.save(existing);
    }
}
