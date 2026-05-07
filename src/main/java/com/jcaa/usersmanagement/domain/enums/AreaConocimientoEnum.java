package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidAreaConocimientoException;

public enum AreaConocimientoEnum {
  HUMANIDADES,
  INGENIERIAS;

  public static boolean isValid(final String value) {
    if (value == null) {
      return false;
    }
    try {
      valueOf(value);
      return true;
    } catch (final IllegalArgumentException ex) {
      return false;
    }
  }

  public static void ensureIsValid(final String value) {
    if (!isValid(value)) {
      throw InvalidAreaConocimientoException.becauseValueIsInvalid(value);
    }
  }
}

