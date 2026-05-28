package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.controller;

import co.edu.udc.desechos_fabrica.user.application.port.in.CreateUserUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.in.DeleteUserUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.in.GetAllUsersUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.in.GetUserByIdUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.in.LoginUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.in.UpdateUserUseCase;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.LoginRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UpdateUserRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UserResponse;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.mapper.UserDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class UserController {

  private final CreateUserUseCase createUserUseCase;
  private final UpdateUserUseCase updateUserUseCase;
  private final DeleteUserUseCase deleteUserUseCase;
  private final GetUserByIdUseCase getUserByIdUseCase;
  private final GetAllUsersUseCase getAllUsersUseCase;
  private final LoginUseCase loginUseCase;

  public List<UserResponse> listAllUsers() {
    final var users = getAllUsersUseCase.execute();
    return UserDesktopMapper.toResponseList(users);
  }

  public UserResponse findUserById(final String id) {
    final var query = UserDesktopMapper.toGetByIdQuery(id);
    final var user = getUserByIdUseCase.execute(query);
    return UserDesktopMapper.toResponse(user);
  }

  public UserResponse createUser(final CreateUserRequest request) {
    final var command = UserDesktopMapper.toCreateCommand(request);
    final var user = createUserUseCase.execute(command);
    return UserDesktopMapper.toResponse(user);
  }

  public UserResponse updateUser(final UpdateUserRequest request) {
    final var command = UserDesktopMapper.toUpdateCommand(request);
    final var user = updateUserUseCase.execute(command);
    return UserDesktopMapper.toResponse(user);
  }

  public void deleteUser(final String id) {
    final var command = UserDesktopMapper.toDeleteCommand(id);
    deleteUserUseCase.execute(command);
  }

  public UserResponse login(final LoginRequest request) {
    final var command = UserDesktopMapper.toLoginCommand(request);
    final var user = loginUseCase.execute(command);
    return UserDesktopMapper.toResponse(user);
  }
}
