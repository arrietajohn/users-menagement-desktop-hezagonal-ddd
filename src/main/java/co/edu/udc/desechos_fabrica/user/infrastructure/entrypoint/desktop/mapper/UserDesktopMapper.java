package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.mapper;

import co.edu.udc.desechos_fabrica.user.application.service.dto.command.CreateUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.DeleteUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.LoginCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.UpdateUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.query.GetUserByIdQuery;
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
        request.id(), request.name(), request.email(), request.password(), request.role());
  }

  public static UpdateUserCommand toUpdateCommand(final UpdateUserRequest request) {
    return new UpdateUserCommand(
        request.id(),
        request.name(),
        request.email(),
        request.password(),
        request.role(),
        request.status());
  }

  public static DeleteUserCommand toDeleteCommand(final String id) {
    return new DeleteUserCommand(id);
  }

  public static GetUserByIdQuery toGetByIdQuery(final String id) {
    return new GetUserByIdQuery(id);
  }

  public static LoginCommand toLoginCommand(final LoginRequest request) {
    return new LoginCommand(request.email(), request.password());
  }

  public static UserResponse toResponse(final UserModel user) {
    return new UserResponse(
        user.getId().value(),
        user.getName().value(),
        user.getEmail().value(),
        user.getRole().name(),
        user.getStatus().name());
  }

  public static List<UserResponse> toResponseList(final List<UserModel> users) {
    return users.stream().map(UserDesktopMapper::toResponse).toList();
  }
}
