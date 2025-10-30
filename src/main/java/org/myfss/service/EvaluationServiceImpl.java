package org.myfss.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myfss.exception.EvaluationExceptions.*;
import org.myfss.model.Evaluation;
import org.myfss.repository.EvaluationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final OralServiceImpl oralServiceImpl;
    private final ReportServiceImpl reportServiceImpl;

    public Evaluation getEvaluationById(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new EvaluationNotFoundException(id));
    }

    @Transactional
    public Evaluation updateEvaluation(Long id, Evaluation updatedEvaluation) {
        Evaluation existing = getEvaluationById(id);

        oralServiceImpl.updateOral(existing.getOral().getId(), updatedEvaluation.getOral());
        reportServiceImpl.updateReport(existing.getReport().getId(), updatedEvaluation.getReport());

        return evaluationRepository.save(existing);
    }
}
