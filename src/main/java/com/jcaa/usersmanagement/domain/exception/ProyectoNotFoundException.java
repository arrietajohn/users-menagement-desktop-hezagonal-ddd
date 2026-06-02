package com.jcaa.usersmanagement.domain.exception;

public final class ProyectoNotFoundException extends DomainException {

  private ProyectoNotFoundException(final String message) {
    super(message);
  }

  public static ProyectoNotFoundException becauseIdWasNotFound(final Long proyectoId) {
    return new ProyectoNotFoundException(
        String.format("El proyecto con id '%d' no fue encontrado.", proyectoId));
  }
}
