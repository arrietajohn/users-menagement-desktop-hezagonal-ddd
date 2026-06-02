package com.jcaa.usersmanagement.domain.valueobject;

public record EmpleadoId(Long value) {

  public EmpleadoId {
    if (value == null) {
      throw new IllegalArgumentException("EmpleadoId no puede ser null");
    }
    if (value <= 0) {
      throw new IllegalArgumentException("EmpleadoId debe ser un número positivo");
    }
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
