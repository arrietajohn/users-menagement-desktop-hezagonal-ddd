package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.AerolineaModel;
import java.util.List;

public interface ListAerolineasUseCase {

    List<AerolineaModel> execute();
}