package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CrearSubastaUseCase;
import com.jcaa.usersmanagement.application.port.in.ListarSubastasUseCase;
import com.jcaa.usersmanagement.application.port.in.EliminarSubastaUseCase;
import com.jcaa.usersmanagement.application.port.out.SubastaRepositoryPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateSubastaCommand;
import com.jcaa.usersmanagement.domain.model.Subasta;
import com.jcaa.usersmanagement.domain.exception.SubastaNoEncontradaException;

import java.util.List;
import java.util.Optional;

public class SubastaService implements CrearSubastaUseCase, ListarSubastasUseCase, EliminarSubastaUseCase {
    private final SubastaRepositoryPort repositoryPort;

    public SubastaService(SubastaRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public void crear(CreateSubastaCommand command) {
        Subasta subasta = new Subasta();
        subasta.setIdArticulo(command.getIdArticulo());
        subasta.setPrecioInicial(command.getPrecioInicial());
        subasta.setPrecioActual(command.getPrecioInicial());
        subasta.setFechaInicio(java.time.LocalDateTime.now());
        subasta.setFechaLimite(command.getFechaLimite());
        subasta.setEstado("ACTIVA");
        repositoryPort.guardar(subasta);
    }

    @Override
    public List<Subasta> listarTodas() {
        return repositoryPort.buscarTodas();
    }

    @Override
    public void eliminar(Integer id) {
        Optional<Subasta> subastaOpt = repositoryPort.buscarPorId(id);
        if (!subastaOpt.isPresent()) {
            throw new SubastaNoEncontradaException("Subasta no encontrada.");
        }
        repositoryPort.eliminar(id);
    }
}