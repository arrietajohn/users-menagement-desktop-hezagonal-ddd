package com.jcaa.usersmanagement.application.trabajogrado;

import com.jcaa.usersmanagement.domain.exception.trabajogrado.TrabajoGradoAlreadyExistsException;
import com.jcaa.usersmanagement.domain.exception.trabajogrado.TrabajoGradoNotFoundException;
import com.jcaa.usersmanagement.domain.model.trabajogrado.TrabajoGrado;
import java.time.LocalDate;
import java.util.List;

public class TrabajoGradoService {

    private final TrabajoGradoRepository repository;

    public TrabajoGradoService(TrabajoGradoRepository repository) {
        this.repository = repository;
    }

    public void crear(Integer numeroOrden, String tema, LocalDate fechaInicio, String alumnoMatricula) {
        if (repository.existsByNumeroOrden(numeroOrden))
            throw new TrabajoGradoAlreadyExistsException(numeroOrden);
        TrabajoGrado tg = TrabajoGrado.create(numeroOrden, tema, fechaInicio, alumnoMatricula);
        repository.save(tg);
    }

    public TrabajoGrado buscarPorNumeroOrden(Integer numeroOrden) {
        return repository.findByNumeroOrden(numeroOrden)
                .orElseThrow(() -> new TrabajoGradoNotFoundException(numeroOrden));
    }

    public List<TrabajoGrado> listarTodos() {
        return repository.findAll();
    }

    public void actualizar(Integer numeroOrden, String nuevoTema, LocalDate nuevaFecha, String nuevaMatricula) {
        if (!repository.existsByNumeroOrden(numeroOrden))
            throw new TrabajoGradoNotFoundException(numeroOrden);
        TrabajoGrado tg = TrabajoGrado.create(numeroOrden, nuevoTema, nuevaFecha, nuevaMatricula);
        repository.update(tg);
    }

    public void eliminar(Integer numeroOrden) {
        if (!repository.existsByNumeroOrden(numeroOrden))
            throw new TrabajoGradoNotFoundException(numeroOrden);
        repository.delete(numeroOrden);
    }
}