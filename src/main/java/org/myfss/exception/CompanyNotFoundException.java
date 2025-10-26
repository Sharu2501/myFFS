package org.myfss.exception;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(Long id) {
        super("L'entreprise numéro " + id + " est introuvable");
    }
}
