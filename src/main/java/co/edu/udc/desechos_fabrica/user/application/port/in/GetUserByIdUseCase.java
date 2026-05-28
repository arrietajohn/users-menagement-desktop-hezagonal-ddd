package co.edu.udc.desechos_fabrica.user.application.port.in;

import co.edu.udc.desechos_fabrica.user.application.service.dto.query.GetUserByIdQuery;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetUserByIdUseCase {
  UserModel execute(@NotNull @Valid GetUserByIdQuery query);
}
