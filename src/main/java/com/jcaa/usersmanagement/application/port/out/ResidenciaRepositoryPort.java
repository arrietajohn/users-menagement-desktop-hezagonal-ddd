// application/port/out/ResidenciaRepositoryPort.java
package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.ResidenciaModel;
import com.jcaa.usersmanagement.domain.valueobject.ResidenciaId;

import java.util.List;
import java.util.Optional;

public interface ResidenciaRepositoryPort {

    ResidenciaModel save(ResidenciaModel residencia);

    ResidenciaModel update(ResidenciaModel residencia);

    void delete(ResidenciaId id);

    Optional<ResidenciaModel> findById(ResidenciaId id);

    List<ResidenciaModel> findAll();
}