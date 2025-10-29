package org.myfss.controller.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.myfss.model.Visit;
import org.myfss.service.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Visits")
@RestController
@RequestMapping("/api/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @GetMapping("/{id}")
    public Visit getVisitById(@PathVariable Long id) {
        return visitService.getVisitById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Visit> updateVisit(@PathVariable Long id, @RequestBody Visit visit) {
        Visit updated = visitService.updateVisit(id, visit);
        return ResponseEntity.ok(updated);
    }
}
