package com.jcaa.usersmanagement.domain.enums;

public enum ProyectoEstado {

  PLANIFICADO, EN_CURSO, PAUSADO, COMPLETADO, CANCELADO;

  public static ProyectoEstado fromString(final String value) {
    for (final ProyectoEstado estado : values()) {
      if (estado.name().equalsIgnoreCase(value)) {
        return estado;
      }
    }
    throw new IllegalArgumentException("Estado de proyecto inválido: " + value);
  }
}
