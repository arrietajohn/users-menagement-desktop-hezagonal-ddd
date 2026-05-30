package co.edu.udc.desechos_fabrica.user.application.service;

import co.edu.udc.desechos_fabrica.user.application.port.in.GetUserByEmailUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.out.GetUserByEmailPort;
import co.edu.udc.desechos_fabrica.user.application.service.dto.query.GetUserByEmailQuery;
import co.edu.udc.desechos_fabrica.user.application.service.mapper.UserApplicationMapper;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class GetUserByEmailService implements GetUserByEmailUseCase {

  private final GetUserByEmailPort getUserByEmailPort;
  private final Validator validator;

  @Override
  public UserModel execute(final GetUserByEmailQuery query) {
    validateQuery(query);

    final UserEmail userEmail = UserApplicationMapper.fromGetUserByEmailQueryToUserEmail(query);
    return getUserByEmailPort
        .getByEmail(userEmail)
        .orElseThrow(() -> UserNotFoundException.becauseEmailWasNotFound(userEmail.value()));
  }

  private void validateQuery(final GetUserByEmailQuery query) {
    final Set<ConstraintViolation<GetUserByEmailQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
