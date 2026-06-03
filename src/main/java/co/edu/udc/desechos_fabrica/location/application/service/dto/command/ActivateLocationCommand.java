package co.edu.udc.desechos_fabrica.location.application.service.dto.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ActivateLocationCommand {

    @NotNull(message = "location id must not be null")
        @Positive(message = "location id must not be greater than zero")
        Long id;
    @NotNull(message = "enterprise id must not be null")
        @Positive(message = "enterprise id must not be greater than zero")
        Long enterpriseId;
}
