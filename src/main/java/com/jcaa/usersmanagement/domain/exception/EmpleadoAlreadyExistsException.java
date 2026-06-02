package com.jcaa.usersmanagement.domain.exception;

public final class EmpleadoAlreadyExistsException extends DomainException {

  private EmpleadoAlreadyExistsException(final String message) {
    super(message);
  }

  public static EmpleadoAlreadyExistsException becauseEmailAlreadyExists(final String email) {
    return new EmpleadoAlreadyExistsException(
        String.format("Ya existe un empleado con el email '%s'.", email));
  }
}
