package org.myfss.exception;

public class MissingReportForOralException extends RuntimeException {

  public MissingReportForOralException() {
    super("Impossible d’ajouter un oral sans rapport associé.");
  }
}
