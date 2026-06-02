package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.TareaModel;
import com.jcaa.usersmanagement.domain.valueobject.TareaId;

import java.util.Optional;

public interface GetTareaByIdPort {
  Optional<TareaModel> getById(TareaId id);
}
