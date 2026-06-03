package co.edu.udc.desechos_fabrica.location.application.port.in;

import co.edu.udc.desechos_fabrica.location.application.service.dto.command.ActivateLocationCommand;
import co.edu.udc.desechos_fabrica.location.domain.model.LocationModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ActivateLocationUseCase {
    LocationModel execute(@NotNull @Valid ActivateLocationCommand command);
}
