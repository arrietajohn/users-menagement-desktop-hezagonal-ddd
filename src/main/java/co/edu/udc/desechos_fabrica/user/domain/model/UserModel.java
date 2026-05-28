package co.edu.udc.desechos_fabrica.user.domain.model;

import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserFirstName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserLastName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserPassword;
import lombok.Value;

@Value
public class UserModel {

  UserFirstName firstName;
  UserLastName lastName;
  UserEmail email;
  UserPassword password;
  UserRole role;
  UserStatus status;

  public static UserModel create(
      final UserFirstName firstName,
      final UserLastName lastName,
      final UserEmail email,
      final UserPassword password,
      final UserRole role) {
    return new UserModel(firstName, lastName, email, password, role, UserStatus.PENDING);
  }

  public UserModel activate() {
    return new UserModel(firstName, lastName, email, password, role, UserStatus.ACTIVE);
  }

  public UserModel deactivate() {
    return new UserModel(firstName, lastName, email, password, role, UserStatus.INACTIVE);
  }
}
