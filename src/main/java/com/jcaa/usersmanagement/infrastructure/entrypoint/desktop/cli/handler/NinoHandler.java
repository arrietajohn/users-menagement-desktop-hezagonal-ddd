package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.application.port.in.*;
import com.jcaa.usersmanagement.application.service.nino.command.CreateNinoCommand;
import com.jcaa.usersmanagement.application.service.nino.dto.NinoResponse;
import com.jcaa.usersmanagement.domain.model.nino.vo.Matricula;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class NinoHandler {

    private final CreateNinoUseCase createNinoUseCase;
    private final ListNinosUseCase listNinosUseCase;
    private final GetNinoByIdUseCase getNinoByIdUseCase;
    private final DeleteNinoUseCase deleteNinoUseCase;
    private final UpdateNinoUseCase updateNinoUseCase;

    public NinoResponse createNino(String matriculaStr, String nombreCompleto,
                                   LocalDate fechaNacimiento, LocalDate fechaIngreso) {
        Matricula matricula = new Matricula(matriculaStr);
        CreateNinoCommand command = new CreateNinoCommand(matricula, nombreCompleto, fechaNacimiento, fechaIngreso);
        return createNinoUseCase.execute(command);
    }

    public List<NinoResponse> listNinos() {
        return listNinosUseCase.execute();
    }

    public NinoResponse getNinoById(Long id) {
        return getNinoByIdUseCase.execute(id);
    }

    public void deleteNino(Long id) {
        deleteNinoUseCase.execute(id);
    }

    public NinoResponse updateNino(Long id, String nombreCompleto, LocalDate fechaNacimiento) {
        return updateNinoUseCase.execute(id, nombreCompleto, fechaNacimiento);
    }
}