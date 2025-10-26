package org.myfss.exception;

public class ApprenticeNotFoundException extends RuntimeException {

    public ApprenticeNotFoundException(Long id) {
        super("L'apprenti numéro " + id + " est introuvable");
    }
}
