package org.myfss.exception;

public class MissingVisitForEvaluationException extends RuntimeException {

    public MissingVisitForEvaluationException() {
        super("Impossible d’enregistrer une évaluation sans visite associée.");
    }
}
