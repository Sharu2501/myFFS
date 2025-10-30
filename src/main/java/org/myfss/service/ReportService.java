package org.myfss.service;

import org.myfss.model.Report;

public interface ReportService {

    Report getReportById(Long id);

    Report updateReport(Long id, Report updatedReport);
}