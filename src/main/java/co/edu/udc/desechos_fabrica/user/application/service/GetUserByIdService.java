package co.edu.udc.desechos_fabrica.user.application.service;

import co.edu.udc.desechos_fabrica.user.application.port.in.GetUserByIdUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.out.GetUserByIdPort;
import co.edu.udc.desechos_fabrica.user.application.service.dto.query.GetUserByIdQuery;
import co.edu.udc.desechos_fabrica.user.application.service.mapper.UserApplicationMapper;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class GetUserByIdService implements GetUserByIdUseCase {

  private final GetUserByIdPort getUserByIdPort;
  private final Validator validator;

  @Override
  public UserModel execute(final GetUserByIdQuery query) {
    validateQuery(query);

    final UserId userId = UserApplicationMapper.fromGetUserByIdQueryToUserId(query);
    return getUserByIdPort
        .getById(userId)
        .orElseThrow(() -> UserNotFoundException.becauseIdWasNotFound(userId.value()));
  }

  private void validateQuery(final GetUserByIdQuery query) {
    final Set<ConstraintViolation<GetUserByIdQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
