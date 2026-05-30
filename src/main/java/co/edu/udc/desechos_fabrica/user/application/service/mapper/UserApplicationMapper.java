package co.edu.udc.desechos_fabrica.user.application.service.mapper;

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

    final UserPassword passwordToUse = resolvePassword(command.password(), currentUser.getPassword());
    final UserRole roleToUse = (command.role() != null)
            ? UserRole.fromString(command.role())
            : currentUser.getRole();

    final UserStatus statusToUse = (command.status() != null)
            ? UserStatus.fromString(command.status())
            : currentUser.getStatus();

    return new UserModel(
        new UserFirstName(command.firstName()),
        new UserLastName(command.lastName()),
        new UserEmail(command.email()),
        passwordToUse,
        roleToUse,
        statusToUse);
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
