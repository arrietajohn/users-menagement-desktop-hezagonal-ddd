package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateEspecieUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteEspecieUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllEspeciesUseCase;
import com.jcaa.usersmanagement.application.port.in.RegistrarExamenMedicoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetEspecieByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateEspecieUseCase;
import com.jcaa.usersmanagement.application.service.dto.command.CreateEspecieCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteEspecieCommand;
import com.jcaa.usersmanagement.application.service.dto.command.RegistrarExamenMedicoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateEspecieCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetEspecieByIdQuery;
import com.jcaa.usersmanagement.domain.model.Especie;
import com.jcaa.usersmanagement.domain.model.NombreCientifico;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EspecieResponseDto;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.EspecieDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class EspecieController {

    private final CreateEspecieUseCase createEspecieUseCase;
    private final UpdateEspecieUseCase updateEspecieUseCase;
    private final DeleteEspecieUseCase deleteEspecieUseCase;
    private final GetEspecieByIdUseCase getEspecieByIdUseCase;
    private final GetAllEspeciesUseCase getAllEspeciesUseCase;
    private final RegistrarExamenMedicoUseCase registrarExamenUseCase;

    public EspecieResponseDto create(
            String nombreCientifico, String nombreEspañol, String descripcion) {
        CreateEspecieCommand command = new CreateEspecieCommand(
            NombreCientifico.of(nombreCientifico), nombreEspañol, descripcion);
        Especie especie = createEspecieUseCase.execute(command);
        return EspecieDesktopMapper.toResponse(especie);
    }

    public EspecieResponseDto getById(String id) {
        Especie especie = getEspecieByIdUseCase.execute(new GetEspecieByIdQuery(id));
        return EspecieDesktopMapper.toResponse(especie);
    }

    public List<EspecieResponseDto> getAll() {
        return getAllEspeciesUseCase.execute()
            .stream()
            .map(EspecieDesktopMapper::toResponse)
            .toList();
    }

    public EspecieResponseDto update(String id, String nombreEspañol, String descripcion) {
        UpdateEspecieCommand command = new UpdateEspecieCommand(id, nombreEspañol, descripcion);
        Especie especie = updateEspecieUseCase.execute(command);
        return EspecieDesktopMapper.toResponse(especie);
    }

    public void delete(String id) {
        deleteEspecieUseCase.execute(new DeleteEspecieCommand(id));
    }
    public void registrarExamenMedico(
            String idEspecie, 
            Integer idCuidador, 
            String diagnostico, 
            String tratamiento, 
            String observaciones) {
        
        RegistrarExamenMedicoCommand command = new RegistrarExamenMedicoCommand(
            idEspecie, idCuidador, diagnostico, tratamiento, observaciones);
        
        registrarExamenUseCase.execute(command);
    }
}

