package org.myfss.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MasterExceptions {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class InvalidMasterDataException extends RuntimeException {
        public InvalidMasterDataException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class MasterAlreadyExistsException extends RuntimeException {
        public MasterAlreadyExistsException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class MasterNotFoundException extends RuntimeException {
        public MasterNotFoundException(Long id) {
            super("Le master avec l'id " + id + " n'a pas été trouvé.");
        }
    }
}
