package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.nino.dto.NinoResponse;

import java.time.LocalDate;

public interface UpdateNinoUseCase {

    NinoResponse execute(Long id, String nombreCompleto, LocalDate fechaNacimiento);

}