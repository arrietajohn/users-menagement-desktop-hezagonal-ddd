package com.jcaa.usersmanagement.domain.exception;

public final class ProgramaNotFoundException extends DomainException {

  private static final String MESSAGE_NOT_FOUND_BY_ID = "El programa con id '%s' no fue encontrado.";

  private ProgramaNotFoundException(final String message) {
    super(message);
  }

  public static ProgramaNotFoundException becauseIdWasNotFound(final String id) {
    return new ProgramaNotFoundException(String.format(MESSAGE_NOT_FOUND_BY_ID, id));
  }
}
