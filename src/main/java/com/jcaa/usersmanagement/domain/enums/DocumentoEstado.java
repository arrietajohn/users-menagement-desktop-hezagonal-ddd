package com.jcaa.usersmanagement.domain.enums;

public enum DocumentoEstado {

  BORRADOR, PUBLICADO, ARCHIVADO;

  public static DocumentoEstado fromString(final String value) {
    for (final DocumentoEstado estado : values()) {
      if (estado.name().equalsIgnoreCase(value)) {
        return estado;
      }
    }
    throw new IllegalArgumentException("Estado de documento inválido: " + value);
  }
}
