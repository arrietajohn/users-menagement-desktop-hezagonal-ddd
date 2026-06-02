package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.FindProyectoByIdQuery;
import com.jcaa.usersmanagement.domain.model.ProyectoModel;

public interface FindProyectoByIdUseCase {
  ProyectoModel execute(FindProyectoByIdQuery query);
}
