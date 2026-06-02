package com.jcaa.usersmanagement.domain.valueobject;

public record DocumentoId(Long value) {

  public DocumentoId {
    if (value == null) {
      throw new IllegalArgumentException("DocumentoId no puede ser null");
    }
    if (value <= 0) {
      throw new IllegalArgumentException("DocumentoId debe ser un número positivo");
    }
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
