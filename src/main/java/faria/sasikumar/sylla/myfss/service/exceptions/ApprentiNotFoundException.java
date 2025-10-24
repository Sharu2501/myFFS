package faria.sasikumar.sylla.myfss.service.exceptions;

/**
 * Exception métier levée lorsqu'un apprenti est introuvable.
 */
public class ApprentiNotFoundException extends RuntimeException {
    public ApprentiNotFoundException(Long id) {
        super("Apprenti avec l'id " + id + " introuvable.");
    }
}

