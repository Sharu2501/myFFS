package org.myfss.controller.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.myfss.model.Evaluation;
import org.myfss.service.EvaluationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Evaluations")
@RestController
@RequestMapping("/api/evaluations")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationServiceImpl evaluationServiceImpl;

    @GetMapping("/{id}")
    public Evaluation getEvaluationById(@PathVariable Long id) {
        return evaluationServiceImpl.getEvaluationById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable Long id, @RequestBody Evaluation evaluation) {
        Evaluation updated = evaluationServiceImpl.updateEvaluation(id, evaluation);
        return ResponseEntity.ok(updated);
    }
}
