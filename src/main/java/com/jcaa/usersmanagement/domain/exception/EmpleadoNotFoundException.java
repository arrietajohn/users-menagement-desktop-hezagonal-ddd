package com.jcaa.usersmanagement.domain.exception;

public final class EmpleadoNotFoundException extends DomainException {

  private EmpleadoNotFoundException(final String message) {
    super(message);
  }

  public static EmpleadoNotFoundException becauseIdWasNotFound(final Long empleadoId) {
    return new EmpleadoNotFoundException(
        String.format("El empleado con id '%d' no fue encontrado.", empleadoId));
  }
}
