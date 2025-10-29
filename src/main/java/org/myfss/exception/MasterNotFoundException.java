package org.myfss.exception;

public class MasterNotFoundException extends RuntimeException {

    public MasterNotFoundException(Long Id) {
        super("Le maitre d'apprentissage numéro " + Id + " est introuvable");
    }
}
