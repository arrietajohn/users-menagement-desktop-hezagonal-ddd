package com.jcaa.usersmanagement.domain.enums;

public enum TareaPrioridad {

  ALTA, MEDIA, BAJA;

  public static TareaPrioridad fromString(final String value) {
    for (final TareaPrioridad prioridad : values()) {
      if (prioridad.name().equalsIgnoreCase(value)) {
        return prioridad;
      }
    }
    throw new IllegalArgumentException("Prioridad de tarea inválida: " + value);
  }
}
