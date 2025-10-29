package org.myfss.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MissionExceptions {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class InvalidMissionDataException extends RuntimeException {
        public InvalidMissionDataException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class MissionNotFoundException extends RuntimeException {
        public MissionNotFoundException(Long id) {
            super("La mission avec l'id " + id + " n'a pas été trouvée.");
        }
    }
}
