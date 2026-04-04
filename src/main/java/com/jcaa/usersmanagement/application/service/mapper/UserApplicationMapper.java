package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateUserCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteUserCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateUserCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetUserByIdQuery;
import com.jcaa.usersmanagement.domain.enums.UserRole;
import com.jcaa.usersmanagement.domain.enums.UserStatus;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserEmail;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import com.jcaa.usersmanagement.domain.valueobject.UserName;
import com.jcaa.usersmanagement.domain.valueobject.UserPassword;
import java.util.Objects;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserApplicationMapper {

  public UserModel fromCreateCommandToModel(final CreateUserCommand command) {
    return UserModel.create(
        new UserId(command.id()),
        new UserName(command.name()),
        new UserEmail(command.email()),
        UserPassword.fromPlainText(command.password()),
        UserRole.fromString(command.role()));
  }

  public UserModel fromUpdateCommandToModel(
      final UpdateUserCommand command, final UserPassword currentPassword) {

    final UserPassword passwordToUse = resolvePassword(command.password(), currentPassword);

    return new UserModel(
        new UserId(command.id()),
        new UserName(command.name()),
        new UserEmail(command.email()),
        passwordToUse,
        UserRole.fromString(command.role()),
        UserStatus.fromString(command.status()));
  }

  public UserId fromGetUserByIdQueryToUserId(final GetUserByIdQuery query) {
    return new UserId(query.id());
  }

  public UserId fromDeleteCommandToUserId(final DeleteUserCommand command) {
    return new UserId(command.id());
  }

  private UserPassword resolvePassword(
      final String newPlainPassword, final UserPassword currentPassword) {
    if (Objects.isNull(newPlainPassword) || newPlainPassword.isBlank()) {
      return currentPassword;
    }
    return UserPassword.fromPlainText(newPlainPassword);
  }
}
