package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.ProyectoModel;
import com.jcaa.usersmanagement.domain.valueobject.ProyectoId;

import java.util.Optional;

public interface GetProyectoByIdPort {
  Optional<ProyectoModel> getById(ProyectoId id);
}
