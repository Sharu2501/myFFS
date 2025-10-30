package org.myfss.controller.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.myfss.model.Report;
import org.myfss.service.ReportServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Reports")
@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportServiceImpl reportServiceImpl;

    @GetMapping("/{id}")
    public Report getReportById(@PathVariable Long id) {
        return reportServiceImpl.getReportById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report report) {
        Report updated = reportServiceImpl.updateReport(id, report);
        return ResponseEntity.ok(updated);
    }
}
