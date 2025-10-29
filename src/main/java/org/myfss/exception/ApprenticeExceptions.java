package org.myfss.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ApprenticeExceptions {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class InvalidApprenticeDataException extends RuntimeException {
        public InvalidApprenticeDataException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class ApprenticeAlreadyExistsException extends RuntimeException {
        public ApprenticeAlreadyExistsException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ApprenticeNotFoundException extends RuntimeException {
        public ApprenticeNotFoundException(Long id) {
            super("L'apprenti avec l'id " + id + " n'a pas été trouvé.");
        }
    }
}
