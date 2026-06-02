package com.jcaa.usersmanagement.domain.exception;

public final class ProyectoAlreadyExistsException extends DomainException {

  private ProyectoAlreadyExistsException(final String message) {
    super(message);
  }

  public static ProyectoAlreadyExistsException becauseNombreClaveAlreadyExists(final String nombreClave) {
    return new ProyectoAlreadyExistsException(
        String.format("Ya existe un proyecto con el nombre clave '%s'.", nombreClave));
  }
}
