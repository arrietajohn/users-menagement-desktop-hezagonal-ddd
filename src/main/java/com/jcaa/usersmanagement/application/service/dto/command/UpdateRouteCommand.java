package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateRouteCommand(

        @NotBlank(message = "id must not be blank")
        String id,

        @NotBlank(message = "name must not be blank")
        String name,

        @NotBlank(message = "neighborhood must not be blank")
        String neighborhood,

        @NotBlank(message = "school must not be blank")
        String school,

        @NotBlank(message = "journey must not be blank")
        String journey,

        @NotNull(message = "maxCapacity must not be null")
        Integer maxCapacity,

        @NotNull(message = "availableSeats must not be null")
        Integer availableSeats,

        @NotNull(message = "active must not be null")
        Boolean active
) {
}