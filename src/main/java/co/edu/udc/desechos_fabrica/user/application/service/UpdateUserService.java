package co.edu.udc.desechos_fabrica.user.application.service;

import co.edu.udc.desechos_fabrica.user.application.port.in.UpdateUserUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.out.GetUserByEmailPort;
import co.edu.udc.desechos_fabrica.user.application.port.out.UpdateUserPort;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.UpdateUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.mapper.UserApplicationMapper;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserAlreadyExistsException;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.service.UserRoleManager;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class UpdateUserService implements UpdateUserUseCase {

  private final UpdateUserPort updateUserPort;
  private final GetUserByEmailPort getUserByEmailPort;
  private final EmailNotificationService emailNotificationService;
  private final Validator validator;
  private final UserRoleManager userRoleManager;

  @Override
  public UserModel execute(final UpdateUserCommand command) {
    validateCommand(command);

    final UserModel actor = findExistingUserOrFail(new UserEmail(command.actorEmail()));
    final UserModel targetUser = findExistingUserOrFail(new UserEmail(command.targetEmail()));
    
    userRoleManager.checkUpdatePermissions(actor, targetUser, null);

    final UserEmail newEmail = new UserEmail(command.newEmail());
    if (!newEmail.equals(targetUser.getEmail())) {
      ensureEmailIsNotTakenByAnotherUser(newEmail);
    }

    final UserModel userToUpdate =
        UserApplicationMapper.fromUpdateCommandToModel(command, targetUser);
    final UserModel updatedUser = updateUserPort.update(targetUser.getEmail(), userToUpdate);

    emailNotificationService.notifyUserUpdated(updatedUser);

    return updatedUser;
  }

  private void validateCommand(final UpdateUserCommand command) {
    final Set<ConstraintViolation<UpdateUserCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private UserModel findExistingUserOrFail(final UserEmail email) {
    return getUserByEmailPort
        .getByEmail(email)
        .orElseThrow(() -> UserNotFoundException.becauseEmailWasNotFound(email.value()));
  }

  private void ensureEmailIsNotTakenByAnotherUser(final UserEmail newEmail) {
    getUserByEmailPort
        .getByEmail(newEmail)
        .ifPresent(
            foundUser -> {
              throw UserAlreadyExistsException.becauseEmailAlreadyExists(newEmail.value());
            });
  }
}
