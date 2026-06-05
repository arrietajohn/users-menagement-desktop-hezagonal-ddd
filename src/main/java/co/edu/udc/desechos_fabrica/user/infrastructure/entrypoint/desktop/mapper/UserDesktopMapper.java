package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.mapper;

import co.edu.udc.desechos_fabrica.user.application.service.dto.command.CreateUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.DeleteUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.LoginCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.UpdateUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.query.GetUserByEmailQuery;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.LoginRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UpdateUserRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UserResponse;

import java.util.List;

public final class UserDesktopMapper {

  private UserDesktopMapper() {}

  public static CreateUserCommand toCreateCommand(final CreateUserRequest request) {
    return new CreateUserCommand(
        request.firstName(), request.lastName(), request.email(), request.password(), request.role().name());
  }

  public static UpdateUserCommand toUpdateCommand(final UpdateUserRequest request) {
    return new UpdateUserCommand(
        request.actorEmail(),
        request.targetEmail(),
        request.newFirstName(),
        request.newLastName(),
        request.newEmail(),
        request.password(),
        request.role(),
        request.status(),
        request.nit());
  }

  public static DeleteUserCommand toDeleteCommand(final String actorEmail, final String targetEmail) {
    return new DeleteUserCommand(actorEmail, targetEmail);
  }

  public static GetUserByEmailQuery toGetByEmailQuery(final String email) {
    return new GetUserByEmailQuery(email);
  }

  public static LoginCommand toLoginCommand(final LoginRequest request) {
    return new LoginCommand(request.email(), request.password());
  }

  public static UserResponse toResponse(final UserModel user) {
    return new UserResponse(
        user.getFirstName().value(),
        user.getLastName().value(),
        user.getEmail().value(),
        user.getRole().name(),
        user.getStatus().name());
  }

  public static List<UserResponse> toResponseList(final List<UserModel> users) {
    return users.stream().map(UserDesktopMapper::toResponse).toList();
  }
}
