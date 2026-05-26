package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.nino.dto.NinoResponse;

public interface GetNinoByIdUseCase {

    NinoResponse execute(Long id);

}