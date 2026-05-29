package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetResidenciaByIdQuery;
import com.jcaa.usersmanagement.domain.model.ResidenciaModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetResidenciaByIdUseCase {
  ResidenciaModel execute(@NotNull @Valid GetResidenciaByIdQuery query);
}
