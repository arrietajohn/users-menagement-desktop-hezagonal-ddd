package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.TareaModel;

public interface SaveTareaPort {
  TareaModel save(TareaModel tarea);
}
