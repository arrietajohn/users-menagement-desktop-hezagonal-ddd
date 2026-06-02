package com.jcaa.usersmanagement.domain.enums;

public enum EmpleadoEstado {

  ACTIVO, INACTIVO;

  public static EmpleadoEstado fromString(final String value) {
    for (final EmpleadoEstado estado : values()) {
      if (estado.name().equalsIgnoreCase(value)) {
        return estado;
      }
    }
    throw new IllegalArgumentException("Estado de empleado inválido: " + value);
  }
}
