package co.edu.udc.desechos_fabrica.user.application.service.mapper;

import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseNit;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.CreateUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.DeleteUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.UpdateUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.query.GetUserByEmailQuery;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.*;

import java.util.Objects;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserApplicationMapper {

  public UserModel fromCreateCommandToModel(final CreateUserCommand command) {
    return UserModel.create(
        new UserFirstName(command.firstName()),
        new UserLastName(command.lastName()),
        new UserEmail(command.email()),
        UserPassword.fromPlainText(command.password()),
        UserRole.fromString(command.role()));
  }

  public UserModel fromUpdateCommandToModel(
    final UpdateUserCommand command, final UserModel currentUser) {

      final UserFirstName newFirstName = new UserFirstName(command.firstName());
      final UserLastName newLastName = new UserLastName(command.lastName());
      final UserEmail newEmail = new UserEmail(command.email());
      final UserPassword newPassword = resolvePassword(command.password(), currentUser.getPassword());
      final UserRole newRole = UserRole.fromString(command.role());
      final UserStatus newStatus = UserStatus.fromString(command.status());
      final EnterpriseNit newEnterpriseNit = command.nit() != null ? new EnterpriseNit(command.nit()) : null;

    return currentUser.updateWith(
        newFirstName, newLastName, newEmail, newPassword, newRole, newStatus, newEnterpriseNit
    );
  }

  public UserEmail fromGetUserByEmailQueryToUserEmail(final GetUserByEmailQuery query) {
    return new UserEmail(query.email());
  }

  public UserEmail fromDeleteCommandToUserEmail(final DeleteUserCommand command) {
    return new UserEmail(command.email());
  }

  private UserPassword resolvePassword(
      final String newPlainPassword, final UserPassword currentPassword) {
    if (Objects.isNull(newPlainPassword) || newPlainPassword.isBlank()) {
      return currentPassword;
    }
    return UserPassword.fromPlainText(newPlainPassword);
  }
}
