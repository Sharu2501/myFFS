package org.myfss.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myfss.exception.EvaluationExceptions.*;
import org.myfss.model.Report;
import org.myfss.repository.ReportRepository;
import org.myfss.util.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public Report getReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
    }

    @Transactional
    public Report updateReport(Long id, Report updatedReport) {
        Report existingReport = getReportById(id);

        if (existingReport.getTheme() != null) {
            validateReportRequiredFields(existingReport);
        }

        applyChanges(existingReport, updatedReport);

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
        if (source.getTheme() != null) {
            target.setTheme(source.getTheme());
        }

        if (source.getGrade() != null && !Objects.equals(source.getGrade(), target.getGrade())) {
            target.setGrade(source.getGrade());
        }

        if (source.getComments() != null) {
            target.setComments(source.getComments());
        }
    }
}
