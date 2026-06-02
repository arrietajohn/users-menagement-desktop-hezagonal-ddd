package com.jcaa.usersmanagement.domain.enums;

public enum TareaEstado {

  PENDIENTE, EN_PROGRESO, COMPLETADA, CANCELADA;

  public static TareaEstado fromString(final String value) {
    for (final TareaEstado estado : values()) {
      if (estado.name().equalsIgnoreCase(value)) {
        return estado;
      }
    }
    throw new IllegalArgumentException("Estado de tarea inválido: " + value);
  }
}
