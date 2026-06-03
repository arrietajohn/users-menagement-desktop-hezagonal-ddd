package co.edu.udc.desechos_fabrica.location.application.service.dto.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public class UpdateLocationCommand {

    @NotBlank(message = "location id must be not blank")
        String id;
    @NotBlank(message = "location name must be not blank")
        @Size(min = 3, message = "location name must have at least 3 characters")
        String newName;
    @NotBlank(message = "enterprise id must be not blank")
        String enterpriseId;
    @NotBlank(message = "location country must be not blank")
        @Size(min = 3, message = "location country must have at least 3 characters")
        String newCountry;
    @NotBlank(message = "location state must be not blank")
        @Size(min = 3, message = "location state must have at least 3 characters")
        String newState;
    @NotBlank(message = "location city must be not blank")
        @Size(min = 3, message = "location city must have at least 3 characters")
        String newCity;
    @NotNull(message = "location coordinate must not be null")
    CoordinateCommand newCoordinate;

    public record CoordinateCommand(
        @NotNull(message = "latitude must not be null")
        Double latitude,
        @NotNull(message = "longitude must not be null")
        Double longitude) {}

}
