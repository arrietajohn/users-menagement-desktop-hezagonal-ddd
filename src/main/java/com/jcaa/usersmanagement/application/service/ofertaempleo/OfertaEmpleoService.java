package com.jcaa.usersmanagement.application.service.ofertaempleo;

import com.jcaa.usersmanagement.application.port.in.ofertaempleo.OfertaEmpleoUseCase;
import com.jcaa.usersmanagement.application.port.out.ofertaempleo.OfertaEmpleoRepositoryPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateOfertaEmpleoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateOfertaEmpleoCommand;
import com.jcaa.usersmanagement.application.service.mapper.OfertaEmpleoApplicationMapper;
import com.jcaa.usersmanagement.domain.model.OfertaEmpleoModel;

import java.util.List;

public class OfertaEmpleoService implements OfertaEmpleoUseCase {

    private final OfertaEmpleoRepositoryPort repository;

    public OfertaEmpleoService(OfertaEmpleoRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void create(CreateOfertaEmpleoCommand command) {
        OfertaEmpleoModel model = OfertaEmpleoApplicationMapper.toModel(command);
        repository.save(model);
    }

    @Override
    public OfertaEmpleoModel getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));
    }

    @Override
    public List<OfertaEmpleoModel> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(UpdateOfertaEmpleoCommand command) {
        OfertaEmpleoModel model = OfertaEmpleoApplicationMapper.toModel(
                new CreateOfertaEmpleoCommand(
                        command.id(),
                        command.titulo(),
                        command.descripcion(),
                        command.empresa(),
                        command.ubicacion(),
                        command.salario(),
                        command.estado()
                )
        );
        repository.update(model);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }
}