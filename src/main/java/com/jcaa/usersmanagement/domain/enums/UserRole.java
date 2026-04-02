package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidUserRoleException;

public enum UserRole {
  ADMIN,
  MEMBER,
  REVIEWER;

  public static UserRole fromString(final String value) {
    for (final UserRole role : values()) {
      if (role.name().equalsIgnoreCase(value)) {
        return role;
      }
    }
    throw InvalidUserRoleException.becauseValueIsInvalid(value);
  }
}
