package org.myfss.exception;

public class MasterNotFoundException extends RuntimeException {

    public MasterNotFoundException(Long id) {
        super("Le master numéro " + id + " est introuvable");
    }
}
