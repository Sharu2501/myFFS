package org.myfss.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class VisitExceptions {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class InvalidVisitDataException extends RuntimeException {
        public InvalidVisitDataException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class VisitNotFoundException extends RuntimeException {
        public VisitNotFoundException(Long id) {
            super("La visite avec l'id " + id + " n'a pas été trouvée.");
        }
    }
}
