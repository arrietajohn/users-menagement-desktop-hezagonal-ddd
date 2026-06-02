package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.FindTareaByIdQuery;
import com.jcaa.usersmanagement.domain.model.TareaModel;

public interface FindTareaByIdUseCase {
  TareaModel execute(FindTareaByIdQuery query);
}
