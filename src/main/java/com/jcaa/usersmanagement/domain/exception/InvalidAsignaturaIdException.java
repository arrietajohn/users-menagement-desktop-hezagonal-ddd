package com.jcaa.usersmanagement.domain.exception;

public final class InvalidAsignaturaIdException extends DomainException {

  private static final String MESSAGE_EMPTY = "El ID de la asignatura no puede estar vacío.";

  private InvalidAsignaturaIdException(final String message) {
    super(message);
  }

  public static InvalidAsignaturaIdException becauseValueIsEmpty() {
    return new InvalidAsignaturaIdException(MESSAGE_EMPTY);
  }
}

