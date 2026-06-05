package co.edu.udc.desechos_fabrica.location.application.port.in;

import co.edu.udc.desechos_fabrica.location.application.service.dto.command.DeactivateLocationCommand;
import co.edu.udc.desechos_fabrica.location.domain.model.LocationModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeactivateLocationUseCase {
    LocationModel execute(@NotNull @Valid DeactivateLocationCommand command);
}
