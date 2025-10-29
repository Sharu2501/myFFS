package org.myfss.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EvaluationExceptions {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class InvalidEvaluationDataException extends RuntimeException {
        public InvalidEvaluationDataException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class EvaluationNotFoundException extends RuntimeException {
        public EvaluationNotFoundException(Long id) {
            super("L'évaluation avec l'id " + id + " n'a pas été trouvée.");
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class OralNotFoundException extends RuntimeException {
        public OralNotFoundException(Long id) {
            super("L'examen oral avec l'id " + id + " n'a pas été trouvé.");
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ReportNotFoundException extends RuntimeException {
        public ReportNotFoundException(Long id) {
            super("Le rapport avec l'id " + id + " n'a pas été trouvé.");
        }
    }
}
