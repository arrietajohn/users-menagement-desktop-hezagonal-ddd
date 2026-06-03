package co.edu.udc.desechos_fabrica.location.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ActivateLocationCommand {

    @NotBlank(message = "location id must be not blank")
        @Positive(message = "location id must be bot a negative number")
        Long id;
    @NotBlank(message = "enterprise id must not be blank")
        @Positive(message = "enterprise id must be bot a negative number")
        String enterpriseId;
}
