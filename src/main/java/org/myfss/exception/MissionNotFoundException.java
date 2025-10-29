package org.myfss.exception;

public class MissionNotFoundException extends RuntimeException {

    public MissionNotFoundException(Long Id) {
        super("La mission numéro " + Id + " est introuvable");
    }
}
