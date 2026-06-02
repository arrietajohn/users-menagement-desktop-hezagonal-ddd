package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.TareaId;

public interface DeleteTareaPort {
  void delete(TareaId id);
}
