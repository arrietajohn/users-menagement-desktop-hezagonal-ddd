package co.edu.udc.desechos_fabrica.user.domain.model;

import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseNit;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserFirstName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserLastName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserPassword;
import lombok.Value;
import java.util.Objects;

@Value
public class UserModel {

  UserFirstName firstName;
  UserLastName lastName;
  UserEmail email;
  EnterpriseNit enterpriseNit;
  UserPassword password;
  UserRole role;
  UserStatus status;

  public static UserModel create(
      final UserFirstName firstName,
      final UserLastName lastName,
      final UserEmail email,
      final UserPassword password,
      final UserRole role) {
    return new UserModel(firstName, lastName, email, null, password, role, UserStatus.PENDING);
  }

  public UserModel activate() {
    return new UserModel(firstName, lastName, email, this.enterpriseNit, password, role, UserStatus.ACTIVE);
  }

  public UserModel deactivate() {
    return new UserModel(firstName, lastName, email, this.enterpriseNit, password, role, UserStatus.INACTIVE);
  }

  public UserModel assignToEnterprise(final EnterpriseNit enterpriseNit) {
    return new UserModel(firstName, lastName, email, enterpriseNit, password, UserRole.ENTERPRISE_ADMIN, this.status);
  }

  public UserModel updateWith(
          final UserFirstName newFirstName,
          final UserLastName newLastName,
          final UserEmail newEmail,
          final UserPassword newPassword,
          final UserRole newRole,
          final UserStatus newStatus,
          final EnterpriseNit newEnterpriseNit) {

    UserStatus finalStatus = (newStatus != null) ? newStatus : this.status;
    UserRole finalRole = (newRole != null) ? newRole : this.role;
    
    boolean enterpriseIsChanging = !Objects.equals(this.enterpriseNit, newEnterpriseNit);

    if (enterpriseIsChanging) {
      if (newEnterpriseNit != null) {
        finalStatus = UserStatus.PENDING;
        finalRole = UserRole.ENTERPRISE_ADMIN;
      } else {
        finalRole = UserRole.MEMBER;
      }
    }

    return new UserModel(newFirstName, newLastName, newEmail, newEnterpriseNit, newPassword, finalRole, finalStatus);
  }
}
