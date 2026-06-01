package co.edu.udc.desechos_fabrica.user.domain.enums;

import co.edu.udc.desechos_fabrica.user.domain.exception.InvalidUserRoleException;
import lombok.Getter;

@Getter
public enum UserRole {
  ADMIN(4),
  REVIEWER(3),
  ENTERPRISE_ADMIN(2),
  MEMBER(1);

  private final int level;

  UserRole(final int level) {
    this.level = level;
  }

    public static UserRole fromString(final String value) {
    for (final UserRole role : values()) {
      if (role.name().equalsIgnoreCase(value)) {
        return role;
      }
    }
    throw InvalidUserRoleException.becauseValueIsInvalid(value);
  }
}
