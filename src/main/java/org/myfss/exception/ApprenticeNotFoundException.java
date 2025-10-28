package org.myfss.exception;

public class ApprenticeNotFoundException extends RuntimeException {

    public ApprenticeNotFoundException(Long Id) {
        super("L'apprenti numéro " + Id + " est introuvable");
    }
}
