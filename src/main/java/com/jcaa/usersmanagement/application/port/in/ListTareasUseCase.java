package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.TareaModel;

import java.util.List;

public interface ListTareasUseCase {
  List<TareaModel> execute();
}
