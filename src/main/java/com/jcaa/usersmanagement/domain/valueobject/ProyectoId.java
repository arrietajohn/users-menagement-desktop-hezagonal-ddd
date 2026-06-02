package com.jcaa.usersmanagement.domain.valueobject;

public record ProyectoId(Long value) {

  public ProyectoId {
    if (value == null) {
      throw new IllegalArgumentException("ProyectoId no puede ser null");
    }
    if (value <= 0) {
      throw new IllegalArgumentException("ProyectoId debe ser un número positivo");
    }
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
