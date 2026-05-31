package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateRepresentanteUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteRepresentanteUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllRepresentantesUseCase;
import com.jcaa.usersmanagement.application.port.in.GetRepresentanteByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateRepresentanteUseCase;
import com.jcaa.usersmanagement.application.service.dto.command.CreateRepresentanteCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateRepresentanteCommand;
import com.jcaa.usersmanagement.domain.model.RepresentanteModel;

import java.util.List;

public class RepresentanteController {

    private final CreateRepresentanteUseCase createRepresentanteUseCase;
    private final GetAllRepresentantesUseCase getAllRepresentantesUseCase;
    private final GetRepresentanteByIdUseCase getRepresentanteByIdUseCase;
    private final UpdateRepresentanteUseCase updateRepresentanteUseCase;
    private final DeleteRepresentanteUseCase deleteRepresentanteUseCase;

    public RepresentanteController(
            CreateRepresentanteUseCase createRepresentanteUseCase,
            GetAllRepresentantesUseCase getAllRepresentantesUseCase,
            GetRepresentanteByIdUseCase getRepresentanteByIdUseCase,
            UpdateRepresentanteUseCase updateRepresentanteUseCase,
            DeleteRepresentanteUseCase deleteRepresentanteUseCase) {
        this.createRepresentanteUseCase = createRepresentanteUseCase;
        this.getAllRepresentantesUseCase = getAllRepresentantesUseCase;
        this.getRepresentanteByIdUseCase = getRepresentanteByIdUseCase;
        this.updateRepresentanteUseCase = updateRepresentanteUseCase;
        this.deleteRepresentanteUseCase = deleteRepresentanteUseCase;
    }

    public RepresentanteModel create(CreateRepresentanteCommand command) {
        return createRepresentanteUseCase.create(command);
    }

    public List<RepresentanteModel> getAll() {
        return getAllRepresentantesUseCase.getAll();
    }

    public RepresentanteModel getById(Integer id) {
        return getRepresentanteByIdUseCase.getById(id);
    }

    public RepresentanteModel update(UpdateRepresentanteCommand command) {
        return updateRepresentanteUseCase.update(command);
    }

    public void delete(Integer id) {
        deleteRepresentanteUseCase.delete(id);
    }
}