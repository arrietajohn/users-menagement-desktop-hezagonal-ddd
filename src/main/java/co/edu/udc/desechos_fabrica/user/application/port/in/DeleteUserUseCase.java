package co.edu.udc.desechos_fabrica.user.application.port.in;

import co.edu.udc.desechos_fabrica.user.application.service.dto.command.DeleteUserCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteUserUseCase {
  void execute(@NotNull @Valid DeleteUserCommand command);
}
