package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.nino.dto.NinoResponse;

public interface UpdateNinoUseCase {

    NinoResponse execute(Long id, String nombreCompleto, String fechaNacimiento);

}