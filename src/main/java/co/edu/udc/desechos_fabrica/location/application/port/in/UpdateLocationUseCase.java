package co.edu.udc.desechos_fabrica.location.application.port.in;

import co.edu.udc.desechos_fabrica.location.application.service.dto.command.UpdateLocationCommand;
import co.edu.udc.desechos_fabrica.location.domain.model.LocationModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateLocationUseCase {
    LocationModel execute(@NotNull @Valid UpdateLocationCommand command);
}
