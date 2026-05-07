package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidAsignaturaIdException;
import java.util.Objects;

public record AsignaturaId(String value) {

  public AsignaturaId {
    final String normalized = Objects.requireNonNull(value, "AsignaturaId cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidAsignaturaIdException.becauseValueIsEmpty();
    }
    value = normalized;
  }

  @Override
  public String toString() {
    return value;
  }
}

