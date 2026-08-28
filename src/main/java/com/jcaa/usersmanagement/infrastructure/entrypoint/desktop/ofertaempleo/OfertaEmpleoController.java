package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.ofertaempleo;

import com.jcaa.usersmanagement.application.port.in.ofertaempleo.OfertaEmpleoUseCase;
import com.jcaa.usersmanagement.application.service.dto.command.CreateOfertaEmpleoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateOfertaEmpleoCommand;
import com.jcaa.usersmanagement.domain.model.OfertaEmpleoModel;

import java.util.List;

public class OfertaEmpleoController {

    private final OfertaEmpleoUseCase useCase;

    public OfertaEmpleoController(OfertaEmpleoUseCase useCase) {
        this.useCase = useCase;
    }

    public void create(CreateOfertaEmpleoCommand command) {
        useCase.create(command);
    }

    public OfertaEmpleoModel getById(String id) {
        return useCase.getById(id);
    }

    public List<OfertaEmpleoModel> getAll() {
        return useCase.getAll();
    }

    public void update(UpdateOfertaEmpleoCommand command) {
        useCase.update(command);
    }

    public void delete(String id) {
        useCase.delete(id);
    }
}