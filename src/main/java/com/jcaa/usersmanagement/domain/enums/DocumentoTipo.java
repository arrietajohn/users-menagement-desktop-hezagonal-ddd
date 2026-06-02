package com.jcaa.usersmanagement.domain.enums;

public enum DocumentoTipo {

  CONTRATO, INFORME, FACTURA, OTRO;

  public static DocumentoTipo fromString(final String value) {
    for (final DocumentoTipo tipo : values()) {
      if (tipo.name().equalsIgnoreCase(value)) {
        return tipo;
      }
    }
    throw new IllegalArgumentException("Tipo de documento inválido: " + value);
  }
}
