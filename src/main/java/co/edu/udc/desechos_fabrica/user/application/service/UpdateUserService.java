package co.edu.udc.desechos_fabrica.user.application.service;

import co.edu.udc.desechos_fabrica.user.application.port.in.UpdateUserUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.out.GetUserByEmailPort;
import co.edu.udc.desechos_fabrica.user.application.port.out.UpdateUserPort;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.UpdateUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.mapper.UserApplicationMapper;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserAlreadyExistsException;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
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

  @Override
  public UserModel execute(final UpdateUserCommand command) {
    validateCommand(command);

    final UserEmail currentEmail = new UserEmail(command.currentEmail());
    final UserModel current = findExistingUserOrFail(currentEmail);
    final UserEmail newEmail = new UserEmail(command.email());

    ensureEmailIsNotTakenByAnotherUser(newEmail, currentEmail);

    final UserModel userToUpdate =
        UserApplicationMapper.fromUpdateCommandToModel(command, current);
    final UserModel updatedUser = updateUserPort.update(currentEmail, userToUpdate);

    emailNotificationService.notifyUserUpdated(updatedUser);

    return updatedUser;
  }

  private void validateCommand(final UpdateUserCommand command) {
    final Set<ConstraintViolation<UpdateUserCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private UserModel findExistingUserOrFail(final UserEmail currentEmail) {
    return getUserByEmailPort
        .getByEmail(currentEmail)
        .orElseThrow(() -> UserNotFoundException.becauseEmailWasNotFound(currentEmail.value()));
  }

  private void ensureEmailIsNotTakenByAnotherUser(final UserEmail newEmail, final UserEmail currentEmail) {
    if (newEmail.equals(currentEmail)) {
      return;
    }
    getUserByEmailPort
        .getByEmail(newEmail)
        .ifPresent(
            found -> {
              if (!found.getEmail().equals(currentEmail)) {
                throw UserAlreadyExistsException.becauseEmailAlreadyExists(newEmail.value());
              }
            });
  }
}
