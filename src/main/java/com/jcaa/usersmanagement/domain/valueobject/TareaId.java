package com.jcaa.usersmanagement.domain.valueobject;

public record TareaId(Long value) {

  public TareaId {
    if (value == null) {
      throw new IllegalArgumentException("TareaId no puede ser null");
    }
    if (value <= 0) {
      throw new IllegalArgumentException("TareaId debe ser un número positivo");
    }
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
