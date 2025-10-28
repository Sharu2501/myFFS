package org.myfss.exception;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(Long Id) {
        super("L'entreprise numéro " + Id + " est introuvable");
    }
}
