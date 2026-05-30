package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetEspecieByIdQuery;
import com.jcaa.usersmanagement.domain.model.Especie;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetEspecieByIdUseCase {
    Especie execute(@NotNull @Valid GetEspecieByIdQuery query);
}