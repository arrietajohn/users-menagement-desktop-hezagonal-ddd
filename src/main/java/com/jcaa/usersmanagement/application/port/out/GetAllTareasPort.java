package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.TareaModel;

import java.util.List;

public interface GetAllTareasPort {
  List<TareaModel> getAll();
}
