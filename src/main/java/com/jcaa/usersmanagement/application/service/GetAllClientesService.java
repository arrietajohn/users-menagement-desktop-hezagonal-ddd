package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllClientesUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllClientesPort;
import com.jcaa.usersmanagement.domain.model.ClienteModel;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public final class GetAllClientesService implements GetAllClientesUseCase {
    private final GetAllClientesPort getAllClientesPort;

    @Override
    public List<ClienteModel> execute() {
        return getAllClientesPort.getAll();
    }
}
