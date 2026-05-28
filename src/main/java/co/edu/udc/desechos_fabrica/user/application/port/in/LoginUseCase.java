package co.edu.udc.desechos_fabrica.user.application.port.in;

import co.edu.udc.desechos_fabrica.user.application.service.dto.command.LoginCommand;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface LoginUseCase {
  UserModel execute(@NotNull @Valid LoginCommand command);
}
