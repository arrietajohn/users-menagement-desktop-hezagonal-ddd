package co.edu.udc.desechos_fabrica.user.application.service;

import co.edu.udc.desechos_fabrica.user.application.port.in.DeleteUserUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.out.DeleteUserPort;
import co.edu.udc.desechos_fabrica.user.application.port.out.GetUserByEmailPort;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.DeleteUserCommand;
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
public final class DeleteUserService implements DeleteUserUseCase {

  private final DeleteUserPort deleteUserPort;
  private final GetUserByEmailPort getUserByEmailPort;
  private final Validator validator;
  private final UserRoleManager userRoleManager;

  @Override
  public void execute(final DeleteUserCommand command) {
    validateCommand(command);

    final UserModel actor = findExistingUserOrFail(new UserEmail(command.actorEmail()));
    final UserModel targetUser = findExistingUserOrFail(new UserEmail(command.email()));

    userRoleManager.checkDeletePermissions(actor, targetUser);

    deleteUserPort.delete(targetUser.getEmail());
  }

  private void validateCommand(final DeleteUserCommand command) {
    final Set<ConstraintViolation<DeleteUserCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private UserModel findExistingUserOrFail(final UserEmail email) {
    return getUserByEmailPort
        .getByEmail(email)
        .orElseThrow(() -> UserNotFoundException.becauseEmailWasNotFound(email.value()));
  }
}
