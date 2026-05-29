package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.ResidenciaModel;
import java.util.List;

public interface GetAllResidenciasUseCase {
  List<ResidenciaModel> execute();
}
