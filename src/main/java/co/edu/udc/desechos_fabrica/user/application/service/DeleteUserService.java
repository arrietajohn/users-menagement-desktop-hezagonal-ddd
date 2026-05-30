package co.edu.udc.desechos_fabrica.user.application.service;

import co.edu.udc.desechos_fabrica.user.application.port.in.DeleteUserUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.out.DeleteUserPort;
import co.edu.udc.desechos_fabrica.user.application.port.out.GetUserByEmailPort;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.DeleteUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.mapper.UserApplicationMapper;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
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

  @Override
  public void execute(final DeleteUserCommand command) {
    validateCommand(command);

    final UserEmail userEmail = UserApplicationMapper.fromDeleteCommandToUserEmail(command);
    ensureUserExists(userEmail);
    deleteUserPort.delete(userEmail);
  }

  private void validateCommand(final DeleteUserCommand command) {
    final Set<ConstraintViolation<DeleteUserCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureUserExists(final UserEmail userEmail) {
    getUserByEmailPort
        .getByEmail(userEmail)
        .orElseThrow(() -> UserNotFoundException.becauseEmailWasNotFound(userEmail.value()));
  }
}
