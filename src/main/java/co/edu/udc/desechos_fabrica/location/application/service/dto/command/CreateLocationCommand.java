package co.edu.udc.desechos_fabrica.location.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class CreateLocationCommand {

    @NotBlank(message = "location name must not be blank")
        @Size(min = 3, message = "location name must have at least 3 characters")
        String name;
    @NotNull(message = "enterprise id must not be null")
        @Positive(message = "enterprise id must be greater than zero")
        Long enterpriseId;
    @NotBlank(message = "location country must not be blank")
        @Size(min = 3, message = "location country must have at least 3 characters")
        String country;
    @NotBlank(message = "location state must not be blank")
        @Size(min = 3, message = "location state must have at least 3 characters")
        String state;
    @NotBlank(message = "location city must not be blank")
        @Size(min = 3, message = "location city must have at least 3 characters")
        String city;
    @NotNull(message = "location coordinate must not be null")
    CoordinateCommand coordinate;

    public record CoordinateCommand(
        @NotNull(message = "latitude must not be null")
        Double latitude,
        @NotNull(message = "longitude must not be null")
        Double longitude) {}

}
