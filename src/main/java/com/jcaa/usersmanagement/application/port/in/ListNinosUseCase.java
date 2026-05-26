package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.nino.dto.NinoResponse;

import java.util.List;

public interface ListNinosUseCase {

    List<NinoResponse> execute();

}