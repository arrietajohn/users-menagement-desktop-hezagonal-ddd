package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.TareaModel;

public interface UpdateTareaPort {
  TareaModel update(TareaModel tarea);
}
