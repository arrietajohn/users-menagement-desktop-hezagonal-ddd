package co.edu.udc.desechos_fabrica.user.application.port.in;

import co.edu.udc.desechos_fabrica.user.application.service.dto.command.UpdateUserCommand;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateUserUseCase {
  UserModel execute(@NotNull @Valid UpdateUserCommand command);
}
