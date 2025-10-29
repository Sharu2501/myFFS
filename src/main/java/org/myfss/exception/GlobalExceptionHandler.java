package org.myfss.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ApprenticeExceptions.InvalidApprenticeDataException.class,
            CompanyExceptions.InvalidCompanyDataException.class,
            MasterExceptions.InvalidMasterDataException.class,
            MissionExceptions.InvalidMissionDataException.class,
            EvaluationExceptions.InvalidEvaluationDataException.class,
            VisitExceptions.InvalidVisitDataException.class
    })
    public ResponseEntity<String> handleBadRequest(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler({
            ApprenticeExceptions.ApprenticeAlreadyExistsException.class,
            CompanyExceptions.CompanyAlreadyExistsException.class,
            MasterExceptions.MasterAlreadyExistsException.class
    })
    public ResponseEntity<String> handleConflict(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler({
            ApprenticeExceptions.ApprenticeNotFoundException.class,
            CompanyExceptions.CompanyNotFoundException.class,
            MasterExceptions.MasterNotFoundException.class,
            MissionExceptions.MissionNotFoundException.class,
            EvaluationExceptions.EvaluationNotFoundException.class,
            EvaluationExceptions.OralNotFoundException.class,
            EvaluationExceptions.ReportNotFoundException.class,
            VisitExceptions.VisitNotFoundException.class
    })
    public ResponseEntity<String> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
