package org.myfss.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CompanyExceptions {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class InvalidCompanyDataException extends RuntimeException {
        public InvalidCompanyDataException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class CompanyAlreadyExistsException extends RuntimeException {
        public CompanyAlreadyExistsException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class CompanyNotFoundException extends RuntimeException {
        public CompanyNotFoundException(Long id) {
            super("La société avec l'id " + id + " n'a pas été trouvée.");
        }
    }
}
