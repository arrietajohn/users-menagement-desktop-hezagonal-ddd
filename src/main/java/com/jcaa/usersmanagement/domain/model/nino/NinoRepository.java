package com.jcaa.usersmanagement.domain.model.nino;

import com.jcaa.usersmanagement.domain.model.nino.vo.Matricula;

import java.util.List;
import java.util.Optional;

public interface NinoRepository {

    Nino save(Nino nino);

    Optional<Nino> findById(Long id);

    Optional<Nino> findByMatricula(Matricula matricula);

    List<Nino> findAll();

    List<Nino> findActivos();

    boolean existsByMatricula(Matricula matricula);

    void delete(Long id);
}
