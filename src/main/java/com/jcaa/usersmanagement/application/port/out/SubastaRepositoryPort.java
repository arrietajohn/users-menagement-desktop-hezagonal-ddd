package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Subasta;
import java.util.List;
import java.util.Optional;

public interface SubastaRepositoryPort {
    void guardar(Subasta subasta);
    Optional<Subasta> buscarPorId(Integer id);
    List<Subasta> buscarTodas();
    void actualizar(Subasta subasta);
    void eliminar(Integer id);
}