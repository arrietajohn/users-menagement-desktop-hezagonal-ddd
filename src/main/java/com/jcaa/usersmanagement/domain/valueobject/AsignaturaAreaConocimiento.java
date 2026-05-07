package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.enums.AreaConocimientoEnum;
import java.util.Objects;

public record AsignaturaAreaConocimiento(String value) {

  public AsignaturaAreaConocimiento {
    final String normalized = Objects.requireNonNull(value, "AsignaturaAreaConocimiento cannot be null").trim();
    AreaConocimientoEnum.ensureIsValid(normalized);
    value = normalized;
  }

  @Override
  public String toString() {
    return value;
  }
}

