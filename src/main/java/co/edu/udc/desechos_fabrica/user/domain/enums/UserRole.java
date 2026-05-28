package co.edu.udc.desechos_fabrica.user.domain.enums;

import co.edu.udc.desechos_fabrica.user.domain.exception.InvalidUserRoleException;

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
