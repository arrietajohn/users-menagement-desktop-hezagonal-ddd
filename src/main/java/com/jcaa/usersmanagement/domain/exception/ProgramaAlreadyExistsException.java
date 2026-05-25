package com.jcaa.usersmanagement.domain.exception;

public final class ProgramaAlreadyExistsException extends DomainException {

  private static final String MESSAGE_ID_EXISTS = "El programa con el id '%s' ya existe.";

  private ProgramaAlreadyExistsException(final String message) {
    super(message);
  }

  public static ProgramaAlreadyExistsException becauseIdAlreadyExists(final String id) {
    return new ProgramaAlreadyExistsException(String.format(MESSAGE_ID_EXISTS, id));
  }
}
