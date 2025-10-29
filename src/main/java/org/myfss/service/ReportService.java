package org.myfss.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myfss.exception.EvaluationExceptions.*;
import org.myfss.model.Report;
import org.myfss.repository.ReportRepository;
import org.myfss.util.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    public Report getReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
    }

    @Transactional
    public Report updateReport(Long id, Report updatedReport) {
        Report existingReport = getReportById(id);

        applyChanges(existingReport, updatedReport);
        validateReportRequiredFields(updatedReport);

        return reportRepository.save(existingReport);
    }

    private void validateReportRequiredFields(Report report) {
        ValidationUtils.validateRequiredFields(
                Map.of(
                        "theme", report.getTheme(),
                        "grade", report.getGrade(),
                        "comments", report.getComments()
                ),
                InvalidEvaluationDataException::new
        );
    }

    private void applyChanges(Report target, Report source) {
        if (!source.getTheme().equals(target.getTheme())) {
            target.setTheme(source.getTheme());
        }

        if (!source.getGrade().equals(target.getGrade())) {
            target.setGrade(source.getGrade());
        }

        if (!source.getComments().equals(target.getComments())) {
            target.setComments(source.getComments());
        }
    }
}
