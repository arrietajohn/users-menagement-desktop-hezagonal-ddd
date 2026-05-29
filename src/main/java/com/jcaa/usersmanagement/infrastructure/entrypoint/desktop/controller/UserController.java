package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.candidate.CreateCandidateUseCase;
import com.jcaa.usersmanagement.application.port.in.CreateUserUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteUserUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllUsersUseCase;
import com.jcaa.usersmanagement.application.port.in.GetUserByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.LoginUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateUserUseCase;
import com.jcaa.usersmanagement.application.voter.CreateVoterUseCase;
import com.jcaa.usersmanagement.application.voter.DeleteVoterUseCase;
import com.jcaa.usersmanagement.application.voter.FindVoterByDniUseCase;
import com.jcaa.usersmanagement.application.voter.ListVotersUseCase;
import com.jcaa.usersmanagement.application.voter.UpdateVoterUseCase;
import com.jcaa.usersmanagement.domain.voter.Voter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.LoginRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.UserDesktopMapper;
import java.util.List;

public final class UserController {

  private final CreateUserUseCase createUserUseCase;
  private final CreateCandidateUseCase createCandidateUseCase;
  private final UpdateUserUseCase updateUserUseCase;
  private final DeleteUserUseCase deleteUserUseCase;
  private final GetUserByIdUseCase getUserByIdUseCase;
  private final GetAllUsersUseCase getAllUsersUseCase;
  private final LoginUseCase loginUseCase;

  private final CreateVoterUseCase createVoterUseCase;
  private final FindVoterByDniUseCase findVoterByDniUseCase;
  private final ListVotersUseCase listVotersUseCase;
  private final UpdateVoterUseCase updateVoterUseCase;
  private final DeleteVoterUseCase deleteVoterUseCase;

  public UserController(
          final CreateUserUseCase createUserUseCase,
          final CreateCandidateUseCase createCandidateUseCase,
          final CreateVoterUseCase createVoterUseCase,
          final FindVoterByDniUseCase findVoterByDniUseCase,
          final ListVotersUseCase listVotersUseCase,
          final UpdateVoterUseCase updateVoterUseCase,
          final DeleteVoterUseCase deleteVoterUseCase,
          final UpdateUserUseCase updateUserUseCase,
          final DeleteUserUseCase deleteUserUseCase,
          final GetUserByIdUseCase getUserByIdUseCase,
          final GetAllUsersUseCase getAllUsersUseCase,
          final LoginUseCase loginUseCase) {
    this.createUserUseCase = createUserUseCase;
    this.createCandidateUseCase = createCandidateUseCase;
    this.createVoterUseCase = createVoterUseCase;
    this.findVoterByDniUseCase = findVoterByDniUseCase;
    this.listVotersUseCase = listVotersUseCase;
    this.updateVoterUseCase = updateVoterUseCase;
    this.deleteVoterUseCase = deleteVoterUseCase;
    this.updateUserUseCase = updateUserUseCase;
    this.deleteUserUseCase = deleteUserUseCase;
    this.getUserByIdUseCase = getUserByIdUseCase;
    this.getAllUsersUseCase = getAllUsersUseCase;
    this.loginUseCase = loginUseCase;
  }

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

  public void createCandidate(final String dni, final String name, final String party) {
    createCandidateUseCase.execute(dni, name, party);
  }

  public void createVoter(final String dni, final String fullName, final String email, final String commune) {
    createVoterUseCase.execute(dni, fullName, email, commune);
  }

  public Voter findVoterByDni(final String dni) {
    return findVoterByDniUseCase.execute(dni);
  }

  public List<Voter> listVoters() {
    return listVotersUseCase.execute();
  }

  public void updateVoter(final String dni, final String fullName, final String email, final String commune) {
    updateVoterUseCase.execute(dni, fullName, email, commune);
  }

  public void deleteVoter(final String dni) {
    deleteVoterUseCase.execute(dni);
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
